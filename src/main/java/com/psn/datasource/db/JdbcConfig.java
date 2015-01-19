package com.psn.datasource.db;

import static com.psn.util.StringUtils.isEmpty;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by zhangdekun on 15-1-19-上午10:52.
 */
public class JdbcConfig {
    private static Properties props = new Properties();
    private static final String CONFIG_FILE="jdbc.properties";

    private static Logger logger = Logger.getLogger(JdbcConfig.class);
    static {
        InputStream in =JdbcConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
        try {
            props.load(in);
        } catch (IOException e) {
            logger.info(e.getMessage(),e);
        }
    }
    public static String jdbcUrl(){
        String url = null;
        url = props.getProperty("jdbc.url",null);
        if(isEmpty(url)){
            logger.error("jdbc.url can not be empty");
        }
        return url;
    }
    public static String jdbcUsername(){
        String username = null;
        username = props.getProperty("jdbc.username",null);
        if(isEmpty(username)){
            logger.error("jdbc.username can not be empty");
        }
        return username;
    }
    public static  String jdbcPassword(){
        String password = null;
        password = props.getProperty("jdbc.password",null);
        if(isEmpty(password)){
            logger.warn("jdbc.password is empty");
        }
        return password;
    }
    public static String jdbcDriverClass(){
        String driver = null;
        driver = props.getProperty("jdbc.driverClass",null);
        if(isEmpty(driver)){
            logger.error("jdbc.driverClass can not be empty");
        }
        return driver;
    }
    //default 3
    public static  int bonecpPartitionCount(){
        int count = Integer.parseInt(props.getProperty("bonecp.partitionCount","3"));
        if(count == 3){
            logger.info("default bonecp partition count 3 if no set");
        }
        return count;
    }
    public static  int bonecpMaxConnectionPerPartition(){
        int max = Integer.parseInt(props.getProperty("bonecp.maxConnectionPerPartition","10"));
        if(max == 10){
            logger.info("deafult bonecp max connection perpartition 10 if no set");
        }
        return max;
    }
    public static  int bonecpMinConnectionPerPartition(){
        int min = Integer.parseInt(props.getProperty("bonecp.minConnectionPerPartition","1"));
        if(min == 10){
            logger.info("deafult bonecp min connection perpartition 1 if no set");
        }
        return min;
    }
    public static int bonecpAcquireIncrement(){
        int increment = Integer.parseInt(props.getProperty("bonecp.acquireIncrement","3"));
        if(increment == 3){
            logger.info("default bonecp acquire increment 3 if no set");
        }
        return increment;
    }

    /**
     * unit by minute
     * @return
     */
    public static int bonecpConnectionTimeout(){
        int timeout = Integer.parseInt(props.getProperty("bonecp.connectionTimeout","60"));
        if(timeout==60){
            logger.info("default bonecp connection timeout 60 minute if no set");
        }
        return timeout;
    }

    /**
     * unit by minute
     * @return
     */
    public static int bonecpMaxConnectionAge(){
        int maxAge = Integer.parseInt(props.getProperty("bonecp.maxConnectionAge","60"));
        if(maxAge == 60){
            logger.info("default bonecp max connection age 60 minute if no set");
        }
        return maxAge;
    }
    /**
     * unit by minute
     * @return
     */
    public static int bonecpMaxIdleAge(){
        int maxIdleAge = Integer.parseInt(props.getProperty("bonecp.idleMaxAge","60"));
        if(maxIdleAge == 60){
            logger.info("default bonecp max idle age 60 minute if no set");
        }
        return maxIdleAge;
    }

    /**
     * unit by minute
     * @return
     */
    public static int bonecpIdleConnectionTestPeriod(){
        int testPeriod = Integer.parseInt(props.getProperty("bonecp.idleConnectionTestPeriod","30"));
        if(testPeriod == 30){
            logger.info("default idle connection test period 30 minute if no set");
        }
        return testPeriod;
    }
}
