package com.cycas.analyse.biz.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author xin.na
 * @since 2024/7/24 9:31
 */
public class ExportDataDTO {

    @Data
    @ApiModel("入参:违规数据导出")
    public static class IllegalExport {
        @ApiModelProperty("违规类别 1:注射用艾司奥美拉唑钠、注射用奥美拉唑 2:注射用氨曲南 3:盐酸氨溴索注射液 4:参芪扶正注射液 5:末梢采血 " +
                "6:动静脉置管护理 7:局部浸润麻醉 8:静脉输液 9:吸痰护理 10:气管插管术 " +
                "11:四肢血管彩色多普勒超声 12:一般专项护理 13:心脏彩色多普勒超声 14:血气分析 15:盐酸莫西沙星氯化钠注射液 " +
                "16:引流管引流(含引流护理及更换引流装置) 17:注射用拉氧头孢钠 18:哌拉西林钠他唑巴坦钠 19:椎管内置管术 20:尿沉渣定量")
        @NotNull(message = "违规类别不能为空")
        @Range(min = 1, max = 20, message = "违规类别不正确")
        private Integer type;

        @ApiModelProperty("导入uuid")
        @NotBlank(message = "导入uuid不能为空")
        private String importUuid;

        @ApiModelProperty("导入明细uuid")
        @NotBlank(message = "导入明细uuid不能为空")
        private String detailImportUuid;

        @ApiModelProperty("医院项目名称")
        private String hospitalProjectName;


    }
}
