package com.provys.catalogue.model;

import javax.validation.constraints.*;
import java.math.BigInteger;

public interface Domain {
    /**
     * Getter for <code>KER.KER_DOMAIN_TB.DOMAIN_ID</code>. UID of domain
     */
    @NotNull
    @Positive
    BigInteger getDomainId();

    /**
     * Setter for <code>KER.KER_DOMAIN_TB.NAME_NM</code>. Internal name of domain
     */
    void setNameNm(String value);

    /**
     * Getter for <code>KER.KER_DOMAIN_TB.NAME_NM</code>. Internal name of domain
     */
    @NotEmpty
    @Size(max = 200)
    String getNameNm();

    /**
     * Setter for <code>KER.KER_DOMAIN_TB.NAME</code>. Name of domain
     */
    void setName(String value);

    /**
     * Getter for <code>KER.KER_DOMAIN_TB.NAME</code>. Name of domain
     */
    String getName();

    /**
     * Getter for <code>KER.KER_DOMAIN_TB.X_UPNAME</code>. Calculated column - uppercase of NAME
     */
    String getXUpname();

    /**
     * Setter for <code>KER.KER_DOMAIN_TB.DATATYPE_NM</code>. PROVYS datatype used for implementation of column of this domain
     */
    void setDatatypeNm(String value);

    /**
     * Getter for <code>KER.KER_DOMAIN_TB.DATATYPE_NM</code>. PROVYS datatype used for implementation of column of this domain
     */
    String getDatatypeNm();

    /**
     * Setter for <code>KER.KER_DOMAIN_TB.DATALENGTH</code>. Length (maximum length) of data column corresponding to this domain
     */
    void setDatalength(Short value);

    /**
     * Getter for <code>KER.KER_DOMAIN_TB.DATALENGTH</code>. Length (maximum length) of data column corresponding to this domain
     */
    Short getDatalength();

    /**
     * Setter for <code>KER.KER_DOMAIN_TB.DATAPRECISION</code>. Precision of columns from this domain (for numbers only)
     */
    void setDataprecision(Byte value);

    /**
     * Getter for <code>KER.KER_DOMAIN_TB.DATAPRECISION</code>. Precision of columns from this domain (for numbers only)
     */
    Byte getDataprecision();

    /**
     * Setter for <code>KER.KER_DOMAIN_TB.QVISIBLE</code>. Default query visibility for columns from this domain
     */
    void setQvisible(String value);

    /**
     * Getter for <code>KER.KER_DOMAIN_TB.QVISIBLE</code>. Default query visibility for columns from this domain
     */
    String getQvisible();

    /**
     * Setter for <code>KER.KER_DOMAIN_TB.LVISIBLE</code>. Default lists visibility for columns from this domain
     */
    void setLvisible(String value);

    /**
     * Getter for <code>KER.KER_DOMAIN_TB.LVISIBLE</code>. Default lists visibility for columns from this domain
     */
    String getLvisible();

    /**
     * Setter for <code>KER.KER_DOMAIN_TB.VALIDATE_CD</code>. PL/SQL block for standard validation (used if no format-specific validation is specified); value should be bound to IN/OUT :c_Value bind variable
     */
    void setValidateCd(String value);

    /**
     * Getter for <code>KER.KER_DOMAIN_TB.VALIDATE_CD</code>. PL/SQL block for standard validation (used if no format-specific validation is specified); value should be bound to IN/OUT :c_Value bind variable
     */
    String getValidateCd();

    /**
     * Setter for <code>KER.KER_DOMAIN_TB.LFORMAT_CD</code>. PL/SQL block for creation of formated output in list
     */
    void setLformatCd(String value);

    /**
     * Getter for <code>KER.KER_DOMAIN_TB.LFORMAT_CD</code>. PL/SQL block for creation of formated output in list
     */
    String getLformatCd();

    /**
     * Setter for <code>KER.KER_DOMAIN_TB.NATORDER</code>. When domain has native ordering, unmodified attribute value is used for ordering in XML lists. For domains without native ordering, formatted value (converted to string) is used
     */
    void setNatorder(String value);

    /**
     * Getter for <code>KER.KER_DOMAIN_TB.NATORDER</code>. When domain has native ordering, unmodified attribute value is used for ordering in XML lists. For domains without native ordering, formatted value (converted to string) is used
     */
    String getNatorder();

    /**
     * Setter for <code>KER.KER_DOMAIN_TB.NOTE</code>. Notes for administrator of catalogue
     */
    void setNote(String value);

    /**
     * Getter for <code>KER.KER_DOMAIN_TB.NOTE</code>. Notes for administrator of catalogue
     */
    String getNote();

    /**
     * Setter for <code>KER.KER_DOMAIN_TB.ALLOWED</code>. Not allowed domains cannot be manualy created and they are reported in servercheck. Items (e.g. attributes or components) with non-allowed domains should be changed to allowed ones.
     */
    void setAllowed(String value);

    /**
     * Getter for <code>KER.KER_DOMAIN_TB.ALLOWED</code>. Not allowed domains cannot be manualy created and they are reported in servercheck. Items (e.g. attributes or components) with non-allowed domains should be changed to allowed ones.
     */
    String getAllowed();
}
