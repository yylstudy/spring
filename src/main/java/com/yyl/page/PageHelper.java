package com.yyl.page;

import org.apache.ibatis.executor.statement.RoutingStatementHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2019/12/12 19:00
 * @Version: 1.0
 */
public class PageHelper {
    public static ThreadLocal<Long> pageTotal = ThreadLocal.withInitial(()->0L);

    public static String getPageSql(Connection connection, String sql, PageInfo pageInfo) throws Exception{
        String dbName = connection.getMetaData().getDatabaseProductName();
        DatabaseIdProviderEnum database = DatabaseIdProviderEnum.getDatabaseIdEnum(dbName);
        if(database==DatabaseIdProviderEnum.MYSQL){
            return sql+" limit "+pageInfo.getPageNo()*pageInfo.getPageSize()+" ,"+pageInfo.getPageSize();
        }
        return sql;
    }



    public static void setTotal(long total){
        pageTotal.set(total);
    }
    public static long getTotal(){
        return pageTotal.get();
    }

}
