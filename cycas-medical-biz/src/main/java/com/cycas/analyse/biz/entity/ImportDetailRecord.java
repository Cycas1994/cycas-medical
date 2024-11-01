package com.cycas.analyse.biz.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xin.na
 * @since 2024/11/1 14:27
 */
@Data
@Accessors(chain = true)
public class ImportDetailRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 导入uuid
     */
    private String importUuid;

    /**
     * 文档行号
     */
    private Long rowNum;

    /**
     * 行json
     */
    private String rowJson;

    /**
     * 失败原因
     */
    private String failureReason;

    /**
     * 创建时间
     */
    private Date createTime;

}
