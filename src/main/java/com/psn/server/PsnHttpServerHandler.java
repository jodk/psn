package com.psn.server;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * Created by zhangdekun on 14-12-8-上午10:42.
 */
public class PsnHttpServerHandler extends ChannelInboundHandlerAdapter{
    private static Log log = LogFactory.getLog(PsnHttpServer.class);
    private HttpRequest request;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try{
            if(msg instanceof HttpRequest){
                this.request = (HttpRequest)msg;
                log.debug("uri:"+this.request.getUri());
            }
            if(msg instanceof HttpContent){
                HttpContent content = (HttpContent)msg;
                ByteBuf buf = content.content();
                log.debug(buf.toString(CharsetUtil.UTF_8));
                System.out.println(buf.toString(CharsetUtil.UTF_8));
                String res = "I am OK...";
                FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,OK, Unpooled.wrappedBuffer(res.getBytes("UTF-8")));
                response.headers().set(CONTENT_TYPE,"text/plain");
                response.headers().set(CONTENT_LENGTH,response.content().readableBytes());
                if(HttpHeaders.isKeepAlive(request)){
                    response.headers().set(CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
                }
                ctx.write(response);
                ctx.flush();
            }
        }finally {
            ReferenceCountUtil.release(msg);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
