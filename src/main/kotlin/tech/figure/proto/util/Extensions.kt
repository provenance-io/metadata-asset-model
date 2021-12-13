package tech.figure.proto.util

import com.google.protobuf.*
import com.google.protobuf.Any
import java.util.*
import tech.figure.util.UUID as ProtoUUID

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

fun UUID.toProtoUUID():  ProtoUUID = ProtoUUID.newBuilder().setValue(this.toString()).build()