package com.flowframe.sflow.examples.sflowtest;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;

import com.flowframe.sflow.core.SContext;
import com.flowframe.sflow.core.constants.UnderTakeFlowConstantEnum;
import com.flowframe.sflow.core.exception.SFlowException;
import com.flowframe.sflow.core.undertake.context.AutoResponse;
import com.flowframe.sflow.core.undertake.context.SflowContext;
import com.flowframe.sflow.engine.SFlowEngine;
import com.flowframe.sflow.examples.TestApplication;
import com.flowframe.sflow.examples.sflowtest.node.qlres.ExpressRunnerSingleton;
import com.ql.util.express.DefaultContext;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.flowframe.sflow.examples.sflowtest.node.RegisterFlowConstantEnum.FLOW_A;

/**
 * @author qrz
 * @description
 * @date 2023/11/20 下午9:49
 * 山不向我走来，我便向它走去
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplication.class)
@Slf4j
public class SflowTest {


    @Resource
    private SFlowEngine sFlowEngine;


    @Test
    public void testRunFlowA() {
        SflowContext sflowContext = new SflowContext();
        sflowContext.setCompanyName("alibaba");
        sflowContext.setSceneCode(FLOW_A.getScene());
        sflowContext.setBizType(FLOW_A.getBiz());
        SflowContext res = (SflowContext)sFlowEngine.startFlow(sflowContext);
        log.info("res"+JSON.toJSONString(res));
    }


    @Test
    public void testQlRun(){
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        SflowContext sflowContext = new SflowContext();
        context.put("sflowContext", sflowContext);


        String qlExpress = " import com.flowframe.sflow ; import org.apache.commons.lang3 ; "
            + " if(StringUtils.isEmpty(sflowContext.getCompanyName())) { sflowContext.setUnderTakeRes(111); return 'RegisterApproveAfterServiceImpl'; } ";
        Object r = null;
        try {
            r = ExpressRunnerSingleton.getInstance().execute(qlExpress, context, null, true, false);
        } catch (Exception e) {
            throw new SFlowException(e);
        }

        System.out.println(sflowContext.getUnderTakeRes());
        System.out.println(JSON.toJSONString(r));
    }

}
