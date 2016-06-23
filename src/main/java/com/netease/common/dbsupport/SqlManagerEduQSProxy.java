package com.netease.common.dbsupport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.time.StopWatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netease.edu.commons.constants.CommonConstants;
import com.netease.edu.commons.utils.log.holder.LogInfo;
import com.netease.edu.commons.utils.log.holder.LogInfoHolder;
import com.netease.framework.dbsupport.SqlManager;
import com.netease.framework.dbsupport.impl.DBResource;
import com.netease.framework.dbsupport.impl.SqlManagerImpl;

import org.apache.log4j.Logger;

public class SqlManagerEduQSProxy extends SqlManagerImpl implements SqlManager {
    private static final Logger logger = Logger.getLogger(SqlManagerImpl.class);


    private static final Log performLog            = LogFactory.getLog(CommonConstants.PERFORM_LOG_NAME);
    private static final Log frequentCalledBeanLog = LogFactory.getLog(CommonConstants.FREQUENT_CALLED_BEAN_LOG);


    private PreparedStatement getPreparedStatement(Connection con, String sql, List<Object> params) {
        PreparedStatement stat = null;

        try {
            stat = con.prepareStatement(sql);
            int e = 1;
            Iterator<Object> var9 = params.iterator();

            while(var9.hasNext()) {
                Object param = var9.next();
                stat.setObject(e++, param);
            }

            return stat;
        } catch (SQLException  var8) {
            String msg = "sql:" + sql + ",params:" + params;
            logger.error(msg, var8);
            this.getRuntimeStatCounter().incrFailCount();
            throw new RuntimeException(msg, var8);
        }
    }


    private boolean hasParams(List<Object> params) {
        return params != null && params.size() != 0;
    }


    public DBResource innerExecuteQuery(String sql, List<Object> params) {
        StopWatch watch = new StopWatch();
        watch.start();
        Connection con = this.getConnection();
        if(logger.isInfoEnabled()) {
            logger.info("query:" + sql + " params:" + params);
        }

        DBResource dbr = null;

        try {
            ResultSet msg1;
            if(this.hasParams(params)) {
                PreparedStatement e = this.getPreparedStatement(con, sql, params);
                msg1 = e.executeQuery();
                dbr = new DBResource(con, e, msg1);
            } else {
                Statement e1 = con.createStatement();
                msg1 = e1.executeQuery(sql);
                dbr = new DBResource(con, e1, msg1);
            }
        } catch (Throwable var8) {
            String msg = "sql:" + sql + ",params:" + params;
            logger.error(msg, var8);
            this.getRuntimeStatCounter().incrFailCount();

            //QS模式下的逻辑处理
            closeDBResource(new DBResource(con,null,null));

            throw new RuntimeException(msg, var8);
        }

        watch.stop();
        if(watch.getTime() > 2000L) {
            logger.warn("query:" + sql + ",params:" + params + ",time:" + watch.getTime());
            this.getRuntimeStatCounter().incrDoneCount();
        }

        return dbr;
    }

    @Override
    public DBResource executeQuery(String sql, List<Object> params) {


        boolean logPerform = performLog.isInfoEnabled();
        long startMilli = 0;
        if (logPerform) {
            startMilli = System.currentTimeMillis();

        }

        DBResource dbr = this.innerExecuteQuery(sql, params);

        if (logPerform) {
            LogInfo logInfo = LogInfoHolder.getLogInfo();
            if (logInfo != null && logInfo.isFrequentCalledBean()) {
                frequentCalledBeanLog.info("calling sql:" + sql + " params: " + params + " using: "
                                           + (System.currentTimeMillis() - startMilli) + " ms. ");
            } else {
                performLog.info("calling sql:" + sql + " params: " + params + " using: "
                                + (System.currentTimeMillis() - startMilli) + " ms. ");
            }

        }

        return dbr;
    }

    @Override
    public int updateRecords(String sql, List<Object> params) {

        boolean logPerform = performLog.isInfoEnabled();
        long startMilli = 0;
        if (logPerform) {
            startMilli = System.currentTimeMillis();

        }

        int cnt = super.updateRecords(sql, params);

        if (logPerform) {
            LogInfo logInfo = LogInfoHolder.getLogInfo();
            if (logInfo != null && logInfo.isFrequentCalledBean()) {
                frequentCalledBeanLog.info("calling sql:" + sql + " params: " + params + " using: "
                                           + (System.currentTimeMillis() - startMilli) + " ms. ");
            } else {
                performLog.info("calling sql:" + sql + " params: " + params + " using: "
                                + (System.currentTimeMillis() - startMilli) + " ms. ");
            }

        }

        return cnt;
    }

}
