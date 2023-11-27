package com.flowframe.sflow.core.model;


import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author qrz
 * @description 节点主类
 * @date 2023/11/17 3:01
 * 山不向我走来，我便向它走去
 */
@Data
public class SNode {

    @NotBlank(message = "流程编码不能为空")
    private String nodeCode;

    @NotBlank(message = "执行描述不能为空")
    private String desc;

    @NotBlank(message = "执行动作组件")
    private String executeComponent;

    /**
     * 执行节点判断  某节点异常/返回某结果后Flow要终止还是继续
     */
    private ReturnHandler returnHandler;


}
