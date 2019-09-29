package com.moba.utils.code;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class CommUtils {

    protected static final Logger logger = LoggerFactory.getLogger(CommUtils.class);

    public static String encodingFileName(String fileName) {
        String newFileName = "noName.xls";
        String returnFileName = "";
        try {
            if (StringUtils.isNotBlank(fileName)) {
                newFileName = fileName;
            }
            returnFileName = URLEncoder.encode(newFileName, "UTF-8");
            returnFileName = StringUtils.replace(returnFileName, "+", "%20");

            returnFileName = new String(newFileName.getBytes("GB2312"), "ISO8859-1");
            returnFileName = StringUtils.replace(returnFileName, " ", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.error("Don't support this encoding ...");
        }
        logger.info("encoded file name is {}", returnFileName);
        logger.info("encoded file name is {}", Integer.valueOf(returnFileName.length()));
        return returnFileName;
    }

    public static String toLowerCaseFirstLetter(String s) {
        if (null == s) {
            return null;
        }

        String firstLetter = String.valueOf(s.charAt(0));

        return s.replaceFirst(firstLetter, firstLetter.toLowerCase());
    }

    public static String capitalize(String s) {
        if (null == s) {
            return null;
        }

        String[] ss = s.split("_");
        for (int i = 0; i < ss.length; i++) {
            ss[i] = StringUtils.capitalize(ss[i].toLowerCase());
        }

        return StringUtils.join(ss);
    }


}
