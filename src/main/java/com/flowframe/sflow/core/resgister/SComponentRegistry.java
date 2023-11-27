package com.flowframe.sflow.core.resgister;

import java.util.Map;

import com.flowframe.sflow.core.SComponent;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author qrz
 * @description 组件流程注册
 * 山不向我走来，我便向它走去
 */
@Slf4j
@Component
public class SComponentRegistry {

    private static final Map<String, SComponent> componentMap = Maps.newConcurrentMap();

    public static SComponent getComponent(String key){
        return componentMap.get(key);
    }

    public static void register(String key , SComponent component){

        if(componentMap.get(key) != null){
            log.error("SComponentRegistry"," same component ! please check key"+key);
            throw new RuntimeException("不允许重复注册组件，code:"+key);
        }
        componentMap.put(key, component);
    }

}
