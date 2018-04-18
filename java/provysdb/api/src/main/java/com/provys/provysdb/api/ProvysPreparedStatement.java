/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.api;

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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 *
 * @author micha
 */
public interface ProvysPreparedStatement extends PreparedStatement {

    void addBatch() throws SQLException;

    void clearParameters() throws SQLException;

    boolean execute() throws SQLException;

    @Deprecated
    long executeLargeUpdate() throws SQLException;

    ResultSet executeQuery() throws SQLException;

    int executeUpdate() throws SQLException;

    ResultSetMetaData getMetaData() throws SQLException;

    ParameterMetaData getParameterMetaData() throws SQLException;

    void setArray(int parameterIndex, Array x) throws SQLException;

    void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException;

    void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException;

    void setAsciiStream(int parameterIndex, InputStream x) throws SQLException;

    void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException;

    void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException;

    void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException;

    void setBinaryStream(int parameterIndex, InputStream x) throws SQLException;

    void setBlob(int parameterIndex, Blob x) throws SQLException;

    void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException;

    void setBlob(int parameterIndex, InputStream inputStream) throws SQLException;

    void setBoolean(int parameterIndex, boolean x) throws SQLException;

    void setByte(int parameterIndex, byte x) throws SQLException;

    void setBytes(int parameterIndex, byte[] x) throws SQLException;

    void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException;

    void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException;

    void setCharacterStream(int parameterIndex, Reader reader) throws SQLException;

    void setClob(int parameterIndex, Clob x) throws SQLException;

    void setClob(int parameterIndex, Reader reader, long length) throws SQLException;

    void setClob(int parameterIndex, Reader reader) throws SQLException;

    void setDate(int parameterIndex, Date x) throws SQLException;

    void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException;

    void setDouble(int parameterIndex, double x) throws SQLException;

    void setFloat(int parameterIndex, float x) throws SQLException;

    void setInt(int parameterIndex, int x) throws SQLException;

    void setLong(int parameterIndex, long x) throws SQLException;

    void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException;

    void setNCharacterStream(int parameterIndex, Reader value) throws SQLException;

    void setNClob(int parameterIndex, NClob value) throws SQLException;

    void setNClob(int parameterIndex, Reader reader, long length) throws SQLException;

    void setNClob(int parameterIndex, Reader reader) throws SQLException;

    void setNString(int parameterIndex, String value) throws SQLException;

    void setNull(int parameterIndex, int sqlType) throws SQLException;

    void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException;

    void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException;

    void setObject(int parameterIndex, Object x) throws SQLException;

    void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException;

    void setObject(int parameterIndex, Object x, SQLType targetSqlType, int scaleOrLength) throws SQLException;

    void setObject(int parameterIndex, Object x, SQLType targetSqlType) throws SQLException;

    void setRef(int parameterIndex, Ref x) throws SQLException;

    void setRowId(int parameterIndex, RowId x) throws SQLException;

    void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException;

    void setShort(int parameterIndex, short x) throws SQLException;

    void setString(int parameterIndex, String x) throws SQLException;

    void setTime(int parameterIndex, Time x) throws SQLException;

    void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException;

    void setTimestamp(int parameterIndex, Timestamp x) throws SQLException;

    void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException;

    void setURL(int parameterIndex, URL x) throws SQLException;

    @Deprecated
    void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException;
    
}
