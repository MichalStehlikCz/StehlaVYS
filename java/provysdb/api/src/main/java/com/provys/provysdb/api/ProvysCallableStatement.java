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
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

/**
 *
 * @author micha
 */
public interface ProvysCallableStatement extends CallableStatement {

    Array getArray(int parameterIndex) throws SQLException;

    Array getArray(String parameterName) throws SQLException;

    BigDecimal getBigDecimal(int parameterIndex) throws SQLException;

    BigDecimal getBigDecimal(String parameterName) throws SQLException;

    @Deprecated
    BigDecimal getBigDecimal(int parameterIndex, int scale) throws SQLException;

    Blob getBlob(int parameterIndex) throws SQLException;

    Blob getBlob(String parameterName) throws SQLException;

    boolean getBoolean(int parameterIndex) throws SQLException;

    boolean getBoolean(String parameterName) throws SQLException;

    byte getByte(int parameterIndex) throws SQLException;

    byte getByte(String parameterName) throws SQLException;

    byte[] getBytes(int parameterIndex) throws SQLException;

    byte[] getBytes(String parameterName) throws SQLException;

    Reader getCharacterStream(int parameterIndex) throws SQLException;

    Reader getCharacterStream(String parameterName) throws SQLException;

    Clob getClob(int parameterIndex) throws SQLException;

    Clob getClob(String parameterName) throws SQLException;

    Date getDate(int parameterIndex) throws SQLException;

    Date getDate(int parameterIndex, Calendar cal) throws SQLException;

    Date getDate(String parameterName) throws SQLException;

    Date getDate(String parameterName, Calendar cal) throws SQLException;

    double getDouble(int parameterIndex) throws SQLException;

    double getDouble(String parameterName) throws SQLException;

    float getFloat(int parameterIndex) throws SQLException;

    float getFloat(String parameterName) throws SQLException;

    int getInt(int parameterIndex) throws SQLException;

    int getInt(String parameterName) throws SQLException;

    long getLong(int parameterIndex) throws SQLException;

    long getLong(String parameterName) throws SQLException;

    Reader getNCharacterStream(int parameterIndex) throws SQLException;

    Reader getNCharacterStream(String parameterName) throws SQLException;

    NClob getNClob(int parameterIndex) throws SQLException;

    NClob getNClob(String parameterName) throws SQLException;

    String getNString(int parameterIndex) throws SQLException;

    String getNString(String parameterName) throws SQLException;

    Object getObject(int parameterIndex) throws SQLException;

    Object getObject(int parameterIndex, Map<String, Class<?>> map) throws SQLException;

    Object getObject(String parameterName) throws SQLException;

    Object getObject(String parameterName, Map<String, Class<?>> map) throws SQLException;

    <T> T getObject(int parameterIndex, Class<T> type) throws SQLException;

    <T> T getObject(String parameterName, Class<T> type) throws SQLException;

    Ref getRef(int parameterIndex) throws SQLException;

    Ref getRef(String parameterName) throws SQLException;

    RowId getRowId(int parameterIndex) throws SQLException;

    RowId getRowId(String parameterName) throws SQLException;

    SQLXML getSQLXML(int parameterIndex) throws SQLException;

    SQLXML getSQLXML(String parameterName) throws SQLException;

    short getShort(int parameterIndex) throws SQLException;

    short getShort(String parameterName) throws SQLException;

    String getString(int parameterIndex) throws SQLException;

    String getString(String parameterName) throws SQLException;

    Time getTime(int parameterIndex) throws SQLException;

    Time getTime(int parameterIndex, Calendar cal) throws SQLException;

    Time getTime(String parameterName) throws SQLException;

    Time getTime(String parameterName, Calendar cal) throws SQLException;

    Timestamp getTimestamp(int parameterIndex) throws SQLException;

    Timestamp getTimestamp(int parameterIndex, Calendar cal) throws SQLException;

    Timestamp getTimestamp(String parameterName) throws SQLException;

    Timestamp getTimestamp(String parameterName, Calendar cal) throws SQLException;

    URL getURL(int parameterIndex) throws SQLException;

    URL getURL(String parameterName) throws SQLException;

    void registerOutParameter(int parameterIndex, int sqlType) throws SQLException;

    void registerOutParameter(int parameterIndex, int sqlType, int scale) throws SQLException;

