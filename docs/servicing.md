# Loan Servicing
<a name="top"></a>



<a name="tech/figure/servicing/v1beta1/loan_state.proto"></a>
<p align="right"><a href="#top">Top</a></p>

## tech/figure/servicing/v1beta1/loan_state.proto



<a name="tech.figure.servicing.v1beta1.LoanState"></a>

### LoanState
Loan state data (servicing data) at a point in time




| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| loan_id | [tech.figure.util.UUID](util#tech.figure.util.UUID) |  | Loan identifier for which this state is calculated |
| effective_time | [google.protobuf.Timestamp](#google.protobuf.Timestamp) |  | Timestamp when this state was produced |
| loan_type | [string](#string) |  | Type of loan (e.g. MORTGAGE, HELOC, PERSONAL_LOAN) |
| servicer_name | [string](#string) |  | Name of servicer for loan |
| total_unpaid_prin_bal | [tech.figure.util.Money](util#tech.figure.util.Money) |  | Total unpaid principal balance |
| accrued_interest | [tech.figure.util.Money](util#tech.figure.util.Money) |  | Total interest accrued to-date |
| daily_int_amount | [tech.figure.util.Money](util#tech.figure.util.Money) |  | Accrued interest amount per day |
| loan_status | [tech.figure.util.Status](util#tech.figure.util.Status) |  | Loan status, such as IN REPAY 1-9 DAYS DELQ 10-29 DAYS DELQ 30-59 DAYS DELQ 60-89 DAYS DELQ 90+ DAYS DELQ PAID_CLOSED PAID_OPEN FORBEARANCE TRANSFERRED |
| current_borrower_info | [tech.figure.util.Borrowers](util#tech.figure.util.Borrowers) |  | Borrower(s), co-signers, etc |
| current_p_and_i | [tech.figure.util.Money](util#tech.figure.util.Money) |  | Currently monthly loan payment amount including principal and interest |
| current_interest_rate | [tech.figure.util.Rate](util#tech.figure.util.Rate) |  | The current interest rate used to calculate the principal and interest payment |
| int_accrual_start_date | [tech.figure.util.Date](util#tech.figure.util.Date) |  | The date on which interest accrual began |
| int_paid_through_date | [tech.figure.util.Date](util#tech.figure.util.Date) |  | The date through which interest is paid with the current payment. The effective date from which interest will be calculated for the application of the next payment. |
| maturity_date | [tech.figure.util.Date](util#tech.figure.util.Date) |  | The date when a loan reaches maturity |
| next_due_date | [tech.figure.util.Date](util#tech.figure.util.Date) |  | Date of next due payment for loan |
| days_delinquent | [int32](#int32) |  | Number of days delinquent |
| remaining_term_months | [int32](#int32) |  | Remaining loan term in months |
| servicing_fee_rate | [tech.figure.util.Rate](util#tech.figure.util.Rate) |  | The fee paid to the servicer, stated as a percentage of the outstanding loan balance |
| apr | [tech.figure.util.Rate](util#tech.figure.util.Rate) |  | Annualized Percentage Rate of the loan |
| autopay_flag | [bool](#bool) |  | If true, payments are automatically deducted from borrower's financial account |
| deferred_int_balance | [tech.figure.util.Money](util#tech.figure.util.Money) |  | Accumulated Interest balance since forbearance_begin_date |
| delinquent_date | [tech.figure.util.Date](util#tech.figure.util.Date) |  | If delinquent, date first payment was missed that made the loan currently delinquent |
| days_forbearance | [uint32](#uint32) |  | Days elapsed since entering Forbearance |
| forbearance_begin_date | [tech.figure.util.Date](util#tech.figure.util.Date) |  | Date forbearance period begins |
| forbearance_end_date | [tech.figure.util.Date](util#tech.figure.util.Date) |  | Date forbearance period ends |
| interest_paid | [tech.figure.util.Money](util#tech.figure.util.Money) |  | Total interest paid to-date |
| interest_rate_cap | [tech.figure.util.Rate](util#tech.figure.util.Rate) |  | Interest rate cap |
| last_payment_date | [tech.figure.util.Date](util#tech.figure.util.Date) |  | Last payment received date |
| monthly_payment_amount | [tech.figure.util.Money](util#tech.figure.util.Money) |  | The minimum monthly payment amount that borrower needs to repay on the loan |
| principal_paid | [tech.figure.util.Money](util#tech.figure.util.Money) |  | Sum of amount paid to principal |
| past_due_amount | [tech.figure.util.Money](util#tech.figure.util.Money) |  | Amount past due |
| kv | [tech.figure.servicing.v1beta1.LoanState.KvEntry](#tech.figure.servicing.v1beta1.LoanState.KvEntry) | repeated | Additional loan state data for a user or tool-defined extension of state. Key is field name. Value is any proto Message. For scalar values, use <a href="https://developers.google.com/protocol-buffers/docs/reference/google.protobuf">Protobuf Well-Known Types</a> |




*Field Validation Rules:*

| Field | Validations |
| ----- | ----------- |
| loan_id | message.required: true  |
| effective_time | timestamp.required: true timestamp.lt_now: true  |
| loan_type | string.min_len: 1  |
| servicer_name | string.min_len: 1  |
| total_unpaid_prin_bal | message.required: true  |
| accrued_interest | message.required: true  |
| daily_int_amount | message.required: true  |








<a name="tech.figure.servicing.v1beta1.LoanState.KvEntry"></a>

### LoanState.KvEntry





| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| key | [string](#string) |  |  |
| value | [google.protobuf.Any](#google.protobuf.Any) |  |  |







<a name="tech.figure.servicing.v1beta1.LoanStructureState"></a>

### LoanStructureState
Aggregation of loan state across a structure (group) of loans




| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| marker_address | [string](#string) |  | Provenance Blockchain Address for the Marker representing this loan structure |
| loan_states | [tech.figure.servicing.v1beta1.LoanState](#tech.figure.servicing.v1beta1.LoanState) | repeated | Loan state data for loans included in the structure |






 

 

 

 



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