package com.psn.rtm;

/**
 * Created by zhangdekun on 14-12-10-下午3:04.
 */

/**
 * http 请求通过此类代理来访问http请求需要的方法
 * 代理中可以对这些方法统一管理
 */
public class MethodForRequestProxy {
    private final AbstractMethodForRequest mfr = new AbstractMethodForRequest();

}
