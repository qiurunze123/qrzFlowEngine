package com.flowframe.sflow.examples.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.flowframe.sflow.core.undertake.context.SflowContext;
import com.flowframe.sflow.core.util.ResourceUtil;

/**
 *  @author qrz
 *  @description 组件流程注册 测试类
 *  山不向我走来，我便向它走去
 */
public class FlowInstanceDataUtils {

    public static final String CONTENT_DEFAULT_JSON = "sflow_qrz_mock_data/sflow_test.json";

    public static String getContentStr() {
        return getContentStr(CONTENT_DEFAULT_JSON);
    }

    public static String getContentStr(String path) {
        String contentStr = ResourceUtil.getStringFromPath(path);
        return contentStr;
    }

    public static SflowContext getSflowContext() {
        return getSflowContext(CONTENT_DEFAULT_JSON);
    }

    public static SflowContext getSflowContext(String path) {
        String contentStr = getContentStr(path);
        JSONObject context = JSON.parseObject(contentStr);
        SflowContext verifyContext = JSON.parseObject(
            context.getString("context"),
            SflowContext.class);

        return verifyContext;
    }
}
