package com.flowframe.sflow.core.undertake.context;

import lombok.Data;

@Data
public class AutoResponse {

    /**
    * 审核结果
    */
    private Integer result;


    /**
     * 审核结论
     */
    private String remark;
}
