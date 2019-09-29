package com.moba.utils.code;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.*;

/**
 *
 */
public class DbUtils {

    private static String dbtype = "mysql";

    private static String username = "sa";

    private static String password = "sql2k8";

    private static String address = "172.16.0.29";

    private static String dbport = "1433";

    private static String databaseName = "PT_STORE_JCGL";

    private static DbUtils dbUtils = new DbUtils();

    public static DbUtils getDbUtils() {
        return dbUtils;
    }

    /**
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Object[] getTables(HttpServletRequest request)
            throws ClassNotFoundException, SQLException {

        Connection conn =
                DbUtils.getCon(dbtype, username, password, address + ":" + dbport, databaseName);
        if ("oracle".equals(dbtype)) {
            databaseName = username;
        }
        Object[] arrOb = {
                databaseName,
                DbUtils.getTablesByCon(conn, "sqlserver".equals(dbtype) ? null : databaseName),
                dbtype
        };
        return arrOb;
    }

    /**
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getDbUtils(HttpServletRequest request)
            throws ClassNotFoundException, SQLException {
        return DbUtils.getCon(dbtype, username, password, address + ":" + dbport, databaseName);
    }

    /**
     * @param dbtype       数据库类型
     * @param username     用户名
     * @param password     密码
     * @param dburl        数据库连接地址:端口
     * @param databaseName 数据库名
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Connection getCon(
            String dbtype, String username, String password, String dburl, String databaseName)
            throws SQLException, ClassNotFoundException {
        if ("mysql".equals(dbtype)) {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://"
                            + dburl
                            + "/"
                            + databaseName
                            + "?user="
                            + username
                            + "&password="
                            + password);
        } else if ("oracle".equals(dbtype)) {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(
                    "jdbc:oracle:thin:@" + dburl + ":" + databaseName, username, password);
        } else if ("sqlserver".equals(dbtype)) {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(
                    "jdbc:sqlserver://" + dburl + "; DatabaseName=" + databaseName, username, password);
        } else {
            return null;
        }
    }

    /**
     * 获取某个conn下的所有表
     *
     * @param conn   数据库连接对象
     * @param schema mysql:数据库名; oracle:用户名;sqlserver:null
     * @return
     */
    public static List<String> getTablesByCon(Connection conn, String schema) {
        try {
            List<String> listTb = new ArrayList<String>();
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getTables(null, schema, null, new String[]{"TABLE"});
            while (rs.next()) {
                listTb.add(rs.getString(3));
            }
            return listTb;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 字段名列表
     *
     * @param conn
     * @param table
     * @return
     * @throws SQLException
     */
    public static List<String> getFieldLsit(Connection conn, String table) throws SQLException {
        PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(table);
        pstmt.execute();
        List<String> columnList = new ArrayList<String>();
        ResultSetMetaData rsmd = (ResultSetMetaData) pstmt.getMetaData();
        for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
            columnList.add(rsmd.getColumnName(i));
        }
        return columnList;
    }

    /**
     * (字段名、类型、长度)列表
     *
     * @param conn
     * @param table
     * @return
     * @throws SQLException
     */
    public static List<Map<String, String>> getFieldParameterLsit(Connection conn, String table)
            throws SQLException {
        PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement("select * from " + table);
        pstmt.execute(); // 这点特别要注意:如果是Oracle而对于mysql可以不用加.
        // 存放字段
        List<Map<String, String>> columnList = new ArrayList<Map<String, String>>();
        ResultSetMetaData rsmd = (ResultSetMetaData) pstmt.getMetaData();
        for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
            Map<String, String> fmap = new HashMap<String, String>();
            // 这个地方替换成为驼峰类型的
            String lowerCaseName = rsmd.getColumnName(i);
            String[] lowerCaseNames = rsmd.getColumnName(i).toLowerCase().split("_");
            for (int i1 = 0; i1 < lowerCaseNames.length; i1++) {
                if (i1 >= 1) {
                    lowerCaseName +=
                            lowerCaseNames[i1].substring(0, 1).toUpperCase() + lowerCaseNames[i1].substring(1);
                } else {
                    lowerCaseName += lowerCaseNames[i1];
                }
            }
            fmap.put("lowerCaseName", lowerCaseName);
            fmap.put("javaName", StringUtils.capitalize(lowerCaseName));
            String javaTypeFullName = JavaTypeNameTranslator.getJavaTypeName(rsmd, i);
            // 字段类型名称
            int lastIndex = javaTypeFullName.lastIndexOf('.');
            fmap.put("javaType", javaTypeFullName.substring(lastIndex + 1));
            // jdbc名称
            fmap.put("jdbcType", JdbcTypeNameTranslator.getJdbcTypeName(rsmd.getColumnType(i)));
            fmap.put("name", rsmd.getColumnName(i));
            // 长度
            fmap.put("fieldLength", String.valueOf(rsmd.getColumnDisplaySize(i)));

            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet resultSet = dbmd.getTables(null, null, table, new String[]{"TABLE"});
            while (resultSet.next()) {
                ResultSet rsmysql = dbmd.getColumns(null, "%", table, "%");
                while (rsmysql.next()) {
                    if (rsmysql.getString("COLUMN_NAME").equalsIgnoreCase(rsmd.getColumnName(i))) {
                        fmap.put("remarks", rsmysql.getString("REMARKS"));
                        break;
                    }
                }
            }

            columnList.add(fmap);
        }
        return columnList;
    }

    /**
     * (字段名、类型、长度)列表
     *
     * @param conn
     * @param table
     * @return
     * @throws SQLException
     */
    public static Set<String> getPackageImportList(Connection conn, String table)
            throws SQLException {
        PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement("select * from " + table);
        pstmt.execute();
        Set<String> packageImportList = new HashSet<String>();
        ResultSetMetaData rsmd = (ResultSetMetaData) pstmt.getMetaData();
        for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
            String javaTypeFullName = JavaTypeNameTranslator.getJavaTypeName(rsmd, i);
            int lastIndex = javaTypeFullName.lastIndexOf('.');
            if (lastIndex != -1 && !javaTypeFullName.startsWith("java.lang.")) {
                packageImportList.add(javaTypeFullName);
            }
        }
        return packageImportList;
  }
}
