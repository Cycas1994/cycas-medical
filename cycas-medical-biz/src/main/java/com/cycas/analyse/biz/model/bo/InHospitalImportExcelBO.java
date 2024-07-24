package com.cycas.analyse.biz.model.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xin.na
 * @since 2024/7/19 14:40
 */
@Data
public class InHospitalImportExcelBO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelProperty(value = "结算单据号")
    private String settleNo;

    @ExcelProperty(value = "医疗机构编码")
    private String medicalOrgCode;

    @ExcelProperty(value = "医疗机构名称")
    private String medicalOrgName;

    @ExcelProperty(value = "医保结算等级")
    private String medicalSettleLevel;

    @ExcelProperty(value = "参保地统筹区域编码")
    private String wholeAreaCode;

    @ExcelProperty(value = "参保地统筹区域名称")
    private String wholeAreaName;

    @DateTimeFormat("yyyyMMddHHmmss")
    @ExcelProperty(value = "结算日期")
    private Date settleDate;

    @ExcelProperty(value = "结算年份")
    private Integer settleYear;

    @ExcelProperty(value = "结算月份")
    private Integer settleMonth;

    @ExcelProperty(value = "住院号")
    private String hospitalNo;

    @ExcelProperty(value = "个人编码")
    private String personCode;

    @ExcelProperty(value = "患者社会保障号码")
    private String societySecurityNo;

    @ExcelProperty(value = "身份证号")
    private String identityCardNo;

    @ExcelProperty(value = "险种类型")
    private String insuranceType;

    @ExcelProperty(value = "人员类型")
    private String personType;

    @ExcelProperty(value = "入院科室")
    private String inHospitalDepartment;

    @ExcelProperty(value = "转科科室")
    private String changeDepartment;

    @ExcelProperty(value = "出院科室")
    private String leaveHospitalDepartment;

    @ExcelProperty(value = "主诊医师编码")
    private String doctorInChargeCode;

    @ExcelProperty(value = "主诊医师姓名")
    private String doctorInChargeName;

    @ExcelProperty(value = "患者姓名")
    private String patientsName;

    @ExcelProperty(value = "患者性别")
    private String patientsSex;

    @DateTimeFormat("yyyyMMdd")
    @ExcelProperty(value = "患者出生日期")
    private Date patientsBirthDate;

    @ExcelProperty(value = "患者年龄")
    private String patientsAge;

    @ExcelProperty(value = "患者床位号")
    private String patientsBedNo;

    @ExcelProperty(value = "住院医疗类型")
    private String inHospitalType;

    @ExcelProperty(value = "异地标志")
    private String otherPlaceFlag;

    @DateTimeFormat("yyyyMMddHHmmss")
    @ExcelProperty(value = "入院日期")
    private Date inHospitalDate;

    @DateTimeFormat("yyyyMMddHHmmss")
    @ExcelProperty(value = "出院日期")
    private Date leaveHospitalDate;

    @ExcelProperty(value = "住院天数")
    private Integer inHospitalDays;

    @ExcelProperty(value = "离院方式")
    private Integer leaveHospitalWay;

    @ExcelProperty(value = "医疗总费用")
    private BigDecimal hospitalizationCosts;

    @ExcelProperty(value = "基本统筹支付")
    private BigDecimal basicWholePay;

    @ExcelProperty(value = "大病保险")
    private BigDecimal seriousIllnessInsurance;

    @ExcelProperty(value = "医疗救助")
    private BigDecimal medicalHelp;

    @ExcelProperty(value = "公务员医疗补助")
    private BigDecimal civilMedicalSubsidy;

    @ExcelProperty(value = "大额补充")
    private BigDecimal largeSubsidy;

    @ExcelProperty(value = "企业补充")
    private BigDecimal companySubsidy;

    @ExcelProperty(value = "个人现金支付")
    private BigDecimal personCashPay;

    @ExcelProperty(value = "个人账户支付")
    private BigDecimal personAccountPay;

    @ExcelProperty(value = "个人自付")
    private BigDecimal personSelfPay;

    @ExcelProperty(value = "个人自费")
    private BigDecimal personSelfCost;

    @ExcelProperty(value = "符合基本医疗保险的费用")
    private BigDecimal basicMedicalInsuranceCost;

    @ExcelProperty(value = "入院诊断编码")
    private String inHospitalDiagnosisCode;

    @ExcelProperty(value = "入院诊断名称")
    private String inHospitalDiagnosisName;

    @ExcelProperty(value = "出院诊断编码")
    private String leaveHospitalDiagnosisCode;

    @ExcelProperty(value = "出院诊断名称")
    private String leaveHospitalDiagnosisName;

    @ExcelProperty(value = "手术及操作编码")
    private String operationCode;

    @ExcelProperty(value = "手术及操作名称")
    private String operationName;
}
