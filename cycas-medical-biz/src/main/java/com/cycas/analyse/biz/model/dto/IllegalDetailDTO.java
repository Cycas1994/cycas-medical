package com.cycas.analyse.biz.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xin.na
 * @since 2024/7/24 13:50
 */
@Data
public class IllegalDetailDTO {

    /**
     * 结算单据号
     */
    private String settleNo;

    /**
     * 医疗机构编码
     */
    private String medicalOrgCode;

    /**
     * 医疗机构名称
     */
    private String medicalOrgName;

    /**
     * 结算日期YYYYMMDDHHMMSS
     */
    private Date settleDate;

    /**
     * 个人编码
     */
    private String personCode;

    /**
     * 患者社会保障号码
     */
    private String societySecurityNo;

    /**
     * 住院号
     */
    private String hospitalNo;

    /**
     * 险种类型
     */
    private String insuranceType;

    /**
     * 人员类型
     */
    private String personType;

    /**
     * 出院科室
     */
    private String leaveHospitalDepartment;

    /**
     * 主诊医师姓名
     */
    private String doctorInChargeName;

    /**
     * 患者姓名
     */
    private String patientsName;

    /**
     * 患者性别
     */
    private String patientsSex;

    /**
     * 入院日期YYYYMMDDHHMMSS
     */
    private Date inHospitalDate;

    /**
     * 出院日期YYYYMMDDHHMMSS
     */
    private Date leaveHospitalDate;

    /**
     * 住院天数
     */
    private Integer inHospitalDays;

    /**
     * 医疗总费用
     */
    private BigDecimal hospitalizationCosts;

    /**
     * 基本统筹支付
     */
    private BigDecimal basicWholePay;

    /**
     * 大病保险
     */
    private BigDecimal seriousIllnessInsurance;

    /**
     * 医疗救助
     */
    private BigDecimal medicalHelp;

    /**
     * 公务员医疗补助
     */
    private BigDecimal civilMedicalSubsidy;

    /**
     * 大额补充
     */
    private BigDecimal largeSubsidy;

    /**
     * 企业补充
     */
    private BigDecimal companySubsidy;

    /**
     * 出院诊断名称
     */
    private String leaveHospitalDiagnosisName;

    /**
     * 出院诊断编码
     */
    private String leaveHospitalDiagnosisCode;

    /**
     * 医院项目名称
     */
    private String hospitalProjectName;

    /**
     * 报销比例
     */
    private String reimbursementRatio;

    /**
     * 支付类别
     */
    private String payCategory;

    /**
     * 数量
     */
    private Integer amount;

    /**
     * 金额
     */
    private BigDecimal money;

}
