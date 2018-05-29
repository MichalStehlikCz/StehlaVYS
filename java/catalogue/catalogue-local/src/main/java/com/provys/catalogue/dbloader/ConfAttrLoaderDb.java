/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.catalogue.dbloader;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.SQLException;
import com.provys.catalogue.model.ConfAttr;
import com.provys.common.datatypes.*;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;
import oracle.sql.*;
import com.provys.common.confobj.ConfObjectLoader;
import com.provys.common.confobj.RowidObjectPair;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author micha
 */
@Stateless
public class ConfAttrLoaderDb extends ConfObjectLoader<ConfAttr> {

    private static final String LOAD_SELECT =
            "SELECT"
            +"\n  alattr.attr_id"
            +"\n, alattr.entity_id"
            +"\n, alattr.name_nm"
            +"\n, alattr.ROWID"
            +"\nFROM"
            +"\n  ker_attr_tb alattr"
            ;

    private static final String LOAD_WHERE_BY_ID =
            "\nWHERE"
            +"\n      (alattr.attr_id=:attr_id)"
            ;
    
    private static final String LOAD_WHERE_BY_ENTITY_ID =
            "\nWHERE"
            +"\n      (alattr.entity_id=:entity_id)"
            ;
    
    @Override
    public RowidObjectPair<ConfAttr> load(DtUid id) {
        ROWID rowid;
        ConfAttr confAttr;
        try (Connection connection = provysDB.getConnection()){
            OraclePreparedStatement statement
                    = (OraclePreparedStatement) connection.prepareStatement(
                    LOAD_SELECT+LOAD_WHERE_BY_ID).unwrap(OraclePreparedStatement.class);
            statement.setOracleObjectAtName("attr_id", new NUMBER(id.getValue()));
            OracleResultSet resultSet = (OracleResultSet)statement.executeQuery();
            if (!resultSet.next()) {
                throw new RuntimeException("Not found entity entity_id="+id.getValue());
            }
            DtUid entityId = new DtUid(resultSet.getNUMBER("ENTITY_ID").stringValue());
            DtNameNm nameNm = new DtNameNm(resultSet.getString("NAME_NM"));
            rowid = resultSet.getROWID("ROWID");
            confAttr=new ConfAttr(id, entityId, nameNm);
        } catch (SQLException e) {
            throw new RuntimeException("SQL error trying to retrieve attr", e);
        }
        return new RowidObjectPair<>(rowid, confAttr);
    }

    public List<RowidObjectPair<ConfAttr>> loadByEntityId(DtUid entityId) {
        List<RowidObjectPair<ConfAttr>> result = new ArrayList();
        try (Connection connection = provysDB.getConnection()){
            OraclePreparedStatement statement
                    = (OraclePreparedStatement) connection.prepareStatement(
                    LOAD_SELECT+LOAD_WHERE_BY_ENTITY_ID).unwrap(OraclePreparedStatement.class);
            statement.setOracleObjectAtName("entity_id", new NUMBER(entityId.getValue()));
            OracleResultSet resultSet = (OracleResultSet)statement.executeQuery();
            while (resultSet.next()) {
                DtUid id = new DtUid(resultSet.getNUMBER("ATTR_ID").stringValue());
                DtNameNm nameNm = new DtNameNm(resultSet.getString("NAME_NM"));
                result.add(new RowidObjectPair<>(resultSet.getROWID("ROWID")
                        , new ConfAttr(id, entityId, nameNm)));
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL error trying to retrieve entity", e);
        }
        return result;
    }
}
