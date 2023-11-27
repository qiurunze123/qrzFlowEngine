package com.flowframe.sflow.examples.sflowtest.node.qlres;


import com.flowframe.sflow.core.SComponent;
import com.flowframe.sflow.core.SContext;
import com.flowframe.sflow.core.constants.FlowStatusEnum;
import com.flowframe.sflow.core.constants.SFlowErrorCode;
import com.flowframe.sflow.core.exception.SFlowException;
import com.flowframe.sflow.core.model.SFlowInstance;
import com.flowframe.sflow.core.resgister.SComponentRegistry;
import com.flowframe.sflow.core.undertake.context.SflowContext;
import com.ql.util.express.DefaultContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author qrz
 * @description
 * @date 2023/09/23 10:22 上午
 * 山不向我走来，我便向它走去
 * todo ql单独放在一个
 */
@Service
@Slf4j
public class RegisterApproveQlExpressServiceImpl implements SComponent<SContext> {

    protected final static String supportAction = "RegisterApproveQlExpressServiceImpl";

    public static final String LAST_ROUTER = "last_router";

    @Override
    public void process(SContext input) {
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        SflowContext sflowContext = (SflowContext)input;
        context.put("sflowContext", sflowContext);
        String qlExpress = String.valueOf(input.getExtMap().get("qlExpress"));
        String nodeCode = String.valueOf(input.getExtMap().get("nodeCode"));
        log.info("RegisterApproveQlExpressServiceImpl_nodeCode", "nodeCode="+nodeCode);
        Object r = null;
        try {
            r = ExpressRunnerSingleton.getInstance().execute(qlExpress, context, null, true, false);
        } catch (Exception e) {
            log.error("RegisterApproveQlExpressServiceImpl_nodeCode_error" ,"QL表达式执行异常 , RegisterApproveQlExpressServiceImpl error", e);
            throw new SFlowException(e);
        }
        if (r != null) {
            log.info(" RegisterApproveQlExpressServiceImpl_result_r","已找到下一步QL表达式执行节点,结果r="+r);
            input.setQlResult(String.valueOf(r));
            /**
             * 流程结束 - 完成FINISH
             */
            SFlowInstance sFlowInstance = input.getSFlowInstance();
            sFlowInstance.setFlowStatusEnum(FlowStatusEnum.FINISH);
            input.setSFlowInstance(sFlowInstance);
        } else {
            log.info("RegisterApproveQlExpressServiceImpl_result_r","未找到QL表达式下一步执行节点,继续下一个节点流程");
            if (LAST_ROUTER.equals(nodeCode)) {
                log.error("RegisterApproveQlExpressServiceImpl_result_r_error","该节点为最后一个节点不应该为空,抛出异常",null);
                throw new SFlowException(SFlowErrorCode.LAST_QL_EMPTY_ERROR.getErrorCode(),
                    SFlowErrorCode.LAST_QL_EMPTY_ERROR.getErrorMsg());
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String key = supportAction;
        SComponentRegistry.register(key, this);
    }
}