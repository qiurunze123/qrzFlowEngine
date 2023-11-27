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
public class QLNode {

    @NotBlank(message = "流程编码不能为空")
    private String nodeCode;
    @NotBlank(message = "节点描述不能为空")
    private String desc;
    /**
     * QL表达式
     */
    private String qlExpress;

    /**
     * 执行组件
     */
    private String executeComponent;


}
