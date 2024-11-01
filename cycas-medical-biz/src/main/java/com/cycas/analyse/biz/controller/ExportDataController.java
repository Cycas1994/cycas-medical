package com.cycas.analyse.biz.controller;

import com.alibaba.excel.EasyExcel;
import com.cycas.analyse.biz.entity.IllegalDetail;
import com.cycas.analyse.biz.enums.IllegalTypeEnum;
import com.cycas.analyse.biz.excel.style.CustomCellStyleStrategy;
import com.cycas.analyse.biz.model.bo.IllegalDetailExportExcelBO;
import com.cycas.analyse.biz.model.bo.IllegalParamBO;
import com.cycas.analyse.biz.model.dto.ExportDataDTO;
import com.cycas.analyse.biz.strategy.IllegalStrategyContext;
import com.cycas.common.biz.exception.BizException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xin.na
 * @since 2024/7/24 9:29
 */
@Slf4j
@RestController
@RequestMapping("/exportData")
public class ExportDataController {

    @ApiOperation("违规数据导出")
    @PostMapping("/illegal/export")
    public void illegalExport(@RequestBody @Valid ExportDataDTO.IllegalExport dto,
                              HttpServletResponse response) {
        // 输出违规数据
        IllegalTypeEnum illegalType = IllegalTypeEnum.match(dto.getType());
        IllegalStrategyContext context = new IllegalStrategyContext(illegalType);
        IllegalParamBO paramBO = new IllegalParamBO();
        paramBO.setImportUuid(dto.getImportUuid());
        paramBO.setDetailImportUuid(dto.getDetailImportUuid());
        paramBO.setHospitalProjectName(dto.getHospitalProjectName());
        List<IllegalDetail> detailList = context.execute(paramBO);
        // 导出数据
        String fileName = null;
        try {
            fileName = URLEncoder.encode(illegalType.getName(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new BizException("不支持的编码");
        }
        response.setHeader("Content-Disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset:utf-8");
        response.setCharacterEncoding("utf-8");
        List<IllegalDetailExportExcelBO> dataList = detailList.stream()
                .map(IllegalDetailExportExcelBO::convertNew)
                .collect(Collectors.toList());

        try {
            EasyExcel.write(response.getOutputStream(), IllegalDetailExportExcelBO.class)
                    .registerWriteHandler(new CustomCellStyleStrategy())
                    .sheet(illegalType.getName())
                    .doWrite(dataList);
        } catch (IOException e) {
            throw new BizException("导出异常,请稍后再试");
        }
    }

}
