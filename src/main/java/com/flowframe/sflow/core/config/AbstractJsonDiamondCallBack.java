package com.flowframe.sflow.core.config;

import com.alibaba.fastjson.JSON;

import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author qrz
 * @description dimaond 基类 供继承
 * @date 2023/11/17 3:01
 * 山不向我走来，我便向它走去
 */
//@Slf4j
//public abstract class AbstractJsonDiamondCallBack<T> implements DiamondDataCallback {
//
//    @Getter
//    protected T config ;
//
//    /**
//     * diamond dataId
//     * @return
//     */
//    public abstract String getDataId();
//
//    /**
//     * diamond groupId
//     * @return
//     */
//    public abstract String getGroupId();
//
//    /**
//     * diamond config json 对象对应的实体类
//     * @return
//     */
//    protected abstract Class<T> getConfigType();
//
//
//    @Override
//    public void received(String data) {
//        log.info("Receive helper from diamond: dataId= {}, data= {}", getDataId(), data);
//        if(StringUtils.isNotEmpty(data)){
//            try{
//                System.out.println(JSON.toJSONString(getConfigType()));
//                this.config= TypeSerializationAdapter.deserialize(getConfigType(),data);
////                this.config =JSON.parseObject(data, SFlowWrapper.class);
//                System.out.println(JSON.toJSONString(this.config));
//            }catch (Throwable e ){
//                log.error("diamond helper to type :type={}",getConfigType(),e);
//            }
//        }
//    }
//
//
//
//
//    public static class TypeSerializationAdapter {
//
//        public static <T> String serialize(T data) {
//            return data != null ? JsonUtil.toJsonString(data) : null;
//        }
//
//        public static <T> T deserialize(Class<T> type, String data) {
//            return deserialize(type, data, null);
//        }
//
//        public static <T> T deserialize(Class<T> type, String data, T defaultValue) {
//            if (org.apache.commons.lang3.StringUtils.isBlank(data)) {
//                return null;
//            }
//            T obj = JsonUtil.parseObject(data, type);
//            return obj != null ? obj : defaultValue;
//        }
//
//    }

//}
