package com.psn.datasource.db;

import com.jolbox.bonecp.BoneCPDataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangdekun on 15-1-19-上午10:50.
 */
public class DbPool {
    private static Logger logger = Logger.getLogger(DbPool.class);
    private BoneCPDataSource boneCPDataSource ;
    private static final Map<Integer,DbPool> cachePool = new ConcurrentHashMap<Integer,DbPool>();
    private DbPool(){}
    private DbPool(Db db){
        boneCPDataSource = new BoneCPDataSource();
        boneCPDataSource.setDriverClass(db.getDriverClass());
        boneCPDataSource.setJdbcUrl(db.getUrl());
        boneCPDataSource.setUsername(db.getUsername());
        boneCPDataSource.setPassword(db.getPassword());

        boneCPDataSource.setAcquireIncrement(JdbcConfig.bonecpAcquireIncrement());
        boneCPDataSource.setMaxConnectionAge(JdbcConfig.bonecpMaxConnectionAge(), TimeUnit.MINUTES);
        boneCPDataSource.setIdleMaxAgeInMinutes(JdbcConfig.bonecpMaxIdleAge());
        boneCPDataSource.setPartitionCount(JdbcConfig.bonecpPartitionCount());
        boneCPDataSource.setMaxConnectionsPerPartition(JdbcConfig.bonecpMaxConnectionPerPartition());
        boneCPDataSource.setMinConnectionsPerPartition(JdbcConfig.bonecpMinConnectionPerPartition());
        boneCPDataSource.setConnectionTimeout(JdbcConfig.bonecpConnectionTimeout(),TimeUnit.MINUTES);
        boneCPDataSource.setIdleConnectionTestPeriodInMinutes(JdbcConfig.bonecpIdleConnectionTestPeriod());
    }
    private static boolean isCached(Db db){
        boolean isCache = false;
        int hac = db.hashCode();
        if(cachePool.containsKey(hac)){
            isCache = true;
        }
        return isCache;
    }
    private static Db getDb(){
        Db db = new Db();
        db.setDriverClass(JdbcConfig.jdbcDriverClass());
        db.setPassword(JdbcConfig.jdbcPassword());
        db.setUrl(JdbcConfig.jdbcUrl());
        db.setUsername(JdbcConfig.jdbcUsername());
        return db;
    }

    public static DbPool instance(){
        Db db = getDb();
        if(isCached(db)){
            return cachePool.get(db.hashCode());
        }else {
            DbPool pool = new DbPool(db);
            cachePool.put(db.hashCode(),pool);
            return pool;
        }
    }
    public Connection getConnection(){
        Connection con = null;
        try{
            con = this.boneCPDataSource.getConnection();
        }catch (Exception e){
            logger.error("get connection error : "+e);
        }finally {
            return con ;
        }
    }
}
