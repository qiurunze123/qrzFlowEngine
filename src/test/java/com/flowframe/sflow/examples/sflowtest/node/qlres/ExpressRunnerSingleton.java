package com.flowframe.sflow.examples.sflowtest.node.qlres;

import com.ql.util.express.ExpressRunner;

/**
 * @author qrz
 * @description
 * @date 2023/5/4 2:08 下午
 * 山不向我走来，我便向它走去
 */
public class ExpressRunnerSingleton {

    private static final ExpressRunner instance = new ExpressRunner();

    public static ExpressRunner getInstance() {
        return instance;
    }
}
