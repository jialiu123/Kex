package com.moba.utils.code;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.*;
import java.util.Locale;
import java.util.Map;

/**
 * Freemarker 模版引擎类
 */
public class FreemarkerUtils {

    public static final String ftlPath = "/templates";

    /**
     * 打印到控制台(测试用)
     *
     * @param ftlName
     */
    public static void print(String ftlName, Map<String, Object> root, String ftlPath)
            throws Exception {
        try {
            Template temp = getTemplate(ftlName, ftlPath);
            temp.process(root, new PrintWriter(System.out));
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 输出到输出到文件
     *
     * @param ftlName  ftl文件名
     * @param root     传入的map
     * @param outFile  输出后的文件全部路径
     * @param filePath 输出前的文件上部路径
     */
    public static void printFile(
            String ftlName, Map<String, Object> root, String outFile, String filePath, String ftlPath)
            throws Exception {
        try {
            File file = new File(filePath + outFile);

            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
            Template template = getTemplate(ftlName, ftlPath);
            template.process(root, out);
            out.flush();
            out.close();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过文件名加载模版
     *
     * @param ftlName
     */
    public static Template getTemplate(String ftlName, String ftlPath) {
        try {
            Configuration cfg = new Configuration();
            cfg.setEncoding(Locale.CHINA, "utf-8");
            cfg.setDirectoryForTemplateLoading(new File(PathUtil.getClassResources() + ftlPath));
            Template temp = cfg.getTemplate(ftlName);
            return temp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过文件名加载模版并且返回内容
     *
     * @param ftlName
     */
    public static String getTemplateReturnString(String ftlName, Object model) {
        try {
            Template template2 = getTemplate(ftlName, ftlPath);
            return FreeMarkerTemplateUtils.processTemplateIntoString(template2, model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
  }
}
