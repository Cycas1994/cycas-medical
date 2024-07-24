package com.cycas.analyse.biz.constant;

/**
 * @author xin.na
 * @since 2024/7/19 15:28
 */
public class ImportDataConstant {

    /**
     * 导入类型,1:主表 2:明细表
     */
    public static final Integer IMPORT_RECORD_TYPE_MAIN = 1;
    public static final Integer IMPORT_RECORD_TYPE_DETAIL = 2;

    /**
     * 导入状态 -1:失败 0:待处理 1:处理中 2:成功
     */
    public static final Integer IMPORT_RECORD_STATUS_FAILURE = -1;
    public static final Integer IMPORT_RECORD_STATUS_SUCCESS = 2;
}
