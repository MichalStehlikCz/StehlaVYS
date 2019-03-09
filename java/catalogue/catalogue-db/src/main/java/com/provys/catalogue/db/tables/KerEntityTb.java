/*
 * This file is generated by jOOQ.
 */
package com.provys.catalogue.db.tables;


import com.provys.catalogue.db.Indexes;
import com.provys.catalogue.db.Ker;
import com.provys.catalogue.db.Keys;
import com.provys.catalogue.db.tables.records.KerEntityTbRecord;

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
 * Entities (catalogue)
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.7"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class KerEntityTb extends TableImpl<KerEntityTbRecord> {

    private static final long serialVersionUID = -1490761631;

    /**
     * The reference instance of <code>KER.KER_ENTITY_TB</code>
     */
    public static final KerEntityTb KER_ENTITY_TB = new KerEntityTb();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<KerEntityTbRecord> getRecordType() {
        return KerEntityTbRecord.class;
    }

    /**
     * The column <code>KER.KER_ENTITY_TB.ENTITY_ID</code>. UID
     */
    public final TableField<KerEntityTbRecord, BigInteger> ENTITY_ID = createField("ENTITY_ID", org.jooq.impl.SQLDataType.DECIMAL_INTEGER.precision(38).nullable(false), this, "UID");

    /**
     * The column <code>KER.KER_ENTITY_TB.NAME_NM</code>. Internal name of entity; base for NSLSHORT
     */
    public final TableField<KerEntityTbRecord, String> NAME_NM = createField("NAME_NM", org.jooq.impl.SQLDataType.VARCHAR(20).nullable(false), this, "Internal name of entity; base for NSLSHORT");

    /**
     * The column <code>KER.KER_ENTITY_TB.NAME</code>. Entity name, base for NLSNAME
     */
    public final TableField<KerEntityTbRecord, String> NAME = createField("NAME", org.jooq.impl.SQLDataType.VARCHAR(200).nullable(false), this, "Entity name, base for NLSNAME");

    /**
     * The column <code>KER.KER_ENTITY_TB.X_UPNAME</code>. Calculated column - uppercase of NAME
     */
    public final TableField<KerEntityTbRecord, String> X_UPNAME = createField("X_UPNAME", org.jooq.impl.SQLDataType.VARCHAR(200).nullable(false), this, "Calculated column - uppercase of NAME");

    /**
     * The column <code>KER.KER_ENTITY_TB.ISCUSTOM</code>. Entity is custom; such entities are not synchronized during upgrade.
     */
    public final TableField<KerEntityTbRecord, String> ISCUSTOM = createField("ISCUSTOM", org.jooq.impl.SQLDataType.CHAR(1).nullable(false).defaultValue(org.jooq.impl.DSL.field("'N' ", org.jooq.impl.SQLDataType.CHAR)), this, "Entity is custom; such entities are not synchronized during upgrade.");

    /**
     * The column <code>KER.KER_ENTITY_TB.ISUSED</code>. Entity is used on given installation.
     */
    public final TableField<KerEntityTbRecord, String> ISUSED = createField("ISUSED", org.jooq.impl.SQLDataType.CHAR(1).nullable(false).defaultValue(org.jooq.impl.DSL.field("'Y' ", org.jooq.impl.SQLDataType.CHAR)), this, "Entity is used on given installation.");

    /**
     * The column <code>KER.KER_ENTITY_TB.OBJECTCLASS</code>. Indicates if it is possible to create object of given type.
     */
    public final TableField<KerEntityTbRecord, String> OBJECTCLASS = createField("OBJECTCLASS", org.jooq.impl.SQLDataType.CHAR(1).nullable(false).defaultValue(org.jooq.impl.DSL.field("'N' ", org.jooq.impl.SQLDataType.CHAR)), this, "Indicates if it is possible to create object of given type.");

    /**
     * The column <code>KER.KER_ENTITY_TB.TABLE_NM</code>. Name of base table/view, containing instances of entity.
     */
    public final TableField<KerEntityTbRecord, String> TABLE_NM = createField("TABLE_NM", org.jooq.impl.SQLDataType.VARCHAR(30), this, "Name of base table/view, containing instances of entity.");

    /**
     * The column <code>KER.KER_ENTITY_TB.VIEW_NM</code>. Name of view to be generated for entity.
     */
    public final TableField<KerEntityTbRecord, String> VIEW_NM = createField("VIEW_NM", org.jooq.impl.SQLDataType.VARCHAR(30), this, "Name of view to be generated for entity.");

    /**
     * The column <code>KER.KER_ENTITY_TB.KEY_NM</code>. Name of primary key of entity (in table TABLE_NM)
     */
    public final TableField<KerEntityTbRecord, String> KEY_NM = createField("KEY_NM", org.jooq.impl.SQLDataType.VARCHAR(30), this, "Name of primary key of entity (in table TABLE_NM)");

    /**
     * The column <code>KER.KER_ENTITY_TB.TEXTTABLE_NM</code>. Name of table with entity text translations for current configuration
     */
    public final TableField<KerEntityTbRecord, String> TEXTTABLE_NM = createField("TEXTTABLE_NM", org.jooq.impl.SQLDataType.VARCHAR(30), this, "Name of table with entity text translations for current configuration");

    /**
     * The column <code>KER.KER_ENTITY_TB.TEXTVIEW_NM</code>. Name of view with translations to be generated for entity
     */
    public final TableField<KerEntityTbRecord, String> TEXTVIEW_NM = createField("TEXTVIEW_NM", org.jooq.impl.SQLDataType.VARCHAR(30), this, "Name of view with translations to be generated for entity");

    /**
     * The column <code>KER.KER_ENTITY_TB.TEXTKEY_NM</code>. Primary key counterpart in TEXTTABLE_NM table.
     */
    public final TableField<KerEntityTbRecord, String> TEXTKEY_NM = createField("TEXTKEY_NM", org.jooq.impl.SQLDataType.VARCHAR(30), this, "Primary key counterpart in TEXTTABLE_NM table.");

    /**
     * The column <code>KER.KER_ENTITY_TB.TEXTALL</code>. Flag indicating, that all rows in text table belong to entity
     */
    public final TableField<KerEntityTbRecord, String> TEXTALL = createField("TEXTALL", org.jooq.impl.SQLDataType.CHAR(1).defaultValue(org.jooq.impl.DSL.field("'N'", org.jooq.impl.SQLDataType.CHAR)), this, "Flag indicating, that all rows in text table belong to entity");

    /**
     * The column <code>KER.KER_ENTITY_TB.GETRIGHTCUSTOM</code>. Access rights for entity on installation are installation specific. They will not be overwritten during upgrade.
     */
    public final TableField<KerEntityTbRecord, String> GETRIGHTCUSTOM = createField("GETRIGHTCUSTOM", org.jooq.impl.SQLDataType.CHAR(1).nullable(false).defaultValue(org.jooq.impl.DSL.field("'N' ", org.jooq.impl.SQLDataType.CHAR)), this, "Access rights for entity on installation are installation specific. They will not be overwritten during upgrade.");

    /**
     * The column <code>KER.KER_ENTITY_TB.GETOPTYPE_ID</code>. Operation type to be tested when accessing objects. Checks are compiled to entity view. Note that it might is possible to access entity's data through DW views on other entities where this entity is joined. It is also possible to bypass rights tests using EP functions.
     */
    public final TableField<KerEntityTbRecord, BigInteger> GETOPTYPE_ID = createField("GETOPTYPE_ID", org.jooq.impl.SQLDataType.DECIMAL_INTEGER.precision(38), this, "Operation type to be tested when accessing objects. Checks are compiled to entity view. Note that it might is possible to access entity's data through DW views on other entities where this entity is joined. It is also possible to bypass rights tests using EP functions.");

    /**
     * The column <code>KER.KER_ENTITY_TB.GETRIGHTRESTR_ID</code>. Right restriction, defining objects that are accessible to all users (public).
     */
    public final TableField<KerEntityTbRecord, BigInteger> GETRIGHTRESTR_ID = createField("GETRIGHTRESTR_ID", org.jooq.impl.SQLDataType.DECIMAL_INTEGER.precision(38), this, "Right restriction, defining objects that are accessible to all users (public).");

    /**
     * The column <code>KER.KER_ENTITY_TB.KEEPDELETED</code>. This field can be used to retain record in object table given number of days after actually deleting object from system. History (of attribute values), remarks and liabilities are kept along with object. Financial data attached to object are cancelled immediately even if object is kept.
     */
    public final TableField<KerEntityTbRecord, Integer> KEEPDELETED = createField("KEEPDELETED", org.jooq.impl.SQLDataType.INTEGER, this, "This field can be used to retain record in object table given number of days after actually deleting object from system. History (of attribute values), remarks and liabilities are kept along with object. Financial data attached to object are cancelled immediately even if object is kept.");

    /**
     * The column <code>KER.KER_ENTITY_TB.X_NOTIFYATTR0</code>. Attr Set Bitmap of attributes for which AfterCreate and AfterChange should be called - part 0
     */
    public final TableField<KerEntityTbRecord, Long> X_NOTIFYATTR0 = createField("X_NOTIFYATTR0", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("0 ", org.jooq.impl.SQLDataType.BIGINT)), this, "Attr Set Bitmap of attributes for which AfterCreate and AfterChange should be called - part 0");

    /**
     * The column <code>KER.KER_ENTITY_TB.X_NOTIFYATTR1</code>. Attr Set Bitmap of attributes for which AfterCreate and AfterChange should be called - part 1
     */
    public final TableField<KerEntityTbRecord, Long> X_NOTIFYATTR1 = createField("X_NOTIFYATTR1", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("0 ", org.jooq.impl.SQLDataType.BIGINT)), this, "Attr Set Bitmap of attributes for which AfterCreate and AfterChange should be called - part 1");

    /**
     * The column <code>KER.KER_ENTITY_TB.X_NOTIFYATTR2</code>. Attr Set Bitmap of attributes for which AfterCreate and AfterChange should be called - part 2
     */
    public final TableField<KerEntityTbRecord, Long> X_NOTIFYATTR2 = createField("X_NOTIFYATTR2", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("0 ", org.jooq.impl.SQLDataType.BIGINT)), this, "Attr Set Bitmap of attributes for which AfterCreate and AfterChange should be called - part 2");

    /**
     * The column <code>KER.KER_ENTITY_TB.X_NOTIFYATTR3</code>. Attr Set Bitmap of attributes for which AfterCreate and AfterChange should be called - part 3
     */
    public final TableField<KerEntityTbRecord, Long> X_NOTIFYATTR3 = createField("X_NOTIFYATTR3", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("0 ", org.jooq.impl.SQLDataType.BIGINT)), this, "Attr Set Bitmap of attributes for which AfterCreate and AfterChange should be called - part 3");

    /**
     * The column <code>KER.KER_ENTITY_TB.X_FLAGDEFINTEREST0</code>. Bitmap of Attributes, changes of which should raise at least one Flag Definition (part 0)
     */
    public final TableField<KerEntityTbRecord, Long> X_FLAGDEFINTEREST0 = createField("X_FLAGDEFINTEREST0", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("0 ", org.jooq.impl.SQLDataType.BIGINT)), this, "Bitmap of Attributes, changes of which should raise at least one Flag Definition (part 0)");

    /**
     * The column <code>KER.KER_ENTITY_TB.X_FLAGDEFINTEREST1</code>. Bitmap of Attributes, changes of which should raise at least one Flag Definition (part 1)
     */
    public final TableField<KerEntityTbRecord, Long> X_FLAGDEFINTEREST1 = createField("X_FLAGDEFINTEREST1", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("0 ", org.jooq.impl.SQLDataType.BIGINT)), this, "Bitmap of Attributes, changes of which should raise at least one Flag Definition (part 1)");

    /**
     * The column <code>KER.KER_ENTITY_TB.X_FLAGDEFINTEREST2</code>. Bitmap of Attributes, changes of which should raise at least one Flag Definition (part 2)
     */
    public final TableField<KerEntityTbRecord, Long> X_FLAGDEFINTEREST2 = createField("X_FLAGDEFINTEREST2", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("0 ", org.jooq.impl.SQLDataType.BIGINT)), this, "Bitmap of Attributes, changes of which should raise at least one Flag Definition (part 2)");

    /**
     * The column <code>KER.KER_ENTITY_TB.X_FLAGDEFINTEREST3</code>. Bitmap of Attributes, changes of which should raise at least one Flag Definition (part 3)
     */
    public final TableField<KerEntityTbRecord, Long> X_FLAGDEFINTEREST3 = createField("X_FLAGDEFINTEREST3", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("0 ", org.jooq.impl.SQLDataType.BIGINT)), this, "Bitmap of Attributes, changes of which should raise at least one Flag Definition (part 3)");

    /**
     * The column <code>KER.KER_ENTITY_TB.X_DELNOTIFYATTR0</code>. Bitmap of Attributes, for which AfterDelete should be called
     */
    public final TableField<KerEntityTbRecord, Long> X_DELNOTIFYATTR0 = createField("X_DELNOTIFYATTR0", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("0 ", org.jooq.impl.SQLDataType.BIGINT)), this, "Bitmap of Attributes, for which AfterDelete should be called");

    /**
     * The column <code>KER.KER_ENTITY_TB.X_DELNOTIFYATTR1</code>.
     */
    public final TableField<KerEntityTbRecord, Long> X_DELNOTIFYATTR1 = createField("X_DELNOTIFYATTR1", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("0 ", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>KER.KER_ENTITY_TB.X_DELNOTIFYATTR2</code>.
     */
    public final TableField<KerEntityTbRecord, Long> X_DELNOTIFYATTR2 = createField("X_DELNOTIFYATTR2", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("0 ", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>KER.KER_ENTITY_TB.X_DELNOTIFYATTR3</code>.
     */
    public final TableField<KerEntityTbRecord, Long> X_DELNOTIFYATTR3 = createField("X_DELNOTIFYATTR3", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("0 ", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>KER.KER_ENTITY_TB.X_COPIEDATTR0</code>. Bitmap of Attributes, which have COPIED set.
     */
    public final TableField<KerEntityTbRecord, Long> X_COPIEDATTR0 = createField("X_COPIEDATTR0", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("0 ", org.jooq.impl.SQLDataType.BIGINT)), this, "Bitmap of Attributes, which have COPIED set.");

    /**
     * The column <code>KER.KER_ENTITY_TB.X_COPIEDATTR1</code>. Bitmap of Attributes, which have COPIED set.
     */
    public final TableField<KerEntityTbRecord, Long> X_COPIEDATTR1 = createField("X_COPIEDATTR1", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("0 ", org.jooq.impl.SQLDataType.BIGINT)), this, "Bitmap of Attributes, which have COPIED set.");

    /**
     * The column <code>KER.KER_ENTITY_TB.X_COPIEDATTR2</code>. Bitmap of Attributes, which have COPIED set.
     */
    public final TableField<KerEntityTbRecord, Long> X_COPIEDATTR2 = createField("X_COPIEDATTR2", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("0 ", org.jooq.impl.SQLDataType.BIGINT)), this, "Bitmap of Attributes, which have COPIED set.");

    /**
     * The column <code>KER.KER_ENTITY_TB.X_COPIEDATTR3</code>. Bitmap of Attributes, which have COPIED set.
     */
    public final TableField<KerEntityTbRecord, Long> X_COPIEDATTR3 = createField("X_COPIEDATTR3", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("0 ", org.jooq.impl.SQLDataType.BIGINT)), this, "Bitmap of Attributes, which have COPIED set.");

    /**
     * The column <code>KER.KER_ENTITY_TB.X_HASLIABILITY</code>. Flag is set if at least one liability type can be used with entity.
     */
    public final TableField<KerEntityTbRecord, String> X_HASLIABILITY = createField("X_HASLIABILITY", org.jooq.impl.SQLDataType.CHAR(1).nullable(false).defaultValue(org.jooq.impl.DSL.field("'N' ", org.jooq.impl.SQLDataType.CHAR)), this, "Flag is set if at least one liability type can be used with entity.");

    /**
     * The column <code>KER.KER_ENTITY_TB.ANCESTOR_ID</code>. Ancestor of this entity; it is OBJECT for most entities, but might be something else if entity is specialization of other one.
     */
    public final TableField<KerEntityTbRecord, BigInteger> ANCESTOR_ID = createField("ANCESTOR_ID", org.jooq.impl.SQLDataType.DECIMAL_INTEGER.precision(38), this, "Ancestor of this entity; it is OBJECT for most entities, but might be something else if entity is specialization of other one.");

    /**
     * The column <code>KER.KER_ENTITY_TB.MASTERTYPE</code>. Type of master-detail relationship
     */
    public final TableField<KerEntityTbRecord, String> MASTERTYPE = createField("MASTERTYPE", org.jooq.impl.SQLDataType.CHAR(1).nullable(false).defaultValue(org.jooq.impl.DSL.field("'N' ", org.jooq.impl.SQLDataType.CHAR)), this, "Type of master-detail relationship");

    /**
     * The column <code>KER.KER_ENTITY_TB.MASTER_ID</code>. Relation to master entity. Meaning of master-detail relationship is described in more detail in MASTERTYPE attribute.
     */
    public final TableField<KerEntityTbRecord, BigInteger> MASTER_ID = createField("MASTER_ID", org.jooq.impl.SQLDataType.DECIMAL_INTEGER.precision(38), this, "Relation to master entity. Meaning of master-detail relationship is described in more detail in MASTERTYPE attribute.");

    /**
     * The column <code>KER.KER_ENTITY_TB.PGPACKAGE_NM</code>. PG package (internal API, which cannot be accessed directly from client and can contain internal functions and parameters) for this entity
     */
    public final TableField<KerEntityTbRecord, String> PGPACKAGE_NM = createField("PGPACKAGE_NM", org.jooq.impl.SQLDataType.VARCHAR(30), this, "PG package (internal API, which cannot be accessed directly from client and can contain internal functions and parameters) for this entity");

    /**
     * The column <code>KER.KER_ENTITY_TB.EPPACKAGE_NM</code>. Name of EP package (read-only functions) for given object type
     */
    public final TableField<KerEntityTbRecord, String> EPPACKAGE_NM = createField("EPPACKAGE_NM", org.jooq.impl.SQLDataType.VARCHAR(30), this, "Name of EP package (read-only functions) for given object type");

    /**
     * The column <code>KER.KER_ENTITY_TB.CPPACKAGE_NM</code>. Name of CP package (API for calls from client) for given object type
     */
    public final TableField<KerEntityTbRecord, String> CPPACKAGE_NM = createField("CPPACKAGE_NM", org.jooq.impl.SQLDataType.VARCHAR(30), this, "Name of CP package (API for calls from client) for given object type");

    /**
     * The column <code>KER.KER_ENTITY_TB.FPPACKAGE_NM</code>.
     */
    public final TableField<KerEntityTbRecord, String> FPPACKAGE_NM = createField("FPPACKAGE_NM", org.jooq.impl.SQLDataType.VARCHAR(30), this, "");

    /**
     * The column <code>KER.KER_ENTITY_TB.ENTITYGRP_ID</code>. Group of entities this entity belongs to
     */
    public final TableField<KerEntityTbRecord, BigInteger> ENTITYGRP_ID = createField("ENTITYGRP_ID", org.jooq.impl.SQLDataType.DECIMAL_INTEGER.precision(38), this, "Group of entities this entity belongs to");

    /**
     * The column <code>KER.KER_ENTITY_TB.NOTE</code>. Short description of entity
     */
    public final TableField<KerEntityTbRecord, String> NOTE = createField("NOTE", org.jooq.impl.SQLDataType.VARCHAR(4000), this, "Short description of entity");

    /**
     * The column <code>KER.KER_ENTITY_TB.CUSTOMNOTE</code>. Description specific for given installation (installation specific)
     */
    public final TableField<KerEntityTbRecord, String> CUSTOMNOTE = createField("CUSTOMNOTE", org.jooq.impl.SQLDataType.VARCHAR(4000), this, "Description specific for given installation (installation specific)");

    /**
     * The column <code>KER.KER_ENTITY_TB.STRUCTUREDOC</code>. Documentation describing structure of the entity and its relations to other entities
     */
    public final TableField<KerEntityTbRecord, String> STRUCTUREDOC = createField("STRUCTUREDOC", org.jooq.impl.SQLDataType.VARCHAR(4000), this, "Documentation describing structure of the entity and its relations to other entities");

    /**
     * The column <code>KER.KER_ENTITY_TB.USAGEDOC</code>. Documentation describing standard usage of the entity
     */
    public final TableField<KerEntityTbRecord, String> USAGEDOC = createField("USAGEDOC", org.jooq.impl.SQLDataType.VARCHAR(4000), this, "Documentation describing standard usage of the entity");

    /**
     * The column <code>KER.KER_ENTITY_TB.BEHAVIOURDOC</code>. Documentation describing dynamic behaviour of the entity
     */
    public final TableField<KerEntityTbRecord, String> BEHAVIOURDOC = createField("BEHAVIOURDOC", org.jooq.impl.SQLDataType.VARCHAR(4000), this, "Documentation describing dynamic behaviour of the entity");

    /**
     * The column <code>KER.KER_ENTITY_TB.IMPLDOC</code>. Documentation describing implementation details of the entity
     */
    public final TableField<KerEntityTbRecord, String> IMPLDOC = createField("IMPLDOC", org.jooq.impl.SQLDataType.VARCHAR(4000), this, "Documentation describing implementation details of the entity");

    /**
     * The column <code>KER.KER_ENTITY_TB.SHORTDESC</code>. Short (preferably single line) description of object; template from which description is generated using GetParams generated parameters
     */
    public final TableField<KerEntityTbRecord, String> SHORTDESC = createField("SHORTDESC", org.jooq.impl.SQLDataType.VARCHAR(4000), this, "Short (preferably single line) description of object; template from which description is generated using GetParams generated parameters");

    /**
     * The column <code>KER.KER_ENTITY_TB.LONGDESC</code>. Long (multi-line) description of object; template from which description is generated using GetParams generated parameters
     */
    public final TableField<KerEntityTbRecord, String> LONGDESC = createField("LONGDESC", org.jooq.impl.SQLDataType.VARCHAR(4000), this, "Long (multi-line) description of object; template from which description is generated using GetParams generated parameters");

    /**
     * The column <code>KER.KER_ENTITY_TB.X_NOTIFYATTR4</code>. Attr Set Bitmap of attributes for which AfterCreate and AfterChange should be called - part 4
     */
    public final TableField<KerEntityTbRecord, Long> X_NOTIFYATTR4 = createField("X_NOTIFYATTR4", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("0 ", org.jooq.impl.SQLDataType.BIGINT)), this, "Attr Set Bitmap of attributes for which AfterCreate and AfterChange should be called - part 4");

    /**
     * The column <code>KER.KER_ENTITY_TB.X_FLAGDEFINTEREST4</code>. Bitmap of Attributes, changes of which should raise at least one Flag Definition (part 4)
     */
    public final TableField<KerEntityTbRecord, Long> X_FLAGDEFINTEREST4 = createField("X_FLAGDEFINTEREST4", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("0 ", org.jooq.impl.SQLDataType.BIGINT)), this, "Bitmap of Attributes, changes of which should raise at least one Flag Definition (part 4)");

    /**
     * The column <code>KER.KER_ENTITY_TB.X_DELNOTIFYATTR4</code>.
     */
    public final TableField<KerEntityTbRecord, Long> X_DELNOTIFYATTR4 = createField("X_DELNOTIFYATTR4", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("0 ", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>KER.KER_ENTITY_TB.X_COPIEDATTR4</code>. Bitmap of Attributes, which have COPIED set.
     */
    public final TableField<KerEntityTbRecord, Long> X_COPIEDATTR4 = createField("X_COPIEDATTR4", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("0 ", org.jooq.impl.SQLDataType.BIGINT)), this, "Bitmap of Attributes, which have COPIED set.");

    /**
     * The column <code>KER.KER_ENTITY_TB.USERLOCKTYPE</code>. Marks usage of user locks in given entity. If specified, system generates lock ID for each object of given entity and stores it in objects table. Predefined types of lock handling are following:
S - shared locking mode for object. System supplies methods for locking given object in shared and exclusive mode. Lock, change and delete operations on object of this type locks object (and its associated user lock) in exclusive mode as part of generated code
E - extended shared locking mode. System supplies methods for shared mode (S), shared exclusive mode (e) and exclusive mode (E). Combination of allowed lock combinations describes following table (O = both locks can be acquired, X = locks are mutually exclusive)
  N S e E
N O O O O
S O O O X
e O O X X
E O X X X
Lock, change and delete operations on object of this type locks object (and its associated user lock) in exclusive mode as part of generated code
O - locking strategy is fully handled by entity specific code, changes of locking modes are available to PG packages. This is for example used in scheduling. System does not generate any code in this case, but still generates lock id for objects of this type
     */
    public final TableField<KerEntityTbRecord, String> USERLOCKTYPE = createField("USERLOCKTYPE", org.jooq.impl.SQLDataType.CHAR(1), this, "Marks usage of user locks in given entity. If specified, system generates lock ID for each object of given entity and stores it in objects table. Predefined types of lock handling are following:\nS - shared locking mode for object. System supplies methods for locking given object in shared and exclusive mode. Lock, change and delete operations on object of this type locks object (and its associated user lock) in exclusive mode as part of generated code\nE - extended shared locking mode. System supplies methods for shared mode (S), shared exclusive mode (e) and exclusive mode (E). Combination of allowed lock combinations describes following table (O = both locks can be acquired, X = locks are mutually exclusive)\n  N S e E\nN O O O O\nS O O O X\ne O O X X\nE O X X X\nLock, change and delete operations on object of this type locks object (and its associated user lock) in exclusive mode as part of generated code\nO - locking strategy is fully handled by entity specific code, changes of locking modes are available to PG packages. This is for example used in scheduling. System does not generate any code in this case, but still generates lock id for objects of this type");

    /**
     * The column <code>KER.KER_ENTITY_TB.CONFOBJ</code>. Defines if given type acts as configuration opbject. Allowed values are N - given object is not considered configuration object and is not stored to CVS, O - given object has configuration stored to CVS and has its own configuration file, C given ob ject is child object and is stored in CVS file of other object
     */
    public final TableField<KerEntityTbRecord, String> CONFOBJ = createField("CONFOBJ", org.jooq.impl.SQLDataType.CHAR(1).nullable(false).defaultValue(org.jooq.impl.DSL.field("'N'", org.jooq.impl.SQLDataType.CHAR)), this, "Defines if given type acts as configuration opbject. Allowed values are N - given object is not considered configuration object and is not stored to CVS, O - given object has configuration stored to CVS and has its own configuration file, C given ob ject is child object and is stored in CVS file of other object");

    /**
     * The column <code>KER.KER_ENTITY_TB.BASEPATH</code>. Base path under which configuration of objects of this type should be stored in CVS. Only usable if object's configuration is stored in CVS
     */
    public final TableField<KerEntityTbRecord, String> BASEPATH = createField("BASEPATH", org.jooq.impl.SQLDataType.VARCHAR(200), this, "Base path under which configuration of objects of this type should be stored in CVS. Only usable if object's configuration is stored in CVS");

    /**
     * The column <code>KER.KER_ENTITY_TB.IMPORTTYPE</code>. Defines processing of objects of this type during upgrades
N - untouched, this entity is not considered part of configuration (eg. programmes)
C - import; all items considered custom and only transferred from test to live database, not transferred from standard (eg. channels)
S - import; all items are considered standard and are synchronised with standard release (eg. domains)
A - import; standard/custom items distinguished by properties (eg. entities)
M - manually processed - import procedure doesn't exist, still these items are transferred during upgrades
     */
    public final TableField<KerEntityTbRecord, String> IMPORTTYPE = createField("IMPORTTYPE", org.jooq.impl.SQLDataType.CHAR(1), this, "Defines processing of objects of this type during upgrades\nN - untouched, this entity is not considered part of configuration (eg. programmes)\nC - import; all items considered custom and only transferred from test to live database, not transferred from standard (eg. channels)\nS - import; all items are considered standard and are synchronised with standard release (eg. domains)\nA - import; standard/custom items distinguished by properties (eg. entities)\nM - manually processed - import procedure doesn't exist, still these items are transferred during upgrades");

    /**
     * The column <code>KER.KER_ENTITY_TB.USECONFOBJ</code>. Flag indicates, if version control is used for this object as master object. Has to be NULL if CONFOBJ is not set to O, is mandatory and defaults to N when CONFOBJ is changed to O
     */
    public final TableField<KerEntityTbRecord, String> USECONFOBJ = createField("USECONFOBJ", org.jooq.impl.SQLDataType.CHAR(1), this, "Flag indicates, if version control is used for this object as master object. Has to be NULL if CONFOBJ is not set to O, is mandatory and defaults to N when CONFOBJ is changed to O");

    /**
     * The column <code>KER.KER_ENTITY_TB.X_USECONFOBJ</code>. Calculated flag indicating if objects of this type are stored in version control; it is same as USECONFOBJ for master objects, is inherits value from entity, attached through relation with Cascade XML export set
     */
    public final TableField<KerEntityTbRecord, String> X_USECONFOBJ = createField("X_USECONFOBJ", org.jooq.impl.SQLDataType.CHAR(1), this, "Calculated flag indicating if objects of this type are stored in version control; it is same as USECONFOBJ for master objects, is inherits value from entity, attached through relation with Cascade XML export set");

    /**
     * The column <code>KER.KER_ENTITY_TB.CONFOBJALL</code>. Indicates if all objects of this type are "masters" (from configuration point of view) or some are considered service objects and are exported with other objects. Can only be set for objects with CONFOBJ set to O, default is Y in this case. If set to N, it is expected that method mf_IsConfObj is implemented in PG package that indicates which object is master and which is included
     */
    public final TableField<KerEntityTbRecord, String> CONFOBJALL = createField("CONFOBJALL", org.jooq.impl.SQLDataType.CHAR(1), this, "Indicates if all objects of this type are \"masters\" (from configuration point of view) or some are considered service objects and are exported with other objects. Can only be set for objects with CONFOBJ set to O, default is Y in this case. If set to N, it is expected that method mf_IsConfObj is implemented in PG package that indicates which object is master and which is included");

    /**
     * The column <code>KER.KER_ENTITY_TB.X_NOTIFYATTR5</code>. Attr Set Bitmap of attributes for which AfterCreate and AfterChange should be called - part 5
     */
    public final TableField<KerEntityTbRecord, Long> X_NOTIFYATTR5 = createField("X_NOTIFYATTR5", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("0\n  ", org.jooq.impl.SQLDataType.BIGINT)), this, "Attr Set Bitmap of attributes for which AfterCreate and AfterChange should be called - part 5");

    /**
     * The column <code>KER.KER_ENTITY_TB.X_FLAGDEFINTEREST5</code>. Bitmap of Attributes, changes of which should raise at least one Flag Definition (part 5)
     */
    public final TableField<KerEntityTbRecord, Long> X_FLAGDEFINTEREST5 = createField("X_FLAGDEFINTEREST5", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("0\n  ", org.jooq.impl.SQLDataType.BIGINT)), this, "Bitmap of Attributes, changes of which should raise at least one Flag Definition (part 5)");

    /**
     * The column <code>KER.KER_ENTITY_TB.X_DELNOTIFYATTR5</code>.
     */
    public final TableField<KerEntityTbRecord, Long> X_DELNOTIFYATTR5 = createField("X_DELNOTIFYATTR5", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("0\n  ", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>KER.KER_ENTITY_TB.X_COPIEDATTR5</code>. Bitmap of Attributes, which have COPIED set.
     */
    public final TableField<KerEntityTbRecord, Long> X_COPIEDATTR5 = createField("X_COPIEDATTR5", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("0 \n", org.jooq.impl.SQLDataType.BIGINT)), this, "Bitmap of Attributes, which have COPIED set.");

    /**
     * Create a <code>KER.KER_ENTITY_TB</code> table reference
     */
    public KerEntityTb() {
        this(DSL.name("KER_ENTITY_TB"), null);
    }

    /**
     * Create an aliased <code>KER.KER_ENTITY_TB</code> table reference
     */
    public KerEntityTb(String alias) {
        this(DSL.name(alias), KER_ENTITY_TB);
    }

    /**
     * Create an aliased <code>KER.KER_ENTITY_TB</code> table reference
     */
    public KerEntityTb(Name alias) {
        this(alias, KER_ENTITY_TB);
    }

    private KerEntityTb(Name alias, Table<KerEntityTbRecord> aliased) {
        this(alias, aliased, null);
    }

    private KerEntityTb(Name alias, Table<KerEntityTbRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("Entities (catalogue)"));
    }

    public <O extends Record> KerEntityTb(Table<O> child, ForeignKey<O, KerEntityTbRecord> key) {
        super(child, key, KER_ENTITY_TB);
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
        return Arrays.<Index>asList(Indexes.KER_ENTITY_ANCESTOR_IX, Indexes.KER_ENTITY_ENTITYGRP_IX, Indexes.KER_ENTITY_GETOPTYPE_IX, Indexes.KER_ENTITY_GETRIGHTRESTR_IX, Indexes.KER_ENTITY_MASTER_IX, Indexes.KER_ENTITY_NM_IX, Indexes.KER_ENTITY_PK, Indexes.KER_ENTITY_TABLE_IX, Indexes.KER_ENTITY_TEXTTABLE_IX, Indexes.KER_ENTITY_TEXTVIEW_IX, Indexes.KER_ENTITY_UPNAME_IX, Indexes.KER_ENTITY_VIEW_IX);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<KerEntityTbRecord> getPrimaryKey() {
        return Keys.KER_ENTITY_PK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<KerEntityTbRecord>> getKeys() {
        return Arrays.<UniqueKey<KerEntityTbRecord>>asList(Keys.KER_ENTITY_PK, Keys.KER_ENTITY_NM_UK, Keys.KER_ENTITY_UPNAME_UK, Keys.KER_ENTITY_VIEW_UK, Keys.KER_ENTITY_TEXTVIEW_UK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<KerEntityTbRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<KerEntityTbRecord, ?>>asList(Keys.KER_ENTITY_ANCESTOR_FK);
    }

    public com.provys.catalogue.db.tables.KerEntityTb kerEntityTb() {
        return new com.provys.catalogue.db.tables.KerEntityTb(this, Keys.KER_ENTITY_ANCESTOR_FK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public KerEntityTb as(String alias) {
        return new KerEntityTb(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public KerEntityTb as(Name alias) {
        return new KerEntityTb(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public KerEntityTb rename(String name) {
        return new KerEntityTb(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public KerEntityTb rename(Name name) {
        return new KerEntityTb(name, null);
    }
}