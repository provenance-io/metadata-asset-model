# Asset (NFT)
<a name="top"></a>



<a name="tech/figure/asset/v1beta1/asset.proto"></a>
<p align="right"><a href="#top">Top</a></p>

## tech/figure/asset/v1beta1/asset.proto



<a name="tech.figure.asset.v1beta1.Asset"></a>

### Asset
An Asset is the representation of an NFT on Provenance Blockchain, and can be any type of digital asset
represented by one or more [Google Protocol Buffers](https://developers.google.com/protocol-buffers).

For specific asset types, use a domain-specific protobuf, such as a [`Loan`](loan#loan-1), in the `kv` map.


Example:
```json
{
  "id": "c6978d46-3c3e-4175-a0d2-8f8ce47e8bb6",
  "type": "LOAN",
  "description": "PERSONAL_LOAN LOAN-1234",
  "kv": {
    "loan": {
      "id": "c6978d46-3c3e-4175-a0d2-8f8ce47e8bb6",
      "originatorName": "Example Loan Company",
      "originatorLoanId": "LOAN-1234",
      "loanType": "PERSONAL_LOAN",
      "terms": {
        "principalAmount": {
          "amount": 10000.00,
          "currency": "USD"
        },
        "totalAmount": {
          "amount": 10200.00,
          "currency": "USD"
        },
        "termInMonths": 12,
        "interestRate": {
          "value": 0.065
        }
      },
      "funding": {
        "started": false,
        "completed": false
      },
      "assetType": {
        "supertype": "PERSONAL_LOAN"
      },
      "uli": "LEI456123456123456123456123",
      "originatorUuid": "00000000-0000-0000-0000-000000000000"
    }
  }
} 
```


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| id | [tech.figure.util.v1beta1.UUID](util.md#tech.figure.util.v1beta1.UUID) |  | Required UUID identifier for this asset |
| type | [string](#string) |  | Optional user-defined type (e.g. LOAN, ART, PROPERTY TITLE, FUND, SHARE CLASS) |
| description | [string](#string) |  | Optional user-defined description, title, name, etc. for display |
| kv | [Asset.KvEntry](#tech.figure.asset.v1beta1.Asset.KvEntry) | repeated | Key-value store of asset data |





<a name="tech.figure.asset.v1beta1.Asset.KvEntry"></a>

### Asset.KvEntry



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
