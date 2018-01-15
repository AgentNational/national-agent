package com.pay.national.agent.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by shuyan.qi on 2018/1/13.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedOpenId {
    /**是否需要openId，默认为false不需要openId
     *
     * @return
     */
    boolean needOpenId() default false;

    /**是否需要关注
     *
     * @return
     */
    boolean subscribeFlag() default false;

    /**显示/隐式授权，默认为false隐式授权
     *
     * @return
     */
    boolean oauth2Flag() default false;

    /**是否需要登录验证，默认为true
     *
     * @return
     */
    boolean isLoginFlag() default true;
}