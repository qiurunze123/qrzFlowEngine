package com.flowframe.sflow.core.model;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;

import com.google.common.collect.Lists;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author qrz
 * @description 流程主类
 * @date 2023/11/17 3:01
 * 山不向我走来，我便向它走去
 */
@Data
public class SFlow {

    @NotBlank(message = "流程编码不能为空")
    private String code;
    @NotBlank(message = "流程描述不能为空")
    private String desc;
    @NotEmpty(message = "流程支持场景不能为空")
    private List<String> supportedScene;
    @NotBlank(message = "流程支持业务类型不能为空")
    private String supportedBizType;
    @NotEmpty(message = "流程节点不能为空")
    private List<SNode> nodes;

    private List<QLNode> qlNodes;
    private Map<String, SNode> nodeMap;


    @JSONField(serialize = false)
    public List<String> getFlowKeys() {
        List<String> flowKeys = Lists.newArrayList();
        for (String scene : getSupportedScene()) {
            String flowKey = scene.concat("#").concat(getSupportedBizType());
            flowKeys.add(flowKey);
        }
        return flowKeys;
    }

}
