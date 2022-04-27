# Loan Validation
<a name="top"></a>



<a name="tech/figure/validation/v1beta1/validation.proto"></a>
<p align="right"><a href="#top">Top</a></p>

## tech/figure/validation/v1beta1/validation.proto



<a name="tech.figure.validation.v1beta1.LoanValidation"></a>

### LoanValidation



| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| iteration | [ValidationIteration](#tech.figure.validation.v1beta1.ValidationIteration) | repeated |  |
| kv | [LoanValidation.KvEntry](#tech.figure.validation.v1beta1.LoanValidation.KvEntry) | repeated |  |





<a name="tech.figure.validation.v1beta1.LoanValidation.KvEntry"></a>

### LoanValidation.KvEntry



| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| key | [string](#string) |  |  |
| value | [google.protobuf.Any](#google.protobuf.Any) |  |  |





<a name="tech.figure.validation.v1beta1.ValidationItem"></a>

### ValidationItem
Individual rules that were executed, including the actual expression that was run


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| code | [string](#string) |  | Identifier for the rule |
| label | [string](#string) |  | Short description of the rule |
| description | [string](#string) |  | Long description of the rule |
| expression | [string](#string) |  | Actual value, comparison operator, and expected value written as a formula or description of a comparison |
| validation_outcome | [ValidationOutcome](#tech.figure.validation.v1beta1.ValidationOutcome) |  | Result of applying the rule, see enum |
| kv | [ValidationItem.KvEntry](#tech.figure.validation.v1beta1.ValidationItem.KvEntry) | repeated | Additional information related to the rule |





<a name="tech.figure.validation.v1beta1.ValidationItem.KvEntry"></a>

### ValidationItem.KvEntry



| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| key | [string](#string) |  |  |
| value | [google.protobuf.Any](#google.protobuf.Any) |  |  |





<a name="tech.figure.validation.v1beta1.ValidationIteration"></a>

### ValidationIteration
List of validation request/response objects

Each loan can go through several rounds of validation


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| request | [ValidationRequest](#tech.figure.validation.v1beta1.ValidationRequest) |  |  |
| results | [ValidationResults](#tech.figure.validation.v1beta1.ValidationResults) |  |  |
| kv | [ValidationIteration.KvEntry](#tech.figure.validation.v1beta1.ValidationIteration.KvEntry) | repeated |  |





<a name="tech.figure.validation.v1beta1.ValidationIteration.KvEntry"></a>

### ValidationIteration.KvEntry



| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| key | [string](#string) |  |  |
| value | [google.protobuf.Any](#google.protobuf.Any) |  |  |





<a name="tech.figure.validation.v1beta1.ValidationRequest"></a>

### ValidationRequest
Validation request including a pointer to the snapshot of data requiring validation


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| request_id | [tech.figure.util.v1beta1.UUID](util#tech.figure.util.v1beta1.UUID) |  | Unique ID for the request |
| effective_time | [google.protobuf.Timestamp](#google.protobuf.Timestamp) |  | Time the validation was requested |
| snapshot_uri | [string](#string) |  | Pointer to the snapshot |
| rule_set_id | [tech.figure.util.v1beta1.UUID](util#tech.figure.util.v1beta1.UUID) |  | ID of rule set that needs to be executed |
| description | [string](#string) |  | Description of the rule set |
| validator_name | [string](#string) |  | Party that will run the validation |
| requester_name | [string](#string) |  | Party invoking the request for validation |
| kv | [ValidationRequest.KvEntry](#tech.figure.validation.v1beta1.ValidationRequest.KvEntry) | repeated | Additional fields from the requester |





<a name="tech.figure.validation.v1beta1.ValidationRequest.KvEntry"></a>

### ValidationRequest.KvEntry



| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| key | [string](#string) |  |  |
| value | [google.protobuf.Any](#google.protobuf.Any) |  |  |





<a name="tech.figure.validation.v1beta1.ValidationResponse"></a>

### ValidationResponse
Input to the validation results p8e contract


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| request_id | [tech.figure.util.v1beta1.UUID](util#tech.figure.util.v1beta1.UUID) |  | Unique ID for the request - should match existing request |
| results | [ValidationResults](#tech.figure.validation.v1beta1.ValidationResults) |  | Validation results associated with the request |





<a name="tech.figure.validation.v1beta1.ValidationResults"></a>

### ValidationResults
Validation results that get stored alongside the validation request when validation is complete


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| result_set_uuid | [tech.figure.util.v1beta1.UUID](util#tech.figure.util.v1beta1.UUID) |  | unique ID for the result set |
| result_set_effective_time | [google.protobuf.Timestamp](#google.protobuf.Timestamp) |  | the date the validation was finalized |
| result_set_provider | [string](#string) |  | name of the validator |
| validation_exception_count | [int32](#int32) |  | Total count of exceptions |
| validation_warning_count | [int32](#int32) |  | Total count of warnings |
| validation_items | [ValidationItem](#tech.figure.validation.v1beta1.ValidationItem) | repeated | Individual rules that were executed |
| policy_documents | [tech.figure.util.v1beta1.DocumentMetadata](util#tech.figure.util.v1beta1.DocumentMetadata) | repeated | Related Documents like TPR Indemnification Policy, deligence scope |
| rating_agency_grading | [tech.figure.util.v1beta1.DocumentMetadata](util#tech.figure.util.v1beta1.DocumentMetadata) |  | Document containing credit rating agency grades |
| kv | [ValidationResults.KvEntry](#tech.figure.validation.v1beta1.ValidationResults.KvEntry) | repeated | Additional fields from the validator |





<a name="tech.figure.validation.v1beta1.ValidationResults.KvEntry"></a>

### ValidationResults.KvEntry



| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| key | [string](#string) |  |  |
| value | [google.protobuf.Any](#google.protobuf.Any) |  |  |







<a name="tech.figure.validation.v1beta1.ValidationOutcome"></a>

### ValidationOutcome
Possible outcomes when applying a validation rule

| Name | Number | Description |
| ---- | ------ | ----------- |
| UNKNOWN_TYPE | 0 |  |
| EXCEPTION | 1 |  |
| WARNING | 2 |  |
| VALID | 3 |  |








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
