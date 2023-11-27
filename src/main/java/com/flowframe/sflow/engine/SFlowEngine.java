package com.flowframe.sflow.engine;


import com.flowframe.sflow.core.SContext;
import com.flowframe.sflow.core.resgister.SFlowRegistry;
import com.flowframe.sflow.engine.executor.InstanceExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author qrz
 * @description
 * @date 2023/11/17 3:01
 * 山不向我走来，我便向它走去
 */
@Slf4j
@Component
public class SFlowEngine {

    @Autowired
    private InstanceExecutor instanceExecutor;

    @Autowired
    private SFlowRegistry sFlowRegistry;

    /**
     * 开始流程Flow
     * @param context
     * @return
     */
    public SContext startFlow(SContext context){
        //启动实例 初始化
        sFlowRegistry.loadFlow(context);
        instanceExecutor.startProcess(context);
        instanceExecutor.startProcessQlNodeComponent(context);
        return context;
    }

    /**
     * 开始流程Process
     * @param context
     * @return
     */
    public SContext startProcess(SContext context){
        //启动实例 初始化
        sFlowRegistry.loadFlow(context);
        instanceExecutor.startProcess(context);
        return context;
    }


    /**
     * 开始流程Process nodeDesc 一个节点
     * @param context
     * @return
     */
    public SContext startProcessOneNode(SContext context,String nodeDesc){
        //启动实例 初始化
        sFlowRegistry.loadFlow(context);
        instanceExecutor.startProcessOneNode(context,nodeDesc);
        return context;
    }

}
