package com.flowframe.sflow.examples.sflowtest.node;

import com.alibaba.fastjson.JSON;

import com.flowframe.sflow.core.SComponent;
import com.flowframe.sflow.core.SContext;
import com.flowframe.sflow.core.resgister.SComponentRegistry;
import com.flowframe.sflow.core.undertake.context.SflowContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author qrz
 * @description
 * @date 2023/11/20 下午10:19
 * 山不向我走来，我便向它走去
 */
@Component
@Slf4j
public class AddressNodeOne implements SComponent<SContext> {

    protected final static String supportAction = "AddressNodeOne";

    @Override
    public void process(SContext input) {
        log.info("CompanyNodeOne#参数为#"+ JSON.toJSONString(input));
        SflowContext supplierContext = (SflowContext) input;
        String address = supplierContext.getAddress();
        if(StringUtils.isEmpty(address)){
            supplierContext.setExistCompanyName(false);
        }else{
            supplierContext.setExistCompanyName(true);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String key = supportAction;
        SComponentRegistry.register(key, this);
    }
}
