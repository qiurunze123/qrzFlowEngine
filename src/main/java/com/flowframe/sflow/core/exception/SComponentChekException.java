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
public class SComponentChekException extends RuntimeException {

    private static final long serialVersionUID = 5262405613183346769L;

    private String code ;
    private String message ;
    private Throwable cause ;

    public SComponentChekException(Throwable e){
        super(e);
        cause = e ;
    }


    public SComponentChekException(SFlowErrorCode errorCode) {
        super(errorCode.getErrorMsg());
        this.code = errorCode.getErrorCode();
        this.message = errorCode.getErrorMsg();
    }


    public SComponentChekException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public SComponentChekException(SFlowErrorCode errorCode, Throwable cause) {
        super(errorCode.getErrorCode(), cause);
        this.code = errorCode.getErrorCode();
        this.message = errorCode.getErrorMsg();
        this.cause = cause;
    }

}
