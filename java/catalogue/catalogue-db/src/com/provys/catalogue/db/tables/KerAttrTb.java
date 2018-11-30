/*
 * This file is generated by jOOQ.
 */
package com.provys.catalogue.db.tables;


import com.provys.catalogue.db.Indexes;
import com.provys.catalogue.db.Ker;
import com.provys.catalogue.db.Keys;
import com.provys.catalogue.db.tables.records.KerAttrTbRecord;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * Attribute (catalogue)
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.7"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class KerAttrTb extends TableImpl<KerAttrTbRecord> {

    private static final long serialVersionUID = -1291774902;

    /**
     * The reference instance of <code>KER.KER_ATTR_TB</code>
     */
    public static final KerAttrTb KER_ATTR_TB = new KerAttrTb();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<KerAttrTbRecord> getRecordType() {
        return KerAttrTbRecord.class;
    }

    /**
     * The column <code>KER.KER_ATTR_TB.ATTR_ID</code>.
     */
    public final TableField<KerAttrTbRecord, BigInteger> ATTR_ID = createField("ATTR_ID", org.jooq.impl.SQLDataType.DECIMAL_INTEGER.precision(38).nullable(false), this, "");

    /**
     * The column <code>KER.KER_ATTR_TB.ENTITY_ID</code>. UID of entity attribute belongs to
     */
    public final TableField<KerAttrTbRecord, BigInteger> ENTITY_ID = createField("ENTITY_ID", org.jooq.impl.SQLDataType.DECIMAL_INTEGER.precision(38).nullable(false), this, "UID of entity attribute belongs to");

    /**
     * The column <code>KER.KER_ATTR_TB.NAME_NM</code>. Internal name of attribute (name of column / textcolumn in database); also default for shortname
     */
    public final TableField<KerAttrTbRecord, String> NAME_NM = createField("NAME_NM", org.jooq.impl.SQLDataType.VARCHAR(100).nullable(false), this, "Internal name of attribute (name of column / textcolumn in database); also default for shortname");

    /**
     * The column <code>KER.KER_ATTR_TB.NAME</code>. Name of attribute
     */
    public final TableField<KerAttrTbRecord, String> NAME = createField("NAME", org.jooq.impl.SQLDataType.VARCHAR(100).nullable(false), this, "Name of attribute");

    /**
     * The column <code>KER.KER_ATTR_TB.NOTE</code>. Note; initialized from comment on given column for "C" and "T" attributes
     */
    public final TableField<KerAttrTbRecord, String> NOTE = createField("NOTE", org.jooq.impl.SQLDataType.VARCHAR(4000), this, "Note; initialized from comment on given column for \"C\" and \"T\" attributes");

    /**
     * The column <code>KER.KER_ATTR_TB.ATTRTYPE</code>. Type of attribute (C=column, T=text column, F=free attribute, I=financial attribute, Q=free SQL, O=formula)
     */
    public final TableField<KerAttrTbRecord, String> ATTRTYPE = createField("ATTRTYPE", org.jooq.impl.SQLDataType.CHAR(1).nullable(false).defaultValue(org.jooq.impl.DSL.field("'C' ", org.jooq.impl.SQLDataType.CHAR)), this, "Type of attribute (C=column, T=text column, F=free attribute, I=financial attribute, Q=free SQL, O=formula)");

    /**
     * The column <code>KER.KER_ATTR_TB.ISCUSTOM</code>. Custom for instalation
     */
    public final TableField<KerAttrTbRecord, String> ISCUSTOM = createField("ISCUSTOM", org.jooq.impl.SQLDataType.CHAR(1).nullable(false).defaultValue(org.jooq.impl.DSL.field("'N' ", org.jooq.impl.SQLDataType.CHAR)), this, "Custom for instalation");

    /**
     * The column <code>KER.KER_ATTR_TB.ISUSED</code>. Used on installation
     */
    public final TableField<KerAttrTbRecord, String> ISUSED = createField("ISUSED", org.jooq.impl.SQLDataType.CHAR(1).nullable(false).defaultValue(org.jooq.impl.DSL.field("'Y' ", org.jooq.impl.SQLDataType.CHAR)), this, "Used on installation");

    /**
     * The column <code>KER.KER_ATTR_TB.ATTRGRP_ID</code>. Attribute group attribute belongs to; used for grouping free or financial attributes on client
     */
    public final TableField<KerAttrTbRecord, BigInteger> ATTRGRP_ID = createField("ATTRGRP_ID", org.jooq.impl.SQLDataType.DECIMAL_INTEGER.precision(38), this, "Attribute group attribute belongs to; used for grouping free or financial attributes on client");

    /**
     * The column <code>KER.KER_ATTR_TB.ORD</code>. Ordering of attributes within group; attributes with same ORD are usually ordered using name
Ordering is used for generate
     */
    public final TableField<KerAttrTbRecord, Integer> ORD = createField("ORD", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("100 ", org.jooq.impl.SQLDataType.INTEGER)), this, "Ordering of attributes within group; attributes with same ORD are usually ordered using name\nOrdering is used for generate");

    /**
     * The column <code>KER.KER_ATTR_TB.DOMAIN_ID</code>. Domain
     */
    public final TableField<KerAttrTbRecord, BigInteger> DOMAIN_ID = createField("DOMAIN_ID", org.jooq.impl.SQLDataType.DECIMAL_INTEGER.precision(38).nullable(false), this, "Domain");

    /**
     * The column <code>KER.KER_ATTR_TB.SUBDOMAIN_NM</code>. Subdomain - closer specification of domain
     */
    public final TableField<KerAttrTbRecord, String> SUBDOMAIN_NM = createField("SUBDOMAIN_NM", org.jooq.impl.SQLDataType.VARCHAR(200), this, "Subdomain - closer specification of domain");

    /**
     * The column <code>KER.KER_ATTR_TB.QVISIBLE</code>. Attribute is visible to users by default; setting can be overriden on profile level (see attribute profile settings).
     */
    public final TableField<KerAttrTbRecord, String> QVISIBLE = createField("QVISIBLE", org.jooq.impl.SQLDataType.CHAR(1).nullable(false).defaultValue(org.jooq.impl.DSL.field("'Y' ", org.jooq.impl.SQLDataType.CHAR)), this, "Attribute is visible to users by default; setting can be overriden on profile level (see attribute profile settings).");

    /**
     * The column <code>KER.KER_ATTR_TB.LLENGTH</code>. Default length of atribute in lists
     */
    public final TableField<KerAttrTbRecord, Integer> LLENGTH = createField("LLENGTH", org.jooq.impl.SQLDataType.INTEGER, this, "Default length of atribute in lists");

    /**
     * The column <code>KER.KER_ATTR_TB.INDEXED</code>. Column is indexed; only usable for "C" attributes
     */
    public final TableField<KerAttrTbRecord, String> INDEXED = createField("INDEXED", org.jooq.impl.SQLDataType.CHAR(1).nullable(false).defaultValue(org.jooq.impl.DSL.field("'N' ", org.jooq.impl.SQLDataType.CHAR)), this, "Column is indexed; only usable for \"C\" attributes");

    /**
     * The column <code>KER.KER_ATTR_TB.UPNAME_NM</code>. Name of upper-cased column; only usable for "C" or "T" attributes
     */
    public final TableField<KerAttrTbRecord, String> UPNAME_NM = createField("UPNAME_NM", org.jooq.impl.SQLDataType.VARCHAR(200), this, "Name of upper-cased column; only usable for \"C\" or \"T\" attributes");

    /**
     * The column <code>KER.KER_ATTR_TB.READONLY</code>. Value of attribute cannot be (directly) set by user.
- for columns and text (translated) attributes this has to be N, no checking is performed
- for formulas and SQL attributes, this has to be Y as it is never possible to modify their values
- for financial attributes, it is not possible to set value (or even define accounting rule for change of value) if attribute is read-only; value should be changed either through other financial attribute or by creating accounting records using other methods (through planned operations or some interface). If it is not read-only, accounting rule must be defined
- for free attributes, if free attribute is marked as read-only, user cannot change its value directly, it is only possible to change it using PG version of mp_Set with p_ChangeReadOnly=TRUE. It can be used for attributes which are replicated from other system or set through CP interface of some entity
     */
    public final TableField<KerAttrTbRecord, String> READONLY = createField("READONLY", org.jooq.impl.SQLDataType.CHAR(1).nullable(false).defaultValue(org.jooq.impl.DSL.field("'N' ", org.jooq.impl.SQLDataType.CHAR)), this, "Value of attribute cannot be (directly) set by user.\n- for columns and text (translated) attributes this has to be N, no checking is performed\n- for formulas and SQL attributes, this has to be Y as it is never possible to modify their values\n- for financial attributes, it is not possible to set value (or even define accounting rule for change of value) if attribute is read-only; value should be changed either through other financial attribute or by creating accounting records using other methods (through planned operations or some interface). If it is not read-only, accounting rule must be defined\n- for free attributes, if free attribute is marked as read-only, user cannot change its value directly, it is only possible to change it using PG version of mp_Set with p_ChangeReadOnly=TRUE. It can be used for attributes which are replicated from other system or set through CP interface of some entity");

    /**
     * The column <code>KER.KER_ATTR_TB.GETOPTYPE_ID</code>. Operation type tested when accessing value of attribute
     */
    public final TableField<KerAttrTbRecord, BigInteger> GETOPTYPE_ID = createField("GETOPTYPE_ID", org.jooq.impl.SQLDataType.DECIMAL_INTEGER.precision(38), this, "Operation type tested when accessing value of attribute");

    /**
     * The column <code>KER.KER_ATTR_TB.SETOPTYPE_ID</code>. Operation type tested when changing value of attribute
     */
    public final TableField<KerAttrTbRecord, BigInteger> SETOPTYPE_ID = createField("SETOPTYPE_ID", org.jooq.impl.SQLDataType.DECIMAL_INTEGER.precision(38), this, "Operation type tested when changing value of attribute");

    /**
     * The column <code>KER.KER_ATTR_TB.RESETOPTYPE_ID</code>. Operation type tested when changing value of attribute to NULL; if not specified, SETOPTYPE_ID is used instead
     */
    public final TableField<KerAttrTbRecord, BigInteger> RESETOPTYPE_ID = createField("RESETOPTYPE_ID", org.jooq.impl.SQLDataType.DECIMAL_INTEGER.precision(38), this, "Operation type tested when changing value of attribute to NULL; if not specified, SETOPTYPE_ID is used instead");

    /**
     * The column <code>KER.KER_ATTR_TB.CONFIRMOTHER</code>. Used for confirmation attributes; value of N means that user cannot set who confirmed action
     */
    public final TableField<KerAttrTbRecord, String> CONFIRMOTHER = createField("CONFIRMOTHER", org.jooq.impl.SQLDataType.CHAR(1), this, "Used for confirmation attributes; value of N means that user cannot set who confirmed action");

    /**
     * The column <code>KER.KER_ATTR_TB.SETOTHEROPTYPE_ID</code>. Operation type tested when changing value of CONFIRM attribute to person other than logged-in user; NULL means same rules apply as for SETOPTYPE_ID
     */
    public final TableField<KerAttrTbRecord, BigInteger> SETOTHEROPTYPE_ID = createField("SETOTHEROPTYPE_ID", org.jooq.impl.SQLDataType.DECIMAL_INTEGER.precision(38), this, "Operation type tested when changing value of CONFIRM attribute to person other than logged-in user; NULL means same rules apply as for SETOPTYPE_ID");

    /**
     * The column <code>KER.KER_ATTR_TB.MULTIVALUE</code>. Multivalue allowed; valid only for free attributes
     */
    public final TableField<KerAttrTbRecord, String> MULTIVALUE = createField("MULTIVALUE", org.jooq.impl.SQLDataType.CHAR(1).nullable(false), this, "Multivalue allowed; valid only for free attributes");

    /**
     * The column <code>KER.KER_ATTR_TB.LOGALLOWED</code>. Logging/dictionary indexing is supported
     */
    public final TableField<KerAttrTbRecord, String> LOGALLOWED = createField("LOGALLOWED", org.jooq.impl.SQLDataType.CHAR(1).nullable(false).defaultValue(org.jooq.impl.DSL.field("'N' ", org.jooq.impl.SQLDataType.CHAR)), this, "Logging/dictionary indexing is supported");

    /**
     * The column <code>KER.KER_ATTR_TB.LOGLEVEL</code>. Log level (N=none, C=current value, H=changes log(history))
     */
    public final TableField<KerAttrTbRecord, String> LOGLEVEL = createField("LOGLEVEL", org.jooq.impl.SQLDataType.CHAR(1).nullable(false).defaultValue(org.jooq.impl.DSL.field("'N' ", org.jooq.impl.SQLDataType.CHAR)), this, "Log level (N=none, C=current value, H=changes log(history))");

    /**
     * The column <code>KER.KER_ATTR_TB.DICTTYPE</code>. Type of storage of attribute in dictionary
     */
    public final TableField<KerAttrTbRecord, String> DICTTYPE = createField("DICTTYPE", org.jooq.impl.SQLDataType.CHAR(1), this, "Type of storage of attribute in dictionary");

    /**
     * The column <code>KER.KER_ATTR_TB.DICTDEL1</code>. Terms delimiter
     */
    public final TableField<KerAttrTbRecord, String> DICTDEL1 = createField("DICTDEL1", org.jooq.impl.SQLDataType.VARCHAR(10), this, "Terms delimiter");

    /**
     * The column <code>KER.KER_ATTR_TB.DICTDEL2</code>. Terms delimiter
     */
    public final TableField<KerAttrTbRecord, String> DICTDEL2 = createField("DICTDEL2", org.jooq.impl.SQLDataType.VARCHAR(10), this, "Terms delimiter");

    /**
     * The column <code>KER.KER_ATTR_TB.TBCODE_CD</code>. Code used to evaluate attribute value (when used internally); for "X" and "O" atributes
     */
    public final TableField<KerAttrTbRecord, String> TBCODE_CD = createField("TBCODE_CD", org.jooq.impl.SQLDataType.VARCHAR(4000), this, "Code used to evaluate attribute value (when used internally); for \"X\" and \"O\" atributes");

    /**
     * The column <code>KER.KER_ATTR_TB.ACCOUNTRULE_NM</code>. Internal name of an accounting rule to be used to book the value for object (only for financial attributes)
     */
    public final TableField<KerAttrTbRecord, String> ACCOUNTRULE_NM = createField("ACCOUNTRULE_NM", org.jooq.impl.SQLDataType.VARCHAR(200), this, "Internal name of an accounting rule to be used to book the value for object (only for financial attributes)");

    /**
     * The column <code>KER.KER_ATTR_TB.ACCDOCOBJECTFORMULA_ID</code>. Accounting document, created when changing value of this financial attribute will be connected to object, evaluated using this attribute. When left blank, document will be connected to object on which action was called (only for financial attributes)
     */
    public final TableField<KerAttrTbRecord, BigInteger> ACCDOCOBJECTFORMULA_ID = createField("ACCDOCOBJECTFORMULA_ID", org.jooq.impl.SQLDataType.DECIMAL_INTEGER.precision(38), this, "Accounting document, created when changing value of this financial attribute will be connected to object, evaluated using this attribute. When left blank, document will be connected to object on which action was called (only for financial attributes)");

    /**
     * The column <code>KER.KER_ATTR_TB.OWNERFORMULA_ID</code>. Owner of ledger used to evaluate value of financial attribute.
     */
    public final TableField<KerAttrTbRecord, BigInteger> OWNERFORMULA_ID = createField("OWNERFORMULA_ID", org.jooq.impl.SQLDataType.DECIMAL_INTEGER.precision(38), this, "Owner of ledger used to evaluate value of financial attribute.");

    /**
     * The column <code>KER.KER_ATTR_TB.FROMOBJECTFORMULA_ID</code>. Used when value of financial attribute is changed - value of this attribute is object to be used on spent side of accounting record
     */
    public final TableField<KerAttrTbRecord, BigInteger> FROMOBJECTFORMULA_ID = createField("FROMOBJECTFORMULA_ID", org.jooq.impl.SQLDataType.DECIMAL_INTEGER.precision(38), this, "Used when value of financial attribute is changed - value of this attribute is object to be used on spent side of accounting record");

    /**
     * The column <code>KER.KER_ATTR_TB.TOOBJECTFORMULA_ID</code>. Used when value of financial attribute is changed - value of this attribute is object to be used on received side of accounting record
     */
    public final TableField<KerAttrTbRecord, BigInteger> TOOBJECTFORMULA_ID = createField("TOOBJECTFORMULA_ID", org.jooq.impl.SQLDataType.DECIMAL_INTEGER.precision(38), this, "Used when value of financial attribute is changed - value of this attribute is object to be used on received side of accounting record");

    /**
     * The column <code>KER.KER_ATTR_TB.APPROXRECORD</code>. Indicates if the created accounting record will be approximate (only for financial attributes)
     */
    public final TableField<KerAttrTbRecord, String> APPROXRECORD = createField("APPROXRECORD", org.jooq.impl.SQLDataType.CHAR(1), this, "Indicates if the created accounting record will be approximate (only for financial attributes)");

    /**
     * The column <code>KER.KER_ATTR_TB.VALUEACCOUNT_NM</code>. Short name of account to read actual value (only for financial attributes)
     */
    public final TableField<KerAttrTbRecord, String> VALUEACCOUNT_NM = createField("VALUEACCOUNT_NM", org.jooq.impl.SQLDataType.VARCHAR(200), this, "Short name of account to read actual value (only for financial attributes)");

    /**
     * The column <code>KER.KER_ATTR_TB.VALUEAMOUNTTYPE</code>. R(eceived), S(pent), T(otal) amount of the balance to read actual value (only for financial attributes)
     */
    public final TableField<KerAttrTbRecord, String> VALUEAMOUNTTYPE = createField("VALUEAMOUNTTYPE", org.jooq.impl.SQLDataType.CHAR(1), this, "R(eceived), S(pent), T(otal) amount of the balance to read actual value (only for financial attributes)");

    /**
     * The column <code>KER.KER_ATTR_TB.DEFOWNER</code>. Default owner points to unit that acts as owner of given object for purpose of accounting
     */
    public final TableField<KerAttrTbRecord, String> DEFOWNER = createField("DEFOWNER", org.jooq.impl.SQLDataType.CHAR(1).nullable(false).defaultValue(org.jooq.impl.DSL.field("'N' ", org.jooq.impl.SQLDataType.CHAR)), this, "Default owner points to unit that acts as owner of given object for purpose of accounting");

    /**
     * The column <code>KER.KER_ATTR_TB.TEXTSRC_ID</code>. Source attribute for translated attribute (only for translated attributes)
     */
    public final TableField<KerAttrTbRecord, BigInteger> TEXTSRC_ID = createField("TEXTSRC_ID", org.jooq.impl.SQLDataType.DECIMAL_INTEGER.precision(38), this, "Source attribute for translated attribute (only for translated attributes)");

    /**
     * The column <code>KER.KER_ATTR_TB.DOCUMENTATION</code>. Addional documentation related to the attribute (not generated to the table column comment)
     */
    public final TableField<KerAttrTbRecord, String> DOCUMENTATION = createField("DOCUMENTATION", org.jooq.impl.SQLDataType.VARCHAR(4000), this, "Addional documentation related to the attribute (not generated to the table column comment)");

    /**
     * The column <code>KER.KER_ATTR_TB.BITMAPORD</code>. Order in Attribute Set Bitmap, 0=Least Significant bit. Used internally in generated parts of PG packages (only for column attributes)
     */
    public final TableField<KerAttrTbRecord, Integer> BITMAPORD = createField("BITMAPORD", org.jooq.impl.SQLDataType.INTEGER, this, "Order in Attribute Set Bitmap, 0=Least Significant bit. Used internally in generated parts of PG packages (only for column attributes)");

    /**
     * The column <code>KER.KER_ATTR_TB.X_ISFLAGDEPBIND</code>.
     */
    public final TableField<KerAttrTbRecord, String> X_ISFLAGDEPBIND = createField("X_ISFLAGDEPBIND", org.jooq.impl.SQLDataType.CHAR(1).nullable(false).defaultValue(org.jooq.impl.DSL.field("'N' ", org.jooq.impl.SQLDataType.CHAR)), this, "");

    /**
     * The column <code>KER.KER_ATTR_TB.X_ISFLAGDEFATTR</code>. Shows if Attribute is used in any Flag Definition (via. FlagDefAttr, or DstAttr of any FlagDep)
     */
    public final TableField<KerAttrTbRecord, String> X_ISFLAGDEFATTR = createField("X_ISFLAGDEFATTR", org.jooq.impl.SQLDataType.CHAR(1).nullable(false).defaultValue(org.jooq.impl.DSL.field("'N' ", org.jooq.impl.SQLDataType.CHAR)), this, "Shows if Attribute is used in any Flag Definition (via. FlagDefAttr, or DstAttr of any FlagDep)");

    /**
     * The column <code>KER.KER_ATTR_TB.MANDATORY</code>. Marks mandatory attribute; only valid for columns (attribute type=C) and only affects way entity's PG package is generated.
     */
    public final TableField<KerAttrTbRecord, String> MANDATORY = createField("MANDATORY", org.jooq.impl.SQLDataType.CHAR(1).nullable(false).defaultValue(org.jooq.impl.DSL.field("'N' ", org.jooq.impl.SQLDataType.CHAR)), this, "Marks mandatory attribute; only valid for columns (attribute type=C) and only affects way entity's PG package is generated.");

    /**
     * The column <code>KER.KER_ATTR_TB.COPIED</code>. Attribute value is copied in copy operations.
     */
    public final TableField<KerAttrTbRecord, String> COPIED = createField("COPIED", org.jooq.impl.SQLDataType.CHAR(1).nullable(false).defaultValue(org.jooq.impl.DSL.field("'Y' ", org.jooq.impl.SQLDataType.CHAR)), this, "Attribute value is copied in copy operations.");

    /**
     * The column <code>KER.KER_ATTR_TB.DEFVALUE</code>. Default value. Only used in generator, should not be changed on installations and is only valid for C (column) attributes.
     */
    public final TableField<KerAttrTbRecord, String> DEFVALUE = createField("DEFVALUE", org.jooq.impl.SQLDataType.VARCHAR(4000), this, "Default value. Only used in generator, should not be changed on installations and is only valid for C (column) attributes.");

    /**
     * The column <code>KER.KER_ATTR_TB.ORDERED</code>. This column represents order (either globally or within children of one parent - see Ordering Group attribute).
     */
    public final TableField<KerAttrTbRecord, String> ORDERED = createField("ORDERED", org.jooq.impl.SQLDataType.CHAR(1).nullable(false).defaultValue(org.jooq.impl.DSL.field("'N' ", org.jooq.impl.SQLDataType.CHAR)), this, "This column represents order (either globally or within children of one parent - see Ordering Group attribute).");

    /**
     * The column <code>KER.KER_ATTR_TB.ORDGROUP</code>. Parent attribute (or comma delimited list of parent attributes). Only used when ordered is set to Y - this column is then treated as ordering within group, made by same values of parent attributes
     */
    public final TableField<KerAttrTbRecord, String> ORDGROUP = createField("ORDGROUP", org.jooq.impl.SQLDataType.VARCHAR(200), this, "Parent attribute (or comma delimited list of parent attributes). Only used when ordered is set to Y - this column is then treated as ordering within group, made by same values of parent attributes");

    /**
     * The column <code>KER.KER_ATTR_TB.GENCOPY</code>. Generate mp_Copy_&lt;***&gt; procedure based on link, represented by this column, to PG package.
     */
    public final TableField<KerAttrTbRecord, String> GENCOPY = createField("GENCOPY", org.jooq.impl.SQLDataType.CHAR(1).nullable(false).defaultValue(org.jooq.impl.DSL.field("'N' ", org.jooq.impl.SQLDataType.CHAR)), this, "Generate mp_Copy_<***> procedure based on link, represented by this column, to PG package.");

    /**
     * The column <code>KER.KER_ATTR_TB.GENMOVE</code>. Generate mp_Move_&lt;***&gt; procedure based on link, represented by this column, to PG package.
     */
    public final TableField<KerAttrTbRecord, String> GENMOVE = createField("GENMOVE", org.jooq.impl.SQLDataType.CHAR(1).nullable(false).defaultValue(org.jooq.impl.DSL.field("'N' ", org.jooq.impl.SQLDataType.CHAR)), this, "Generate mp_Move_<***> procedure based on link, represented by this column, to PG package.");

    /**
     * The column <code>KER.KER_ATTR_TB.GENDELETE</code>. Generate mp_Delete_&lt;***&gt; procedure based on link, represented by this column, to PG package.
     */
    public final TableField<KerAttrTbRecord, String> GENDELETE = createField("GENDELETE", org.jooq.impl.SQLDataType.CHAR(1).nullable(false).defaultValue(org.jooq.impl.DSL.field("'N' ", org.jooq.impl.SQLDataType.CHAR)), this, "Generate mp_Delete_<***> procedure based on link, represented by this column, to PG package.");

    /**
     * The column <code>KER.KER_ATTR_TB.HIERARCHICAL</code>.
     */
    public final TableField<KerAttrTbRecord, String> HIERARCHICAL = createField("HIERARCHICAL", org.jooq.impl.SQLDataType.CHAR(1).nullable(false).defaultValue(org.jooq.impl.DSL.field("'N' ", org.jooq.impl.SQLDataType.CHAR)), this, "");

    /**
     * The column <code>KER.KER_ATTR_TB.MULTICREATE</code>. Create function accepts multiline in this attribute. Only valid for (C)olumn type attributes with domains UID and REF
     */
    public final TableField<KerAttrTbRecord, String> MULTICREATE = createField("MULTICREATE", org.jooq.impl.SQLDataType.CHAR(1).nullable(false).defaultValue(org.jooq.impl.DSL.field("'N' ", org.jooq.impl.SQLDataType.CHAR)), this, "Create function accepts multiline in this attribute. Only valid for (C)olumn type attributes with domains UID and REF");

    /**
     * The column <code>KER.KER_ATTR_TB.VALIDATED</code>. Specifies if validation is supported for given attribute. Is used by client to determine if editation in validate edit should be allowed when this attribute is used as format, or if seelction through LOV is required.
     */
    public final TableField<KerAttrTbRecord, String> VALIDATED = createField("VALIDATED", org.jooq.impl.SQLDataType.CHAR(1).nullable(false).defaultValue(org.jooq.impl.DSL.field("'N' ", org.jooq.impl.SQLDataType.CHAR)), this, "Specifies if validation is supported for given attribute. Is used by client to determine if editation in validate edit should be allowed when this attribute is used as format, or if seelction through LOV is required.");

    /**
     * The column <code>KER.KER_ATTR_TB.ISINTABLE</code>. Attribute is implemented in table of this Entity
     */
    public final TableField<KerAttrTbRecord, String> ISINTABLE = createField("ISINTABLE", org.jooq.impl.SQLDataType.CHAR(1).nullable(false).defaultValue(org.jooq.impl.DSL.field("'N' ", org.jooq.impl.SQLDataType.CHAR)), this, "Attribute is implemented in table of this Entity");

    /**
     * The column <code>KER.KER_ATTR_TB.ISOWN</code>. Attribute is not inherited
     */
    public final TableField<KerAttrTbRecord, String> ISOWN = createField("ISOWN", org.jooq.impl.SQLDataType.CHAR(1).nullable(false).defaultValue(org.jooq.impl.DSL.field("'N' ", org.jooq.impl.SQLDataType.CHAR)), this, "Attribute is not inherited");

    /**
     * The column <code>KER.KER_ATTR_TB.KEYORD</code>. Order of given attribute in key of given entity
     */
    public final TableField<KerAttrTbRecord, Integer> KEYORD = createField("KEYORD", org.jooq.impl.SQLDataType.INTEGER.defaultValue(org.jooq.impl.DSL.field("NULL", org.jooq.impl.SQLDataType.INTEGER)), this, "Order of given attribute in key of given entity");

    /**
     * The column <code>KER.KER_ATTR_TB.OPTYPETYPE_RF</code>. Specifies type of operation for UID / operation type attributes that support automatic optype creation
     */
    public final TableField<KerAttrTbRecord, BigInteger> OPTYPETYPE_RF = createField("OPTYPETYPE_RF", org.jooq.impl.SQLDataType.DECIMAL_INTEGER.precision(38), this, "Specifies type of operation for UID / operation type attributes that support automatic optype creation");

    /**
     * The column <code>KER.KER_ATTR_TB.IMGSRC_ID</code>. Reference to the image souce, which we should resize
     */
    public final TableField<KerAttrTbRecord, BigInteger> IMGSRC_ID = createField("IMGSRC_ID", org.jooq.impl.SQLDataType.DECIMAL_INTEGER.precision(38), this, "Reference to the image souce, which we should resize");

    /**
     * The column <code>KER.KER_ATTR_TB.IMGWIDTH</code>. The width of the picture to resize
     */
    public final TableField<KerAttrTbRecord, Integer> IMGWIDTH = createField("IMGWIDTH", org.jooq.impl.SQLDataType.INTEGER, this, "The width of the picture to resize");

    /**
     * The column <code>KER.KER_ATTR_TB.IMGHEIGHT</code>. The height of the picture to resize
     */
    public final TableField<KerAttrTbRecord, Integer> IMGHEIGHT = createField("IMGHEIGHT", org.jooq.impl.SQLDataType.INTEGER, this, "The height of the picture to resize");

    /**
     * The column <code>KER.KER_ATTR_TB.FILESERVER_ID</code>. [KO150414-21B7CDA] Ukladani souboru mimo databazi
     */
    public final TableField<KerAttrTbRecord, BigInteger> FILESERVER_ID = createField("FILESERVER_ID", org.jooq.impl.SQLDataType.DECIMAL_INTEGER.precision(38), this, "[KO150414-21B7CDA] Ukladani souboru mimo databazi");

    /**
     * The column <code>KER.KER_ATTR_TB.GENMIGR</code>. Used for generator of migration framework
     */
    public final TableField<KerAttrTbRecord, String> GENMIGR = createField("GENMIGR", org.jooq.impl.SQLDataType.CHAR(1).defaultValue(org.jooq.impl.DSL.field("'N'", org.jooq.impl.SQLDataType.CHAR)), this, "Used for generator of migration framework");

    /**
     * The column <code>KER.KER_ATTR_TB.EVALCVALUE</code>.
     */
    public final TableField<KerAttrTbRecord, String> EVALCVALUE = createField("EVALCVALUE", org.jooq.impl.SQLDataType.CHAR(1).defaultValue(org.jooq.impl.DSL.field("NULL", org.jooq.impl.SQLDataType.CHAR)), this, "");

    /**
     * The column <code>KER.KER_ATTR_TB.OPTLOBLIMIT</code>. Optional limit for length of lob free atribut. It show alert.  The limit  is in bytes
     */
    public final TableField<KerAttrTbRecord, BigDecimal> OPTLOBLIMIT = createField("OPTLOBLIMIT", org.jooq.impl.SQLDataType.NUMERIC, this, "Optional limit for length of lob free atribut. It show alert.  The limit  is in bytes");

    /**
     * The column <code>KER.KER_ATTR_TB.MANLOBLIMIT</code>. Mandatory limit for length of lob free atribute. It show error. The limit is in bytes.
     */
    public final TableField<KerAttrTbRecord, BigDecimal> MANLOBLIMIT = createField("MANLOBLIMIT", org.jooq.impl.SQLDataType.NUMERIC, this, "Mandatory limit for length of lob free atribute. It show error. The limit is in bytes.");

    /**
     * Create a <code>KER.KER_ATTR_TB</code> table reference
     */
    public KerAttrTb() {
        this(DSL.name("KER_ATTR_TB"), null);
    }

    /**
     * Create an aliased <code>KER.KER_ATTR_TB</code> table reference
     */
    public KerAttrTb(String alias) {
        this(DSL.name(alias), KER_ATTR_TB);
    }

    /**
     * Create an aliased <code>KER.KER_ATTR_TB</code> table reference
     */
    public KerAttrTb(Name alias) {
        this(alias, KER_ATTR_TB);
    }

    private KerAttrTb(Name alias, Table<KerAttrTbRecord> aliased) {
        this(alias, aliased, null);
    }

    private KerAttrTb(Name alias, Table<KerAttrTbRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("Attribute (catalogue)"));
    }

    public <O extends Record> KerAttrTb(Table<O> child, ForeignKey<O, KerAttrTbRecord> key) {
        super(child, key, KER_ATTR_TB);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Ker.KER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.KER_ATTR_ACCDOCOBJECTFORMUL_IX, Indexes.KER_ATTR_ATTRGRP_IX, Indexes.KER_ATTR_DOMAIN_IX, Indexes.KER_ATTR_ENTANDNAME_UK, Indexes.KER_ATTR_ENTANDNM_UK, Indexes.KER_ATTR_ENTITY_IX, Indexes.KER_ATTR_FILESERVER_IX, Indexes.KER_ATTR_FROMOBJECTFORMULA_IX, Indexes.KER_ATTR_GETOPTYPE_IX, Indexes.KER_ATTR_IMGSRC_IX, Indexes.KER_ATTR_OPTYPETYPE_IX, Indexes.KER_ATTR_OWNERFORMULA_IX, Indexes.KER_ATTR_PK, Indexes.KER_ATTR_RESETOPTYPE_IX, Indexes.KER_ATTR_SETOPTYPE_IX, Indexes.KER_ATTR_SETOTHEROPTYPE_IX, Indexes.KER_ATTR_TEXTSRC_IX, Indexes.KER_ATTR_TOOBJECTFORMULA_IX);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<KerAttrTbRecord> getPrimaryKey() {
        return Keys.KER_ATTR_PK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<KerAttrTbRecord>> getKeys() {
        return Arrays.<UniqueKey<KerAttrTbRecord>>asList(Keys.KER_ATTR_PK, Keys.KER_ATTR_ENTANDNAME_UK, Keys.KER_ATTR_ENTANDNM_UK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<KerAttrTbRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<KerAttrTbRecord, ?>>asList(Keys.KER_ATTR_ENTITY_FK, Keys.KER_ATTR_DOMAIN_FK, Keys.KER_ATTR_ACCDOCOBJECTFORMUL_FK, Keys.KER_ATTR_OWNERFORMULA_FK, Keys.KER_ATTR_FROMOBJECTFORMULA_FK, Keys.KER_ATTR_TOOBJECTFORMULA_FK, Keys.KER_ATTR_TEXTSRC_FK, Keys.KER_ATTR_IMGSRC_FK);
    }

    public KerEntityTb kerEntityTb() {
        return new KerEntityTb(this, Keys.KER_ATTR_ENTITY_FK);
    }

    public KerDomainTb kerDomainTb() {
        return new KerDomainTb(this, Keys.KER_ATTR_DOMAIN_FK);
    }

    public com.provys.catalogue.db.tables.KerAttrTb kerAttrAccdocobjectformulFk() {
        return new com.provys.catalogue.db.tables.KerAttrTb(this, Keys.KER_ATTR_ACCDOCOBJECTFORMUL_FK);
    }

    public com.provys.catalogue.db.tables.KerAttrTb kerAttrOwnerformulaFk() {
        return new com.provys.catalogue.db.tables.KerAttrTb(this, Keys.KER_ATTR_OWNERFORMULA_FK);
    }

    public com.provys.catalogue.db.tables.KerAttrTb kerAttrFromobjectformulaFk() {
        return new com.provys.catalogue.db.tables.KerAttrTb(this, Keys.KER_ATTR_FROMOBJECTFORMULA_FK);
    }

    public com.provys.catalogue.db.tables.KerAttrTb kerAttrToobjectformulaFk() {
        return new com.provys.catalogue.db.tables.KerAttrTb(this, Keys.KER_ATTR_TOOBJECTFORMULA_FK);
    }

    public com.provys.catalogue.db.tables.KerAttrTb kerAttrTextsrcFk() {
        return new com.provys.catalogue.db.tables.KerAttrTb(this, Keys.KER_ATTR_TEXTSRC_FK);
    }

    public com.provys.catalogue.db.tables.KerAttrTb kerAttrImgsrcFk() {
        return new com.provys.catalogue.db.tables.KerAttrTb(this, Keys.KER_ATTR_IMGSRC_FK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public KerAttrTb as(String alias) {
        return new KerAttrTb(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public KerAttrTb as(Name alias) {
        return new KerAttrTb(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public KerAttrTb rename(String name) {
        return new KerAttrTb(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public KerAttrTb rename(Name name) {
        return new KerAttrTb(name, null);
    }
}
