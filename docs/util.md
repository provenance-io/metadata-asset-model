# Util
<a name="top"></a>



<a name="tech/figure/util/v1beta1/document.proto"></a>
<p align="right"><a href="#top">Top</a></p>

## tech/figure/util/v1beta1/document.proto



<a name="tech.figure.util.DigitalSignature"></a>

### DigitalSignature
A cryptographic digital signature of a document/file




| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| signature | [string](#string) |  |  |
| time_received_from_third_party | [google.protobuf.Timestamp](#google.protobuf.Timestamp) |  | Date/time document was received |
| vendor_name | [string](#string) |  | Name of the vendor/firm providing this digitally-signed data |




*Field Validation Rules:*

| Field | Validations |
| ----- | ----------- |
| signature | string.min_len: 1  |
| vendor_name | string.min_len: 1  |








<a name="tech.figure.util.DocumentMetadata"></a>

### DocumentMetadata
Information about a file or document. `DocumentMetadata` includes only describing information about the file; the
actual contents of the file are stored elsewhere (described by the `uri`).




| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| id | [tech.figure.util.UUID](util#tech.figure.util.UUID) |  | Identifier unique to this document |
| uri | [string](#string) |  | URI Location where document is hosted/located |
| file_name | [string](#string) |  | Name of the original file, including file extension |
| content_type | [string](#string) |  | Where possible, use MIME type, such as `application/pdf` or `application/xml` |
| document_type | [string](#string) |  | Examples: HELOC_AGREEMENT, CREDIT_DISCLOSURE, PROOF_OF_RECORDED_DEED, TRANSFER_OF_SERVICING_RIGHTS, etc. |
| checksum | [tech.figure.util.Checksum](util#tech.figure.util.Checksum) |  | Hash or checksum of document bytes |




*Field Validation Rules:*

| Field | Validations |
| ----- | ----------- |
| id | message.required: true  |
| content_type | string.min_len: 1  |
| document_type | string.min_len: 1  |








<a name="tech.figure.util.ESigData"></a>

### ESigData





| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| person_id | [tech.figure.util.UUID](util#tech.figure.util.UUID) |  |  |
| notary | [tech.figure.util.Notary](util#tech.figure.util.Notary) |  |  |
| email | [string](#string) |  |  |
| ip | [string](#string) |  |  |
| user_agent | [string](#string) |  |  |
| two_factor_signing_token | [string](#string) |  |  |
| two_factor_timestamp | [google.protobuf.Timestamp](#google.protobuf.Timestamp) |  |  |
| signing_timestamp | [google.protobuf.Timestamp](#google.protobuf.Timestamp) |  |  |
| name | [tech.figure.util.Name](util#tech.figure.util.Name) |  |  |
| witnesses | [tech.figure.util.Witness](util#tech.figure.util.Witness) | repeated |  |




*Field Validation Rules:*

| Field | Validations |
| ----- | ----------- |
| person_id | message.required: true  |
| email | string.email: true  |








<a name="tech.figure.util.ESignedDoc"></a>

### ESignedDoc
Represents metadata about document electronically-signed by a person.

_Note: Actual document file/data is stored separately._




| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| signed_document | [tech.figure.util.DocumentMetadata](util#tech.figure.util.DocumentMetadata) |  | Fully-completed, electronically-signed document metadata |
| unsigned_document | [tech.figure.util.DocumentMetadata](util#tech.figure.util.DocumentMetadata) |  | Representation of document without signature. Usually a document template. |
| electronic_signature | [tech.figure.util.ElectronicSignature](util#tech.figure.util.ElectronicSignature) |  | Customer's electronic signature data |
| substitution_data | [tech.figure.util.ESignedDoc.SubstitutionDataEntry](util#tech.figure.util.ESignedDoc.SubstitutionDataEntry) | repeated | If `unsigned_document` is a template, this is the data inserted into the template. Key is the field name. |
| signed_doc_checksum | [tech.figure.util.Checksum](util#tech.figure.util.Checksum) |  | Checksum (hash) of the signed version of the document |







<a name="tech.figure.util.ESignedDoc.SubstitutionDataEntry"></a>

### ESignedDoc.SubstitutionDataEntry





| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| key | [string](#string) |  |  |
| value | [string](#string) |  |  |







<a name="tech.figure.util.ElectronicSignature"></a>

### ElectronicSignature
An Electronic Signature (e-signature) is a digital replacement for a handwritten signature.

_Note: this distinct from a cryptographic [DigitalSignature](util#digitalsignature)._




| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| checksum | [tech.figure.util.Checksum](util#tech.figure.util.Checksum) |  | Checksum (hash) of the e-signature |
| data | [tech.figure.util.ESigData](util#tech.figure.util.ESigData) | repeated |  |
| person_id | [tech.figure.util.UUID](util#tech.figure.util.UUID) |  | Id of the person who signed the document (e.g. a borrower Id) |
| asset_id | [tech.figure.util.UUID](util#tech.figure.util.UUID) |  | Id of the asset to which this document relates (e.g. a loan Id) |
| document_id | [tech.figure.util.UUID](util#tech.figure.util.UUID) |  | Id of the signed document |




*Field Validation Rules:*

| Field | Validations |
| ----- | ----------- |
| person_id | message.required: true  |
| asset_id | message.required: true  |
| document_id | message.required: true  |








<a name="tech.figure.util.Notary"></a>

### Notary
Detail of the notary (person) who witnessed the signing and their commission information.




| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| person_id | [tech.figure.util.UUID](util#tech.figure.util.UUID) |  |  |
| name | [tech.figure.util.Name](util#tech.figure.util.Name) |  |  |
| commission_id | [string](#string) |  |  |
| commission_expiration | [google.protobuf.Timestamp](#google.protobuf.Timestamp) |  |  |
| is_resident | [bool](#bool) |  |  |




*Field Validation Rules:*

| Field | Validations |
| ----- | ----------- |
| person_id | message.required: true  |








<a name="tech.figure.util.SignedData"></a>

### SignedData
Data or document digitally-signed by the source that created the data/document.




| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| meta | [tech.figure.util.DocumentMetadata](util#tech.figure.util.DocumentMetadata) |  | Information about the data/document |
| signature | [tech.figure.util.DigitalSignature](util#tech.figure.util.DigitalSignature) |  | Signature of vendor on this data/document |
| data | [google.protobuf.BytesValue](#google.protobuf.BytesValue) |  | Byte array of data/docum``ent contents |




*Field Validation Rules:*

| Field | Validations |
| ----- | ----------- |
| meta | message.required: true  |
| signature | message.required: true  |
| data | message.required: true  |








<a name="tech.figure.util.Witness"></a>

### Witness
A non-notary witness to the document signing




| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| person_id | [tech.figure.util.UUID](util#tech.figure.util.UUID) |  |  |
| name | [tech.figure.util.Name](util#tech.figure.util.Name) |  |  |




*Field Validation Rules:*

| Field | Validations |
| ----- | ----------- |
| person_id | message.required: true  |







 

 

 

 



<a name="tech/figure/util/v1beta1/account.proto"></a>
<p align="right"><a href="#top">Top</a></p>

## tech/figure/util/v1beta1/account.proto



<a name="tech.figure.util.ACH"></a>

### ACH
ACH is the most common method of transferring funds bank-to-bank.

The Automated Clearing House protocol has been in use since 1974.
> Watch out ACH, USDF comin' to gitcha!




| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| account_type | [tech.figure.util.ACH.AccountType](util#tech.figure.util.ACH.AccountType) |  | CHECKING, SAVINGS, or OTHER |
| owner_type | [string](#string) |  | If required for ACH, type of account, e.g. INDIVIDUAL or COMMERCIAL |




*Field Validation Rules:*

| Field | Validations |
| ----- | ----------- |
| account_type | enum.defined_only: true  |








<a name="tech.figure.util.Account"></a>

### Account
Represents an account where currency is held, either in a traditional financial institution or a Provenance Blockchain account.




| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| account_owner_id | [tech.figure.util.UUID](util#tech.figure.util.UUID) |  | UUID of the borrower/owner of the account (if loan, should match one of `Loan.borrowers` IDs) |
| financial | [tech.figure.util.FinancialAccount](util#tech.figure.util.FinancialAccount) |  | Traditional financial account, e.g. bank account |
| provenance | [tech.figure.util.ProvenanceAccount](util#tech.figure.util.ProvenanceAccount) |  | Provenance Blockchain account |




*Field Validation Rules:*

| Field | Validations |
| ----- | ----------- |
| account_owner_id | message.required: true  |








<a name="tech.figure.util.FinancialAccount"></a>

### FinancialAccount
Represents the location of an account at a financial institution, located by `account` and `routing` numbers




| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| id | [tech.figure.util.UUID](util#tech.figure.util.UUID) |  | Unique UUID identifier for this bank account |
| owner_name | [string](#string) |  | Name of the person who owns this account |
| financial_institution | [string](#string) |  | Name of bank or financial institution |
| account_number | [string](#string) |  | 4-17 digit Account number |
| routing_number | [string](#string) |  | 9-digit Routing number |
| movement | [tech.figure.util.MoneyMovement](util#tech.figure.util.MoneyMovement) | repeated | Instructions for moving money from this account |




*Field Validation Rules:*

| Field | Validations |
| ----- | ----------- |
| id | message.required: true  |
| account_number | string.min_len: 4 string.max_len: 17  |
| routing_number | string.len: 9  |








<a name="tech.figure.util.MoneyMovement"></a>

### MoneyMovement
Specification of how money may be sent to this account: ACH or wire transfer (domestic or international/SWIFT).




| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| ach | [tech.figure.util.ACH](util#tech.figure.util.ACH) |  | ACH |
| wire | [tech.figure.util.WIRE](util#tech.figure.util.WIRE) |  | Wire transfer, domestic or international/SWIFT |







<a name="tech.figure.util.ProvenanceAccount"></a>

### ProvenanceAccount
An account on the Provenance Blockchain, located at `address`.

See <a href="https://docs.provenance.io/blockchain/basics/accounts">Account Documentation</a> for details.




| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| address | [string](#string) |  | Provenance Blockchain address for this account. <a href="https://docs.provenance.io/blockchain/basics/accounts">See Documentation</a> |
| description | [string](#string) |  | Optional field to denote account use, name, or type (e.g. FUNDING, MARKER, or USER_ACCOUNT) |




*Field Validation Rules:*

| Field | Validations |
| ----- | ----------- |
| address | string.min_len: 41 string.pattern: ^(pb|tp)1.+  |








<a name="tech.figure.util.SWIFT"></a>

### SWIFT
Society for Worldwide Interbank Financial Telecommunications (SWIFT) is member-owned cooperative for sending
international wire transfers.




| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| swift_id | [string](#string) |  | SWIFT bank account Id |
| swift_bank_address | [tech.figure.util.Address](util#tech.figure.util.Address) |  | SWIFT bank mailing address |




*Field Validation Rules:*

| Field | Validations |
| ----- | ----------- |
| swift_id | string.min_len: 1  |
| swift_bank_address | message.required: true  |








<a name="tech.figure.util.WIRE"></a>

### WIRE
A wire transfer is an electronic transfer of funds via a network that is administered by banks and transfer service agencies worldwide.




| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| account_address | [tech.figure.util.Address](util#tech.figure.util.Address) |  | Account owner mailing address |
| wire_instructions | [string](#string) |  | Wire-specific instructions |
| swift_instructions | [tech.figure.util.SWIFT](util#tech.figure.util.SWIFT) |  | If international wire, include Swift instructions |




*Field Validation Rules:*

| Field | Validations |
| ----- | ----------- |
| account_address | message.required: true  |
| wire_instructions | string.min_len: 1  |







 


<a name="tech.figure.util.ACH.AccountType"></a>

### ACH.AccountType


| Name | Number | Description |
| ---- | ------ | ----------- |
| ACCOUNT_TYPE_UNKNOWN | 0 |  |
| CHECKING | 1 |  |
| SAVINGS | 2 |  |
| OTHER | 3 |  |


 

 

 



<a name="tech/figure/util/v1beta1/education.proto"></a>
<p align="right"><a href="#top">Top</a></p>

## tech/figure/util/v1beta1/education.proto



<a name="tech.figure.util.Education"></a>

### Education
Describes a educational course of study toward a degree




| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| school_name | [string](#string) |  | Name of educational institution |
| school_code | [string](#string) |  | School code |
| degree_level | [tech.figure.util.Education.DegreeLevel](util#tech.figure.util.Education.DegreeLevel) |  | Degree obtained or working toward |
| start_date | [tech.figure.util.Date](util#tech.figure.util.Date) |  | Date program of study started |
| end_date | [tech.figure.util.Date](util#tech.figure.util.Date) |  | Date program of study ended or planned to end |
| is_degree_complete | [bool](#bool) |  | True if course of study was completed and degree awarded |
| graduation_month | [string](#string) |  | Month of graduation |
| graduation_year | [string](#string) |  | Year of graduation |






 


<a name="tech.figure.util.Education.DegreeLevel"></a>

### Education.DegreeLevel


| Name | Number | Description |
| ---- | ------ | ----------- |
| UNKNOWN_DEGREE_LEVEL | 0 |  |
| BA | 1 |  |
| BS | 2 |  |
| MA | 3 |  |
| MS | 4 |  |
| MBA | 5 |  |
| JD | 6 |  |
| PHD | 7 |  |
| MD | 8 |  |
| DO | 9 |  |
| DDS_DMD | 10 |  |
| DVM_VMD | 11 |  |
| PHARMD | 12 |  |
| DPT | 13 |  |
| DNP | 14 |  |
| OD | 15 |  |


 

 

 



<a name="tech/figure/util/v1beta1/types.proto"></a>
<p align="right"><a href="#top">Top</a></p>

## tech/figure/util/v1beta1/types.proto



<a name="tech.figure.util.Checksum"></a>

### Checksum
A Checksum (hash) produced by a checksum `algorithm`.




| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| checksum | [string](#string) |  | The hash of the data |
| algorithm | [string](#string) |  | Checksum algorithm, e.g. MD5, SHA256 |




*Field Validation Rules:*

| Field | Validations |
| ----- | ----------- |
| checksum | string.min_len: 1  |
| algorithm | string.min_len: 1  |








<a name="tech.figure.util.Date"></a>

### Date
An ISO8601 Date.

In Java: Use `LocalDate` to represent this value.  (For `OffsetDateTime`, use a protobuf `Timestamp`.)




| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| value | [string](#string) |  | Date stored as ISO8601 string in the form "yyyy-MM-dd". EX: "2020-05-22" |




*Field Validation Rules:*

| Field | Validations |
| ----- | ----------- |
| value | string.pattern: \d{4}-\d{2}-\d{2}  |








<a name="tech.figure.util.Money"></a>

### Money
A monetary `amount` in specified `currency`.

In Java: Use <a href="https://www.joda.org/joda-money/">Joda Money</a>, `BigMoney`, or `BigDecimal` to represent this value.




| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| amount | [double](#double) |  | Positive amounts only |
| currency | [string](#string) |  | ISO 4217 3-digit currency code |




*Field Validation Rules:*

| Field | Validations |
| ----- | ----------- |
| amount | double.gte: 0  |
| currency | string.len: 3  |








<a name="tech.figure.util.Rate"></a>

### Rate
Rate/percentage expressed as a decimal between 0 and 1.0, where 1.0 represents 100%.

In Java: Use `BigDecimal` to represent this value.




| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| value | [double](#double) |  | Value in the range [0,1], inclusive |




*Field Validation Rules:*

| Field | Validations |
| ----- | ----------- |
| value | double.lte: 1 double.gte: 0  |








<a name="tech.figure.util.Status"></a>

### Status
A Status indicator along with the timestamp it became effective




| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| status | [string](#string) |  | Status string is context-dependant |
| effective_time | [google.protobuf.Timestamp](#google.protobuf.Timestamp) |  | Date/time this status was establish |




*Field Validation Rules:*

| Field | Validations |
| ----- | ----------- |
| status | string.min_len: 1  |








<a name="tech.figure.util.UUID"></a>

### UUID
Universally Unique Identifier.

As defined by ISO/IEC 11578:1996 and in ITU-T Rec. X.667 | ISO/IEC 9834-8:2005.




| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| value | [string](#string) |  |  |




*Field Validation Rules:*

| Field | Validations |
| ----- | ----------- |
| value | string.uuid: true  |







 

 

 

 



<a name="tech/figure/util/v1beta1/options.proto"></a>
<p align="right"><a href="#top">Top</a></p>

## tech/figure/util/v1beta1/options.proto


 

 


<a name="tech/figure/util/v1beta1/options.proto-extensions"></a>

### File-level Extensions
| Extension | Type | Base | Number | Description |
| --------- | ---- | ---- | ------ | ----------- |
| description | string | .google.protobuf.EnumValueOptions | 50003 |  |
| pii | bool | .google.protobuf.FieldOptions | 65536 | True if this field value contains Personally Identifying Information (PII). |

 

 



<a name="tech/figure/util/v1beta1/fee.proto"></a>
<p align="right"><a href="#top">Top</a></p>

## tech/figure/util/v1beta1/fee.proto



<a name="tech.figure.util.Fee"></a>

### Fee
Describes a fee charged, typically as part of a loan origination




| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| rate | [tech.figure.util.Rate](util#tech.figure.util.Rate) |  | If a loan, describes the fee amount as a percentage of loan principal amount |
| amount | [tech.figure.util.Money](util#tech.figure.util.Money) |  | Value amount of origination fee charged |
| type | [string](#string) |  | Fee type, e.g. ORIGINATION_FEE |
| subtype | [string](#string) |  | Subtype allows variation of fee type. For an origination fee, subtype might be: NO_FEE, CAPITALIZED, UNCAPITALIZED_SPREAD, UNCAPITALIZED_UPFRONT |




*Field Validation Rules:*

| Field | Validations |
| ----- | ----------- |
| amount | message.required: true  |
| type | string.min_len: 1  |







 

 

 

 



<a name="tech/figure/util/v1beta1/address.proto"></a>
<p align="right"><a href="#top">Top</a></p>

## tech/figure/util/v1beta1/address.proto



<a name="tech.figure.util.Address"></a>

### Address
Physical or mailing address




| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| street | [string](#string) |  | Street address including number, e.g. "123 Main St." |
| street2 | [string](#string) |  | Used for multi-line street addresses |
| street3 | [string](#string) |  | Used for multi-line street addresses |
| city | [string](#string) |  | City |
| state | [string](#string) |  | State or Province |
| country | [string](#string) |  | Country/nation |
| zip | [string](#string) |  | Zip Code |
| unit_number | [string](#string) |  | Unit number, e.g. apartment number ("Apt #3B") |
| address_type | [string](#string) |  | e.g. HOME, MAILING, SECONDARY, INVESTMENT |
| ownership_type | [string](#string) |  | Property ownership type: e.g. SOLE, JOINT, TRUST, LLC, etc |







<a name="tech.figure.util.ContactInformation"></a>

### ContactInformation
Contact information for a third-party




| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| name | [string](#string) |  | Name of contract person or company |
| phone | [tech.figure.util.PhoneNumber](util#tech.figure.util.PhoneNumber) |  | Phone number |
| email | [string](#string) |  | RFC 1034-compliant email address |
| address | [tech.figure.util.Address](util#tech.figure.util.Address) |  | Mailing address |
| fax | [string](#string) |  | Fax number |
| url | [string](#string) |  | A web url relating to the contact - their homepage, their company's homepage, etc |




*Field Validation Rules:*

| Field | Validations |
| ----- | ----------- |
| email | string.email: true  |
| url | string.uri: true  |








<a name="tech.figure.util.PhoneNumber"></a>

### PhoneNumber
Telephone number




| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| number | [string](#string) |  | Phone number |
| number_type | [string](#string) |  | e.g. HOME, MOBILE, WORK |






 

 

 

 



<a name="tech/figure/util/v1beta1/property.proto"></a>
<p align="right"><a href="#top">Top</a></p>

## tech/figure/util/v1beta1/property.proto



<a name="tech.figure.util.Appraisal"></a>

### Appraisal





| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| appraisal_amount | [tech.figure.util.Money](util#tech.figure.util.Money) |  |  |
| appraisal_date | [tech.figure.util.Date](util#tech.figure.util.Date) |  |  |







<a name="tech.figure.util.Property"></a>

### Property





| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| address | [tech.figure.util.Address](util#tech.figure.util.Address) |  |  |
| location | [tech.figure.util.PropertyLocation](util#tech.figure.util.PropertyLocation) |  |  |
| site | [tech.figure.util.PropertySite](util#tech.figure.util.PropertySite) |  |  |
| ownership | [tech.figure.util.PropertyOwnership](util#tech.figure.util.PropertyOwnership) |  |  |
| type | [string](#string) |  |  |
| usage | [string](#string) |  |  |
| occupancy_type | [string](#string) |  |  |
| appraisal | [tech.figure.util.Appraisal](util#tech.figure.util.Appraisal) |  |  |
| number_of_units | [int32](#int32) |  |  |







<a name="tech.figure.util.PropertyLocation"></a>

### PropertyLocation





| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| latitude | [google.protobuf.DoubleValue](#google.protobuf.DoubleValue) |  |  |
| longitude | [google.protobuf.DoubleValue](#google.protobuf.DoubleValue) |  |  |
| fips_code | [string](#string) |  |  |
| parcel_number | [string](#string) |  |  |
| parcel_sequence | [string](#string) |  |  |
| carrier_route | [string](#string) |  |  |
| alternate_apn | [string](#string) |  |  |
| apn | [string](#string) |  |  |
| census_block | [string](#string) |  |  |
| county | [string](#string) |  |  |
| legal_block | [string](#string) |  |  |
| legal_description | [string](#string) |  |  |
| legal_lot | [string](#string) |  |  |
| legal_book_page | [string](#string) |  |  |
| neighborhood_code | [string](#string) |  |  |
| state | [string](#string) |  |  |
| school_district | [string](#string) |  |  |
| subdivision | [string](#string) |  |  |
| township | [string](#string) |  |  |
| township_range_section | [string](#string) |  |  |
| census_tract | [string](#string) |  |  |
| tract_number | [string](#string) |  |  |
| map_reference1 | [string](#string) |  |  |
| map_reference2 | [string](#string) |  |  |
| census_block_group | [string](#string) |  |  |
| municipality_name | [string](#string) |  |  |
| neighborhood | [string](#string) |  |  |
| school_district_name | [string](#string) |  |  |
| market_area | [string](#string) |  |  |







<a name="tech.figure.util.PropertyOwnership"></a>

### PropertyOwnership





| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| owner_names | [string](#string) | repeated |  |
| vesting_owners | [string](#string) | repeated |  |
| address | [tech.figure.util.Address](util#tech.figure.util.Address) |  |  |
| phone_number | [string](#string) |  |  |
| vesting_etal | [string](#string) |  |  |
| vesting_ownership_right | [string](#string) |  |  |
| owner_occupied_ind | [string](#string) |  |  |
| pending_record_ind | [string](#string) |  |  |
| corporate_owner | [string](#string) |  |  |
| owner_vesting_code | [string](#string) |  |  |







<a name="tech.figure.util.PropertySite"></a>

### PropertySite





| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| acres | [int32](#int32) |  |  |
| building_class | [string](#string) |  |  |
| building_depth | [string](#string) |  |  |
| building_width | [string](#string) |  |  |
| commercial_units | [string](#string) |  |  |
| county_use_code | [string](#string) |  |  |
| land_use | [string](#string) |  |  |
| lot_square_feet | [int32](#int32) |  |  |
| lot_depth | [string](#string) |  |  |
| lot_shape | [string](#string) |  |  |
| lot_width | [string](#string) |  |  |
| number_of_buildings | [int32](#int32) |  |  |
| residential_units | [int32](#int32) |  |  |
| sewer_type | [string](#string) |  |  |
| site_influence | [string](#string) |  |  |
| state_land_use | [string](#string) |  |  |
| topography | [string](#string) |  |  |
| usable_lot_area | [string](#string) |  |  |
| water | [string](#string) |  |  |
| water_district | [string](#string) |  |  |
| zoning | [string](#string) |  |  |
| state_land_use_code | [string](#string) |  |  |
| zoning_code | [string](#string) |  |  |
| county_use | [string](#string) |  |  |






 

 

 

 



<a name="tech/figure/util/v1beta1/income.proto"></a>
<p align="right"><a href="#top">Top</a></p>

## tech/figure/util/v1beta1/income.proto



<a name="tech.figure.util.AssetAccount"></a>

### AssetAccount





| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| account_name | [string](#string) |  | Account name |
| account_mask | [string](#string) |  | Masked account number |
| type | [string](#string) |  | Examples: 401K, IRA, ROTH_401K, ROTH, STOCK_PLAN, CD, CHECKING, SAVINGS, MONEY_MARKET, KEOGH, MUTUAL FUND |
| historical_balances | [tech.figure.util.Balance](util#tech.figure.util.Balance) | repeated | Account balances over time |
| current_balance_amount | [tech.figure.util.Money](util#tech.figure.util.Money) |  | Current account balance |




*Field Validation Rules:*

| Field | Validations |
| ----- | ----------- |
| type | string.min_len: 1  |








<a name="tech.figure.util.AssetDepletion"></a>

### AssetDepletion





| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| retirement | [tech.figure.util.Money](util#tech.figure.util.Money) |  |  |
| savings | [tech.figure.util.Money](util#tech.figure.util.Money) |  |  |
| investment | [tech.figure.util.Money](util#tech.figure.util.Money) |  |  |







<a name="tech.figure.util.AssetIncomeSource"></a>

### AssetIncomeSource





| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| vendor | [string](#string) |  | Firm holding this asset account(s) |
| asset_accounts | [tech.figure.util.AssetAccount](util#tech.figure.util.AssetAccount) | repeated | One or more accounts held at this vendor |
| transaction_history_length_days | [int32](#int32) |  | Number of days of transaction history used in verification |




*Field Validation Rules:*

| Field | Validations |
| ----- | ----------- |
| vendor | string.min_len: 1  |
| asset_accounts | repeated.min_items: 1  |








<a name="tech.figure.util.Balance"></a>

### Balance





| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| current | [tech.figure.util.Money](util#tech.figure.util.Money) |  | Account balance, current as of `date` |
| date | [tech.figure.util.Date](util#tech.figure.util.Date) |  | Date on which this balance was accurate |




*Field Validation Rules:*

| Field | Validations |
| ----- | ----------- |
| current | message.required: true  |
| date | message.required: true  |








<a name="tech.figure.util.Income"></a>

### Income





| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| borrower_id | [tech.figure.util.UUID](util#tech.figure.util.UUID) |  | Id of borrower for whom these records pertain |
| stated_yearly_income | [tech.figure.util.Money](util#tech.figure.util.Money) |  | Annual income as stated by the individual |
| verified_yearly_income | [tech.figure.util.VerifiedIncome](util#tech.figure.util.VerifiedIncome) |  | Annual income as verified through some process |
| income_sources | [tech.figure.util.IncomeSource](util#tech.figure.util.IncomeSource) | repeated | Borrower's sources of income (other than asset accounts) |
| asset_income_sources | [tech.figure.util.AssetIncomeSource](util#tech.figure.util.AssetIncomeSource) | repeated | Income from asset accounts (e.g. investments, retirement, etc.) |




*Field Validation Rules:*

| Field | Validations |
| ----- | ----------- |
| borrower_id | message.required: true  |








<a name="tech.figure.util.IncomeSource"></a>

### IncomeSource





| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| source | [string](#string) |  | Name of employer or source of income (e.g. self-employed, child support, alimony) |
| yearly_amount | [tech.figure.util.Money](util#tech.figure.util.Money) |  | Yearly income amount form this income source |
| employment_status | [tech.figure.util.Status](util#tech.figure.util.Status) |  | E.g. FULL_TIME, PART_TIME, STUDENT, UNEMPLOYED |
| verification_timestamp | [google.protobuf.Timestamp](#google.protobuf.Timestamp) |  | Date/time at which employment was verified |




*Field Validation Rules:*

| Field | Validations |
| ----- | ----------- |
| source | string.min_len: 1  |
| yearly_amount | message.required: true  |
| verification_timestamp | timestamp.required: true  |








<a name="tech.figure.util.VerifiedIncome"></a>

### VerifiedIncome





| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| yearly_amount | [tech.figure.util.Money](util#tech.figure.util.Money) |  | Total borrower income, which has been verified through records |
| date_verified | [tech.figure.util.Date](util#tech.figure.util.Date) |  | Date this income amount was verified |
| verified_asset_depletion | [tech.figure.util.AssetDepletion](util#tech.figure.util.AssetDepletion) |  | Retirement/savings/investment account balances |
| months_of_history | [int32](#int32) |  | Months of income that has been verified through records |
| verification_methods_used | [string](#string) | repeated | Examples: PLAID_TRANSACTIONS, POINTSERV_PAYROLL, POINTSERV_TAX, EMPINFO, POINTSERV_W2, POINTSERV_1099_MISC, MANUAL_PAYSTUB, TRUEWORK_INSTANT, OTHER |




*Field Validation Rules:*

| Field | Validations |
| ----- | ----------- |
| yearly_amount | message.required: true  |
| date_verified | message.required: true  |
| verification_methods_used | repeated.min_items: 1  |







 

 

 

 



<a name="tech/figure/util/v1beta1/person.proto"></a>
<p align="right"><a href="#top">Top</a></p>

## tech/figure/util/v1beta1/person.proto



<a name="tech.figure.util.Borrowers"></a>

### Borrowers
The primary borrower of a loan, plus any co-borrowers or co-signers.

See `Person.party_type` to distinguish between borrower roles (e.g. primary vs co-borrower vs co-signer).




| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| primary | [tech.figure.util.Person](util#tech.figure.util.Person) |  | The main individual responsible for the loan |
| additional | [tech.figure.util.Person](util#tech.figure.util.Person) | repeated | Co-borrowers and co-signers, if any |







<a name="tech.figure.util.Name"></a>

### Name
Name of a person.




| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| first_name | [string](#string) |  | Given name |
| last_name | [string](#string) |  | Family name |
| middle_name | [string](#string) |  |  |
| prefix | [string](#string) |  |  |
| suffix | [string](#string) |  |  |




*Field Validation Rules:*

| Field | Validations |
| ----- | ----------- |
| first_name | string.len: 1  |
| last_name | string.len: 1  |








<a name="tech.figure.util.Person"></a>

### Person
Identifying information for a person.




| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| id | [tech.figure.util.UUID](util#tech.figure.util.UUID) |  | Id for this person; may be referenced elsewhere in the model to link data to this individual |
| party_type | [string](#string) |  | PRIMARY_BORROWER, COBORROWER, COSIGNER, etc. |
| name | [tech.figure.util.Name](util#tech.figure.util.Name) |  | Name |
| formerly_known_as | [tech.figure.util.Name](util#tech.figure.util.Name) | repeated | Populated if person has previous names or aliases (e.g. maiden name) |
| dob | [tech.figure.util.Date](util#tech.figure.util.Date) |  | Date of birth |
| phone_numbers | [tech.figure.util.PhoneNumber](util#tech.figure.util.PhoneNumber) | repeated | Contact phone numbers |
| addresses | [tech.figure.util.Address](util#tech.figure.util.Address) | repeated | E.g. residential address or mailing address |
| ssn | [string](#string) |  | Social Security Number |
| email | [string](#string) |  | Email address |
| citizenship | [string](#string) |  | Country of citizenship |
| marital_status | [tech.figure.util.MaritalStatus](util#tech.figure.util.MaritalStatus) |  | Marital status |
| is_self_employed | [bool](#bool) |  | True if individual is self-employed |




*Field Validation Rules:*

| Field | Validations |
| ----- | ----------- |
| id | message.required: true  |
| name | message.required: true  |
| email | string.email: true  |







 


<a name="tech.figure.util.MaritalStatus"></a>

### MaritalStatus


| Name | Number | Description |
| ---- | ------ | ----------- |
| MARITAL_STATUS_UNKNOWN | 0 |  |
| SINGLE | 1 |  |
| MARRIED | 2 |  |
| SEPARATED | 3 |  |
| CIVIL_UNION | 4 |  |
| DOMESTIC_PARTNERSHIP | 5 |  |
| OTHER | 6 |  |


 

 

 



<a name="tech/figure/util/v1beta1/credit_report.proto"></a>
<p align="right"><a href="#top">Top</a></p>

## tech/figure/util/v1beta1/credit_report.proto



<a name="tech.figure.util.CreditReport"></a>

### CreditReport
Detailed breakdown of an individual's credit history prepared by a credit bureau.




| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| id | [tech.figure.util.UUID](util#tech.figure.util.UUID) |  | Id of this report |
| borrower_id | [tech.figure.util.UUID](util#tech.figure.util.UUID) |  | Id of the borrower for whom this report was generated |
| report_timestamp | [google.protobuf.Timestamp](#google.protobuf.Timestamp) |  | Date and time this report was generated |
| credit_provider | [string](#string) |  | Name of credit report provider: EXPERIAN, TRANS_UNION, EQUIFAX |
| pull_type | [tech.figure.util.CreditReport.CreditPullType](util#tech.figure.util.CreditReport.CreditPullType) |  | SOFT or HARD credit pull |
| credit_score | [uint32](#uint32) |  | Credit Score |
| attributes | [tech.figure.util.CreditReport.AttributesEntry](util#tech.figure.util.CreditReport.AttributesEntry) | repeated | Attributes |
| risk_models | [tech.figure.util.RiskModel](util#tech.figure.util.RiskModel) | repeated | Type of risk model used |
| expiration | [google.protobuf.Timestamp](#google.protobuf.Timestamp) |  | Date credit report is valid until |




*Field Validation Rules:*

| Field | Validations |
| ----- | ----------- |
| id | message.required: true  |
| borrower_id | message.required: true  |








<a name="tech.figure.util.CreditReport.AttributesEntry"></a>

### CreditReport.AttributesEntry





| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| key | [string](#string) |  |  |
| value | [string](#string) |  |  |







<a name="tech.figure.util.RiskModel"></a>

### RiskModel
Detail of the risk model used in a credit report.




| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| score | [int32](#int32) |  |  |
| factors | [tech.figure.util.RiskModel.FactorsEntry](util#tech.figure.util.RiskModel.FactorsEntry) | repeated |  |
| risk_type | [tech.figure.util.RiskModel.RiskType](util#tech.figure.util.RiskModel.RiskType) |  |  |
| other_type | [tech.figure.util.RiskModel.OtherRiskType](util#tech.figure.util.RiskModel.OtherRiskType) |  |  |







<a name="tech.figure.util.RiskModel.FactorsEntry"></a>

### RiskModel.FactorsEntry





| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| key | [string](#string) |  |  |
| value | [string](#string) |  |  |







<a name="tech.figure.util.RiskModel.OtherRiskType"></a>

### RiskModel.OtherRiskType





| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| name | [string](#string) |  |  |
| description | [string](#string) |  |  |






 


<a name="tech.figure.util.CreditReport.CreditPullType"></a>

### CreditReport.CreditPullType


| Name | Number | Description |
| ---- | ------ | ----------- |
| UNKNOWN_CREDIT_PULL_TYPE | 0 |  |
| SOFT | 1 |  |
| HARD | 2 |  |



<a name="tech.figure.util.RiskModel.RiskType"></a>

### RiskModel.RiskType


| Name | Number | Description |
| ---- | ------ | ----------- |
| UNKNOWN | 0 |  |
| OTHER | 1 |  |
| VANTAGE3 | 2 |  |
| FICO9 | 3 |  |
| VANTAGE4 | 4 |  |


 

 

 



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
