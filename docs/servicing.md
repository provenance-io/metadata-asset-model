# Loan Servicing
<a name="top"></a>



<a name="tech/figure/servicing/v1beta1/ledger.proto"></a>
<p align="right"><a href="#top">Top</a></p>

## tech/figure/servicing/v1beta1/ledger.proto



<a name="tech.figure.servicing.v1beta1.Allocation"></a>

### Allocation
Used to track loan balances and how LedgerEntries break down into various types
"owed" may be thought of as debits
"applied" may be thought of as credits
Note: both owed and applied are typically positive, with the exception of reversals and adjustments
Exception: Credit owed to the borrower / held for the borrower is negative (ex: escrow balance)
Note: "bucket" is used interchangeably with Allocation and AllocationType


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| allocation_type | [string](#string) |  | AllocationType or String of another type |
| amount_owed | [tech.figure.util.v1beta1.Money](util#tech.figure.util.v1beta1.Money) |  | Balance owed - if LedgerEntry adjustment, alters owed amounts without appearing as if a payment were made (no change to applied) |
| amount_applied | [tech.figure.util.v1beta1.Money](util#tech.figure.util.v1beta1.Money) |  | Amount paid so far - if LedgerEntry, shows portion of entry applied to a type (or requested to be applied to a type) |





<a name="tech.figure.servicing.v1beta1.LedgerEntry"></a>

### LedgerEntry
LedgerEntry describes a single modification to a loan that change the balance (interest, principal, etc)
Ex: disbursement, payments, late fee, adjustment


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| entry_uuid | [tech.figure.util.v1beta1.UUID](util#tech.figure.util.v1beta1.UUID) |  | Ledger entry UUID |
| ledger_entry_type | [string](#string) |  | See LedgerEntryType |
| amount | [tech.figure.util.v1beta1.Money](util#tech.figure.util.v1beta1.Money) |  | derived amount based upon the AllocationType amount provider |
| breakdown | [LedgerEntryBreakdown](#tech.figure.servicing.v1beta1.LedgerEntryBreakdown) |  | Per-asset allocations of ledger entry (entry need not apply to every asset) |
| effective_breakdown | [LedgerEntryBreakdown](#tech.figure.servicing.v1beta1.LedgerEntryBreakdown) |  | "Final" breakdown as applied to the asset. Contains any adjustments caused by linked entries (ex: BACKDATED_ENTRY_ADJUSTMENT, REVERSAL_ADJUSTMENT) |
| effective_time | [google.protobuf.Timestamp](#google.protobuf.Timestamp) |  | Time ledger entry applied to loan |
| posted_time | [google.protobuf.Timestamp](#google.protobuf.Timestamp) |  | Time ledger entry created |
| reversed_time | [google.protobuf.Timestamp](#google.protobuf.Timestamp) |  | Time ledger entry reversed (if applicable) |
| reversed_reason | [string](#string) |  | Reversal reason (if applicable) |





<a name="tech.figure.servicing.v1beta1.LedgerEntryBreakdown"></a>

### LedgerEntryBreakdown
Breakdowns contain exactly how the entry applied to the loan (or family of loans)


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| before | [Allocation](#tech.figure.servicing.v1beta1.Allocation) | repeated | asset totals before the entry was applied (ex: $50000 principal, $50 interest) |
| applied | [Allocation](#tech.figure.servicing.v1beta1.Allocation) | repeated | asset totals after the entry was applied (ex: $49900 principal, $0 interest) |
| after | [Allocation](#tech.figure.servicing.v1beta1.Allocation) | repeated | breakdown of how the entry affected loan (ex: $100 to principal and $50 to interest) |







<a name="tech.figure.servicing.v1beta1.Allocation.AllocationType"></a>

### Allocation.AllocationType
Types of allocations

| Name | Number | Description |
| ---- | ------ | ----------- |
| UNKNOWN | 0 |  |
| FEE | 1 | Generic fee |
| FLOOD | 2 | Flood |
| HAZARD | 3 | Hazard |
| INTEREST | 4 | Interest |
| CAP_ORIGINATION_FEE | 5 | Cap origination fee |
| NON_CAP_ORIGINATION_FEE | 6 | Non cap origination fee |
| PRINCIPAL | 7 | Principal |
| PRINCIPAL_OVERPAY | 8 | Principal paid in excess of monthly required amount (reporting type, does not impact loan balance) |
| CREDIT | 9 | Credit owed to the borrower |
| RECORDING_FEE | 10 | Recording fee |
| MORTGAGE_INSURANCE | 11 | Mortgage insurance |
| HOMEOWNER_TAXES | 12 | Homeowner taxes |
| HOA_FEE | 13 | HOA fee |
| LATE_FEE | 14 | Late fees |
| INTEREST_PREPAYMENT | 15 | Interest prepayment |
| ESCROW | 16 | Escrow |
| NSF_FEE | 17 | Non-sufficient funds fee |
| DEFERRED_INTEREST | 18 | Use Deferred interest |
| INVESTOR_RECOVERABLE_FEES | 19 | Investor recoverable fees |
| DEFERRED_PRINCIPAL | 20 | Deferred principal |
| BORROWER_RECOVERABLE_FEES | 21 | Unused - for future use |
| RESTRICTED_ESCROW | 22 | Borrower recoverable fees |
| SUSPENSE | 23 | Partial payments that cannot be applied to the balance of the loan are held in suspense |
| LENDER_PLACED_FLOOD_INSURANCE | 24 | Force-placed flood insurance |
| LENDER_PLACED_HAZARD_INSURANCE | 25 | Force-placed hazard insurance |
| SUBORDINATION_FEE | 26 | Subordination fee |
| SERVICER_ADVANCE_PROPERTY_TAX_REPAYMENT | 27 | Servicer advance property tax repayment |
| SERVICER_ADVANCE_HOA_REPAYMENT | 28 | Servicer advance property HOA repayment |
| SERVICER_ADVANCE_PROPERTY_PRESERVATION | 29 | Servicer advance property preservation |
| SERVICER_ADVANCE_DELINQUENCY_EXPENSE | 30 | Servicer advance delinquency expense |
| SERVICER_ADVANCE_LEGAL_EXPENSE | 31 | Servicer advance legal expense |


<a name="tech.figure.servicing.v1beta1.LedgerEntryType"></a>

### LedgerEntryType
Types of ledger entries

| Name | Number | Description |
| ---- | ------ | ----------- |
| UNKNOWN | 0 |  |
| MONTHLY_PAYMENT | 1 | Payment, typically automatic ACH withdraw |
| PRINCIPAL_REPAYMENT | 2 | Principal repayment |
| PRINCIPAL_PREPAYMENT | 3 | Principal prepayment |
| DRAW_DOWN | 4 | HELOC / MERLOC only - additional disbursement (child loans only) |
| WRITE_OFF | 5 | Write-off |
| DISBURSEMENT | 6 | Initial disbursement of funds |
| REVERSAL | 7 | Reversal of another LedgerEntry |
| FEE | 8 | Generic fee |
| CREDIT | 9 | Non-payment funds applied to loan |
| ORIGINATION_FEE | 10 | Capitalized origination fee (other origination fee types currently unsupported) |
| MANUAL_PAYMENT | 11 | Payment, typically borrower-requested or CSP entered |
| LATE_FEE | 12 | Late fee |
| ADJUSTMENT | 13 | Basic adjustment type |
| REFUND | 14 | Refund (ex: Payoff's unapplied amount after covering loan balance and release fee) |
| TRANSFER | 15 | Transfer. |
| PAY_AHEAD_PAYMENT | 16 | Pay ahead payment |
| HOMEOWNERS_INSURANCE_PREMIUM | 17 | Homeowners insurance premium |
| COUNTY_TAXES | 18 | County taxes |
| FLOOD_INSURANCE_PREMIUM | 19 | Flood insurance premium |
| MORTGAGE_INSURANCE | 20 | Additional premium charged when mortgage loans are taken out by the borrower. |
| ESCROW_PAYMENT | 21 | Escrow Payment |
| INTEREST_PREPAYMENT | 22 | Interest prepayment |
| NSF_FEE | 23 | Non-sufficient funds fee, charged after payment reversal |
| ADJUSTMENT_FOR_RATE_CHANGE | 24 | Back-dated rate changes may result in an adjustment to other LedgerEntries |
| DEFERRED_INTEREST | 25 | Deferred interest (ex: forbearance) |
| INVESTOR_RECOVERABLE_FEES_DISBURSEMENT | 26 | Unused - for future use |
| INVESTOR_RECOVERABLE_FEES_PAYMENT | 27 | Unused - for future use |
| INSURANCE_DISBURSEMENT | 28 | Escrow disbursement for insurance premium |
| ESCROW_DISBURSEMENT | 29 | Escrow disbursement |
| ESCROW_REFUND | 30 | Escrow refund |
| FORECLOSURE | 31 | Foreclosure |
| INSURANCE_REFUND | 32 | Insurance refund |
| INTEREST_ONLY_PAYMENT | 33 | Interest only payment |
| DEFERRED_PRINCIPAL | 34 | Deferred principal |
| LATE_FEE_PAYMENT | 35 | Late fee payment |
| LATE_FEE_WAIVE | 36 | Late fee waive |
| BORROWER_RECOVERABLE_FEE_PAYMENT | 37 | Borrower recoverable fee payment |
| BORROWER_RECOVERABLE_FEES_DISBURSEMENT | 38 | Borrower recoverable fees disbursement |
| BORROWER_NONRECOVERABLE_FEE_PAYMENT | 39 | Borrower nonrecoverable fee payment |
| NSF_FEE_PAYMENT | 40 | NSF fee payment |
| NSF_FEE_WAIVE | 41 | NSF fee waive |
| PAYOFF | 42 | Manually entered payment to cover loan balance |
| UNAPPLIED_PAYMENT | 43 | Unapplied payment |
| TAX_DISBURSEMENT | 44 | Escrow disbursement for taxes |
| TAX_REFUND | 45 | Tax refund |
| UNAPPLIED_DISBURSEMENT | 46 | Unapplied disbursement |
| LOSS_DRAFT_DISBURSEMENT | 47 | Loss draft disbursement |
| LOSS_DRAFT_PAYMENT | 48 | Loss draft payment |
| RELEASE_FEE | 49 | completion fee / redemption administration fee / discharge fee |
| LENDER_PLACED_FLOOD_INSURANCE | 50 | force-placed flood insurance premium |
| LENDER_PLACED_HAZARD_INSURANCE | 51 | force-placed hazard insurance premium |
| FEE_ADJUSTMENT | 52 | Fee adjustment |
| INTEREST_ON_ESCROW | 53 | Interest on escrow |
| REVERSAL_ADJUSTMENT | 54 | Indicates values adjusted to level out the head state of a loan after bucket changes are required for a reversal |
| BACKDATED_ENTRY_ADJUSTMENT | 55 | Indicates values adjusted to level out the head state of a loan after an entry is inserted to be effective before other entries |
| ESCROW_PREPAYMENT | 56 | Starting escrow balance during loan onboarding (not the same as prepaid escrow that is paid to tax/insurance entities during closing, this value is the starting escrow balance for servicing) |
| VOIDED | 57 | Indicates LedgerEntry is void should be ignored (replaces previous type) |
| SUSPENDED_PAYMENT | 58 | Mortgage only - Payment created from partial payments held in suspense |
| SUBORDINATION_FEE | 59 | Refinance of a mortgage will pay a fee to keep HELOC in second lien position |
| SLS_TRANSACTION | 60 | A ledger entry that was created by SLS, a third party servicer. Ignored by calculator and is for reporting only. |
| RECALCULATION_ADJUSTMENT | 61 | Indicates that the loan totals been recalculated based on a specific change to the loan state, the details of which should be within the audit message |
| ESCROW_CLOSEOUT | 62 | Close out escrow for transferred mortgage |
| SERVICER_ADVANCE | 63 | Advances to borrower |
| REQUESTED_ALLOCATION_MANUAL_PAYMENT | 64 | These entries won't be subject to owed allocations and will take precedence to allow borrowers to make targeted manual payments |
| DEFERMENT | 65 | Defer allocations (can include prin/int or whatever else) |
| DEFERMENT_ADJUSTMENT | 66 | Adjustment that indicates a change in loan totals resulting from a deferment |
| EXTERNAL_PAYMENT | 67 | Payments received from third party |
| MONTHLY_PAYMENT_IN_KIND | 68 | Autopay using collateral currency |
| MANUAL_PAYMENT_IN_KIND | 69 | Manual payment using collateral currency |
| COLLATERAL_LIQUIDATION | 70 | Payment initiated by servicer to cover a margin call using existing collateral |
| COLLATERAL_SLIPPAGE | 71 | When we liquidate collateral, shifting BTC/ETH price may result in liquidating more collateral than requested - which needs to be applied to loan |








<a name="tech/figure/servicing/v1beta1/loan_state.proto"></a>
<p align="right"><a href="#top">Top</a></p>

## tech/figure/servicing/v1beta1/loan_state.proto



<a name="tech.figure.servicing.v1beta1.LoanState"></a>

### LoanState
Loan state data (servicing data) at a point in time


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| id | [tech.figure.util.v1beta1.UUID](util#tech.figure.util.v1beta1.UUID) |  | Loan State Identifier |
| effective_time | [google.protobuf.Timestamp](#google.protobuf.Timestamp) |  | Timestamp when this state was produced |
| servicer_name | [string](#string) |  | Name of servicer for loan |
| total_unpaid_prin_bal | [tech.figure.util.v1beta1.Money](util#tech.figure.util.v1beta1.Money) |  | Total unpaid principal balance |
| accrued_interest | [tech.figure.util.v1beta1.Money](util#tech.figure.util.v1beta1.Money) |  | Total interest accrued to-date |
| daily_int_amount | [tech.figure.util.v1beta1.Money](util#tech.figure.util.v1beta1.Money) |  | Accrued interest amount per day |
| loan_status | [tech.figure.util.v1beta1.Status](util#tech.figure.util.v1beta1.Status) |  | Loan status, such as: IN REPAY 1-9 DAYS DELQ 10-29 DAYS DELQ 30-59 DAYS DELQ 60-89 DAYS DELQ 90+ DAYS DELQ PAID_CLOSED PAID_OPEN FORBEARANCE TRANSFERRED |
| current_p_and_i | [tech.figure.util.v1beta1.Money](util#tech.figure.util.v1beta1.Money) |  | Currently monthly loan payment amount including principal and interest |
| current_interest_rate | [tech.figure.util.v1beta1.Rate](util#tech.figure.util.v1beta1.Rate) |  | The current interest rate used to calculate the principal and interest payment |
| int_accrual_start_date | [tech.figure.util.v1beta1.Date](util#tech.figure.util.v1beta1.Date) |  | The date on which interest accrual began |
| int_paid_through_date | [tech.figure.util.v1beta1.Date](util#tech.figure.util.v1beta1.Date) |  | The date through which interest is paid with the current payment. The effective date from which interest will be calculated for the application of the next payment. |
| maturity_date | [tech.figure.util.v1beta1.Date](util#tech.figure.util.v1beta1.Date) |  | The date when a loan reaches maturity |
| next_due_date | [tech.figure.util.v1beta1.Date](util#tech.figure.util.v1beta1.Date) |  | Date of next due payment for loan |
| days_delinquent | [int32](#int32) |  | Number of days delinquent |
| remaining_term_months | [int32](#int32) |  | Remaining loan term in months |
| servicing_fee_rate | [tech.figure.util.v1beta1.Rate](util#tech.figure.util.v1beta1.Rate) |  | The fee paid to the servicer, stated as a percentage of the outstanding loan balance |
| apr | [tech.figure.util.v1beta1.Rate](util#tech.figure.util.v1beta1.Rate) |  | Annualized Percentage Rate of the loan |
| autopay_flag | [bool](#bool) |  | If true, payments are automatically deducted from borrower's financial account |
| deferred_int_balance | [tech.figure.util.v1beta1.Money](util#tech.figure.util.v1beta1.Money) |  | Accumulated Interest balance since forbearance_begin_date |
| delinquent_date | [tech.figure.util.v1beta1.Date](util#tech.figure.util.v1beta1.Date) |  | If delinquent, date first payment was missed that made the loan currently delinquent |
| days_forbearance | [uint32](#uint32) |  | Days elapsed since entering Forbearance |
| forbearance_begin_date | [tech.figure.util.v1beta1.Date](util#tech.figure.util.v1beta1.Date) |  | Date forbearance period begins |
| forbearance_end_date | [tech.figure.util.v1beta1.Date](util#tech.figure.util.v1beta1.Date) |  | Date forbearance period ends |
| interest_paid | [tech.figure.util.v1beta1.Money](util#tech.figure.util.v1beta1.Money) |  | Total interest paid to-date |
| interest_rate_cap | [tech.figure.util.v1beta1.Rate](util#tech.figure.util.v1beta1.Rate) |  | Interest rate cap |
| last_payment_date | [tech.figure.util.v1beta1.Date](util#tech.figure.util.v1beta1.Date) |  | Last payment received date |
| monthly_payment_amount | [tech.figure.util.v1beta1.Money](util#tech.figure.util.v1beta1.Money) |  | The minimum monthly payment amount that borrower needs to repay on the loan |
| principal_paid | [tech.figure.util.v1beta1.Money](util#tech.figure.util.v1beta1.Money) |  | Sum of amount paid to principal |
| past_due_amount | [tech.figure.util.v1beta1.Money](util#tech.figure.util.v1beta1.Money) |  | Amount past due |
| deferred_principal | [tech.figure.util.v1beta1.Money](util#tech.figure.util.v1beta1.Money) |  | Deferred Principal balance |
| ledger_entry | [LedgerEntry](#tech.figure.servicing.v1beta1.LedgerEntry) | repeated | Ledger Entries effective on this date |
| kv | [LoanState.KvEntry](#tech.figure.servicing.v1beta1.LoanState.KvEntry) | repeated | Additional loan state data for a user or tool-defined extension of state. Key is field name. Value is any proto Message. For scalar values, use <a href="https://developers.google.com/protocol-buffers/docs/reference/google.protobuf">Protobuf Well-Known Types</a> |





<a name="tech.figure.servicing.v1beta1.LoanState.KvEntry"></a>

### LoanState.KvEntry



| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| key | [string](#string) |  |  |
| value | [google.protobuf.Any](#google.protobuf.Any) |  |  |





<a name="tech.figure.servicing.v1beta1.LoanStateMetadata"></a>

### LoanStateMetadata
Metadata associated with a single loan state object stored in the object store


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| id | [tech.figure.util.v1beta1.UUID](util#tech.figure.util.v1beta1.UUID) |  | Identifier unique to this loan state object |
| effective_time | [google.protobuf.Timestamp](#google.protobuf.Timestamp) |  | Timestamp when this state was produced |
| uri | [string](#string) |  | URI Location where document is hosted/located |
| checksum | [tech.figure.util.v1beta1.Checksum](util#tech.figure.util.v1beta1.Checksum) |  | Hash or checksum of document bytes |





<a name="tech.figure.servicing.v1beta1.LoanStateSubmission"></a>

### LoanStateSubmission
A submission of loan state metadata for a single loan


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| loan_state | [LoanStateMetadata](#tech.figure.servicing.v1beta1.LoanStateMetadata) | repeated | Loan state metadata for the loan |





<a name="tech.figure.servicing.v1beta1.LoanStructureState"></a>

### LoanStructureState
Aggregation of loan state across a structure (group) of loans


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| marker_address | [string](#string) |  | Provenance Blockchain Address for the Marker representing this loan structure |
| loan_states | [LoanState](#tech.figure.servicing.v1beta1.LoanState) | repeated | Loan state data for loans included in the structure |





<a name="tech.figure.servicing.v1beta1.ServicingData"></a>

### ServicingData
Aggregation of loan state for a single loan

A combination of static loan data that does not change throughout the life of the loan and a pointer to an object
in the object store that contains a list of loan states

Note: static data is possibly repeated elsewhere, but required here to make life easier for applications that require servicing data


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| loan_id | [tech.figure.util.v1beta1.UUID](util#tech.figure.util.v1beta1.UUID) |  | Loan Identifier |
| asset_type | [tech.figure.util.v1beta1.AssetType](util#tech.figure.util.v1beta1.AssetType) |  | Asset type (See docs/util.md) |
| current_borrower_info | [tech.figure.util.v1beta1.Borrowers](util#tech.figure.util.v1beta1.Borrowers) |  | Borrower(s), co-signers, etc |
| original_note_amount | [tech.figure.util.v1beta1.Money](util#tech.figure.util.v1beta1.Money) |  | Total unpaid principal balance when the note is signed |
| doc_meta | [tech.figure.util.v1beta1.DocumentMetadata](util#tech.figure.util.v1beta1.DocumentMetadata) | repeated | Metadata about documents related to the loan (document files stored separately) |
| loan_state | [LoanStateMetadata](#tech.figure.servicing.v1beta1.LoanStateMetadata) | repeated | List of pointers to the objects containing LoanState messages |













<a name="tech/figure/servicing/v1beta1/servicing_rights.proto"></a>
<p align="right"><a href="#top">Top</a></p>

## tech/figure/servicing/v1beta1/servicing_rights.proto



<a name="tech.figure.servicing.v1beta1.ServicingRights"></a>

### ServicingRights
Identity of the loan servicer and sub-servicer, if using


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| servicer_id | [tech.figure.util.v1beta1.UUID](util#tech.figure.util.v1beta1.UUID) |  | Primary servicer ID |
| servicer_name | [string](#string) |  | Primary servicer name |
| sub_servicer_id | [tech.figure.util.v1beta1.UUID](util#tech.figure.util.v1beta1.UUID) |  | Sub-servicer ID |
| sub_servicer_name | [string](#string) |  | Sub-servicer name |
| kv | [ServicingRights.KvEntry](#tech.figure.servicing.v1beta1.ServicingRights.KvEntry) | repeated | Any additional data related to servicing rights |





<a name="tech.figure.servicing.v1beta1.ServicingRights.KvEntry"></a>

### ServicingRights.KvEntry



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
