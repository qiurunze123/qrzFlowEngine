package com.flowframe.sflow.core;

import org.springframework.beans.factory.InitializingBean;

/**
 * @author qrz
 * @description
 * @date 2023/11/17 3:01
 * 山不向我走来，我便向它走去
 */
public interface SComponent<T extends SContext> extends InitializingBean {

    void process(T input) ;
}
