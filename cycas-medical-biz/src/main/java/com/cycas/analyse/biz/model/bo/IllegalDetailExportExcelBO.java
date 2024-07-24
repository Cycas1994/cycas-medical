package com.cycas.analyse.biz.model.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ContentFontStyle;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.cycas.analyse.biz.entity.IllegalDetail;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xin.na
 * @since 2024/7/24 15:22
 */
@Data
@HeadFontStyle(fontHeightInPoints = 10) // 头字体设置
@ContentFontStyle(fontHeightInPoints = 10) // 内容字体设置
public class IllegalDetailExportExcelBO implements Serializable {

    @ExcelProperty(value = "结算单据号", index = 0)
    private String settleNo;

    @ExcelProperty(value = "医疗机构编码", index = 1)
    private String medicalOrgCode;

    @ExcelProperty(value = "医疗机构名称", index = 2)
    private String medicalOrgName;

    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty(value = "结算日期", index = 3)
    private Date settleDate;

    @ExcelProperty(value = "个人编码", index = 4)
    private String personCode;

    @ExcelProperty(value = "患者社会保障号码", index = 5)
    private String societySecurityNo;

    @ExcelProperty(value = "住院号", index = 6)
    private String hospitalNo;

    @ExcelProperty(value = "险种类型", index = 7)
    private String insuranceType;

    @ExcelProperty(value = "人员类型", index = 8)
    private String personType;

    @ExcelProperty(value = "出院科室", index = 9)
    private String leaveHospitalDepartment;

    @ExcelProperty(value = "主诊医师姓名", index = 10)
    private String doctorInChargeName;

    @ExcelProperty(value = "患者姓名", index = 11)
    private String patientsName;

    @ExcelProperty(value = "患者性别", index = 12)
    private String patientsSex;

    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty(value = "入院日期", index = 13)
    private Date inHospitalDate;

    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty(value = "出院日期", index = 14)
    private Date leaveHospitalDate;

    @ExcelProperty(value = "住院天数", index = 15)
    private Integer inHospitalDays;

    @ExcelProperty(value = "医疗总费用", index = 16)
    private BigDecimal hospitalizationCosts;

    @ExcelProperty(value = "基本统筹支付", index = 17)
    private BigDecimal basicWholePay;

    @ExcelProperty(value = "大病保险", index = 18)
    private BigDecimal seriousIllnessInsurance;

    @ExcelProperty(value = "医疗救助", index = 19)
    private BigDecimal medicalHelp;

    @ExcelProperty(value = "公务员医疗补助", index = 20)
    private BigDecimal civilMedicalSubsidy;

    @ExcelProperty(value = "大额补充", index = 21)
    private BigDecimal largeSubsidy;

    @ExcelProperty(value = "企业补充", index = 22)
    private BigDecimal companySubsidy;

    @ExcelProperty(value = "出院诊断名称", index = 23)
    private String leaveHospitalDiagnosisName;

    @ExcelProperty(value = "出院诊断编码", index = 24)
    private String leaveHospitalDiagnosisCode;

    @ExcelProperty(value = "医院项目名称", index = 25)
    private String hospitalProjectName;

    @ExcelProperty(value = "报销比例", index = 26)
    private String reimbursementRatio;

    @ExcelProperty(value = "支付类别", index = 27)
    private String payCategory;

    @ExcelProperty(value = "数量", index = 28)
    private Integer amount;

    @ExcelProperty(value = "金额", index = 29)
    private BigDecimal money;

    public static IllegalDetailExportExcelBO convertNew(IllegalDetail detail) {
        IllegalDetailExportExcelBO exportExcelBO = new IllegalDetailExportExcelBO();
        BeanUtils.copyProperties(detail, exportExcelBO);
        return exportExcelBO;
    }
}
