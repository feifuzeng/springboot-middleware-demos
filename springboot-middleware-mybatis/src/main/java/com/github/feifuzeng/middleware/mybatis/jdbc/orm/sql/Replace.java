package com.github.feifuzeng.middleware.mybatis.jdbc.orm.sql;

import lombok.Data;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author feifz
 * @version 1.0.0
 * @Description replace 语句替换
 * @createTime 2019年08月26日 13:48:00
 */
@Data
public class Replace {

    private String tableName;
    private Map<String, String> columns = new LinkedHashMap<>();


    public String toStatementString() {
        StringBuilder buf = new StringBuilder(columns.size() * 15 + tableName.length() + 10);
        buf.append("replace into ")
                .append(tableName);
        if (columns.size() == 0) {
            buf.append(' ').append("values ( )");
        } else {
            buf.append(" (");
            Iterator iter = columns.keySet().iterator();
            while (iter.hasNext()) {
                buf.append(iter.next());
                if (iter.hasNext()) {
                    buf.append(", ");
                }
            }
            buf.append(") values (");
            iter = columns.values().iterator();
            while (iter.hasNext()) {
                buf.append(iter.next());
                if (iter.hasNext()) {
                    buf.append(", ");
                }
            }
            buf.append(')');
        }
        return buf.toString();
    }
}
