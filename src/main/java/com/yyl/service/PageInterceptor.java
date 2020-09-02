package com.yyl.service;

import com.yyl.page.PageHelper;
import com.yyl.page.PageInfo;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.javassist.tools.reflect.Metaobject;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2020/3/4 14:13
 * @Version: 1.0
 */
@Component("pageInterceptor")
@Intercepts(@Signature(type = StatementHandler.class,method = "prepare",args = {Connection.class,Integer.class}))
public class PageInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        RoutingStatementHandler routingStatementHandler = (RoutingStatementHandler)invocation.getTarget();
        Method method = invocation.getMethod();
        Object[] args = invocation.getArgs();
        Connection connection = (Connection)args[0];
        Integer transactionTimeout = (Integer)args[1];
        Object paramObject = routingStatementHandler.getParameterHandler().getParameterObject();
        if(isNeedPage(paramObject)){
            BoundSql boundSql = routingStatementHandler.getBoundSql();
            String countSql = "select count(1) from ("+boundSql.getSql()+") totalTable";
            PreparedStatement ps = connection.prepareStatement(countSql);
            routingStatementHandler.parameterize(ps);
            ResultSet rs = ps.executeQuery();
            long total = 0;
            if(rs.next()){
                total = rs.getLong(1);
            }
            PageHelper.setTotal(total);
            String pageSql = PageHelper.getPageSql(connection,boundSql.getSql(),getPageInfo(paramObject));
            MetaObject boundSqlMetaObject = SystemMetaObject.forObject(boundSql);
            boundSqlMetaObject.setValue("sql",pageSql);
        }
        return method.invoke(routingStatementHandler,connection,transactionTimeout);
    }

    private boolean isNeedPage(Object paramObject){
        return getPageInfo(paramObject)!=null;
    }

    private PageInfo getPageInfo(Object paramObject){
        if(paramObject instanceof Map){
            return (PageInfo)((Map) paramObject).values().stream().filter(param->param instanceof PageInfo).findFirst().orElse(null);
        }
        if(paramObject instanceof PageInfo){
            return (PageInfo)paramObject;
        }
        return null;
    }



    @Override
    public Object plugin(Object target) {
        if(target instanceof StatementHandler){
            return Plugin.wrap(target, this);
        }
        return target;
    }
}
