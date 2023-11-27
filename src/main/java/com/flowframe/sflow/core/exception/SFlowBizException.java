package com.flowframe.sflow.core.exception;



import com.flowframe.sflow.core.constants.SFlowErrorCode;
import lombok.Data;

/**
 * @author qrz
 * @description
 * @date 2023/11/17 3:01
 * 山不向我走来，我便向它走去
 */
@Data
public class SFlowBizException extends RuntimeException {

    private static final long serialVersionUID = 1146513161992391457L;
    /**
     * 异常码
     */
    private String code;

    /**
     * 异常信息
     */
    private String message;

    /**
     * 异常堆栈
     */
    private Throwable cause;


    public SFlowBizException(Throwable e) {
        super(e);

        cause = e;
    }


    public SFlowBizException(SFlowErrorCode errorCode) {
        super(errorCode.getErrorMsg());
        this.code = errorCode.getErrorCode();
        this.message = errorCode.getErrorMsg();
    }


    public SFlowBizException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public SFlowBizException(SFlowErrorCode errorCode, Throwable cause) {
        super(errorCode.getErrorCode(), cause);
        this.code = errorCode.getErrorCode();
        this.message = errorCode.getErrorMsg();
        this.cause = cause;
    }
}
