# Loan
<a name="top"></a>



<a name="tech/figure/loan/v1beta1/loan.proto"></a>
<p align="right"><a href="#top">Top</a></p>

## tech/figure/loan/v1beta1/loan.proto



<a name="tech.figure.loan.v1beta1.ApplicationData"></a>

### ApplicationData
Data supplied by borrower during loan application and used by the underwriting process


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| loan_purpose | [string](#string) |  | Intended use of loan (e.g. Home improvement, Debt consolidation, Major purchase, Other purchase, Cash-out Refinance, Rate Refinance) |
| income | [tech.figure.util.v1beta1.Income](util.md#tech.figure.util.v1beta1.Income) | repeated | Stated and/or verified income data |
| credit_reports | [tech.figure.util.v1beta1.CreditReport](util.md#tech.figure.util.v1beta1.CreditReport) | repeated | Borrower credit reports |
| kv | [ApplicationData.KvEntry](#tech.figure.loan.v1beta1.ApplicationData.KvEntry) | repeated | Additional application data |





<a name="tech.figure.loan.v1beta1.ApplicationData.KvEntry"></a>

### ApplicationData.KvEntry



| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| key | [string](#string) |  |  |
| value | [google.protobuf.Any](#google.protobuf.Any) |  |  |





<a name="tech.figure.loan.v1beta1.Disbursement"></a>

### Disbursement
Detail of a single funding disbursement. Most loans will have only one disbursement. An example of a multi-disbursement loan might be a Student Loan Refinance, where
multiple previous student loans are being consolidated and paid off. Each previous loan payoff would be a separate disbursement.


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| id | [tech.figure.util.v1beta1.UUID](util.md#tech.figure.util.v1beta1.UUID) |  | Disbursement identifier |
| amount | [tech.figure.util.v1beta1.Money](util.md#tech.figure.util.v1beta1.Money) |  | Amount to send |
| disburse_account | [tech.figure.util.v1beta1.Account](util.md#tech.figure.util.v1beta1.Account) |  | Destination to send funds |
| status | [tech.figure.util.v1beta1.Status](util.md#tech.figure.util.v1beta1.Status) |  | Status of sending funds for this disbursement: UNFUNDED, INITIATED, COMPLETED, CANCELLED |
| started | [google.protobuf.Timestamp](#google.protobuf.Timestamp) |  | Time this disbursement was initiated |
| completed | [google.protobuf.Timestamp](#google.protobuf.Timestamp) |  | Time this disbursement was completed |
| kv | [Disbursement.KvEntry](#tech.figure.loan.v1beta1.Disbursement.KvEntry) | repeated | Additional process-specific data related to funding this disbursement |





<a name="tech.figure.loan.v1beta1.Disbursement.KvEntry"></a>

### Disbursement.KvEntry



| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| key | [string](#string) |  |  |
| value | [google.protobuf.Any](#google.protobuf.Any) |  |  |





<a name="tech.figure.loan.v1beta1.Funding"></a>

### Funding
Detail of the loan funding process


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| status | [tech.figure.util.v1beta1.Status](util.md#tech.figure.util.v1beta1.Status) |  | Status of loan funding process: e.g. UNFUNDED, INITIATED, FUNDED, CANCELLED |
| started | [google.protobuf.Timestamp](#google.protobuf.Timestamp) |  | Time funding process was initiated |
| completed | [google.protobuf.Timestamp](#google.protobuf.Timestamp) |  | Time funding process was completed |
| disbursements | [Disbursement](#tech.figure.loan.v1beta1.Disbursement) | repeated | Detailed information about one or more monetary disbursements to borrower |





<a name="tech.figure.loan.v1beta1.Loan"></a>

### Loan
Loan information at the time of origination describing the data collected through the LOS (Loan Origination System).

This data is generally not expected to change over time. The evolving state of the loan (commonly called "servicing data")
is represented by the [`LoanState`](loan_state) proto.


Example:
```json
{
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
```


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| id | [tech.figure.util.v1beta1.UUID](util.md#tech.figure.util.v1beta1.UUID) |  | Loan UUID identifier |
| originator_name | [string](#string) |  | Human-readable name of the originating firm |
| originator_loan_id | [string](#string) |  | Originator's internal loan identifier |
| borrowers | [tech.figure.util.v1beta1.Borrowers](util.md#tech.figure.util.v1beta1.Borrowers) |  | Borrower(s), co-signers, etc |
| terms | [Terms](#tech.figure.loan.v1beta1.Terms) |  | Loan terms: amount, duration, rates |
| funding | [Funding](#tech.figure.loan.v1beta1.Funding) |  | Funding detail |
| app | [ApplicationData](#tech.figure.loan.v1beta1.ApplicationData) |  | Data collected by LOS loan application process and used for underwriting |
| underwriting | [Underwriting](#tech.figure.loan.v1beta1.Underwriting) |  | Underwriting |
| doc_meta | [tech.figure.util.v1beta1.DocumentMetadata](util.md#tech.figure.util.v1beta1.DocumentMetadata) | repeated | Metadata about documents related to the loan (document files stored separately) |
| signed_data | [tech.figure.util.v1beta1.SignedData](util.md#tech.figure.util.v1beta1.SignedData) | repeated | Digitally-signed-by-source data/documents |
| asset_type | [tech.figure.util.v1beta1.AssetType](util.md#tech.figure.util.v1beta1.AssetType) |  | Specify loan type if none of the above types are used, e.g. PERSONAL_LOAN |
| uli | [string](#string) |  | Universal Loan Identifier (ULI) is a unique number made up of 23 to 45 characters that begins with the loan originator's Legal Entity Identifier (LEI). An originator's LEI can be found by searching the [GLEIF Website](https://search.gleif.org/#/search/). |
| originator_uuid | [tech.figure.util.v1beta1.UUID](util.md#tech.figure.util.v1beta1.UUID) |  | Originator UUID identifier |
| mortgage | [Mortgage](#tech.figure.loan.v1beta1.Mortgage) |  | Mortgage |
| heloc | [Heloc](#tech.figure.loan.v1beta1.Heloc) |  | Home Equity Line of Credit |
| student_loan | [StudentLoan](#tech.figure.loan.v1beta1.StudentLoan) |  | New or refinanced student loan |
| loan_type | [string](#string) |  | **Deprecated.** DEPRECATED: Please use asset_type** Specify loan type if none of the above types are used, e.g. PERSONAL_LOAN |
| kv | [Loan.KvEntry](#tech.figure.loan.v1beta1.Loan.KvEntry) | repeated | Key-value map allowing originator to provide additional data |





<a name="tech.figure.loan.v1beta1.Loan.KvEntry"></a>

### Loan.KvEntry



| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| key | [string](#string) |  |  |
| value | [google.protobuf.Any](#google.protobuf.Any) |  |  |





<a name="tech.figure.loan.v1beta1.LoanDates"></a>

### LoanDates
Milestone dates for the loan established at origination


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| initial_offer_date | [tech.figure.util.v1beta1.Date](util.md#tech.figure.util.v1beta1.Date) |  | Date loan was offered to borrower |
| origination_date | [tech.figure.util.v1beta1.Date](util.md#tech.figure.util.v1beta1.Date) |  | Date the loan is closed (typically same as `signed_date`) |
| signed_date | [tech.figure.util.v1beta1.Date](util.md#tech.figure.util.v1beta1.Date) |  | Date the Note is signed |
| funding_date | [tech.figure.util.v1beta1.Date](util.md#tech.figure.util.v1beta1.Date) |  | Date originator sent funds to borrower |
| first_payment_due_date | [tech.figure.util.v1beta1.Date](util.md#tech.figure.util.v1beta1.Date) |  | Date first payment is due from borrower |
| grace_period_end_date | [tech.figure.util.v1beta1.Date](util.md#tech.figure.util.v1beta1.Date) |  | If loan has grace period, date grace period ends and repayment must begin |
| amortization_start_date | [tech.figure.util.v1beta1.Date](util.md#tech.figure.util.v1beta1.Date) |  | Date amortization schedule starts and interest begins accrual |





<a name="tech.figure.loan.v1beta1.Payment"></a>

### Payment
Details of the payment requirements for the loan


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| first_payment_amount | [tech.figure.util.v1beta1.Money](util.md#tech.figure.util.v1beta1.Money) |  | The amount of the first scheduled payment to be made by the borrower. Includes principal, interest, taxes, and insurance (if escrows are not waived) |
| monthly_payment_amount | [tech.figure.util.v1beta1.Money](util.md#tech.figure.util.v1beta1.Money) |  | Principal and interest only (no escrow/fees) |
| payment_method | [string](#string) |  | Borrower-select method of payment (e.g. MANUAL or AUTOPAY) |
| autopay_account | [tech.figure.util.v1beta1.Account](util.md#tech.figure.util.v1beta1.Account) |  | Populated if borrower has agreed to automated monthly payments |





<a name="tech.figure.loan.v1beta1.RateDiscount"></a>

### RateDiscount
A discount on the interest rate for the loan


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| type | [string](#string) |  | Reason for the rate discount, e.g. AUTOPAY, CREDIT_UNION_MEMBERSHIP |
| rate | [tech.figure.util.v1beta1.Rate](util.md#tech.figure.util.v1beta1.Rate) |  | Amount interest rate is reduced |
| included_in_margin | [bool](#bool) |  | Indicate whether a discount is included in the loan margin or not. E.g. autopay discount only applied if starting on autopay. |





<a name="tech.figure.loan.v1beta1.Terms"></a>

### Terms
The terms and conditions for a loan: interest rates, fees, dates, etc.


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| principal_amount | [tech.figure.util.v1beta1.Money](util.md#tech.figure.util.v1beta1.Money) |  | Loan amount, not including fees or interest |
| total_amount | [tech.figure.util.v1beta1.Money](util.md#tech.figure.util.v1beta1.Money) |  | Principal loan amount + origination fees |
| term_in_months | [int32](#int32) |  | Loan term (duration) in months |
| rate_type | [string](#string) |  | Interest rate type (e.g. fixed or floating) See InterestRateExpectedType |
| interest_rate | [tech.figure.util.v1beta1.Rate](util.md#tech.figure.util.v1beta1.Rate) |  | Total interest rate for loan (margin_rate + index_rate - any rate discounts at time of origination) |
| index_rate | [tech.figure.util.v1beta1.Rate](util.md#tech.figure.util.v1beta1.Rate) |  | Benchmarked indexed rate value |
| index_rate_type | [string](#string) |  | Benchmarked index rate type (e.g. Prime, LIBOR) See IndexRateType |
| fees | [tech.figure.util.v1beta1.Fee](util.md#tech.figure.util.v1beta1.Fee) | repeated | Fees charged to the loan, e.g. origination fee, application fee, annual fee |
| interest_rate_cap | [tech.figure.util.v1beta1.Rate](util.md#tech.figure.util.v1beta1.Rate) |  | Maximum allow interest rate on the loan (most relevant to variable-rate loans) |
| rate_discounts | [RateDiscount](#tech.figure.loan.v1beta1.RateDiscount) | repeated | All rate discounts available to this asset. |
| payment | [Payment](#tech.figure.loan.v1beta1.Payment) |  | Details about initial and monthly payments |
| dates | [LoanDates](#tech.figure.loan.v1beta1.LoanDates) |  | Original milestone dates for the loan |





<a name="tech.figure.loan.v1beta1.Underwriting"></a>

### Underwriting
Information derived during the underwriting process


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| effective_time | [google.protobuf.Timestamp](#google.protobuf.Timestamp) |  | Timestamp when underwriting was performed ("as of" date) |
| version | [string](#string) |  | Underwriting software or algorithm version |
| pre_loan_ltv | [tech.figure.util.v1beta1.Rate](util.md#tech.figure.util.v1beta1.Rate) |  | Loan-to-value ratio - value of loan divided by value of asset (i.e. property value) - prior to this loan |
| pre_loan_cltv | [tech.figure.util.v1beta1.Rate](util.md#tech.figure.util.v1beta1.Rate) |  | Combined loan-to-value ration - sum of values of all loans divided by value of asset (i.e. property value) - prior to this loan |
| pre_loan_dti | [tech.figure.util.v1beta1.Rate](util.md#tech.figure.util.v1beta1.Rate) |  | Pre-loan debt to income ratio |
| post_loan_ltv | [tech.figure.util.v1beta1.Rate](util.md#tech.figure.util.v1beta1.Rate) |  | Loan-to-value ratio - value of loan divided by value of asset (i.e. property value) - after/including this loan |
| post_loan_cltv | [tech.figure.util.v1beta1.Rate](util.md#tech.figure.util.v1beta1.Rate) |  | Combined loan-to-value ratio - sum of values of all loans divided by value of asset (i.e. property value) - after/including this loan |
| post_loan_dti | [tech.figure.util.v1beta1.Rate](util.md#tech.figure.util.v1beta1.Rate) |  | Post-loan debt to income ratio |
| payment_to_income | [tech.figure.util.v1beta1.Rate](util.md#tech.figure.util.v1beta1.Rate) |  | Ratio of monthly loan payment to monthly income |
| kv | [Underwriting.KvEntry](#tech.figure.loan.v1beta1.Underwriting.KvEntry) | repeated | Additional underwriting calculations |





<a name="tech.figure.loan.v1beta1.Underwriting.KvEntry"></a>

### Underwriting.KvEntry



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
