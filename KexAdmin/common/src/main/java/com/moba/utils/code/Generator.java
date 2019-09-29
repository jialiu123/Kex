package com.moba.utils.code;

import org.apache.commons.lang.time.DateFormatUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

/**
 * @author Liujia
 * @version 1.0.0
 */

public class Generator {

    private static String saveBasePath, projectName, packagePrefix;

    // Absoulute package path
    private static String relativeRootPath, projectPackage, commonsPackage;

    // Absoulute save path
    private static String domainPath, daoMapperPath, serviceImplPath, controllerPath, dictionaryPath, jspPath;

    // start up
    static {

        // Base configration
        projectName = "winning.bifx";
        packagePrefix = "com";
        commonsPackage = "com";

        saveBasePath = "D:\\generateCode\\";

        relativeRootPath = new StringBuffer(packagePrefix.replace(".", File.separator)).append(File.separator)
                .append(projectName).append(File.separator).toString();

        projectPackage = packagePrefix + "." + projectName;

        domainPath = relativeRootPath + "com/winning/model";

        daoMapperPath = relativeRootPath + "com/moba/mapper";

        serviceImplPath = relativeRootPath + "com/moba/service";

        controllerPath = relativeRootPath + "com/moba/controller";

        dictionaryPath = relativeRootPath + "com/moba/dictionary";

        jspPath = relativeRootPath;

    }

    public static void run(HttpServletRequest request, HttpServletResponse response, String[] tableNames) throws Exception {
        // ftl路径
        String ftlPath = FreemarkerUtils.ftlPath;
        List<Map<String, Object>> tableList = new ArrayList<Map<String, Object>>();
        for (String tableName : tableNames) {

            // 读取字段信息
            List<Map<String, String>> columnList =
                    DbUtils.getFieldParameterLsit(DbUtils.getDbUtils(request), tableName);

            // 读取Package信息
            Set<String> packageImportList =
                    DbUtils.getPackageImportList(DbUtils.getDbUtils(request), tableName);
            Arrays.sort(packageImportList.toArray());

            // fileName
            String domainFileName = CommUtils.capitalize(tableName.toUpperCase()) + ".java";
            String serviceImplFileName = CommUtils.capitalize(tableName.toUpperCase()) + "Service" + ".java";
            String serviceFacadeImplName = "FacadeService" + ".java";
            String daoImplFileName = CommUtils.capitalize(tableName.toUpperCase()) + "Mapper" + ".java";
            String daoImplSqlMapFileName = CommUtils.capitalize(tableName.toUpperCase()) + "Mapper" + ".xml";

            String controllerFileName = CommUtils.capitalize(tableName.toUpperCase()) + "Controller" + ".java";

            String dictionaryFileName = CommUtils.capitalize(tableName.toUpperCase()) + "Dictionary" + ".txt";
            // Initialize a model
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("packagePrefix", projectPackage);
            model.put("commonsPackagePrefix", commonsPackage);
            model.put("generatorTime", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm"));
            model.put("packageImportList", packageImportList);

            Map<String, Object> table = new HashMap<String, Object>();
            table.put("javaName", CommUtils.capitalize(tableName.toUpperCase()));
            table.put("javaNameUpperCase", CommUtils.capitalize(tableName).toUpperCase());
            table.put("name", tableName);
            table.put("variableName", CommUtils.toLowerCaseFirstLetter(CommUtils.capitalize(tableName.toUpperCase())));
            table.put("columeList", columnList);
            model.put("table", table);

            tableList.add(table);
            model.put("tableList", tableList);

            /* 生成实体类 */
            FreemarkerUtils.printFile("domain.ftl", model, domainPath + "/" + domainFileName, saveBasePath, ftlPath);
//            /* 生成service层 */
//            FreemarkerUtils.printFile("serviceImplForMysql.ftl", model, serviceImplPath + "/" + serviceImplFileName,
//                    saveBasePath, ftlPath);
//            /* 生成dao层 */
//            FreemarkerUtils.printFile("daoImpl.ftl", model, daoMapperPath + "/" + daoImplFileName, saveBasePath, ftlPath);
//            FreemarkerUtils.printFile("daoImplSqlMapForMysql.ftl", model, daoMapperPath + "/" + daoImplSqlMapFileName,
//                    saveBasePath, ftlPath);
//
//            /* 生成controller */
//            FreemarkerUtils.printFile("controller.ftl", model, controllerPath + "/" + controllerFileName, saveBasePath,
//                    ftlPath);
//
//            /* 生成数字字典 */
//            FreemarkerUtils.printFile("dictionary.ftl", model, dictionaryPath + "/" + dictionaryFileName, saveBasePath,
//                    ftlPath);
        }
    }
}