    void registerOutParameter(int parameterIndex, int sqlType, String typeName) throws SQLException;

    void registerOutParameter(String parameterName, int sqlType) throws SQLException;

    void registerOutParameter(String parameterName, int sqlType, int scale) throws SQLException;

    void registerOutParameter(String parameterName, int sqlType, String typeName) throws SQLException;

    void registerOutParameter(int parameterIndex, SQLType sqlType) throws SQLException;

    void registerOutParameter(int parameterIndex, SQLType sqlType, int scale) throws SQLException;

    void registerOutParameter(int parameterIndex, SQLType sqlType, String typeName) throws SQLException;

    void registerOutParameter(String parameterName, SQLType sqlType) throws SQLException;

    void registerOutParameter(String parameterName, SQLType sqlType, int scale) throws SQLException;

    void registerOutParameter(String parameterName, SQLType sqlType, String typeName) throws SQLException;

    void setAsciiStream(String parameterName, InputStream x, int length) throws SQLException;

    void setAsciiStream(String parameterName, InputStream x, long length) throws SQLException;

    void setAsciiStream(String parameterName, InputStream x) throws SQLException;

    void setBigDecimal(String parameterName, BigDecimal x) throws SQLException;

    void setBinaryStream(String parameterName, InputStream x, int length) throws SQLException;

    void setBinaryStream(String parameterName, InputStream x, long length) throws SQLException;

    void setBinaryStream(String parameterName, InputStream x) throws SQLException;

    void setBlob(String parameterName, InputStream inputStream, long length) throws SQLException;

    void setBlob(String parameterName, Blob x) throws SQLException;

    void setBlob(String parameterName, InputStream inputStream) throws SQLException;

    void setBoolean(String parameterName, boolean x) throws SQLException;

    void setByte(String parameterName, byte x) throws SQLException;

    void setBytes(String parameterName, byte[] x) throws SQLException;

    void setCharacterStream(String parameterName, Reader reader, int length) throws SQLException;

    void setCharacterStream(String parameterName, Reader reader, long length) throws SQLException;

    void setCharacterStream(String parameterName, Reader reader) throws SQLException;

    void setClob(String parameterName, Reader reader, long length) throws SQLException;

    void setClob(String parameterName, Clob x) throws SQLException;

    void setClob(String parameterName, Reader reader) throws SQLException;

    void setDate(String parameterName, Date x) throws SQLException;

    void setDate(String parameterName, Date x, Calendar cal) throws SQLException;

    void setDouble(String parameterName, double x) throws SQLException;

    void setFloat(String parameterName, float x) throws SQLException;

    void setInt(String parameterName, int x) throws SQLException;

    void setLong(String parameterName, long x) throws SQLException;

    void setNCharacterStream(String parameterName, Reader value, long length) throws SQLException;

    void setNCharacterStream(String parameterName, Reader value) throws SQLException;

    void setNClob(String parameterName, NClob value) throws SQLException;

    void setNClob(String parameterName, Reader reader, long length) throws SQLException;

    void setNClob(String parameterName, Reader reader) throws SQLException;

    void setNString(String parameterName, String value) throws SQLException;

    void setNull(String parameterName, int sqlType) throws SQLException;

    void setNull(String parameterName, int sqlType, String typeName) throws SQLException;

    void setObject(String parameterName, Object x, int targetSqlType, int scale) throws SQLException;

    void setObject(String parameterName, Object x, int targetSqlType) throws SQLException;

    void setObject(String parameterName, Object x) throws SQLException;

    void setObject(String parameterName, Object x, SQLType targetSqlType, int scaleOrLength) throws SQLException;

    void setObject(String parameterName, Object x, SQLType targetSqlType) throws SQLException;

    void setRowId(String parameterName, RowId x) throws SQLException;

    void setSQLXML(String parameterName, SQLXML xmlObject) throws SQLException;

    void setShort(String parameterName, short x) throws SQLException;

    void setString(String parameterName, String x) throws SQLException;

    void setTime(String parameterName, Time x) throws SQLException;

    void setTime(String parameterName, Time x, Calendar cal) throws SQLException;

    void setTimestamp(String parameterName, Timestamp x) throws SQLException;

    void setTimestamp(String parameterName, Timestamp x, Calendar cal) throws SQLException;

    void setURL(String parameterName, URL val) throws SQLException;

    boolean wasNull() throws SQLException;
    
}
