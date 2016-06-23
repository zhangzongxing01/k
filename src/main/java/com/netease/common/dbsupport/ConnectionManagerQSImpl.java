package com.netease.common.dbsupport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import org.apache.commons.dbcp.BasicDataSource;

import com.netease.dbsupport.IConnectionManager;
import com.netease.dbsupport.exception.DBSupportRuntimeException;


public class ConnectionManagerQSImpl implements IConnectionManager{


    private BasicDataSource m_dataSource;
    private String          m_driver          = "com.mysql.jdbc.Driver";

    private String          m_user            = null;

    private String          m_pass            = null;

    private String          m_url             = null;

    private int             m_initialSize     = 0;

    private int             m_maxActive       = 10;

    private int             m_maxIdle         = 10;

    private int             m_minIdle         = 0;

    private int             m_maxWait         = -1;

    private String          m_validationQuery = "SELECT 1";

    public ConnectionManagerQSImpl() {
    }

    private boolean m_testOnBorrow = false;

    private boolean m_testOnReturn = false;

    public void setTestOnReturn(boolean m_testOnReturn) {
        this.m_testOnReturn = m_testOnReturn;
    }

    private boolean m_testWhileIdle = true;

    public void setTestWhileIdle(boolean m_testWhileIdle) {
        this.m_testWhileIdle = m_testWhileIdle;
    }

    private long m_timeBetweenEvictionRunsMillis = 60000L;

    public void setTimeBetweenEvictionRunsMillis(long m_timeBetweenEvictionRunsMillis) {
        this.m_timeBetweenEvictionRunsMillis = m_timeBetweenEvictionRunsMillis;
    }

    private int m_numTestsPerEvictionRun = 10;

    public void setNumTestsPerEvictionRun(int m_numTestsPerEvictionRun) {
        this.m_numTestsPerEvictionRun = m_numTestsPerEvictionRun;
    }

    private long m_minEvictableIdleTimeMillis = 1800000L;

    public void setMinEvictableIdleTimeMillis(long m_minEvictableIdleTimeMillis) {
        this.m_minEvictableIdleTimeMillis = m_minEvictableIdleTimeMillis;
    }

    private String m_idTableName = "IDGenerator";

    public int getInitialSize() {
        return this.m_initialSize;
    }

    public void setInitialSize(int _initialSize) {
        this.m_initialSize = _initialSize;
    }

    public int getMaxActive() {
        return this.m_maxActive;
    }

    public void setMaxActive(int _maxActive) {
        this.m_maxActive = _maxActive;
    }

    public int getMaxIdle() {
        return this.m_maxIdle;
    }

    public void setMaxIdle(int _maxIdle) {
        this.m_maxIdle = _maxIdle;
    }

    public int getMinIdle() {
        return this.m_minIdle;
    }

    public void setMinIdle(int _minIdle) {
        this.m_minIdle = _minIdle;
    }

    public int getMaxWait() {
        return this.m_maxWait;
    }

    public void setMaxWait(int _maxWait) {
        this.m_maxWait = _maxWait;
    }

    public String getValidationQuery() {
        return this.m_validationQuery;
    }

    public void setValidationQuery(String _validationQuery) {
        this.m_validationQuery = _validationQuery;
    }

    public boolean isTestOnBorrow() {
        return this.m_testOnBorrow;
    }

    public void setTestOnBorrow(boolean _testOnBorrow) {
        this.m_testOnBorrow = _testOnBorrow;
    }

    public String getIdTableName() {
        return this.m_idTableName;
    }

    public void setIdTableName(String _idTableName) {
        this.m_idTableName = _idTableName;
    }

    public String getDriver() {
        return this.m_driver;
    }

    public void setDriver(String _driver) {
        this.m_driver = _driver;
    }

    public String getUser() {
        return this.m_user;
    }

    public void setUser(String _user) {
        this.m_user = _user;
    }

    public String getPass() {
        return this.m_pass;
    }

    public void setPass(String _pass) {
        this.m_pass = _pass;
    }

    public String getUrl() {
        return this.m_url;
    }

    public void setUrl(String _url) {
        this.m_url = _url;
    }

    public BasicDataSource getDataSource() {
        return this.m_dataSource;
    }

    public void setDataSource(BasicDataSource _dataSource) {
        this.m_dataSource = _dataSource;
    }

    public boolean init() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(getDriver());
        ds.setUsername(getUser());
        ds.setPassword(getPass());
        ds.setUrl(getUrl());
        ds.setMaxActive(getMaxActive());
        ds.setMaxIdle(getMaxIdle());
        ds.setMaxWait(getMaxWait());
        ds.setMinIdle(getMinIdle());
        ds.setInitialSize(getInitialSize());
        ds.setValidationQuery(getValidationQuery());
        ds.setTestOnBorrow(isTestOnBorrow());
        ds.setTestWhileIdle(getTestWhileIdle());
        ds.setTestOnReturn(getTestOnReturn());
        ds.setTimeBetweenEvictionRunsMillis(getTimeBetweenEvictionRunsMillis());
        ds.setNumTestsPerEvictionRun(getNumTestsPerEvictionRun());
        ds.setMinEvictableIdleTimeMillis(getMinEvictableIdleTimeMillis());
        setDataSource(ds);
        return true;
    }

    private long getMinEvictableIdleTimeMillis() {
        return this.m_minEvictableIdleTimeMillis;
    }

    private int getNumTestsPerEvictionRun() {
        return this.m_numTestsPerEvictionRun;
    }

    private long getTimeBetweenEvictionRunsMillis() {
        return this.m_timeBetweenEvictionRunsMillis;
    }

    private boolean getTestOnReturn() {
        return this.m_testOnReturn;
    }

    private boolean getTestWhileIdle() {
        return this.m_testWhileIdle;
    }

    public long genID(Connection _connection, String _tableName) {
        String sql = "select allocate_record_id from " + _tableName;

        long id = -1L;
        try {
            PreparedStatement statement = _connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if ((rs != null) && (rs.next())) {
                id = rs.getLong(1);
                rs.close();
                statement.close();
            }
        } catch (SQLException e) {
            throw new DBSupportRuntimeException(sql + " error " + e.getMessage(), e);
        }
//        PreparedStatement statement;
        if (id <= 0L) {
            throw new DBSupportRuntimeException("allocate_record_id from " + _tableName + " failed!");}
        return id;
    }

    public Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            throw new DBSupportRuntimeException("Fail to get Mysql connection for " + getUrl() + " .", e);
        }
    }

}
