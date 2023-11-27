package com.flowframe.sflow.core.config.diamond;

import com.alibaba.fastjson.JSON;

import com.flowframe.sflow.core.model.SFlowWrapper;
import com.flowframe.sflow.core.resgister.SFlowRegistry;
import com.google.common.base.Strings;
import org.springframework.stereotype.Component;

/**
 * @author qrz
 * @description 配置流程类 可以在配置自己的diamond 数据 现在的mock 数据放在resources 方便测试
 * @date 2023/11/17 3:01
 * 山不向我走来，我便向它走去
 */
//@Component
//@DiamondListener(dataId = SFlowConfig.DATA_ID, groupId = SFlowConfig.GROUP_ID)
//public class SFlowConfig implements DiamondDataCallback {
    //
    //static final String DATA_ID = "com.alibaba.c2m.discosupplier";
    //static final String GROUP_ID = "sflow.config";
    //
    //public static SFlowWrapper sFlowWrapper = null;
    //@Override
    //public void received(String data) {
    //    LogUtil.info("SFlowConfig_received","Diamond sflowConfig  Data="+data);
    //    try {
    //        if (!Strings.isNullOrEmpty(data)) {
    //            sFlowWrapper = JSON.parseObject(data, SFlowWrapper.class);
    //            System.out.println("sFlowWrapper 已加载完成"+sFlowWrapper.toString());
    //            SFlowRegistry.registerFlows(sFlowWrapper.getSflows());
    //        }
    //    }catch (Exception e){
    //        LogUtil.error("SFlowConfig_error diamomd","diamond初始化失败",e);
    //        return;
    //    }
    //}
//}
