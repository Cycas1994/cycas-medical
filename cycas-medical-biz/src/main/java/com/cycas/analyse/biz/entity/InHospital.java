package com.cycas.analyse.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cycas.analyse.biz.model.bo.InHospitalImportExcelBO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xin.na
 * @since 2024/7/19 11:06
 */
@Data
@Accessors(chain = true)
public class InHospital implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
     * 主键
     */
    private Integer id;

    /**
     * 导入记录uuid
     */
    private String importUuid;

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
     * 医保结算等级
     */
    private String medicalSettleLevel;

    /**
     * 统筹区域编码
     */
    private String wholeAreaCode;

    /**
     * 统筹区域名称
     */
    private String wholeAreaName;

    /**
     * 结算日期YYYYMMDDHHMMSS
     */
    private Date settleDate;

    /**
     * 结算年份
     */
    private Integer settleYear;

    /**
     * 结算月份
     */
    private Integer settleMonth;

    /**
     * 住院号
     */
    private String hospitalNo;

    /**
     * 个人编码
     */
    private String personCode;

    /**
     * 患者社会保障号码
     */
    private String societySecurityNo;

    /**
     * 身份证号
     */
    private String identityCardNo;

    /**
     * 险种类型
     */
    private String insuranceType;

    /**
     * 人员类型
     */
    private String personType;

    /**
     * 入院科室
     */
    private String inHospitalDepartment;

    /**
     * 转科科室
     */
    private String changeDepartment;

    /**
     * 出院科室
     */
    private String leaveHospitalDepartment;

    /**
     * 主诊医师编码
     */
    private String doctorInChargeCode;

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
     * 患者出生日期YYYYMMDD
     */
    private Date patientsBirthDate;

    /**
     * 患者年龄，就诊时的年龄
     */
    private String patientsAge;

    /**
     * 患者床位号
     */
    private String patientsBedNo;

    /**
     * 住院医疗类型
     */
    private String inHospitalType;

    /**
     * 异地标志
     */
    private String otherPlaceFlag;

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
     * 离院方式，1.医嘱离院 2.医嘱转院、转社区、转卫生机构 3.非医嘱离院 4.死亡 9.其他
     */
    private Integer leaveHospitalWay;

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
     * 个人现金支付
     */
    private BigDecimal personCashPay;

    /**
     * 个人账户支付
     */
    private BigDecimal personAccountPay;

    /**
     * 个人自付(医保报销范围内需个人承担的费用)
     */
    private BigDecimal personSelfPay;

    /**
     * 个人自费(报销范围外全部由个人承担的费用)
     */
    private BigDecimal personSelfCost;

    /**
     * 符合基本医疗保险的费用
     */
    private BigDecimal basicMedicalInsuranceCost;

    /**
     * 入院诊断编码
     */
    private String inHospitalDiagnosisCode;

    /**
     * 入院诊断名称
     */
    private String inHospitalDiagnosisName;

    /**
     * 出院诊断编码
     */
    private String leaveHospitalDiagnosisCode;

    /**
     * 出院诊断名称
     */
    private String leaveHospitalDiagnosisName;

    /**
     * 手术及操作编码
     */
    private String operationCode;

    /**
     * 手术及操作名称
     */
    private String operationName;

    public static InHospital from(InHospitalImportExcelBO bo) {
        return new InHospital()
                .setSettleNo(bo.getSettleNo())
                .setMedicalOrgCode(bo.getMedicalOrgCode())
                .setMedicalOrgName(bo.getMedicalOrgName())
                .setMedicalSettleLevel(bo.getMedicalSettleLevel())
                .setWholeAreaCode(bo.getWholeAreaCode())
                .setWholeAreaName(bo.getWholeAreaName())
                .setSettleDate(bo.getSettleDate())
                .setSettleYear(bo.getSettleYear())
                .setSettleMonth(bo.getSettleMonth())
                .setHospitalNo(bo.getHospitalNo())
                .setPersonCode(bo.getPersonCode())
                .setSocietySecurityNo(bo.getSocietySecurityNo())
                .setIdentityCardNo(bo.getIdentityCardNo())
                .setInsuranceType(bo.getInsuranceType())
                .setPersonType(bo.getPersonType())
                .setInHospitalDepartment(bo.getInHospitalDepartment())
                .setChangeDepartment(bo.getChangeDepartment())
                .setLeaveHospitalDepartment(bo.getLeaveHospitalDepartment())
                .setDoctorInChargeCode(bo.getDoctorInChargeCode())
                .setDoctorInChargeName(bo.getDoctorInChargeName())
                .setPatientsName(bo.getPatientsName())
                .setPatientsSex(bo.getPatientsSex())
                .setPatientsBirthDate(bo.getPatientsBirthDate())
                .setPatientsAge(bo.getPatientsAge())
                .setPatientsBedNo(bo.getPatientsBedNo())
                .setInHospitalType(bo.getInHospitalType())
                .setOtherPlaceFlag(bo.getOtherPlaceFlag())
                .setInHospitalDate(bo.getInHospitalDate())
                .setLeaveHospitalDate(bo.getLeaveHospitalDate())
                .setInHospitalDays(bo.getInHospitalDays())
                .setLeaveHospitalWay(bo.getLeaveHospitalWay())
                .setHospitalizationCosts(bo.getHospitalizationCosts())
                .setBasicWholePay(bo.getBasicWholePay())
                .setSeriousIllnessInsurance(bo.getSeriousIllnessInsurance())
                .setMedicalHelp(bo.getMedicalHelp())
                .setCivilMedicalSubsidy(bo.getCivilMedicalSubsidy())
                .setLargeSubsidy(bo.getLargeSubsidy())
                .setCompanySubsidy(bo.getCompanySubsidy())
                .setPersonCashPay(bo.getPersonCashPay())
                .setPersonAccountPay(bo.getPersonAccountPay())
                .setPersonSelfPay(bo.getPersonSelfPay())
                .setPersonSelfCost(bo.getPersonSelfCost())
                .setBasicMedicalInsuranceCost(bo.getBasicMedicalInsuranceCost())
                .setInHospitalDiagnosisCode(bo.getInHospitalDiagnosisCode())
                .setInHospitalDiagnosisName(bo.getInHospitalDiagnosisName())
                .setLeaveHospitalDiagnosisCode(bo.getLeaveHospitalDiagnosisCode())
                .setLeaveHospitalDiagnosisName(bo.getLeaveHospitalDiagnosisName())
                .setOperationCode(bo.getOperationCode())
                .setOperationName(bo.getOperationName());
    }
}
