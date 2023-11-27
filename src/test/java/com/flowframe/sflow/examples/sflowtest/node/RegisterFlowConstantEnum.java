package com.flowframe.sflow.examples.sflowtest.node;

/**
 * @author qrz
 * @description scene+biz = 唯一flow 流程
 * @date 2023/4/23 9:58 上午
 * 山不向我走来，我便向它走去
 */
public enum RegisterFlowConstantEnum {

    //商家路由条件判断
    /**
     * 关联公司不为空 && 在白名单
     */
    FLOW_A("machine_check","flowA_supplier"),
    /**
     * 命中 关联公司为空 && 在白名单
     */
    FLOW_B("machine_check","flowB_supplier"),
    /**
     * 命中 关联公司不为空 && 不在白名单
     */
    FLOW_C("machine_check","flowC_supplier"),
    /**
     * 命中 关联公司为空 && 不在白名单
     */
    FLOW_D("machine_check","flowD_supplier"),
    /**
     * 命中 关联公司不为空 && 不在白名单
     */
    FLOW_E("machine_check","flowE_supplier"),
    /**
     * 命中 关联公司为空 && 不在白名单
     */
    FLOW_F("machine_check","flowF_supplier"),
    /**
     * MCN商家
     */
    FLOW_G("machine_check","flowG_supplier"),



    FLOW_H("machine_check","flowH_supplier"),
    FLOW_I("machine_check","flowI_supplier"),
    FLOW_J("machine_check","flowJ_supplier"),
    FLOW_K("machine_check","flowK_supplier");


    private String scene;
    private String biz ;

    RegisterFlowConstantEnum(String scene, String biz) {
        this.scene = scene;
        this.biz = biz;

    }

    public String getScene() {
        return scene;
    }

    public String getBiz() {
        return biz;
    }




}
