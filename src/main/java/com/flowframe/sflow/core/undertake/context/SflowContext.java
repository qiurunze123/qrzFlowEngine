package com.flowframe.sflow.core.undertake.context;

import com.flowframe.sflow.core.SContext;
import lombok.Data;

/**
 * @author qrz
 * @description
 * @date 2023/11/20 下午10:14
 * 山不向我走来，我便向它走去
 */
@Data
public class SflowContext extends SContext {

    /**
     * 公司名称
     */
    private String companyName;
    private boolean isExistCompanyName;


    /**
     * 地址
     */
    private String address;

    /**
     * 结果承接
     */
    private Integer underTakeRes;


}
