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
    
}
