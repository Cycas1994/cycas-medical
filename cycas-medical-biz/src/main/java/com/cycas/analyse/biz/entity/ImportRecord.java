package com.cycas.analyse.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

import static com.cycas.analyse.biz.constant.ImportDataConstant.*;

/**
 * @author xin.na
 * @since 2024/7/19 13:41
 */
@Data
@Accessors(chain = true)
public class ImportRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
     * 主键
     */
    private Integer id;

    /**
     * uuid
     */
    private String uuid;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 导入类型,1:主表 2:子表
     */
    private Integer type;

    /**
     * 导入状态,-1:失败 0:待处理 1:处理中 2:成功
     */
    private Integer status;

    /**
     * 总数
     */
    private Long totalNum;

    /**
     * 失败数
     */
    private Long failureNum;

    /**
     * 失败原因
     */
    private String failureReason;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public void markSuccess(Long totalNum, Long failureNum) {
        this.totalNum = totalNum;
        this.failureNum = failureNum;
        this.status = failureNum == 0 ? IMPORT_RECORD_STATUS_SUCCESS : IMPORT_RECORD_STATUS_PART_SUCCESS;
    }

    public void markFailure(String failureReason) {
        this.status = IMPORT_RECORD_STATUS_FAILURE;
        this.failureReason = failureReason;
    }
}
