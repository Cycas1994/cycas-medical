package com.cycas.analyse.biz.model.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author xin.na
 * @since 2024/7/22 17:03
 */
@Data
public class InHospitalDetailImportExcelBO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelProperty(value = "结算单据号")
    private String settleNo;

    @ExcelProperty(value = "个人编码")
    private String personCode;

    @ExcelProperty(value = "住院号")
    private String hospitalNo;

    @ExcelProperty(value = "患者姓名")
    private String patientsName;

    @ExcelProperty(value = "开单科室编码")
    private String billingDepartmentCode;

    @ExcelProperty(value = "开单科室名称")
    private String billingDepartmentName;

    @ExcelProperty(value = "开单医师编码")
    private String billingDoctorCode;

    @ExcelProperty(value = "开单医师姓名")
    private String billingDoctorName;

    @ExcelProperty(value = "入院诊断名称")
    private String inHospitalDiagnosisName;

    @ExcelProperty(value = "出院诊断名称")
    private String leaveHospitalDiagnosisName;

    @ExcelProperty(value = "费用类别")
    private String chargeType;

//    @DateTimeFormat("yyyyMMddHHmmss")
//    @ExcelProperty(value = "项目使用时间")
//    private Date productUseDate;
//
//    @DateTimeFormat("yyyyMMddHHmmss")
//    @ExcelProperty(value = "结算日期")
//    private Date settleDate;

    @ExcelProperty(value = "结算年份")
    private Integer settleYear;

    @ExcelProperty(value = "结算月份")
    private Integer settleMonth;

    @ExcelProperty(value = "出院带药标识")
    private String takeDrugFlag;

    @ExcelProperty(value = "医院项目编码")
    private String hospitalProjectCode;

    @ExcelProperty(value = "医院项目名称")
    private String hospitalProjectName;

    @ExcelProperty(value = "医保项目编码")
    private String medicareProjectCode;

    @ExcelProperty(value = "医保项目名称")
    private String medicareProjectName;

    @ExcelProperty(value = "规格")
    private String specification;

    @ExcelProperty(value = "剂型")
    private String dosageForm;

    @ExcelProperty(value = "计价单位")
    private String chargeUnit;

    @ExcelProperty(value = "单价")
    private BigDecimal unitPrice;

    @ExcelProperty(value = "数量")
    private Integer amount;

    @ExcelProperty(value = "金额")
    private BigDecimal money;

    @ExcelProperty(value = "拒付金额")
    private BigDecimal refusePayMoney;

    @ExcelProperty(value = "医保范围内金额")
    private BigDecimal medicareInsideMoney;

    @ExcelProperty(value = "支付类别")
    private String payCategory;

    @ExcelProperty(value = "报销比例")
    private String reimbursementRatio;

}
