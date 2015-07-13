package com.ctc.isweather.db;

import java.io.InputStream;

/**
 * 数据库连接接口
 * Created by chris on 2015/7/13.
 */
public interface DatabaseOperations {
    void importDB(String packageName,InputStream input);
}
