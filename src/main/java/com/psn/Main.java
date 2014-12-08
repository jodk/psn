package com.psn;

import com.psn.server.PsnHttpServer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *start server
 */
public class Main
{
    private static Log log = LogFactory.getLog(Main.class);
    public static void main( String[] args ) throws Exception
    {
        int port = 8080;

        PsnHttpServer psnHttpServer = new PsnHttpServer(port);
        log.info("PSN Http server listening on 8080...");
        psnHttpServer.start();
    }
}
