# Provenance Blockchain Reference Use Case Data Model

This repository contains a protobuf data model reference standard for the [Provenance Blockchain Metadata module](https://docs.provenance.io/modules/metadata-module)
tailored for usage in the [P8e Contract Execution Environment](https://docs.provenance.io/p8e/overview).

The model includes definitions for generic NFTs (Assets) as well as business domain objects — such as loans and loan servicing data — with language bindings for Java and Kotlin.

## Status
[![Build][build-badge]][build-workflow]
[![stability-beta][stability-badge]][stability-info]
[![License][license-badge]][license-url]
[![LOC][loc-badge]][loc-url]
### Publications
[![Latest Release][release-badge]][release-latest]
#### JAR
[![Maven Artifact][publication-badge]][publication-url]

#### Maven Usage

```xml
<dependency>
  <groupId>io.provenance.model</groupId>
  <artifactId>metadata-asset-model</artifactId>
  <version>${version}</version>
</dependency>
```

#### Gradle Usage
**Groovy**
```groovy
implementation 'io.provenance.model:metadata-asset-model:${version}'
```
**Kotlin**
```kotlin
implementation("io.provenance.model:metadata-asset-model:$metadataAssetModelVersion")
```

[build-badge]: https://img.shields.io/github/actions/workflow/status/provenance-io/metadata-asset-model/build.yml?branch=main&style=for-the-badge
[build-workflow]: https://github.com/provenance-io/metadata-asset-model/actions/workflows/build.yml
[stability-badge]: https://img.shields.io/badge/stability-beta-33bbff.svg?style=for-the-badge
[stability-info]: https://github.com/mkenney/software-guides/blob/master/STABILITY-BADGES.md#beta
[release-badge]: https://img.shields.io/github/v/tag/provenance-io/metadata-asset-model.svg?sort=semver&style=for-the-badge
[release-latest]: https://github.com/provenance-io/metadata-asset-model/releases/latest
[publication-badge]: https://maven-badges.herokuapp.com/maven-central/io.provenance.model/metadata-asset-model/badge.svg?style=for-the-badge
[publication-url]: https://maven-badges.herokuapp.com/maven-central/io.provenance.model/metadata-asset-model
[license-badge]: https://img.shields.io/github/license/provenance-io/metadata-asset-model.svg?style=for-the-badge
[license-url]: https://github.com/provenance-io/metadata-asset-model/blob/main/LICENSE
[loc-badge]: https://img.shields.io/tokei/lines/github/provenance-io/metadata-asset-model?style=for-the-badge
[loc-url]: https://github.com/provenance-io/metadata-asset-model

## Useful Links

- [Protocol Buffers Documentation](docs/README.md)
- [P8e Contract Execution Environment Source Code](https://github.com/provenance-io/p8e-scope-sdk/)
- [Associated P8e Scope Specification](https://github.com/provenance-io/loan-package-contracts/)

## Example Usage

This example shows how an NFT image file might be defined for usage in the P8e Contract Execution Environment, with the object hash stored on the blockchain through the metadata module.
The full source code can be found in [AssetTest](src/test/kotlin/tech/figure/asset/v1beta1/AssetTest.kt).

```kotlin
val file = File("src/test/resources/logo.png")
val fileBytes = file.readBytes()

val asset1 = asset {
    id = UUID.randomUUID().toProtoUUID()
    type = FileNFT.ASSET_TYPE
    description = file.name
    kv.put(FileNFT.KEY_FILENAME, file.name.toProtoAny())
    kv.put(FileNFT.KEY_SIZE, fileBytes.size.toLong().toProtoAny())
    kv.put(FileNFT.KEY_BYTES, fileBytes.toProtoAny())
    kv.put(FileNFT.KEY_CONTENT_TYPE, "image/png".toProtoAny())
}
```

The JSON representation of this asset would look something like:

```jsonc
{
    "id": "4861c0c0-d46e-4ca2-b765-c66b45a41464",
    "type": "FILE",
    "description": "file",
    "kv": {
        "filename": {
            "type_url": "type.googleapis.com/google.protobuf.StringValue",
            "value": "Cg9pbWFnZSAoMTM4KS5wbmc="
        },
        "size": {
            "type_url": "type.googleapis.com/google.protobuf.Int64Value",
            "value": "CK3zEQ=="
        },
        "bytes": {
            "type_url": "type.googleapis.com/google.protobuf.BytesValue",
            "value": "Cq3zEYlQTkcNChoKAAAADUlIRF", // Value truncated
        },
        "content-type": {
            "type_url": "type.googleapis.com/google.protobuf.StringValue",
            "value": "CglpbWFnZS9wbmc="
        }
    }
}
```
