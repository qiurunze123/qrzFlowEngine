package com.flowframe.sflow.core.constants;

/**
 * @author qrz
 * @description
 * @date 2023/11/17 3:01
 * 山不向我走来，我便向它走去
 */
public enum SFlowJudgeEnum {

    HAVE_CORP("CORP","判断是否有关联公司"),
    SUPPLIER_IN_WHITE("SUPPLIER_IN_WHITE","判断是否有当前供应商白名单存在"),
    WRITE_CORP("WRITE_CORP","判断是否填写关联公司"),
    REL_RISK("REL_RISK","判断关联公司是否有风险"),
    RESULT_DEAL("REL_RISK","判断关联公司是否有风险");


    private final String errorCode;
    private final String errorMsg;

    private SFlowJudgeEnum(String errorCode, String errorMsg) {
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
