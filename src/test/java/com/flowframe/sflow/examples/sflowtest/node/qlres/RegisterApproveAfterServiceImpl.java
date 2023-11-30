package com.flowframe.sflow.examples.sflowtest.node.qlres;

import com.alibaba.fastjson.JSON;

import com.flowframe.sflow.core.SComponent;
import com.flowframe.sflow.core.SContext;
import com.flowframe.sflow.core.constants.FlowStatusEnum;
import com.flowframe.sflow.core.model.SFlowInstance;
import com.flowframe.sflow.core.resgister.SComponentRegistry;
import com.flowframe.sflow.core.undertake.context.AutoResponse;
import com.flowframe.sflow.core.undertake.context.SflowContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author qrz
 * @description 最终查询节点
 * @date 2023/10/21 9:34 上午
 * 山不向我走来，我便向它走去
 */
@Service
@Slf4j
public class RegisterApproveAfterServiceImpl implements SComponent<SContext> {

    protected final static String supportAction = "RegisterApproveAfterServiceImpl";

    @Override
    public void process(SContext input) {
        SflowContext sflowContext = (SflowContext)input;
        Integer response = sflowContext.getUnderTakeRes();
        String nodeCode = (String)sflowContext.getExtMap().get("nodeCode");
        if(0 == response ){
            log.info("最终结果返回response approve,response :{} , nodeCode:{}  最终出口在这", JSON.toJSONString(response),nodeCode);
        }else if((1 == response )){
            log.info("最终结果返回response reject ,response :{} , nodeCode:{} 最终出口在这", JSON.toJSONString(response),nodeCode);
        }
        SFlowInstance sFlowInstance = input.getSFlowInstance();
        sFlowInstance.setFlowStatusEnum(FlowStatusEnum.FINISH);
        input.setSFlowInstance(sFlowInstance);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String key = supportAction;
        SComponentRegistry.register(key, this);
    }

}
