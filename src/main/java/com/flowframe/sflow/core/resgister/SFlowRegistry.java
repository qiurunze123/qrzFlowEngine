package com.flowframe.sflow.core.resgister;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import com.flowframe.sflow.core.SContext;
import com.flowframe.sflow.core.constants.SFlowErrorCode;
import com.flowframe.sflow.core.exception.SFlowException;
import com.flowframe.sflow.core.model.SFlow;
import com.flowframe.sflow.core.model.SFlowInstance;
import com.flowframe.sflow.core.model.SFlowWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/**
 * @author qrz
 * @description Sflow 流程注册
 * 山不向我走来，我便向它走去
 */
@Slf4j
@Component
public class SFlowRegistry implements InitializingBean {

    private static final Map<String, SFlow> flowMap = Maps.newConcurrentMap();

    @Value("${flow.base.path}")
    private String flowBasePath;

    public SFlowInstance getFlowInstance(String key) {
        if (flowMap.get(key) == null) {
            log.error("SFlowRegistry_getFlowInstance", "register not get flow key=" + key);
            throw new RuntimeException("没有找到注册flow，key:" + key);
        }
        SFlowInstance sInstance = new SFlowInstance();
        sInstance.setSFlow(flowMap.get(key));
        return sInstance;
    }

    public SContext loadFlow(SContext sContext) {
        String key = generateFlowKey(sContext.getSceneCode(), sContext.getBizType());
        SFlowInstance fl = this.getFlowInstance(key);
        if (fl == null) {
            log.info("SFlowRegistry_loadFlow", "not found flow with key=" + key);
            throw new SFlowException(SFlowErrorCode.NOT_FOND_FLOW);
        }
        sContext.setSFlowInstance(fl);
        return sContext;
    }

    public String generateFlowKey(String sceneCode, String bizType) {
        return sceneCode.concat("#").concat(bizType);
    }

    private static void register(String key, SFlow flow) {
        //不恢复 现在diamond 配置 修改后直接生效 就不校验了
        //if (flowMap.get(key) != null) {
        //    log.error("not allow register flow with key:{}, code:{}, because same key have registered", key, flow
        //    .getCode());
        //    throw new RuntimeException("不允许重复注册流程，code:" + flow.getCode());
        //}
        flowMap.put(key, flow);
        log.info("SFlowRegistry_register", "have register flow with key=" + key + "code=" + flow.getCode());
        //log.info("have register flow with key:{}, code:{}", key, flow.getCode());
    }

    public static void registerFlows(List<SFlow> flows) {
        if (CollectionUtils.isEmpty(flows)) {
            return;
        }

        for (SFlow flow : flows) {
            List<String> flowKeys = flow.getFlowKeys();
            for (String it : flowKeys) {
                register(it, flow);
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        ClassPathResource pathResource = new ClassPathResource(flowBasePath);
        if (!pathResource.exists()) {
            return;
        }
        List<SFlowWrapper> sFlowWrappers = new ArrayList<>();
        loadFlowFromFile(pathResource.getFile(),sFlowWrappers);
        System.out.println("sFlowWrapper 已加载完成" + JSON.toJSONString(sFlowWrappers));
        SFlowRegistry.registerFlows(sFlowWrappers.get(0).getSflows());
    }

    private void loadFlowFromFile(File file,List<SFlowWrapper>  sFlowWrappers ) throws IOException {
        if (file.isDirectory()) {
            log.info("load flow config from directory:{}", file.getAbsolutePath());
            for (File fl : file.listFiles()) {
                loadFlowFromFile(fl,sFlowWrappers);
            }
        } else {
            log.info("load flow config from file:{}", file.getAbsolutePath());
            String flowConfigStr = FileUtils.readFileToString(file, Charset.forName("UTF-8"));
            sFlowWrappers.add(JSON.parseObject(flowConfigStr, SFlowWrapper.class));
        }

    }
}



