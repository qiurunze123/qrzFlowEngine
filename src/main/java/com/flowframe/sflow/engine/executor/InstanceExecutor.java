package com.flowframe.sflow.engine.executor;

import java.util.List;

import com.alibaba.fastjson.JSON;

import com.flowframe.sflow.core.SComponent;
import com.flowframe.sflow.core.SContext;
import com.flowframe.sflow.core.constants.FlowStatusEnum;
import com.flowframe.sflow.core.constants.SFlowErrorCode;
import com.flowframe.sflow.core.exception.SFlowBizException;
import com.flowframe.sflow.core.exception.SFlowException;
import com.flowframe.sflow.core.model.QLNode;
import com.flowframe.sflow.core.model.SFlow;
import com.flowframe.sflow.core.model.SFlowInstance;
import com.flowframe.sflow.core.model.SNode;
import com.flowframe.sflow.core.resgister.SComponentRegistry;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author qrz
 * @description
 * @date 2023/11/17 3:01
 * 山不向我走来，我便向它走去
 */
@Slf4j
@Component
public class InstanceExecutor {

    public void startProcess(SContext sContext) {
        SFlowInstance sFlowInstance = sContext.getSFlowInstance();
        SFlow sFlow = sFlowInstance.getSFlow();
        List<SNode> nodes = sFlow.getNodes();
        List<QLNode> qlNodes = sFlow.getQlNodes();
        for (SNode node : nodes) {
            log.info("InstanceExecutor_startProcess", "SFlowScene=" +
                sFlow.getSupportedScene() + "SFlowBiz=" + sFlow.getSupportedBizType() + "nodeCode=" + node.getNodeCode()
                + "nodeDesc=" + node.getDesc());
            /**
             * 如果QL表达式直接 走到了审核通过/不通过/驳回等状态 直接结束流程
             */
            if (sFlowInstance.getFlowStatusEnum() != null && sFlowInstance.getFlowStatusEnum().equals(
                FlowStatusEnum.FINISH)) {
                break;
            }
            try {
                actionProcess(sContext, node);
            } catch (SFlowBizException e) {
                /**
                 *  抛出 SFlowBizException 异常
                 *  设置FINISH 则流程结束 - 设置流程结束状态
                 *  设置CONTINUE 则异常不受影响 流程继续
                 */
                if (node.getReturnHandler() == null
                    || StringUtils.isEmpty(node.getReturnHandler().getExceptionHandleType())
                    || node.getReturnHandler().getExceptionHandleType().equals(FlowStatusEnum.CONTINUE.getCode())) {
                    log.info("InstanceExecutor_SFlowBizException_startProcess", "上一个流程异常无所谓，继续执行下次任务！");
                    continue;
                }
                if (node.getReturnHandler().getExceptionHandleType().equals(FlowStatusEnum.FINISH.getCode())) {
                    log.info("InstanceExecutor_SFlowBizException_startProcess", "上一个流程已经结束完成，无需执行下次任务！");
                    sFlowInstance.setFlowStatusEnum(FlowStatusEnum.FINISH);
                    continue;
                }
            }
            actionNodeQlHandler(sContext, node);
        }

        for (QLNode qlNode : qlNodes) {
            if (sFlowInstance.getFlowStatusEnum() != null && sFlowInstance.getFlowStatusEnum().equals(
                FlowStatusEnum.FINISH)) {
                break;
            }
            actionQLProcess(sContext, qlNode);
        }
    }

    /**
     * 单独一个节点 startProcessOneNode 进行测试
     * @param sContext
     * @param nodeDesc
     */

