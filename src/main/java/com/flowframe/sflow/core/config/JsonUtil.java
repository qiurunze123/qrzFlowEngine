/*
package com.flowframe.sflow.core.config;

import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.base.Joiner;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

*/
/**
 * @author qrz
 * @description Json静态工具（基于jackson）
 * 考虑到大多数使用场景，所以：反序列化方法会吞掉异常；序列化方法会抛出异常
 * @date 2023/11/17 3:01
 * 山不向我走来，我便向它走去
 * UtilityClass 通过用@UtilityClass标记你的类，lombok会自动生成一个抛出异常的私有构造函数，将你添加的任何显式构造函数标记为错误，并将该类标记为final。如果该类是一个内部类，该类也被标记为static
 *//*

@Slf4j
@UtilityClass
public class JsonUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final ObjectMapper MAPPER_SNAKE = new ObjectMapper();

    static {
        //序列化时，跳过null属性
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //序列化时，遇到空bean（无属性）时不会失败
        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //反序列化时，遇到未知属性（在bean上找不到对应属性）时不会失败
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //通过fields来探测（不通过标准getter探测）
        MAPPER.configure(MapperFeature.AUTO_DETECT_FIELDS, true);

        //序列化时，跳过null属性
        MAPPER_SNAKE.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //序列化时，遇到空bean（无属性）时不会失败
        MAPPER_SNAKE.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //反序列化时，遇到未知属性（在bean上找不到对应属性）时不会失败
        MAPPER_SNAKE.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //通过fields来探测（不通过标准getter探测）
        MAPPER_SNAKE.configure(MapperFeature.AUTO_DETECT_FIELDS, true);

        MAPPER_SNAKE.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    */
/**
     * 反序列化
     *//*

    public static <T> T parseObject(String json, Class<T> type) {
        if (org.apache.commons.lang3.StringUtils.isBlank(json)) {
            return null;
        }
        try {
            return MAPPER.readValue(json, type);
        } catch (Exception e) {
            log.warn("jackson deserialize failed. json: {} type: {}", json, type, e);
            throw new RuntimeException(e);
        }
    }

    */
/**
     * 支持@type autotype
     *//*

    public static <T> T parseObject(byte[] json, Class<T> type) {
        try {
            return MAPPER.readValue(json, type);
        } catch (Exception e) {
            log.warn("jackson deserialize failed. json: {} type: {}", json, type, e);
            throw new RuntimeException(e);
        }
    }

    public static String toJsonString(Object target) {
        try {
            return MAPPER.writeValueAsString(target);
        } catch (JsonProcessingException e) {
            log.warn("jackson serialize failed. target: {}", target.getClass(), e);
            return "";
        }
    }

    public static String toJsonStringSnakeCase(Object target) {
        try {
            return MAPPER_SNAKE.writeValueAsString(target);
        } catch (JsonProcessingException e) {
            log.warn("jackson serialize failed. target: {}", target.getClass(), e);
            return "";
        }
    }

    public static byte[] toJsonBytes(Object target) {
        try {
            return MAPPER.writeValueAsBytes(target);
        } catch (JsonProcessingException e) {
            log.warn("jackson serialize failed. target: {}", target.getClass(), e);
        }
        return new byte[0];
    }

    public static <T> T convertFromMap(java.util.Map value, Class<T> clazz) {
        return MAPPER.convertValue(value, clazz);
    }

    public static <T> T convertFromMapSnakeCase(java.util.Map value, Class<T> clazz) {
        return MAPPER_SNAKE.convertValue(value, clazz);
    }

    */
/**
     * 解析 返回数据中的花名 中文逗号
     * @param cateOwner
     * @return
     *//*

    public String parseCateOwner(String cateOwner) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(cateOwner)) {
            return "";
        }
        java.util.List<java.util.Map> cateOwnerArray = JSONArray.parseArray(cateOwner, java.util.Map.class);
        java.util.List<String> nickNames = cateOwnerArray.stream().
            map(map -> String.valueOf(map.get("nickName"))).collect(Collectors.toList());
        String nickName = Joiner.on("，").join(nickNames);
        return nickName;
    }

    */
/**
     * 解析 返回数据中的花名
     * @param operator
     * @return
     *//*

    public String parseOperatorName(String operator) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(operator)) {
            return "";
        }
        JSONObject jsonObject = (JSONObject) JSONObject.parse(operator);
        String nickName = (String)jsonObject.get("operatorName");
        return nickName;
    }

}
*/
