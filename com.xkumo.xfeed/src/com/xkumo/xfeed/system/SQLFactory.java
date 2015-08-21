package com.xkumo.xfeed.system;

public class SQLFactory {
	public static String getPageSql(String sql, int pageIndex, int limit)
    {
        StringBuilder pageSql = new StringBuilder();

        pageSql.append(sql);
        pageSql.append(" limit " + pageIndex + "," + limit + " ");

        return pageSql.toString();
    }
}
