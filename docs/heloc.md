# HELOC
<a name="top"></a>



<a name="tech/figure/loan/v1beta1/heloc.proto"></a>
<p align="right"><a href="#top">Top</a></p>

## tech/figure/loan/v1beta1/heloc.proto



<a name="tech.figure.loan.v1beta1.Heloc"></a>

### Heloc
A Home Equity Line of Credit (HELOC) Loan


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| lien_property | [tech.figure.util.v1beta1.Property](util.md#tech.figure.util.v1beta1.Property) |  | Subject property |
| lien_position | [uint32](#uint32) |  | Lien position: 1 = first lien position, 2 or higher = junior lien position |
| draw_term_in_months | [google.protobuf.UInt32Value](#google.protobuf.UInt32Value) |  | Total number of months the borrower can draw on the line |
| draw_percentage | [tech.figure.util.v1beta1.Rate](util.md#tech.figure.util.v1beta1.Rate) |  | The maximum amount a borrower can redraw as a percent of the paid balance of the original draw (borrower cannot draw more than the original balance) |
| recording_status | [tech.figure.util.v1beta1.Status](util.md#tech.figure.util.v1beta1.Status) |  | Loan recording status (e.g. PENDING, RECORDED) |
| credit_limit_amount | [tech.figure.util.v1beta1.Money](util.md#tech.figure.util.v1beta1.Money) |  | HELOC credit limit |
| paid_draw_bonus_months | [int32](#int32) |  | Number of months draw period is extended by if paid off in original draw period |
| static_draw_rate_flag | [bool](#bool) |  | If true, use interest_rate for any future draws. If false, use current prime rate |
| recording_info | [Recording](#tech.figure.loan.v1beta1.Recording) |  | The registration of the lien in a public record by a government agency |













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