    public void startProcessOneNode(SContext sContext,String nodeDesc) {
        SFlowInstance sFlowInstance = sContext.getSFlowInstance();
        SFlow sFlow = sFlowInstance.getSFlow();
        List<SNode> nodes = sFlow.getNodes();
        List<QLNode> qlNodes = sFlow.getQlNodes();
        for (SNode node : nodes) {

            if(!node.getNodeCode().equals(nodeDesc)){
                log.info("InstanceExecutor_startProcess", "SFlowScene=" +
                    sFlow.getSupportedScene() + "SFlowBiz=" + sFlow.getSupportedBizType() + "nodeCode=" + node.getNodeCode()
                    + "nodeDesc=" + node.getDesc());
                continue;
            }

            /**
             * 如果QL表达式直接 走到了审核通过/不通过/驳回等状态 直接结束流程
             */
            if (sFlowInstance.getFlowStatusEnum() != null && sFlowInstance.getFlowStatusEnum().equals(
                FlowStatusEnum.FINISH)) {
                break;
            }
            try {
                actionProcess(sContext, node);
            } catch (SFlowBizException e) {
                /**
                 *  抛出 SFlowBizException 异常
                 *  设置FINISH 则流程结束 - 设置流程结束状态
                 *  设置CONTINUE 则异常不受影响 流程继续
                 */
                if (node.getReturnHandler() == null
                    || StringUtils.isEmpty(node.getReturnHandler().getExceptionHandleType())
                    || node.getReturnHandler().getExceptionHandleType().equals(FlowStatusEnum.CONTINUE.getCode())) {
                    log.info("InstanceExecutor_SFlowBizException_startProcess", "上一个流程异常无所谓，继续执行下次任务！");
                    continue;
                }
                if (node.getReturnHandler().getExceptionHandleType().equals(FlowStatusEnum.FINISH.getCode())) {
                    log.info("InstanceExecutor_SFlowBizException_startProcess", "上一个流程已经结束完成，无需执行下次任务！");
                    sFlowInstance.setFlowStatusEnum(FlowStatusEnum.FINISH);
                    continue;
                }
            }
            actionNodeQlHandler(sContext, node);
        }

        //for (QLNode qlNode : qlNodes) {
        //    if (sFlowInstance.getFlowStatusEnum() != null && sFlowInstance.getFlowStatusEnum().equals(
        //        FlowStatusEnum.FINISH)) {
        //        break;
        //    }
        //    actionQLProcess(sContext, qlNode);
        //}
    }

    public void actionNodeQlHandler(SContext sContext, SNode node) {

        if (StringUtils.isEmpty(node.getReturnHandler().getResultHandle())) {
            log.info("InstanceExecutor_actionNodeQlHandler", "没有配置 ，直接进行下一个流程");
            sContext.putToExt("qlExpress", "");
            return;
        }
        try {
            executeComponentQl(sContext, node);
            log.info("InstanceExecutor_actionNodeQlHandler", "node=" + node.getNodeCode() + "desc=" +
                node.getDesc());
        } catch (Exception e) {
            log.error("InstanceExecutor_actionNodeQlHandler", "node=" + node.getNodeCode(), e);
            throw e;
        }
    }

    public void startProcessQlNode(SContext sContext) {
        SFlow sFlow = sContext.getSFlowInstance().getSFlow();
        List<QLNode> qlNodes = sFlow.getQlNodes();
        log.info("InstanceExecutor_actionNodeQlHandler", "startProcess start flowCode=" + sFlow.getCode());
        try {
            for (QLNode qlNode : qlNodes) {
                actionQLProcess(sContext, qlNode);
            }
        } catch (SFlowBizException e) {
            log.error("InstanceExecutor_startProcessQlNode", "flow=" + sFlow.getCode() + "msg=" + e.getMessage(),
                e);
            throw new SFlowBizException(SFlowErrorCode.PARAM_CHECK_ERROR);
        }
    }

    public void startProcessQlNodeComponent(SContext sContext) {
        String qlResult = sContext.getQlResult();
        SComponent sComponent = SComponentRegistry.getComponent(qlResult);
        if (sComponent == null) {
            log.error("InstanceExecutor_startProcessQlNodeComponent", "流程sComponent未发现请检查参数与注册bean比较,qlResult=" +
                qlResult);
            throw new SFlowException(SFlowErrorCode.PARAM_CHECK_ERROR);
        }
        sComponent.process(sContext);
    }

    private void actionProcess(SContext sContext, SNode curNode) {
        SFlow sFlow = sContext.getSFlowInstance().getSFlow();
        log.info("InstanceExecutor_actionProcess", "sflowCode=" +
            sFlow.getCode() + "curNode_nodeCode=" + curNode.getNodeCode());
        try {
            executeComponent(sContext, curNode);
        } catch (Exception e) {
            log.error("InstanceExecutor_actionProcess_error", "sflowCode=" +
                sFlow.getCode() + "curNode_nodeCode=" + curNode.getNodeCode(), e);
            throw e;
        }
    }

