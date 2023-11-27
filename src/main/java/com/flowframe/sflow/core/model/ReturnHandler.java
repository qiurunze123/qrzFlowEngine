package com.flowframe.sflow.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qrz
 * @description
 * @date 2023/11/17 3:01
 * 山不向我走来，我便向它走去
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnHandler {

    private String exceptionHandleType;
    private String resultHandle;
    private String executeComponent;
}
