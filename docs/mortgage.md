# Mortgage
<a name="top"></a>



<a name="tech/figure/loan/v1beta1/mortgage.proto"></a>
<p align="right"><a href="#top">Top</a></p>

## tech/figure/loan/v1beta1/mortgage.proto



<a name="tech.figure.loan.v1beta1.ARM"></a>

### ARM
Terms and dates for an Adjustable-Rate Mortgage (ARM)


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| index | [tech.figure.util.v1beta1.Rate](util.md#tech.figure.util.v1beta1.Rate) |  |  |
| initial_fixed_rate_period_months | [uint32](#uint32) |  |  |
| initial_payment_adj_date | [tech.figure.util.v1beta1.Date](util.md#tech.figure.util.v1beta1.Date) |  |  |
| initial_rate_adj_cap | [tech.figure.util.v1beta1.Rate](util.md#tech.figure.util.v1beta1.Rate) |  |  |
| initial_rate_adj_date | [tech.figure.util.v1beta1.Date](util.md#tech.figure.util.v1beta1.Date) |  |  |
| initial_rate_adj_floor | [tech.figure.util.v1beta1.Rate](util.md#tech.figure.util.v1beta1.Rate) |  |  |
| lifetime_rate_adj_cap | [tech.figure.util.v1beta1.Rate](util.md#tech.figure.util.v1beta1.Rate) |  |  |
| margin | [tech.figure.util.v1beta1.Rate](util.md#tech.figure.util.v1beta1.Rate) |  |  |
| max_rate | [tech.figure.util.v1beta1.Rate](util.md#tech.figure.util.v1beta1.Rate) |  |  |
| min_rate | [tech.figure.util.v1beta1.Rate](util.md#tech.figure.util.v1beta1.Rate) |  |  |
| next_rate_adj_date | [tech.figure.util.v1beta1.Date](util.md#tech.figure.util.v1beta1.Date) |  |  |
| subs_rate_adj_cap | [tech.figure.util.v1beta1.Rate](util.md#tech.figure.util.v1beta1.Rate) |  |  |
| subs_rate_adj_period_months | [uint32](#uint32) |  |  |





<a name="tech.figure.loan.v1beta1.Escrow"></a>

### Escrow
Monies held in an account, typically by the servicer, for paying certain expenses related to the lien property,
such as taxes and insurance.


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| escrow_monthly_amount | [tech.figure.util.v1beta1.Money](util.md#tech.figure.util.v1beta1.Money) |  | Sum of all monthly escrow amounts paid by the borrower in addition to the monthly payment amount |
| pre_paid_escrow_amount | [tech.figure.util.v1beta1.Money](util.md#tech.figure.util.v1beta1.Money) |  | Prepaid escrow (paid out at closing to insurance / tax entities) |
| initial_escrow_amount | [tech.figure.util.v1beta1.Money](util.md#tech.figure.util.v1beta1.Money) |  | Initial/starting escrow balance (held in escrow account by the servicer for future use) |
| insurance_coverage | [InsuranceEscrow](#tech.figure.loan.v1beta1.InsuranceEscrow) | repeated | Homeowner's insurance |
| tax_escrow | [TaxEscrow](#tech.figure.loan.v1beta1.TaxEscrow) | repeated | Property tax escrow |
| hoa | [HomeOwnersAssociationEscrow](#tech.figure.loan.v1beta1.HomeOwnersAssociationEscrow) |  | Home Owner's Association information and dues |





<a name="tech.figure.loan.v1beta1.EscrowWithdrawPolicy"></a>

### EscrowWithdrawPolicy
Describes how and when funds are withdrawn from an escrow account to pay an item


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| first_withdraw_date | [tech.figure.util.v1beta1.Date](util.md#tech.figure.util.v1beta1.Date) |  | The initial withdraw date after funding |
| withdraw_frequency | [string](#string) |  | How often funds are withdrawn from escrow account to pay this item e.g. MONTHLY, SEMIANNUAL, YEARLY |
| withdraw_amount | [tech.figure.util.v1beta1.Money](util.md#tech.figure.util.v1beta1.Money) |  | Amount to be withdrawn at the given frequency |





<a name="tech.figure.loan.v1beta1.HomeOwnersAssociationEscrow"></a>

### HomeOwnersAssociationEscrow
Detail about escrow withholding for a Home Owners' Association for the lien property


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| escrow_flag | [bool](#bool) |  | Indication whether HOA dues are part of escrow |
| escrow_monthly_amount | [tech.figure.util.v1beta1.Money](util.md#tech.figure.util.v1beta1.Money) |  | If escrow flag is set, the amount withheld from the loan payment for this item |
| withdraw_policy | [EscrowWithdrawPolicy](#tech.figure.loan.v1beta1.EscrowWithdrawPolicy) |  | HOA dues payment policy |
| contact_info | [tech.figure.util.v1beta1.ContactInformation](util.md#tech.figure.util.v1beta1.ContactInformation) |  | HOA contact information |





<a name="tech.figure.loan.v1beta1.InsuranceEscrow"></a>

### InsuranceEscrow
Detail about escrow withholding for insurance related to the lien property


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| escrow_flag | [bool](#bool) |  | Indication whether loan is escrowed for this type of insurance |
| escrow_monthly_amount | [tech.figure.util.v1beta1.Money](util.md#tech.figure.util.v1beta1.Money) |  | If escrow flag is set, the amount withheld from the loan payment for this item |
| type | [string](#string) |  | Type of insurance, e.g. HOMEOWNERS, FLOOD, EARTHQUAKE, CONDO, HOA, PMI, MIP |
| coverage_amount | [tech.figure.util.v1beta1.Money](util.md#tech.figure.util.v1beta1.Money) |  | Amount for which the property is insured |
| expiration_date | [tech.figure.util.v1beta1.Date](util.md#tech.figure.util.v1beta1.Date) |  | Policy expiration date |
| policy_number | [string](#string) |  | Policy account number |
| withdraw_policy | [EscrowWithdrawPolicy](#tech.figure.loan.v1beta1.EscrowWithdrawPolicy) |  | Insurance escrow payment policy |
| start_date | [tech.figure.util.v1beta1.Date](util.md#tech.figure.util.v1beta1.Date) |  | Policy start date |
| contact_info | [tech.figure.util.v1beta1.ContactInformation](util.md#tech.figure.util.v1beta1.ContactInformation) |  | Insurance company contact information |





<a name="tech.figure.loan.v1beta1.Mortgage"></a>

### Mortgage
A home Mortgage loan


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| lien_property | [tech.figure.util.v1beta1.Property](util.md#tech.figure.util.v1beta1.Property) |  | Property for which this mortgage was obtained |
| lien_position | [uint32](#uint32) |  | Lien position: 1 = first lien position, 2 or more = junior lien position |
| cash_out_amount | [tech.figure.util.v1beta1.Money](util.md#tech.figure.util.v1beta1.Money) |  | Cash proceeds to the borrower from a cash out refinancing after all other loans to be paid by the mortgage proceeds have been satisfied |
| arm_flag | [bool](#bool) |  | True if loan is an Adjustable Rate Mortgage |
| arm | [ARM](#tech.figure.loan.v1beta1.ARM) |  | If Adjustable-Rate Mortgage, the ARM terms |
| io_flag | [bool](#bool) |  | True if loan is an interest-only loan |
| io_term_months | [uint32](#uint32) |  | Number of months mortgage is interest-only |
| escrow_flag | [bool](#bool) |  | Escrow enabled flag: Indicates whether various home ownership expenses (taxes, insurance, HOA dues) are paid by the borrower directly or through an escrow account. Borrower paid=false, paid through escrow=true |
| neg_am_flag | [bool](#bool) |  | True if negative amortization |
| agency_eligible_flag | [bool](#bool) |  | Desktop Underwriter: true if agency eligible. If Jumbo, should be false |
| agency_program | [string](#string) |  | If agency eligible, the program to which this loan complies (e.g. FNMA HB, FNMA, JUMBO) |
| escrow | [Escrow](#tech.figure.loan.v1beta1.Escrow) |  | Items held in escrow for this loan |
| recording_info | [Recording](#tech.figure.loan.v1beta1.Recording) |  | Mortgage county recording information |





<a name="tech.figure.loan.v1beta1.Recording"></a>

### Recording
The registration of the mortgage in a public record by a government agency


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| recorded_document_id | [tech.figure.util.v1beta1.UUID](util.md#tech.figure.util.v1beta1.UUID) |  |  |
| recording_status | [tech.figure.util.v1beta1.Status](util.md#tech.figure.util.v1beta1.Status) |  |  |
| recorded_timestamp | [google.protobuf.Timestamp](#google.protobuf.Timestamp) |  |  |
| instrument_number | [string](#string) |  |  |
| security_instrument_date | [tech.figure.util.v1beta1.Date](util.md#tech.figure.util.v1beta1.Date) |  |  |
| county_recorded_date | [tech.figure.util.v1beta1.Date](util.md#tech.figure.util.v1beta1.Date) |  |  |
| document_number | [string](#string) |  |  |
| book | [string](#string) |  |  |
| page | [string](#string) |  |  |
| county_recorded | [string](#string) |  |  |
| town_recorded | [string](#string) |  |  |
| state_recorded | [string](#string) |  |  |
| recordation_number | [string](#string) |  |  |
| mortgagee_of_record | [string](#string) |  | Mortgagee of record (DART, MERS, or lender) |
| original_mortgagee_of_record | [string](#string) |  | Original mortgagee of record (DART, MERS, or lender) |





<a name="tech.figure.loan.v1beta1.TaxEscrow"></a>

### TaxEscrow
Detail about escrow witholding for tax payments related to the lien property


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| escrow_flag | [bool](#bool) |  | Indication whether loan is escrowed for taxes |
| escrow_monthly_amount | [tech.figure.util.v1beta1.Money](util.md#tech.figure.util.v1beta1.Money) |  | If escrow flag is set, the amount withheld from the loan payment for this item |
| tax_type | [string](#string) |  | Type of tax, e.g. COMBINED, CITY, COUNTY, SCHOOL, MISC, BONDS, HOA_DUES, MOBILE_HOME, GROUND_RENT, OTHER, DELINQUENT |
| withdraw_policy | [EscrowWithdrawPolicy](#tech.figure.loan.v1beta1.EscrowWithdrawPolicy) |  | Tax escrow payment policy |
| tax_year | [string](#string) |  | Initial tax year |
| contact_info | [tech.figure.util.v1beta1.ContactInformation](util.md#tech.figure.util.v1beta1.ContactInformation) |  | Tax authority contact information |













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
