package tech.figure.asset.v1beta1

import com.google.protobuf.*
import com.google.protobuf.Any
import org.junit.jupiter.api.Test
import tech.figure.proto.util.FileNFT
import java.io.File
import java.util.UUID as JavaUUID
import tech.figure.util.v1beta1.UUID as FigureTechUUID

class AssetTest {

    @Test
    fun roundTrip() {

        val file = File("src/test/resources/logo.png")
        val fileBytes = file.readBytes()

        val asset1 = asset {
            id = JavaUUID.randomUUID().toProtoUUID()
            type = FileNFT.ASSET_TYPE
            description = file.name
            kv.put(FileNFT.KEY_FILENAME, file.name.toProtoAny())
            kv.put(FileNFT.KEY_SIZE, fileBytes.size.toLong().toProtoAny())
            kv.put(FileNFT.KEY_BYTES, fileBytes.toProtoAny())
            kv.put(FileNFT.KEY_CONTENT_TYPE, "image/png".toProtoAny())
        }

        //println(asset1)

        val asset2 = Asset.parseFrom(asset1.toByteArray())
        val data = asset2.getKvOrThrow(FileNFT.KEY_BYTES)
        writeFile("build/output.png", data.unpack(BytesValue::class.java).value.toByteArray())
    }
}

private fun JavaUUID.toProtoUUID(): FigureTechUUID = FigureTechUUID.newBuilder().setValue(this.toString()).build()

fun ByteArray.toProtoAny(): Any = Any.pack(
    BytesValue.newBuilder().setValue(ByteString.copyFrom(this)).build()
)

fun String.toProtoAny(): Any = Any.pack(
    StringValue.newBuilder().setValue(this).build()
)

fun Long.toProtoAny(): Any = Any.pack(
    Int64Value.newBuilder().setValue(this).build()
)

fun Message.toProtoAny(): Any = Any.pack(this)

fun readFileAsText(fileName: String): String = File(fileName).readText(Charsets.UTF_8)
fun writeFile(fileName: String, bytes: ByteArray) = File(fileName).writeBytes(bytes)
