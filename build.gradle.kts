import com.google.protobuf.gradle.*
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URI

buildscript {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

@Suppress("DSL_SCOPE_VIOLATION") /** https://github.com/gradle/gradle/issues/22797 */
plugins {
    kotlin("jvm") version "1.8.10"
    alias(libs.plugins.protobuf.gradle)
    alias(libs.plugins.protocGen.krotoPlus)
    `java-library`
    `maven-publish`
    signing
    alias(libs.plugins.nexusPublishing)
}

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    withSourcesJar()
    withJavadocJar() /** Needed to pass Nexus validation rules */
}

tasks.withType<Javadoc>().all {
    options {
        /**
         * Necessary to suppress errors of invalid Javadocs generated from protobufs.
         * Via https://stackoverflow.com/a/73930431
         */
        (this as CoreJavadocOptions).addStringOption("Xdoclint:none", "-quiet")
    }
}

tasks.withType<KotlinCompile>().all {
    kotlinOptions {
        freeCompilerArgs += listOf(
            "-Xjsr305=strict",
        )
        jvmTarget = "17"
        apiVersion = "1.8"
        languageVersion = "1.8"
        /** Necessary to allow deprecation warnings from the Java & Kotlin generated for deprecated protobuf fields */
        allWarningsAsErrors = false
    }
}

val projectVersion = project.property("version")?.takeIf { it != "unspecified" } ?: "1.0-SNAPSHOT"

dependencies {
    listOf(
        libs.kotlin.stdLib,
        libs.protocGen.validate.base,
        libs.protocGen.validate.javaStub,
        libs.bundles.protobuf,
    ).forEach(::implementation)

    listOf(
        libs.junit.jupiter.api,
    ).forEach(::testImplementation)

    listOf(
        libs.junit.jupiter.engine,
    ).forEach(::testRuntimeOnly)
}

fun Provider<MinimalExternalModuleDependency>.toModule(): ModuleIdentifier = map { it.module }.get()

fun ProviderConvertible<String>.toVersionString(): String = asProvider().get()

protobuf {

    // protoc plugin names
    val validation = "javapgv"

    protoc {
        artifact = "${libs.protoc.toModule()}:${libs.versions.protobuf.toVersionString()}"
    }
    plugins {
        id(validation) {
            artifact = "${libs.protocGen.validate.base.toModule()}:${libs.versions.protocGenValidate.get()}"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                ofSourceSet("main")
                id(validation) {
                    option("lang=java")
                }
            }
            it.builtins {
                id("kotlin")
            }
        }
    }
}

configurations.forEach { it.exclude("org.slf4j", "slf4j-api") }

tasks.withType<Test> {
    useJUnitPlatform {
        includeEngines("junit-jupiter")
    }
    testLogging {
        events("passed", "skipped", "failed")
    }
    reports.html.required.set(true)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "io.provenance.model"
            artifactId = "metadata-asset-model"
            version = "$projectVersion"

            from(components["java"])

            pom {
                name.set("Asset Metadata Model")
                description.set("Asset/NFT reference data model for Provenance Blockchain metadata module")
                url.set("https://figure.tech")

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }

                developers {
                    developer {
                        id.set("vwagner")
                        name.set("Valerie Wagner")
                        email.set("tech@figure.com")
                    }
                }

                scm {
                    connection.set("git@github.com:provenance-io/metadata-asset-model.git")
                    developerConnection.set("git@github.com/provenance-io/metadata-asset-model.git")
                    url.set("https://github.com/provenance-io/metadata-asset-model")
                }
            }
        }
    }
    signing {
        sign(publishing.publications["maven"])
    }
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
            username.set(findProject("ossrhUsername")?.toString() ?: System.getenv("OSSRH_USERNAME"))
            password.set(findProject("ossrhPassword")?.toString() ?: System.getenv("OSSRH_PASSWORD"))
            stagingProfileId.set("3180ca260b82a7") // prevents querying for the staging profile id, performance optimization
        }
    }
}

object Repos {
    private object SonaTypeRepositoryURLs {
        const val snapshots = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
        const val releases = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
    }

    fun RepositoryHandler.sonatypeOss(projectVersion: String): MavenArtifactRepository {
        val mavenUrl =
            if (projectVersion == "1.0-SNAPSHOT") SonaTypeRepositoryURLs.snapshots
            else SonaTypeRepositoryURLs.releases

        return maven {
            name = "Sonatype"
            url = URI.create(mavenUrl)
            credentials {
                username = System.getenv("OSSRH_USERNAME")
                password = System.getenv("OSSRH_PASSWORD")
            }
        }
    }
}
