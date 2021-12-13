# Provenance Blockchain Reference Use Case Data Model

This repository contains the protobuf data model reference standard for [Provenance Blockchain Metadata module](https://docs.provenance.io/modules/metadata-module) 
and [P8e Contract Execution Environment](https://docs.provenance.io/p8e/overview). 

The model includes definitions for generic NFTs (Assets) as well as 
business domain objects, such as loans and loan servicing data, and Java language-bindings.


### Maven

```xml
<dependency>
  <groupId>tech.figure.asset</groupId>
  <artifactId>metadata-asset-model</artifactId>
  <version>${version}</version>
</dependency>
```

### Gradle

```groovy
implementation 'tech.figure.asset:metadata-asset-model:${version}'
```


### [Model Documentation](docs/README.md)

### Example Usage:

This example shows how an NFT image file might be loaded into the P8e Contract Execution Environment, with the object
hash store on the blockchain through the metadata module. Source code for this example is in
[AssetTest](src/test/kotlin/tech.figure.proto/AssetTest.kt).

```kotlin
val file = File("src/test/data/logo.png")
val fileBytes = file.readBytes()

val asset1 = AssetOuterClassBuilders.Asset {
    id = UUID.randomUUID().toProtoUUID()
    type = "FILE"
    description = file.name
    putKv(FileNFT.KEY_FILENAME, file.name.toProtoAny())
    putKv(FileNFT.KEY_SIZE, fileBytes.size.toLong().toProtoAny())
    putKv(FileNFT.KEY_BYTES, fileBytes.toProtoAny())
    putKv(FileNFT.KEY_CONTENT_TYPE, "image/png".toProtoAny())
}
```

The JSON representation of this asset would look like:

```json
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
            "value": "Cq3zEYlQTkcNChoKAAAADUlIRF... image bytes truncated ...",
        },
        "content-type": {
            "type_url": "type.googleapis.com/google.protobuf.StringValue",
            "value": "CglpbWFnZS9wbmc="
        }
    }
}
```