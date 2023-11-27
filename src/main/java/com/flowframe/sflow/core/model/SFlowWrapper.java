package com.flowframe.sflow.core.model;

import java.util.List;

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
public class SFlowWrapper {

    private List<SFlow> sflows;
}
