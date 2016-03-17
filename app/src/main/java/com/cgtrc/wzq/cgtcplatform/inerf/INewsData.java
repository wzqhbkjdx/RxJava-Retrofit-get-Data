package com.cgtrc.wzq.cgtcplatform.inerf;

/**
 * Created by bym on 16/3/16.
 * 获取的新闻数据的接口
 */
public interface INewsData extends IBaseData {
    boolean isHeader();
    boolean isNormalNews();
    String getType();
}
