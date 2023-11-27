package com.flowframe.sflow.core.model;


import com.flowframe.sflow.core.constants.FlowStatusEnum;
import lombok.Data;

/**
 * @author qrz
 * @description
 * @date 2023/11/17 3:01
 * 山不向我走来，我便向它走去
 */
@Data
public class SFlowInstance {

    private SFlow sFlow;

    /**
     * 流程状态更新
     */
    private FlowStatusEnum flowStatusEnum;

}
