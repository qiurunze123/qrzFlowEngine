package com.flowframe.sflow.core.constants;

/**
 * @author qrz
 * @description
 * @date 2023/11/17 3:01
 * 山不向我走来，我便向它走去
 */
public enum  SFlowErrorCode {
    SYSTEM_ERROR("system_error", "系统异常"),
    PARAM_ERROR("param_error", "参数错误"),
    PARAM_CHECK_ERROR("param_check_error", "参数校验错误"),
    FLOW_CONTEXT_PARAM_MISSING("flow_context_param_missing", "流程实例参数缺失"),
    CHANNEL_VALIDATE_RESULT_PARAM_MISSING("channel_validate_result_param_missing", "验证渠道返回验真结果参数缺失"),
    CHANNEL_VALIDATE_RESULT_EMPTY("channel_validate_result_empty", "验证渠道返回验真结果为空"),
    EXISTS_PROCESSING_INSTANCE("exists_processing_instance", "已存在运行中的流程"),
    EXISTS_UNFINISHED_FRONT_NODE("exists_unfinished_front_node", "存在还未完结的前置节点"),
    INSTANCE_STATUS_HAVE_END("instance_status_have_end", "流程实例状态已终结"),
    PARSED_VERIFY_RULE_EMPTY("parsed_verify_rule_empty", "解析后的验真规则为空"),
    NOT_FOND_FLOW("not_fond_flow", "没加载到流程"),
    INSTANCE_RESUME_ASYNC("instance_resume_async", "流程异步重试"),
    NOT_FOND_VERIFY_RULE("not_fond_verify_rule", "没加载到验真规则"),
    NO_FOUND_AVAILABLE_STRATEGY("no_found_available_strategy", "没找到可用的验真策略"),
    NO_FOUND_AVAILABLE_CHANNEL("no_found_available_channel", "没找到可用的验真渠道"),
    NO_FOUND_AVAILABLE_ACTION_COMPONENT("no_found_available_action_component", "未加载到需要的流程action组件"),
    NO_FOUND_AVAILABLE_MTEE_PARAM_CONVERT("no_found_available_mtee_param_convert", "未加载到需要的风控参数转换器"),
    VERIFY_BIZ_REQUEST_PARAM_EMPTY("verify_biz_request_param_empty", "流程实例启动前注入参数为空"),
    CUSTOMS_VERIFY_REQUEST_PARAM_EMPTY("customs_verify_request_param_empty", "报关单验真请求参数为空"),
    TA_ORDER_ID_EMPTY("ta_order_id_empty", "信保单号为空"),
    DECLARATION_NUMBER_EMPTY("declaration_number_empty", "报关单号为空"),
    LAST_QL_EMPTY_ERROR("last_ql_empty_error", "最后ql节点不应该为错误"),
    DECLARATION_VERIFY_PRODUCT_EMPTY("declaration_verify_product_empty", "通关验真商品为空"),
        ;

    private final String errorCode;
    private final String errorMsg;

    private SFlowErrorCode(String errorCode, String errorMsg) {
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public static SFlowErrorCode parse(String code) {
        if (code == null) {
            return null;
        } else {
            SFlowErrorCode[] var1 = values();
            int var2 = var1.length;

            for (int var3 = 0; var3 < var2; ++var3) {
                SFlowErrorCode temp = var1[var3];
                if (temp.getErrorCode().equals(code)) {
                    return temp;
                }
            }

            return null;
        }
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }
}
