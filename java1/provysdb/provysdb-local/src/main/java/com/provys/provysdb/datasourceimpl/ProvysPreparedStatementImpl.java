/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.datasourceimpl;

import com.provys.common.datatypes.*;
import com.provys.common.error.ProvysException;
import com.provys.common.error.ProvysSqlException;
import com.provys.provysdb.call.BindValue;
import com.provys.provysdb.datasource.ProvysPreparedStatement;
import com.provys.provysdb.datasource.ProvysResultSet;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;
import oracle.jdbc.OraclePreparedStatement;

/**
 *
 * @author stehlik
 */
public class ProvysPreparedStatementImpl extends ProvysStatementImpl
        implements ProvysPreparedStatement {
    private static final Logger LOG = Logger.getLogger(ProvysPreparedStatementImpl.class.getName());
    
    /**
     *
     * @param statement
     * @throws SQLException
     */
    public ProvysPreparedStatementImpl(PreparedStatement statement)
            throws SQLException {
        super(statement.unwrap(OraclePreparedStatement.class));
    }

    /**
     * Getter method for statement
     * @return OraclePreparedStatement this instance decorates
     */
    private OraclePreparedStatement getStatement() {
        return (OraclePreparedStatement) this.statement;
    }
    
    @Override
    public boolean execute() throws SQLException {
        return getStatement().execute();
    }

    @Override
    public ProvysResultSet executeQuery() throws SQLException {
        return new ProvysResultSetImpl(getStatement().executeQuery());
    }

    @Override
    public int executeUpdate() throws SQLException {
        return getStatement().executeUpdate();
    }

    @Override
    public long executeLargeUpdate() throws SQLException {
        return getStatement().executeLargeUpdate();
    }
    
    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return getStatement().getMetaData();
    }

    @Override
    public void addBatch() throws SQLException {
        getStatement().addBatch();
    }

    @Override
    public ParameterMetaData getParameterMetaData() throws SQLException {
        return getStatement().getParameterMetaData();
    }

    @Override
    public void clearParameters() throws SQLException {
        getStatement().clearParameters();
    }

    @Override
    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        getStatement().setNull(parameterIndex, sqlType);
    }

    private void setNull(String parameterName, int sqlType) throws SQLException {
        getStatement().setNullAtName(parameterName, sqlType);
    }

    @Override
    public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
        getStatement().setNull(parameterIndex, sqlType, typeName);
    }

    private void setNull(String parameterName, int sqlType, String typeName) throws SQLException {
        getStatement().setNullAtName(parameterName, sqlType, typeName);
    }

    @Override
    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
        getStatement().setBoolean(parameterIndex, x);
    }

    private void setBoolean(String parameterName, boolean x) throws SQLException {
        getStatement().setBooleanAtName(parameterName, x);
    }

    @Override
    public void setByte(int parameterIndex, byte x) throws SQLException {
        getStatement().setByte(parameterIndex, x);
    }

    private void setByte(String parameterName, byte x) throws SQLException {
        getStatement().setByteAtName(parameterName, x);
    }

    @Override
    public void setShort(int parameterIndex, short x) throws SQLException {
        getStatement().setShort(parameterIndex, x);
    }

    private void setShort(String parameterName, short x) throws SQLException {
        getStatement().setShortAtName(parameterName, x);
    }

    @Override
    public void setInt(int parameterIndex, int x) throws SQLException {
        getStatement().setInt(parameterIndex, x);
    }

    private void setInt(String parameterName, int x) throws SQLException {
        getStatement().setIntAtName(parameterName, x);
    }

    @Override
    public void setLong(int parameterIndex, long x) throws SQLException {
        getStatement().setLong(parameterIndex, x);
    }

    private void setLong(String parameterName, long x) throws SQLException {
        getStatement().setLongAtName(parameterName, x);
    }

    @Override
    public void setFloat(int parameterIndex, float x) throws SQLException {
        getStatement().setFloat(parameterIndex, x);
    }

    private void setFloat(String parameterName, float x) throws SQLException {
        getStatement().setFloatAtName(parameterName, x);
    }

    @Override
    public void setDouble(int parameterIndex, double x) throws SQLException {
        getStatement().setDouble(parameterIndex, x);
    }

    private void setDouble(String parameterName, double x) throws SQLException {
        getStatement().setDoubleAtName(parameterName, x);
    }

    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
        getStatement().setBigDecimal(parameterIndex, x);
    }

    private void setBigDecimal(String parameterName, BigDecimal x) throws SQLException {
        getStatement().setBigDecimalAtName(parameterName, x);
    }

    @Override
    public void setString(int parameterIndex, String x) throws SQLException {
        getStatement().setString(parameterIndex, x);
    }

    private void setString(String parameterName, String x) throws SQLException {
        getStatement().setStringAtName(parameterName, x);
    }

    @Override
    public void setBytes(int parameterIndex, byte[] x) throws SQLException {
        getStatement().setBytes(parameterIndex, x);
    }

    private void setBytes(String parameterName, byte[] x) throws SQLException {
        getStatement().setBytesAtName(parameterName, x);
    }

    @Override
    public void setDate(int parameterIndex, Date x) throws SQLException {
        getStatement().setDate(parameterIndex, x);
    }

    private void setDate(String parameterName, Date x) throws SQLException {
        getStatement().setDateAtName(parameterName, x);
    }

    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
        getStatement().setDate(parameterIndex, x, cal);
    }

    private void setDate(String parameterName, Date x, Calendar cal) throws SQLException {
        getStatement().setDateAtName(parameterName, x, cal);
    }

    @Override
    public void setTime(int parameterIndex, Time x) throws SQLException {
        getStatement().setTime(parameterIndex, x);
    }

    private void setTime(String parameterName, Time x) throws SQLException {
        getStatement().setTimeAtName(parameterName, x);
    }

    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
        getStatement().setTime(parameterIndex, x, cal);
    }

    private void setTime(String parameterName, Time x, Calendar cal) throws SQLException {
        getStatement().setTimeAtName(parameterName, x, cal);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        getStatement().setTimestamp(parameterIndex, x);
    }

    private void setTimestamp(String parameterName, Timestamp x) throws SQLException {
        getStatement().setTimestampAtName(parameterName, x);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
        getStatement().setTimestamp(parameterIndex, x, cal);
    }

    private void setTimestamp(String parameterName, Timestamp x, Calendar cal) throws SQLException {
        getStatement().setTimestampAtName(parameterName, x, cal);
    }

    @Override
    public void setObject(int parameterIndex, Object x) throws SQLException {
        getStatement().setObject(parameterIndex, x);
    }

    private void setObjectAtName(String parameterName, Object x) throws SQLException {
        getStatement().setObjectAtName(parameterName, x);
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
        getStatement().setObject(parameterIndex, x, targetSqlType);
    }

    private void setObject(String parameterName, Object x, int targetSqlType) throws SQLException {
        getStatement().setObjectAtName(parameterName, x, targetSqlType);
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
        getStatement().setObject(parameterIndex, x, targetSqlType, scaleOrLength);
    }

    private void setObject(String parameterName, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
        getStatement().setObjectAtName(parameterName, x, targetSqlType, scaleOrLength);
    }

    @Override
    public void setObject(int parameterIndex, Object x, SQLType targetSqlType) throws SQLException {
        getStatement().setObject(parameterIndex, x, targetSqlType);
    }

    @Override
    public void setObject(int parameterIndex, Object x, SQLType targetSqlType, int scaleOrLength) throws SQLException {
        getStatement().setObject(parameterIndex, x, targetSqlType, scaleOrLength);
    }

    @Override
    public void setRef(int parameterIndex, Ref x) throws SQLException {
        getStatement().setRef(parameterIndex, x);
    }

    private void setRef(String parameterName, Ref x) throws SQLException {
        getStatement().setRefAtName(parameterName, x);
    }

    @Override
    public void setBlob(int parameterIndex, Blob x) throws SQLException {
        getStatement().setBlob(parameterIndex, x);
    }

    private void setBlob(String parameterName, Blob x) throws SQLException {
        getStatement().setBlobAtName(parameterName, x);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
        getStatement().setBlob(parameterIndex, inputStream, length);
    }

    private void setBlob(String parameterName, InputStream inputStream, long length) throws SQLException {
        getStatement().setBlobAtName(parameterName, inputStream, length);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
        getStatement().setBlob(parameterIndex, inputStream);
    }

    private void setBlob(String parameterName, InputStream inputStream) throws SQLException {
        getStatement().setBlobAtName(parameterName, inputStream);
    }

    @Override
    public void setClob(int parameterIndex, Clob x) throws SQLException {
        getStatement().setClob(parameterIndex, x);
    }

    private void setClob(String parameterName, Clob x) throws SQLException {
        getStatement().setClobAtName(parameterName, x);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
        getStatement().setClob(parameterIndex, reader, length);
    }

    private void setClob(String parameterName, Reader reader, long length) throws SQLException {
        getStatement().setClobAtName(parameterName, reader, length);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader) throws SQLException {
        getStatement().setClob(parameterIndex, reader);
    }

    private void setClob(String parameterName, Reader reader) throws SQLException {
        getStatement().setClobAtName(parameterName, reader);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
        getStatement().setNClob(parameterIndex, reader, length);
    }

    private void setNClob(String parameterName, Reader reader, long length) throws SQLException {
        getStatement().setNClobAtName(parameterName, reader, length);
    }

    @Override
    public void setNClob(int parameterIndex, NClob value) throws SQLException {
        getStatement().setNClob(parameterIndex, value);
    }

    private void setNClob(String parameterName, NClob value) throws SQLException {
        getStatement().setNClobAtName(parameterName, value);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader) throws SQLException {
        getStatement().setNClob(parameterIndex, reader);
    }

    private void setNClob(String parameterName, Reader reader) throws SQLException {
        getStatement().setNClobAtName(parameterName, reader);
    }

    @Override
    public void setArray(int parameterIndex, Array x) throws SQLException {
        getStatement().setArray(parameterIndex, x);
    }

    private void setArray(String parameterName, Array x) throws SQLException {
        getStatement().setArrayAtName(parameterName, x);
    }

    @Override
    public void setURL(int parameterIndex, URL x) throws SQLException {
        getStatement().setURL(parameterIndex, x);
    }

    private void setURL(String parameterName, URL x) throws SQLException {
        getStatement().setURLAtName(parameterName, x);
    }

    @Override
    public void setRowId(int parameterIndex, RowId x) throws SQLException {
        getStatement().setRowId(parameterIndex, x);
    }

    private void setRowId(String parameterName, RowId x) throws SQLException {
        getStatement().setRowIdAtName(parameterName, x);
    }

    @Override
    public void setNString(int parameterIndex, String value) throws SQLException {
        getStatement().setNString(parameterIndex, value);
    }

    private void setNString(String parameterName, String value) throws SQLException {
        getStatement().setNStringAtName(parameterName, value);
    }

    @Override
    public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
        getStatement().setSQLXML(parameterIndex, xmlObject);
    }

    private void setSQLXML(String parameterName, SQLXML xmlObject) throws SQLException {
        getStatement().setSQLXMLAtName(parameterName, xmlObject);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
        getStatement().setAsciiStream(parameterIndex, x);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
        getStatement().setAsciiStream(parameterIndex, x, length);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
        getStatement().setAsciiStream(parameterIndex, x, length);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
        getStatement().setBinaryStream(parameterIndex, x);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
        getStatement().setBinaryStream(parameterIndex, x, length);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
        getStatement().setBinaryStream(parameterIndex, x, length);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
        getStatement().setCharacterStream(parameterIndex, reader);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
        getStatement().setCharacterStream(parameterIndex, reader, length);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
        getStatement().setCharacterStream(parameterIndex, reader, length);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
        getStatement().setNCharacterStream(parameterIndex, value, length);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
        getStatement().setNCharacterStream(parameterIndex, value);
    }

    @Deprecated
    @Override
    public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
        getStatement().setUnicodeStream(parameterIndex, x, length);
    }

    @Override
    public void setDtBoolean(int parameterIndex, DtBoolean value)
            throws SQLException {
        setString(parameterIndex, value.toStringValue());
    }
    
    @Override
    public void setDtBoolean(String parameterName, DtBoolean value)
            throws SQLException {
        setString(parameterName, value.toStringValue());
    }

    @Override
    public void setDtOptBoolean(int parameterIndex, DtOptBoolean value)
            throws SQLException {
        if (!value.isPresent()) {
            setNull(parameterIndex, Types.VARCHAR);
        } else {
            setString(parameterIndex, value.toStringValue());
        }
    }
    
    @Override
    public void setDtOptBoolean(String parameterName, DtOptBoolean value)
            throws SQLException {
        if (!value.isPresent()) {
            setNull(parameterName, Types.VARCHAR);
        } else {
            setString(parameterName, value.toStringValue());
        }
    }
    
    @Override
    public void setDtInteger(int parameterIndex, DtInteger value)
            throws SQLException {
        getStatement().setInt(parameterIndex, value.getValue());
    }

    @Override
    public void setDtInteger(String parameterName, DtInteger value)
            throws SQLException {
        setInt(parameterName, value.getValue());
    }

    @Override
    public void setDtOptInteger(int parameterIndex, DtOptInteger value)
            throws SQLException {
        if (!value.isPresent()) {
            setNull(parameterIndex, Types.INTEGER);
        } else {
            setInt(parameterIndex, value.get());
        }
    }

    @Override
    public void setDtOptInteger(String parameterName, DtOptInteger value)
            throws SQLException {
        if (!value.isPresent()) {
            setNull(parameterName, Types.INTEGER);
        } else {
            setInt(parameterName, value.get());
        }
    }

    @Override
    public void setDtName(int parameterIndex, DtName value) 
            throws SQLException {
        setString(parameterIndex, value.getValue());
    }

    @Override
    public void setDtName(String parameterName, DtName value) 
            throws SQLException {
        setString(parameterName, value.getValue());
    }

    @Override
    public void setDtOptName(int parameterIndex, DtOptName value) 
            throws SQLException {
        if (!value.isPresent()) {
            setNull(parameterIndex, Types.VARCHAR);
        } else {
            setString(parameterIndex, value.get());
        }
    }

    @Override
    public void setDtOptName(String parameterName, DtOptName value) 
            throws SQLException {
        if (!value.isPresent()) {
            setNull(parameterName, Types.VARCHAR);
        } else {
            setString(parameterName, value.get());
        }
    }

    @Override
    public void setDtNameNm(int parameterIndex, DtNameNm value) 
            throws SQLException {
        setString(parameterIndex, value.getValue());
    }

    @Override
    public void setDtNameNm(String parameterName, DtNameNm value) 
            throws SQLException {
        setString(parameterName, value.getValue());
    }

    @Override
    public void setDtOptNameNm(int parameterIndex, DtOptNameNm value) 
            throws SQLException {
        if (!value.isPresent()) {
            setNull(parameterIndex, Types.VARCHAR);
        } else {
            setString(parameterIndex, value.get());
        }
    }

    @Override
    public void setDtOptNameNm(String parameterName, DtOptNameNm value) 
            throws SQLException {
        if (!value.isPresent()) {
            setNull(parameterName, Types.VARCHAR);
        } else {
            setString(parameterName, value.get());
        }
    }

    @Override
    public void setDtNumber(int parameterIndex, DtNumber value)
            throws SQLException {
        setBigDecimal(parameterIndex, value.getValue());
    }

    @Override
    public void setDtNumber(String parameterName, DtNumber value)
            throws SQLException {
        setBigDecimal(parameterName, value.getValue());
    }

    @Override
    public void setDtOptNumber(int parameterIndex, DtOptNumber value) 
            throws SQLException {
        if (!value.isPresent()) {
            setNull(parameterIndex, Types.NUMERIC);
        } else {
            setBigDecimal(parameterIndex, value.get());
        }
    }

    @Override
    public void setDtOptNumber(String parameterName, DtOptNumber value) 
            throws SQLException {
        if (!value.isPresent()) {
            setNull(parameterName, Types.NUMERIC);
        } else {
            setBigDecimal(parameterName, value.get());
        }
    }

    @Override
    public void setDtUid(int parameterIndex, DtUid value) throws SQLException {
        setBigDecimal(parameterIndex, new BigDecimal(value.getValue()));
    }

    @Override
    public void setDtUid(String parameterName, DtUid value) throws SQLException {
        setBigDecimal(parameterName, new BigDecimal(value.getValue()));
    }

    @Override
    public void setDtOptUid(int parameterIndex, DtOptUid value) 
            throws SQLException {
        if (!value.isPresent()) {
            setNull(parameterIndex, Types.NUMERIC);
        } else {
            setBigDecimal(parameterIndex, new BigDecimal(value.get()));
        }
    }

    @Override
    public void setDtOptUid(String parameterName, DtOptUid value) 
            throws SQLException {
        if (!value.isPresent()) {
            setNull(parameterName, Types.VARCHAR);
        } else {
            setBigDecimal(parameterName, new BigDecimal(value.get()));
        }
    }

    @Override
    public void setDtVarchar(int parameterIndex, DtVarchar value)
            throws SQLException {
        setString(parameterIndex, value.getValue());
    }

    @Override
    public void setDtVarchar(String parameterName, DtVarchar value)
            throws SQLException {
        setString(parameterName, value.getValue());
    }

    @Override
    public void setDtOptVarchar(int parameterIndex, DtOptVarchar value) 
            throws SQLException {
        if (!value.isPresent()) {
            setNull(parameterIndex, Types.VARCHAR);
        } else {
            setString(parameterIndex, value.get());
        }
    }

    @Override
    public void setDtOptVarchar(String parameterName, DtOptVarchar value) 
            throws SQLException {
        if (!value.isPresent()) {
            setNull(parameterName, Types.VARCHAR);
        } else {
            setString(parameterName, value.get());
        }
    }

    @Override
    public void setBind(BindValue bind) throws SQLException {
        switch (bind.getType()) {
            case "DtBoolean":
                setDtBoolean(bind.getName(), (DtBoolean) bind.getValue());
                break;
            case "DtOptBoolean":
                setDtOptBoolean(bind.getName(), (DtOptBoolean) bind.getValue());
                break;
            case "DtInteger":
                setDtInteger(bind.getName(), (DtInteger) bind.getValue());
                break;
            case "DtOptInteger":
                setDtOptInteger(bind.getName(), (DtOptInteger) bind.getValue());
                break;
            case "DtName":
                setDtName(bind.getName(), (DtName) bind.getValue());
                break;
            case "DtOptName":
                setDtOptName(bind.getName(), (DtOptName) bind.getValue());
                break;
            case "DtNameNm":
                setDtNameNm(bind.getName(), (DtNameNm) bind.getValue());
                break;
            case "DtOptNameNm":
                setDtOptNameNm(bind.getName(), (DtOptNameNm) bind.getValue());
                break;
            case "DtNumber":
                setDtNumber(bind.getName(), (DtNumber) bind.getValue());
                break;
            case "DtOptNumber":
                setDtOptNumber(bind.getName(), (DtOptNumber) bind.getValue());
                break;
            case "DtUid":
                setDtUid(bind.getName(), (DtUid) bind.getValue());
                break;
            case "DtOptUid":
                setDtOptUid(bind.getName(), (DtOptUid) bind.getValue());
                break;
            case "DtVarchar":
                setDtVarchar(bind.getName(), (DtVarchar) bind.getValue());
                break;
            case "DtOptVarchar":
                setDtOptVarchar(bind.getName(), (DtOptVarchar) bind.getValue());
                break;
/*
            case "Boolean":
                setBoolean(bind.getName(), (Boolean) bind.getValue());
                break;
            case "Byte":
                setByte(bind.getName(), (Byte) bind.getValue());
                break;
            case "Short":
                setShort(bind.getName(), (Short) bind.getValue());
                break;
            case "Integer":
                setInt(bind.getName(), (Integer) bind.getValue());
                break;
            case "Long":
                setLong(bind.getName(), (Long) bind.getValue());
                break;
            case "Float":
                setFloat(bind.getName(), (Float) bind.getValue());
                break;
            case "Double":
                setDouble(bind.getName(), (Double) bind.getValue());
                break;
            case "BigDecimal":
                setBigDecimal(bind.getName(), (BigDecimal) bind.getValue());
                break;
            case "String":
                setString(bind.getName(), (String) bind.getValue());
                break;
            case "Date":
                setDate(bind.getName(), (Date) bind.getValue());
                break;
            case "Time":
                setTime(bind.getName(), (Time) bind.getValue());
                break;
            case "Timestamp":
                setTimestamp(bind.getName(), (Timestamp) bind.getValue());
                break;
*/
            default:
                throw new UnsupportedBindDatatypeException(bind.getType());
        }
    }

    @Override
    public void setBinds(List<BindValue> binds) {
        binds.forEach((bind) -> {
            try {
                this.setBind(bind);
            } catch (SQLException e) {
                throw new ProvysSqlException(e);
            }
        });
    }

    /**
     * Exception raised when value supplied to Bind is not one of supported
     * types
     */
    @SuppressWarnings("PublicInnerClass")
    static public class UnsupportedBindDatatypeException
            extends ProvysException {

        private static final long serialVersionUID = 1L;

        UnsupportedBindDatatypeException(String type) {
            super("Unsupported data type for bind: " + type);
        }
    }

}
