# MISMO Loan
<a name="top"></a>



<a name="tech/figure/loan/v1beta1/mismo_loan.proto"></a>
<p align="right"><a href="#top">Top</a></p>

## tech/figure/loan/v1beta1/mismo_loan.proto



<a name="tech.figure.loan.v1beta1.MISMOLoan"></a>

### MISMOLoan
Loan originators may choose to simply upload a MISMO standardized Loan Package to store static loan data collected through
their Loan Origination System. The current standard is MISMO v3.4.

This data is generally not expected to change over time. The evolving state of the loan (commonly called "servicing data")
is represented by the [`LoanState`](loan_state) proto.


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| uli | [string](#string) |  | Universal Loan Identifier (ULI) is a unique number made up of 23 to 45 characters that begins with the loan originator's Legal Entity Identifier (LEI). An originator's LEI can be found by searching the [GLEIF Website](https://search.gleif.org/#/search/). |
| data | [google.protobuf.BytesValue](#google.protobuf.BytesValue) |  | Byte array of the MISMO XML file |
| recording_info | [Recording](#tech.figure.loan.v1beta1.Recording) |  | The registration of the mortgage in a public record by a government agency |
| kv | [MISMOLoan.KvEntry](#tech.figure.loan.v1beta1.MISMOLoan.KvEntry) | repeated | Key-value map allowing originator to provide additional data |





<a name="tech.figure.loan.v1beta1.MISMOLoan.KvEntry"></a>

### MISMOLoan.KvEntry



| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| key | [string](#string) |  |  |
| value | [google.protobuf.Any](#google.protobuf.Any) |  |  |





<a name="tech.figure.loan.v1beta1.MISMOLoanMetadata"></a>

### MISMOLoanMetadata
Like any other loan document, the MISMO Loan Package can be stored separately in an Object Store and referenced here.

This is the most common scenario for loans in the Provenance/DART ecosystem.


Example:
```json
{
  "typeUrl": "/tech.figure.asset.loan.MISMOLoanMetadata",
  "uli": "LEI456123456123456123456123",
  "document": {
    "id": "",
    "uri": "",
    "fileName": "",
    "contentType": "application/xml",
    "documentType": "",
    "checksum": ""
  }
} 
```


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| uli | [string](#string) |  | Universal Loan Identifier (ULI) is a unique number made up of 23 to 45 characters that begins with the loan originator's Legal Entity Identifier (LEI). An originator's LEI can be found by searching the [GLEIF Website](https://search.gleif.org/#/search/). |
| document | [tech.figure.util.v1beta1.DocumentMetadata](util.md#tech.figure.util.v1beta1.DocumentMetadata) |  | Pointer to MISMO loan file in Object Store |
| recording_info | [Recording](#tech.figure.loan.v1beta1.Recording) |  | The registration of the mortgage in a public record by a government agency |
| kv | [MISMOLoanMetadata.KvEntry](#tech.figure.loan.v1beta1.MISMOLoanMetadata.KvEntry) | repeated | Key-value map allowing originator to provide additional data |





<a name="tech.figure.loan.v1beta1.MISMOLoanMetadata.KvEntry"></a>

### MISMOLoanMetadata.KvEntry



| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| key | [string](#string) |  |  |
| value | [google.protobuf.Any](#google.protobuf.Any) |  |  |













## Scalar Value Types

| .proto Type | Notes | C++ | Java | Python | Go | C# | PHP | Ruby |
| ----------- | ----- | --- | ---- | ------ | -- | -- | --- | ---- |
| <a name="double" /> double |  | double | double | float | float64 | double | float | Float |
| <a name="float" /> float |  | float | float | float | float32 | float | float | Float |
| <a name="int32" /> int32 | Uses variable-length encoding. Inefficient for encoding negative numbers – if your field is likely to have negative values, use sint32 instead. | int32 | int | int | int32 | int | integer | Bignum or Fixnum (as required) |
| <a name="int64" /> int64 | Uses variable-length encoding. Inefficient for encoding negative numbers – if your field is likely to have negative values, use sint64 instead. | int64 | long | int/long | int64 | long | integer/string | Bignum |
| <a name="uint32" /> uint32 | Uses variable-length encoding. | uint32 | int | int/long | uint32 | uint | integer | Bignum or Fixnum (as required) |
| <a name="uint64" /> uint64 | Uses variable-length encoding. | uint64 | long | int/long | uint64 | ulong | integer/string | Bignum or Fixnum (as required) |
| <a name="sint32" /> sint32 | Uses variable-length encoding. Signed int value. These more efficiently encode negative numbers than regular int32s. | int32 | int | int | int32 | int | integer | Bignum or Fixnum (as required) |
| <a name="sint64" /> sint64 | Uses variable-length encoding. Signed int value. These more efficiently encode negative numbers than regular int64s. | int64 | long | int/long | int64 | long | integer/string | Bignum |
| <a name="fixed32" /> fixed32 | Always four bytes. More efficient than uint32 if values are often greater than 2^28. | uint32 | int | int | uint32 | uint | integer | Bignum or Fixnum (as required) |
| <a name="fixed64" /> fixed64 | Always eight bytes. More efficient than uint64 if values are often greater than 2^56. | uint64 | long | int/long | uint64 | ulong | integer/string | Bignum |
| <a name="sfixed32" /> sfixed32 | Always four bytes. | int32 | int | int | int32 | int | integer | Bignum or Fixnum (as required) |
| <a name="sfixed64" /> sfixed64 | Always eight bytes. | int64 | long | int/long | int64 | long | integer/string | Bignum |
| <a name="bool" /> bool |  | bool | boolean | boolean | bool | bool | boolean | TrueClass/FalseClass |
| <a name="string" /> string | A string must always contain UTF-8 encoded or 7-bit ASCII text. | string | String | str/unicode | string | string | string | String (UTF-8) |
| <a name="bytes" /> bytes | May contain any arbitrary sequence of bytes. | string | ByteString | str | []byte | ByteString | string | String (ASCII-8BIT) |
