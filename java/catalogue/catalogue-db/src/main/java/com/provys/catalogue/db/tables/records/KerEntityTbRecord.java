/*
 * This file is generated by jOOQ.
 */
package com.provys.catalogue.db.tables.records;


import com.provys.catalogue.db.tables.KerEntityTb;

import java.math.BigInteger;

import javax.annotation.Generated;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


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
public class KerEntityTbRecord extends UpdatableRecordImpl<KerEntityTbRecord> {

    private static final long serialVersionUID = 1474033044;

    /**
     * Setter for <code>KER.KER_ENTITY_TB.ENTITY_ID</code>. UID
     */
    public void setEntityId(BigInteger value) {
        set(0, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.ENTITY_ID</code>. UID
     */
    public BigInteger getEntityId() {
        return (BigInteger) get(0);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.NAME_NM</code>. Internal name of entity; base for NSLSHORT
     */
    public void setNameNm(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.NAME_NM</code>. Internal name of entity; base for NSLSHORT
     */
    public String getNameNm() {
        return (String) get(1);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.NAME</code>. Entity name, base for NLSNAME
     */
    public void setName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.NAME</code>. Entity name, base for NLSNAME
     */
    public String getName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.X_UPNAME</code>. Calculated column - uppercase of NAME
     */
    public void setXUpname(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.X_UPNAME</code>. Calculated column - uppercase of NAME
     */
    public String getXUpname() {
        return (String) get(3);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.ISCUSTOM</code>. Entity is custom; such entities are not synchronized during upgrade.
     */
    public void setIscustom(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.ISCUSTOM</code>. Entity is custom; such entities are not synchronized during upgrade.
     */
    public String getIscustom() {
        return (String) get(4);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.ISUSED</code>. Entity is used on given installation.
     */
    public void setIsused(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.ISUSED</code>. Entity is used on given installation.
     */
    public String getIsused() {
        return (String) get(5);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.OBJECTCLASS</code>. Indicates if it is possible to create object of given type.
     */
    public void setObjectclass(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.OBJECTCLASS</code>. Indicates if it is possible to create object of given type.
     */
    public String getObjectclass() {
        return (String) get(6);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.TABLE_NM</code>. Name of base table/view, containing instances of entity.
     */
    public void setTableNm(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.TABLE_NM</code>. Name of base table/view, containing instances of entity.
     */
    public String getTableNm() {
        return (String) get(7);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.VIEW_NM</code>. Name of view to be generated for entity.
     */
    public void setViewNm(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.VIEW_NM</code>. Name of view to be generated for entity.
     */
    public String getViewNm() {
        return (String) get(8);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.KEY_NM</code>. Name of primary key of entity (in table TABLE_NM)
     */
    public void setKeyNm(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.KEY_NM</code>. Name of primary key of entity (in table TABLE_NM)
     */
    public String getKeyNm() {
        return (String) get(9);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.TEXTTABLE_NM</code>. Name of table with entity text translations for current configuration
     */
    public void setTexttableNm(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.TEXTTABLE_NM</code>. Name of table with entity text translations for current configuration
     */
    public String getTexttableNm() {
        return (String) get(10);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.TEXTVIEW_NM</code>. Name of view with translations to be generated for entity
     */
    public void setTextviewNm(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.TEXTVIEW_NM</code>. Name of view with translations to be generated for entity
     */
    public String getTextviewNm() {
        return (String) get(11);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.TEXTKEY_NM</code>. Primary key counterpart in TEXTTABLE_NM table.
     */
    public void setTextkeyNm(String value) {
        set(12, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.TEXTKEY_NM</code>. Primary key counterpart in TEXTTABLE_NM table.
     */
    public String getTextkeyNm() {
        return (String) get(12);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.TEXTALL</code>. Flag indicating, that all rows in text table belong to entity
     */
    public void setTextall(String value) {
        set(13, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.TEXTALL</code>. Flag indicating, that all rows in text table belong to entity
     */
    public String getTextall() {
        return (String) get(13);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.GETRIGHTCUSTOM</code>. Access rights for entity on installation are installation specific. They will not be overwritten during upgrade.
     */
    public void setGetrightcustom(String value) {
        set(14, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.GETRIGHTCUSTOM</code>. Access rights for entity on installation are installation specific. They will not be overwritten during upgrade.
     */
    public String getGetrightcustom() {
        return (String) get(14);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.GETOPTYPE_ID</code>. Operation type to be tested when accessing objects. Checks are compiled to entity view. Note that it might is possible to access entity's data through DW views on other entities where this entity is joined. It is also possible to bypass rights tests using EP functions.
     */
    public void setGetoptypeId(BigInteger value) {
        set(15, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.GETOPTYPE_ID</code>. Operation type to be tested when accessing objects. Checks are compiled to entity view. Note that it might is possible to access entity's data through DW views on other entities where this entity is joined. It is also possible to bypass rights tests using EP functions.
     */
    public BigInteger getGetoptypeId() {
        return (BigInteger) get(15);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.GETRIGHTRESTR_ID</code>. Right restriction, defining objects that are accessible to all users (public).
     */
    public void setGetrightrestrId(BigInteger value) {
        set(16, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.GETRIGHTRESTR_ID</code>. Right restriction, defining objects that are accessible to all users (public).
     */
    public BigInteger getGetrightrestrId() {
        return (BigInteger) get(16);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.KEEPDELETED</code>. This field can be used to retain record in object table given number of days after actually deleting object from system. History (of attribute values), remarks and liabilities are kept along with object. Financial data attached to object are cancelled immediately even if object is kept.
     */
    public void setKeepdeleted(Integer value) {
        set(17, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.KEEPDELETED</code>. This field can be used to retain record in object table given number of days after actually deleting object from system. History (of attribute values), remarks and liabilities are kept along with object. Financial data attached to object are cancelled immediately even if object is kept.
     */
    public Integer getKeepdeleted() {
        return (Integer) get(17);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.X_NOTIFYATTR0</code>. Attr Set Bitmap of attributes for which AfterCreate and AfterChange should be called - part 0
     */
    public void setXNotifyattr0(Long value) {
        set(18, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.X_NOTIFYATTR0</code>. Attr Set Bitmap of attributes for which AfterCreate and AfterChange should be called - part 0
     */
    public Long getXNotifyattr0() {
        return (Long) get(18);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.X_NOTIFYATTR1</code>. Attr Set Bitmap of attributes for which AfterCreate and AfterChange should be called - part 1
     */
    public void setXNotifyattr1(Long value) {
        set(19, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.X_NOTIFYATTR1</code>. Attr Set Bitmap of attributes for which AfterCreate and AfterChange should be called - part 1
     */
    public Long getXNotifyattr1() {
        return (Long) get(19);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.X_NOTIFYATTR2</code>. Attr Set Bitmap of attributes for which AfterCreate and AfterChange should be called - part 2
     */
    public void setXNotifyattr2(Long value) {
        set(20, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.X_NOTIFYATTR2</code>. Attr Set Bitmap of attributes for which AfterCreate and AfterChange should be called - part 2
     */
    public Long getXNotifyattr2() {
        return (Long) get(20);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.X_NOTIFYATTR3</code>. Attr Set Bitmap of attributes for which AfterCreate and AfterChange should be called - part 3
     */
    public void setXNotifyattr3(Long value) {
        set(21, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.X_NOTIFYATTR3</code>. Attr Set Bitmap of attributes for which AfterCreate and AfterChange should be called - part 3
     */
    public Long getXNotifyattr3() {
        return (Long) get(21);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.X_FLAGDEFINTEREST0</code>. Bitmap of Attributes, changes of which should raise at least one Flag Definition (part 0)
     */
    public void setXFlagdefinterest0(Long value) {
        set(22, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.X_FLAGDEFINTEREST0</code>. Bitmap of Attributes, changes of which should raise at least one Flag Definition (part 0)
     */
    public Long getXFlagdefinterest0() {
        return (Long) get(22);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.X_FLAGDEFINTEREST1</code>. Bitmap of Attributes, changes of which should raise at least one Flag Definition (part 1)
     */
    public void setXFlagdefinterest1(Long value) {
        set(23, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.X_FLAGDEFINTEREST1</code>. Bitmap of Attributes, changes of which should raise at least one Flag Definition (part 1)
     */
    public Long getXFlagdefinterest1() {
        return (Long) get(23);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.X_FLAGDEFINTEREST2</code>. Bitmap of Attributes, changes of which should raise at least one Flag Definition (part 2)
     */
    public void setXFlagdefinterest2(Long value) {
        set(24, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.X_FLAGDEFINTEREST2</code>. Bitmap of Attributes, changes of which should raise at least one Flag Definition (part 2)
     */
    public Long getXFlagdefinterest2() {
        return (Long) get(24);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.X_FLAGDEFINTEREST3</code>. Bitmap of Attributes, changes of which should raise at least one Flag Definition (part 3)
     */
    public void setXFlagdefinterest3(Long value) {
        set(25, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.X_FLAGDEFINTEREST3</code>. Bitmap of Attributes, changes of which should raise at least one Flag Definition (part 3)
     */
    public Long getXFlagdefinterest3() {
        return (Long) get(25);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.X_DELNOTIFYATTR0</code>. Bitmap of Attributes, for which AfterDelete should be called
     */
    public void setXDelnotifyattr0(Long value) {
        set(26, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.X_DELNOTIFYATTR0</code>. Bitmap of Attributes, for which AfterDelete should be called
     */
    public Long getXDelnotifyattr0() {
        return (Long) get(26);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.X_DELNOTIFYATTR1</code>.
     */
    public void setXDelnotifyattr1(Long value) {
        set(27, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.X_DELNOTIFYATTR1</code>.
     */
    public Long getXDelnotifyattr1() {
        return (Long) get(27);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.X_DELNOTIFYATTR2</code>.
     */
    public void setXDelnotifyattr2(Long value) {
        set(28, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.X_DELNOTIFYATTR2</code>.
     */
    public Long getXDelnotifyattr2() {
        return (Long) get(28);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.X_DELNOTIFYATTR3</code>.
     */
    public void setXDelnotifyattr3(Long value) {
        set(29, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.X_DELNOTIFYATTR3</code>.
     */
    public Long getXDelnotifyattr3() {
        return (Long) get(29);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.X_COPIEDATTR0</code>. Bitmap of Attributes, which have COPIED set.
     */
    public void setXCopiedattr0(Long value) {
        set(30, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.X_COPIEDATTR0</code>. Bitmap of Attributes, which have COPIED set.
     */
    public Long getXCopiedattr0() {
        return (Long) get(30);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.X_COPIEDATTR1</code>. Bitmap of Attributes, which have COPIED set.
     */
    public void setXCopiedattr1(Long value) {
        set(31, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.X_COPIEDATTR1</code>. Bitmap of Attributes, which have COPIED set.
     */
    public Long getXCopiedattr1() {
        return (Long) get(31);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.X_COPIEDATTR2</code>. Bitmap of Attributes, which have COPIED set.
     */
    public void setXCopiedattr2(Long value) {
        set(32, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.X_COPIEDATTR2</code>. Bitmap of Attributes, which have COPIED set.
     */
    public Long getXCopiedattr2() {
        return (Long) get(32);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.X_COPIEDATTR3</code>. Bitmap of Attributes, which have COPIED set.
     */
    public void setXCopiedattr3(Long value) {
        set(33, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.X_COPIEDATTR3</code>. Bitmap of Attributes, which have COPIED set.
     */
    public Long getXCopiedattr3() {
        return (Long) get(33);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.X_HASLIABILITY</code>. Flag is set if at least one liability type can be used with entity.
     */
    public void setXHasliability(String value) {
        set(34, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.X_HASLIABILITY</code>. Flag is set if at least one liability type can be used with entity.
     */
    public String getXHasliability() {
        return (String) get(34);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.ANCESTOR_ID</code>. Ancestor of this entity; it is OBJECT for most entities, but might be something else if entity is specialization of other one.
     */
    public void setAncestorId(BigInteger value) {
        set(35, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.ANCESTOR_ID</code>. Ancestor of this entity; it is OBJECT for most entities, but might be something else if entity is specialization of other one.
     */
    public BigInteger getAncestorId() {
        return (BigInteger) get(35);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.MASTERTYPE</code>. Type of master-detail relationship
     */
    public void setMastertype(String value) {
        set(36, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.MASTERTYPE</code>. Type of master-detail relationship
     */
    public String getMastertype() {
        return (String) get(36);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.MASTER_ID</code>. Relation to master entity. Meaning of master-detail relationship is described in more detail in MASTERTYPE attribute.
     */
    public void setMasterId(BigInteger value) {
        set(37, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.MASTER_ID</code>. Relation to master entity. Meaning of master-detail relationship is described in more detail in MASTERTYPE attribute.
     */
    public BigInteger getMasterId() {
        return (BigInteger) get(37);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.PGPACKAGE_NM</code>. PG package (internal API, which cannot be accessed directly from client and can contain internal functions and parameters) for this entity
     */
    public void setPgpackageNm(String value) {
        set(38, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.PGPACKAGE_NM</code>. PG package (internal API, which cannot be accessed directly from client and can contain internal functions and parameters) for this entity
     */
    public String getPgpackageNm() {
        return (String) get(38);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.EPPACKAGE_NM</code>. Name of EP package (read-only functions) for given object type
     */
    public void setEppackageNm(String value) {
        set(39, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.EPPACKAGE_NM</code>. Name of EP package (read-only functions) for given object type
     */
    public String getEppackageNm() {
        return (String) get(39);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.CPPACKAGE_NM</code>. Name of CP package (API for calls from client) for given object type
     */
    public void setCppackageNm(String value) {
        set(40, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.CPPACKAGE_NM</code>. Name of CP package (API for calls from client) for given object type
     */
    public String getCppackageNm() {
        return (String) get(40);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.FPPACKAGE_NM</code>.
     */
    public void setFppackageNm(String value) {
        set(41, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.FPPACKAGE_NM</code>.
     */
    public String getFppackageNm() {
        return (String) get(41);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.ENTITYGRP_ID</code>. Group of entities this entity belongs to
     */
    public void setEntitygrpId(BigInteger value) {
        set(42, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.ENTITYGRP_ID</code>. Group of entities this entity belongs to
     */
    public BigInteger getEntitygrpId() {
        return (BigInteger) get(42);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.NOTE</code>. Short description of entity
     */
    public void setNote(String value) {
        set(43, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.NOTE</code>. Short description of entity
     */
    public String getNote() {
        return (String) get(43);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.CUSTOMNOTE</code>. Description specific for given installation (installation specific)
     */
    public void setCustomnote(String value) {
        set(44, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.CUSTOMNOTE</code>. Description specific for given installation (installation specific)
     */
    public String getCustomnote() {
        return (String) get(44);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.STRUCTUREDOC</code>. Documentation describing structure of the entity and its relations to other entities
     */
    public void setStructuredoc(String value) {
        set(45, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.STRUCTUREDOC</code>. Documentation describing structure of the entity and its relations to other entities
     */
    public String getStructuredoc() {
        return (String) get(45);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.USAGEDOC</code>. Documentation describing standard usage of the entity
     */
    public void setUsagedoc(String value) {
        set(46, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.USAGEDOC</code>. Documentation describing standard usage of the entity
     */
    public String getUsagedoc() {
        return (String) get(46);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.BEHAVIOURDOC</code>. Documentation describing dynamic behaviour of the entity
     */
    public void setBehaviourdoc(String value) {
        set(47, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.BEHAVIOURDOC</code>. Documentation describing dynamic behaviour of the entity
     */
    public String getBehaviourdoc() {
        return (String) get(47);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.IMPLDOC</code>. Documentation describing implementation details of the entity
     */
    public void setImpldoc(String value) {
        set(48, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.IMPLDOC</code>. Documentation describing implementation details of the entity
     */
    public String getImpldoc() {
        return (String) get(48);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.SHORTDESC</code>. Short (preferably single line) description of object; template from which description is generated using GetParams generated parameters
     */
    public void setShortdesc(String value) {
        set(49, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.SHORTDESC</code>. Short (preferably single line) description of object; template from which description is generated using GetParams generated parameters
     */
    public String getShortdesc() {
        return (String) get(49);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.LONGDESC</code>. Long (multi-line) description of object; template from which description is generated using GetParams generated parameters
     */
    public void setLongdesc(String value) {
        set(50, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.LONGDESC</code>. Long (multi-line) description of object; template from which description is generated using GetParams generated parameters
     */
    public String getLongdesc() {
        return (String) get(50);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.X_NOTIFYATTR4</code>. Attr Set Bitmap of attributes for which AfterCreate and AfterChange should be called - part 4
     */
    public void setXNotifyattr4(Long value) {
        set(51, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.X_NOTIFYATTR4</code>. Attr Set Bitmap of attributes for which AfterCreate and AfterChange should be called - part 4
     */
    public Long getXNotifyattr4() {
        return (Long) get(51);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.X_FLAGDEFINTEREST4</code>. Bitmap of Attributes, changes of which should raise at least one Flag Definition (part 4)
     */
    public void setXFlagdefinterest4(Long value) {
        set(52, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.X_FLAGDEFINTEREST4</code>. Bitmap of Attributes, changes of which should raise at least one Flag Definition (part 4)
     */
    public Long getXFlagdefinterest4() {
        return (Long) get(52);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.X_DELNOTIFYATTR4</code>.
     */
    public void setXDelnotifyattr4(Long value) {
        set(53, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.X_DELNOTIFYATTR4</code>.
     */
    public Long getXDelnotifyattr4() {
        return (Long) get(53);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.X_COPIEDATTR4</code>. Bitmap of Attributes, which have COPIED set.
     */
    public void setXCopiedattr4(Long value) {
        set(54, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.X_COPIEDATTR4</code>. Bitmap of Attributes, which have COPIED set.
     */
    public Long getXCopiedattr4() {
        return (Long) get(54);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.USERLOCKTYPE</code>. Marks usage of user locks in given entity. If specified, system generates lock ID for each object of given entity and stores it in objects table. Predefined types of lock handling are following:
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
    public void setUserlocktype(String value) {
        set(55, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.USERLOCKTYPE</code>. Marks usage of user locks in given entity. If specified, system generates lock ID for each object of given entity and stores it in objects table. Predefined types of lock handling are following:
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
    public String getUserlocktype() {
        return (String) get(55);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.CONFOBJ</code>. Defines if given type acts as configuration opbject. Allowed values are N - given object is not considered configuration object and is not stored to CVS, O - given object has configuration stored to CVS and has its own configuration file, C given ob ject is child object and is stored in CVS file of other object
     */
    public void setConfobj(String value) {
        set(56, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.CONFOBJ</code>. Defines if given type acts as configuration opbject. Allowed values are N - given object is not considered configuration object and is not stored to CVS, O - given object has configuration stored to CVS and has its own configuration file, C given ob ject is child object and is stored in CVS file of other object
     */
    public String getConfobj() {
        return (String) get(56);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.BASEPATH</code>. Base path under which configuration of objects of this type should be stored in CVS. Only usable if object's configuration is stored in CVS
     */
    public void setBasepath(String value) {
        set(57, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.BASEPATH</code>. Base path under which configuration of objects of this type should be stored in CVS. Only usable if object's configuration is stored in CVS
     */
    public String getBasepath() {
        return (String) get(57);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.IMPORTTYPE</code>. Defines processing of objects of this type during upgrades
N - untouched, this entity is not considered part of configuration (eg. programmes)
C - import; all items considered custom and only transferred from test to live database, not transferred from standard (eg. channels)
S - import; all items are considered standard and are synchronised with standard release (eg. domains)
A - import; standard/custom items distinguished by properties (eg. entities)
M - manually processed - import procedure doesn't exist, still these items are transferred during upgrades
     */
    public void setImporttype(String value) {
        set(58, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.IMPORTTYPE</code>. Defines processing of objects of this type during upgrades
N - untouched, this entity is not considered part of configuration (eg. programmes)
C - import; all items considered custom and only transferred from test to live database, not transferred from standard (eg. channels)
S - import; all items are considered standard and are synchronised with standard release (eg. domains)
A - import; standard/custom items distinguished by properties (eg. entities)
M - manually processed - import procedure doesn't exist, still these items are transferred during upgrades
     */
    public String getImporttype() {
        return (String) get(58);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.USECONFOBJ</code>. Flag indicates, if version control is used for this object as master object. Has to be NULL if CONFOBJ is not set to O, is mandatory and defaults to N when CONFOBJ is changed to O
     */
    public void setUseconfobj(String value) {
        set(59, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.USECONFOBJ</code>. Flag indicates, if version control is used for this object as master object. Has to be NULL if CONFOBJ is not set to O, is mandatory and defaults to N when CONFOBJ is changed to O
     */
    public String getUseconfobj() {
        return (String) get(59);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.X_USECONFOBJ</code>. Calculated flag indicating if objects of this type are stored in version control; it is same as USECONFOBJ for master objects, is inherits value from entity, attached through relation with Cascade XML export set
     */
    public void setXUseconfobj(String value) {
        set(60, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.X_USECONFOBJ</code>. Calculated flag indicating if objects of this type are stored in version control; it is same as USECONFOBJ for master objects, is inherits value from entity, attached through relation with Cascade XML export set
     */
    public String getXUseconfobj() {
        return (String) get(60);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.CONFOBJALL</code>. Indicates if all objects of this type are "masters" (from configuration point of view) or some are considered service objects and are exported with other objects. Can only be set for objects with CONFOBJ set to O, default is Y in this case. If set to N, it is expected that method mf_IsConfObj is implemented in PG package that indicates which object is master and which is included
     */
    public void setConfobjall(String value) {
        set(61, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.CONFOBJALL</code>. Indicates if all objects of this type are "masters" (from configuration point of view) or some are considered service objects and are exported with other objects. Can only be set for objects with CONFOBJ set to O, default is Y in this case. If set to N, it is expected that method mf_IsConfObj is implemented in PG package that indicates which object is master and which is included
     */
    public String getConfobjall() {
        return (String) get(61);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.X_NOTIFYATTR5</code>. Attr Set Bitmap of attributes for which AfterCreate and AfterChange should be called - part 5
     */
    public void setXNotifyattr5(Long value) {
        set(62, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.X_NOTIFYATTR5</code>. Attr Set Bitmap of attributes for which AfterCreate and AfterChange should be called - part 5
     */
    public Long getXNotifyattr5() {
        return (Long) get(62);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.X_FLAGDEFINTEREST5</code>. Bitmap of Attributes, changes of which should raise at least one Flag Definition (part 5)
     */
    public void setXFlagdefinterest5(Long value) {
        set(63, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.X_FLAGDEFINTEREST5</code>. Bitmap of Attributes, changes of which should raise at least one Flag Definition (part 5)
     */
    public Long getXFlagdefinterest5() {
        return (Long) get(63);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.X_DELNOTIFYATTR5</code>.
     */
    public void setXDelnotifyattr5(Long value) {
        set(64, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.X_DELNOTIFYATTR5</code>.
     */
    public Long getXDelnotifyattr5() {
        return (Long) get(64);
    }

    /**
     * Setter for <code>KER.KER_ENTITY_TB.X_COPIEDATTR5</code>. Bitmap of Attributes, which have COPIED set.
     */
    public void setXCopiedattr5(Long value) {
        set(65, value);
    }

    /**
     * Getter for <code>KER.KER_ENTITY_TB.X_COPIEDATTR5</code>. Bitmap of Attributes, which have COPIED set.
     */
    public Long getXCopiedattr5() {
        return (Long) get(65);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<BigInteger> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached KerEntityTbRecord
     */
    public KerEntityTbRecord() {
        super(KerEntityTb.KER_ENTITY_TB);
    }

    /**
     * Create a detached, initialised KerEntityTbRecord
     */
    public KerEntityTbRecord(BigInteger entityId, String nameNm, String name, String xUpname, String iscustom, String isused, String objectclass, String tableNm, String viewNm, String keyNm, String texttableNm, String textviewNm, String textkeyNm, String textall, String getrightcustom, BigInteger getoptypeId, BigInteger getrightrestrId, Integer keepdeleted, Long xNotifyattr0, Long xNotifyattr1, Long xNotifyattr2, Long xNotifyattr3, Long xFlagdefinterest0, Long xFlagdefinterest1, Long xFlagdefinterest2, Long xFlagdefinterest3, Long xDelnotifyattr0, Long xDelnotifyattr1, Long xDelnotifyattr2, Long xDelnotifyattr3, Long xCopiedattr0, Long xCopiedattr1, Long xCopiedattr2, Long xCopiedattr3, String xHasliability, BigInteger ancestorId, String mastertype, BigInteger masterId, String pgpackageNm, String eppackageNm, String cppackageNm, String fppackageNm, BigInteger entitygrpId, String note, String customnote, String structuredoc, String usagedoc, String behaviourdoc, String impldoc, String shortdesc, String longdesc, Long xNotifyattr4, Long xFlagdefinterest4, Long xDelnotifyattr4, Long xCopiedattr4, String userlocktype, String confobj, String basepath, String importtype, String useconfobj, String xUseconfobj, String confobjall, Long xNotifyattr5, Long xFlagdefinterest5, Long xDelnotifyattr5, Long xCopiedattr5) {
        super(KerEntityTb.KER_ENTITY_TB);

        set(0, entityId);
        set(1, nameNm);
        set(2, name);
        set(3, xUpname);
        set(4, iscustom);
        set(5, isused);
        set(6, objectclass);
        set(7, tableNm);
        set(8, viewNm);
        set(9, keyNm);
        set(10, texttableNm);
        set(11, textviewNm);
        set(12, textkeyNm);
        set(13, textall);
        set(14, getrightcustom);
        set(15, getoptypeId);
        set(16, getrightrestrId);
        set(17, keepdeleted);
        set(18, xNotifyattr0);
        set(19, xNotifyattr1);
        set(20, xNotifyattr2);
        set(21, xNotifyattr3);
        set(22, xFlagdefinterest0);
        set(23, xFlagdefinterest1);
        set(24, xFlagdefinterest2);
        set(25, xFlagdefinterest3);
        set(26, xDelnotifyattr0);
        set(27, xDelnotifyattr1);
        set(28, xDelnotifyattr2);
        set(29, xDelnotifyattr3);
        set(30, xCopiedattr0);
        set(31, xCopiedattr1);
        set(32, xCopiedattr2);
        set(33, xCopiedattr3);
        set(34, xHasliability);
        set(35, ancestorId);
        set(36, mastertype);
        set(37, masterId);
        set(38, pgpackageNm);
        set(39, eppackageNm);
        set(40, cppackageNm);
        set(41, fppackageNm);
        set(42, entitygrpId);
        set(43, note);
        set(44, customnote);
        set(45, structuredoc);
        set(46, usagedoc);
        set(47, behaviourdoc);
        set(48, impldoc);
        set(49, shortdesc);
        set(50, longdesc);
        set(51, xNotifyattr4);
        set(52, xFlagdefinterest4);
        set(53, xDelnotifyattr4);
        set(54, xCopiedattr4);
        set(55, userlocktype);
        set(56, confobj);
        set(57, basepath);
        set(58, importtype);
        set(59, useconfobj);
        set(60, xUseconfobj);
        set(61, confobjall);
        set(62, xNotifyattr5);
        set(63, xFlagdefinterest5);
        set(64, xDelnotifyattr5);
        set(65, xCopiedattr5);
    }
}
