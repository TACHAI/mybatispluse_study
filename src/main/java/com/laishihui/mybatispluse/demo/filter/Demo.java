package com.laishihui.mybatispluse.demo.filter;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

/**
 * Create by tachai on 2019-07-30 13:47
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */

public class Demo implements Filter {
    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
    }
}
