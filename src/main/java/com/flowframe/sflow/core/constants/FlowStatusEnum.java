package com.flowframe.sflow.core.constants;

/**
 * @author qrz
 * @description
 * @date 2023/11/17 3:01
 * 山不向我走来，我便向它走去
 */
public enum FlowStatusEnum {
    INIT("INIT", "初始化"),
    FINISH("FINISH", "已结束"),
    PROCESSING("PROCESSING", "处理中"),
    CONTINUE("CONTINUE", "继续"),
    SUSPEND("SUSPEND", "暂停运行"),
    CANCEL("CANCEL", "已取消"),
    ERROR("ERROR", "执行错误"),
    NOT_EXIST("NOT_EXIST", "不存在");

    private String code;
    private String desc;

    FlowStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static FlowStatusEnum getByCode(String code) {
        for (FlowStatusEnum value : FlowStatusEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return NOT_EXIST;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
