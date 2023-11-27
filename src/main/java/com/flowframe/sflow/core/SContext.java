package com.flowframe.sflow.core;

import java.util.Map;

import com.flowframe.sflow.core.model.SFlowInstance;
import com.google.common.collect.Maps;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author qrz
 * @description
 * @date 2023/11/17 3:01
 * 山不向我走来，我便向它走去
 */
@Data
public class SContext {

    /**
     * 场景编码
     */
    @NotBlank(message = "场景编码不能为空")
    private String sceneCode;
    /**
     * 业务类型
     */
    @NotBlank(message = "业务类型不能为空")
    private String bizType;

    /**
     * ql表达式执行结果
     */
    private String qlResult;

    private SFlowInstance sFlowInstance;
    private Map<String, Object> extMap;

    public Object getObjectFromExt(String key) {
        return extMap == null ? null : extMap.get(key);
    }

    public synchronized void putToExtIfAbsent(String key, Object value) {
        if (extMap == null) {
            extMap = Maps.newHashMap();
        }
        extMap.putIfAbsent(key, value);
    }


    public synchronized void putToExt(String key, Object value) {
        if (extMap == null) {
            extMap = Maps.newHashMap();
        }
        extMap.put(key, value);
    }

}


