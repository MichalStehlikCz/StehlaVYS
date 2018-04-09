/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.catalogue.ejb;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.SQLException;
import com.provys.catalogue.model.ConfEntity;
import com.provys.common.datatypes.*;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;
import oracle.sql.*;
import com.provys.common.confobj.ConfNMObjectLoader;
import com.provys.common.confobj.RowidObjectPair;

/**
 *
 * @author micha
 */
@Stateless
public class ConfEntityLoaderBean extends ConfNMObjectLoader<ConfEntity> {

    private static final String LOADSELECT =
            "SELECT"
            +"\n  alentity.entity_id"
            +"\n, alentity.name_nm"
            +"\n, alentity.ROWID"
            +"\nFROM"
            +"\n  ker_entity_tb alentity"
            ;

    private static final String LOADWHEREBYID =
            "\nWHERE"
            +"\n      (alentity.entity_id=:entity_id)"
            ;
    
    private static final String LOADWHEREBYNM =
            "\nWHERE"
            +"\n      (alentity.name_nm=:name_nm)"
            ;
    
    @Override
    public RowidObjectPair<ConfEntity> load(DtUid id) {
        ROWID rowid;
        DtNameNm nameNm;
        try (Connection connection = provysDB.getConnection()){
            OraclePreparedStatement statement
                    = (OraclePreparedStatement) connection.prepareStatement(
                    LOADSELECT+LOADWHEREBYID).unwrap(OraclePreparedStatement.class);
            statement.setOracleObjectAtName("entity_id", new NUMBER(id.getValue()));
            OracleResultSet resultSet = (OracleResultSet)statement.executeQuery();
            if (!resultSet.next()) {
                throw new RuntimeException("Not found entity entity_id="+id.getValue());
            }
            rowid = resultSet.getROWID("ROWID");
            nameNm = new DtNameNm(resultSet.getString("NAME_NM"));
        } catch (SQLException e) {
            throw new RuntimeException("SQL error trying to retrieve entity", e);
        }
        return new RowidObjectPair<>(rowid, new ConfEntity(id, nameNm));
    }

    @Override
    public RowidObjectPair<ConfEntity> loadByNm(DtNameNm nameNm) {
        ROWID rowid;
        DtUid id;
        try (Connection connection = provysDB.getConnection()){
            OraclePreparedStatement statement
                    = (OraclePreparedStatement) connection.prepareStatement(
                    LOADSELECT+LOADWHEREBYNM).unwrap(OraclePreparedStatement.class);
            statement.setStringAtName("name_nm", nameNm.getValue());
            OracleResultSet resultSet = (OracleResultSet)statement.executeQuery();
            if (!resultSet.next()) {
                throw new RuntimeException("Not found entity entity_id="+nameNm.getValue());
            }
            rowid = resultSet.getROWID("ROWID");
            id = new DtUid(resultSet.getNUMBER("ENTITY_ID").stringValue());
        } catch (SQLException e) {
            throw new RuntimeException("SQL error trying to retrieve entity", e);
        }
        return new RowidObjectPair<>(rowid, new ConfEntity(id, nameNm));
    }
}
