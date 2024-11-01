package com.cycas.analyse.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cycas.analyse.biz.model.bo.InHospitalDetailImportExcelBO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xin.na
 * @since 2024/7/22 17:01
 */
@Data
@Accessors(chain = true)
public class InHospitalDetail implements Serializable {

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
     * 个人编码
     */
    private String personCode;

    /**
     * 住院号
     */
    private String hospitalNo;

    /**
     * 患者姓名
     */
    private String patientsName;

    /**
     * 开单科室编码
     */
    private String billingDepartmentCode;

    /**
     * 开单科室名称
     */
    private String billingDepartmentName;

    /**
     * 开单医师编码
     */
    private String billingDoctorCode;

    /**
     * 开单医师姓名
     */
    private String billingDoctorName;

    /**
     * 入院诊断名称
     */
    private String inHospitalDiagnosisName;

    /**
     * 出院诊断名称
     */
    private String leaveHospitalDiagnosisName;

    /**
     * 费用类别
     */
    private String chargeType;

    /**
     * 项目使用时间YYYYMMDDHHMMSS
     */
    private Date productUseDate;

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
     * 出院带药标识，0.否 1.是
     */
    private String takeDrugFlag;

    /**
     * 医院项目编码
     */
    private String hospitalProjectCode;

    /**
     * 医院项目名称
     */
    private String hospitalProjectName;

    /**
     * 医保项目编码
     */
    private String medicareProjectCode;

    /**
     * 医保项目名称
     */
    private String medicareProjectName;

    /**
     * 规格
     */
    private String specification;

    /**
     * 剂型
     */
    private String dosageForm;

    /**
     * 计价单位
     */
    private String chargeUnit;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 数量
     */
    private Integer amount;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 拒付金额
     */
    private BigDecimal refusePayMoney;

    /**
     * 医保范围内金额
     */
    private BigDecimal medicareInsideMoney;

    /**
     * 支付类别
     */
    private String payCategory;

    /**
     * 报销比例
     */
    private String reimbursementRatio;

    public static InHospitalDetail from(InHospitalDetailImportExcelBO bo) {
        return new InHospitalDetail()
                .setSettleNo(bo.getSettleNo())
                .setPersonCode(bo.getPersonCode())
                .setHospitalNo(bo.getHospitalNo())
                .setPatientsName(bo.getPatientsName())
                .setBillingDepartmentCode(bo.getBillingDepartmentCode())
                .setBillingDepartmentName(bo.getBillingDepartmentName())
                .setBillingDoctorCode(bo.getBillingDoctorCode())
                .setBillingDoctorName(bo.getBillingDoctorName())
                .setInHospitalDiagnosisName(bo.getInHospitalDiagnosisName())
                .setLeaveHospitalDiagnosisName(bo.getLeaveHospitalDiagnosisName())
                .setChargeType(bo.getChargeType())
//                .setProductUseDate(bo.getProductUseDate())
//                .setSettleDate(bo.getSettleDate())
                .setSettleYear(bo.getSettleYear())
                .setSettleMonth(bo.getSettleMonth())
                .setTakeDrugFlag(bo.getTakeDrugFlag())
                .setHospitalProjectCode(bo.getHospitalProjectCode())
                .setHospitalProjectName(bo.getHospitalProjectName())
                .setMedicareProjectCode(bo.getMedicareProjectCode())
                .setMedicareProjectName(bo.getMedicareProjectName())
                .setSpecification(bo.getSpecification())
                .setDosageForm(bo.getDosageForm())
                .setChargeUnit(bo.getChargeUnit())
                .setUnitPrice(bo.getUnitPrice())
                .setAmount(bo.getAmount())
                .setMoney(new BigDecimal(bo.getMoney()))
                .setRefusePayMoney(new BigDecimal(bo.getRefusePayMoney()))
                .setMedicareInsideMoney(new BigDecimal(bo.getMedicareInsideMoney()))
                .setPayCategory(bo.getPayCategory())
                .setReimbursementRatio(bo.getReimbursementRatio());
    }

}