    private void actionQLProcess(SContext sContext, QLNode curNode) {
        log.info("InstanceExecutor_actionQLProcess", "curNode=" + JSON.toJSONString(curNode));
        try {
            executeComponentQl(sContext, curNode);
        } catch (Exception e) {
            log.error("InstanceExecutor_actionQLProcess_error",
                "curNode=" + JSON.toJSONString(curNode) + "executeComponent=" + curNode.getExecuteComponent(), e);
            throw e;
        }
    }

    /**
     * 执行QL表达式 计划
     *
     * @param node
     */
    private void executeComponentQl(SContext context, QLNode node) {

        String executeComponent = node.getExecuteComponent();
        if (StringUtils.isEmpty(executeComponent)) {
            log.info("InstanceExecutor_executeComponentQl", "未匹配组件能力，继续流程");
            return;
        }
        StringBuilder keyExec = new StringBuilder(executeComponent);
        log.info("InstanceExecutor_executeComponentQl",
            "flow=" + context.getSFlowInstance().getSFlow().getCode() + "key=" + keyExec.toString());

        SComponent component = SComponentRegistry.getComponent(keyExec.toString());
        if (component == null) {
            log.error("InstanceExecutor_executeComponentQl", "component 执行器为空 ,keyExec=" + keyExec.toString());
            throw new SFlowException(SFlowErrorCode.NO_FOUND_AVAILABLE_ACTION_COMPONENT);
        }
        log.info("InstanceExecutor_executeComponentQl",
            "flow=" + context.getSFlowInstance().getSFlow().getCode() + "key=" + keyExec.toString()
                + "component_class=" + component.getClass());
        try {
            context.putToExt("qlExpress", node.getQlExpress());
            context.putToExt("nodeCode", node.getNodeCode());
            component.process(context);
        } catch (Exception e) {
            log.error("InstanceExecutor_executeComponentQl_error", "异常executeComponentQl", e);
            throw e;
        }
    }

    /**
     * 执行QL表达式 计划
     *
     * @param node
     */
    private void executeComponentQl(SContext context, SNode node) {

        String executeComponent = node.getReturnHandler().getExecuteComponent();
        StringBuilder keyExec = new StringBuilder(executeComponent);
        log.info("InstanceExecutor_executeComponent",
            "flow=" + context.getSFlowInstance().getSFlow().getCode() + "key=" + keyExec.toString());

        SComponent component = SComponentRegistry.getComponent(keyExec.toString());
        if (component == null) {
            log.error("InstanceExecutor_executeComponentQl", "component 执行器为空 ,keyExec=" + keyExec.toString());
            throw new SFlowException(SFlowErrorCode.NO_FOUND_AVAILABLE_ACTION_COMPONENT);
        }
        log.info("InstanceExecutor_executeComponentQl",
            "flow=" + context.getSFlowInstance().getSFlow().getCode() + "key=" + keyExec.toString()
                + "component_class=" + component.getClass());
        try {
            context.putToExt("qlExpress", node.getReturnHandler().getResultHandle());
            component.process(context);
        } catch (Exception e) {
            log.error("InstanceExecutor_executeComponent_error", "异常executeComponent", e);
            throw e;
        }
    }

    private void executeComponent(SContext context, SNode sNode) {
        String executeComponent = sNode.getExecuteComponent();
        StringBuilder keyExec = new StringBuilder(executeComponent);
        log.info("InstanceExecutor_executeComponent",
            "flow=" + context.getSFlowInstance().getSFlow().getCode() + "key=" + keyExec.toString());

        SComponent component = SComponentRegistry.getComponent(keyExec.toString());
        if (component == null) {
            log.error("InstanceExecutor_executeComponent", "component 执行器为空 ,keyExec=" + keyExec.toString());
            throw new SFlowException(SFlowErrorCode.NO_FOUND_AVAILABLE_ACTION_COMPONENT);
        }
        log.info("InstanceExecutor_executeComponent",
            "flow=" + context.getSFlowInstance().getSFlow().getCode() + "key=" + keyExec.toString()
                + "component_class=" + component.getClass());
        try {
            context.putToExt("nodeCode", sNode.getNodeCode());
            component.process(context);
        } catch (Exception e) {
            log.error("InstanceExecutor_executeComponent_error", "异常executeComponent"+ e);
            throw e;
        }
    }

}
