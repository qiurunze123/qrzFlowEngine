package com.flowframe.sflow.core.constants;

/**
 * @author qrz
 * @description scene+biz = 唯一flow 流程
 * @date 2023/4/23 9:58 上午
 * 山不向我走来，我便向它走去
 */
public enum UnderTakeFlowConstantEnum {

    //商家路由条件判断
    /**
     * 通过
     */
    APPROVE("approve",0),
    /**
     * 拒绝
     */
    REJECT("machine_check",1) ;


    private String scene;
    private Integer code;

    UnderTakeFlowConstantEnum(String scene, Integer code) {
        this.scene = scene;
        this.code = code;

    }

    public String getScene() {
        return scene;
    }

    public Integer getCode() {
        return code;
    }

}
