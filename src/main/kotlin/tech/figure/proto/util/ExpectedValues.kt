package tech.figure.proto.util

// alphabetical

enum class AddressExpectedType {
    HOME,
    INVESTMENT,
    MAILING,
    SECONDARY,
}

enum class ConsentExpectedType {
    ACH_AUTOPAY,    // APR/Autopay
    COMMUNICATIONS, // Electronic Communications Policy and Consent
    HARD_CREDIT,    // Hard Credit Pull
    MARKETING,      // Marketing Calls/Text (requires explicit opt in)
    PRIVACY,        // Privacy Policy
    SMS,            // SMS communication
    SOFT_CREDIT,    // Soft Credit Pull
    TERMS,          // Terms of Use
}

enum class DocumentExpectedType {
    ACCOUNTANT_LETTER,
    ADMINISTRATION,
    APPROVAL_LETTER,
    ARTICLES_OF_INCORPORATION,
    AUDITED_FINANCIALS,
    CUSTODIAN_AGREEMENT,
    FORM_OF_ORGANIZATION,
    GOOD_STANDING_CERTIFICATE,
    HOMEOWNERPROTECTIONCENTERNOTIF,
    KYC_AML_COMPLIANCE,
    LICENSE_EXEMPTION,
    LLP_AGREEMENT,
    LLP_CERTIFICATE,
    MANAGEMENT_REPORT,
    ORGANIZATIONAL_AMENDMENT,
    ORGANIZATIONAL_CHART,
    OTHER_AGREEMENT,
    OTHER_COMPLIANCE,
    OTHER_FINANCIAL,
    OTHER_LICENSE,
    OTHER_ORGANIZATIONAL,
    OTHER_PROCEDURAL,
    OTHER_TAX,
    OTHER_UNDERWRITING,
    POLICY_GUIDE,
    PROCEDURE_GUIDE,
    QUALITY_CONTROL_PLAN,
    RECORDED_DEED,
    RESUME,
    SERVICING_AGREEMENT,
    SERVICING_GUIDELINES,
    SERVICING_POLICY,
    SERVICINGDISCLOSURE,
    STATE_LICENSE,
    TAX_RETURN,
    UNDERWRITING_DUE_DILIGENCE,
    UNDERWRITING_POLICY,
    UNDERWRITING_RATE_SHEET,
}

enum class EmploymentStatus {
    FULL_TIME,
    PART_TIME,
    STUDENT,
    UNEMPLOYED,
}

enum class FiatMovementType {
    ACH,
    WIRE,
}

enum class IndexRateType {
    LIBOR,
    PRIME
}

enum class InterestRateExpectedType {
    FIXED,
    FLOATING // variable interest rate
}

enum class LoanExpectedType {
    HELOC,
    HELOC_DRAW,
    MORTGAGE_CASHOUT_REFI,
    MORTGAGE_CONVENTIONAL,
    MORTGAGE_ARM,
    MORTGAGE_JUMBO,
    MORTGAGE_HIGHBALANCE,
    STUDENT_LOAN_REFI,
    PERSONAL_LOAN,
    RESIDENTIAL_BRIDGE,
    AUTO_LOAN,
}

enum class NumberExpectedType {
    HOME,
    MOBILE,
    WORK,
}

enum class PaymentExpectedType {
    MANUAL_PAYMENT,
    AUTOPAY,
}

enum class RateDiscountType {
    AUTOPAY,
    CREDIT_UNION_MEMBERSHIP,
}

enum class OrigFeeExpectedType {
    CAPITALIZED,
    NO_FEE,
    UNCAPITALIZED_SPREAD,
    UNCAPITALIZED_UPFRONT,
}


