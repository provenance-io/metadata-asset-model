import com.google.protobuf.gradle.*



buildscript {

    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.0")
        classpath("com.google.protobuf:protobuf-gradle-plugin:0.8.18")
    }
}


plugins {
    id("org.jetbrains.kotlin.jvm") version "1.3.72"
    id("com.google.protobuf") version "0.8.18"
    id("com.github.marcoferrer.kroto-plus") version "0.6.1"
    `java-library`
    `maven-publish`
}

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}


object Versions {
    const val Protobuf = "3.19.1"

    // Test dependencies
    const val JunitJupiter = "5.8.2"

    // Plugins
    const val Kotlin = "1.5.0"
    const val ProtobufPlugin = "0.8.18"
}
dependencies {
    implementation("io.envoyproxy.protoc-gen-validate:pgv-java-stub:0.6.2")
    implementation("io.envoyproxy.protoc-gen-validate:protoc-gen-validate:0.6.2")
    implementation("com.github.marcoferrer.krotoplus:protoc-gen-kroto-plus:0.6.1")
    implementation("com.google.protobuf:protobuf-java:${Versions.Protobuf}")
    implementation("com.google.protobuf:protobuf-java-util:${Versions.Protobuf}")
    implementation("javax.annotation:javax.annotation-api:1.3.1")

    testImplementation("org.junit.jupiter:junit-jupiter-api:${Versions.JunitJupiter}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${Versions.JunitJupiter}")
}

protobuf {

    // protoc plugin names
    val kroto = "kroto"
    val validation = "javapgv"

    protoc {
        artifact = "com.google.protobuf:protoc:${Versions.Protobuf}"
    }
    plugins {
        id(kroto) {
            artifact = "com.github.marcoferrer.krotoplus:protoc-gen-kroto-plus:0.6.1"
        }
        id(validation) {
            artifact = "io.envoyproxy.protoc-gen-validate:protoc-gen-validate:0.6.2"
        }
    }
    generateProtoTasks {
        val krotoConfig = file("kroto-config.yaml")

        all().forEach {
            it.inputs.files(krotoConfig)
            it.plugins {
                ofSourceSet("main")
                id(kroto) {
                    outputSubDir = "java"
                    option("ConfigPath=$krotoConfig")
                }
                id(validation) {
                    option("lang=java")
                }
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
    reports.html.isEnabled = true
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "tech.figure.asset"
            artifactId = "metadata-asset-model"
            version = "1.0.0-SNAPSHOT"

            from(components["java"])
        }
    }
}