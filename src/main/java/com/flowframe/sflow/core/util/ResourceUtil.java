package com.flowframe.sflow.core.util;

import java.io.File;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author qrz
 * @date 2020/10/30
 */
@Slf4j
public class ResourceUtil {

    public static  <T> T getObjectFromJsonPath(String path, Class<T> z){
        T t=null;
        try {
            File file1 = new File(Thread.currentThread().getContextClassLoader().getResource(path).getFile());
            t= JSON.parseObject(FileUtils.readFileToString(file1, "UTF-8"),z);
        } catch (Exception e) {
            log.error("error", e);
        }
        return t;
    }

    public static String getStringFromPath(String path){
        String t=null;
        try {
            File file1 = new File(Thread.currentThread().getContextClassLoader().getResource(path).getFile());
            t= FileUtils.readFileToString(file1, "UTF-8");
        } catch (Exception e) {
            log.error("getStringFromPath fail",e);
        }
        return t;
    }

}
