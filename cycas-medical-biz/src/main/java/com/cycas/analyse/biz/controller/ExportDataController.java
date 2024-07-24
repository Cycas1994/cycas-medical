package com.cycas.analyse.biz.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.cycas.analyse.biz.entity.IllegalDetail;
import com.cycas.analyse.biz.enums.IllegalTypeEnum;
import com.cycas.analyse.biz.model.bo.IllegalDetailExportExcelBO;
import com.cycas.analyse.biz.model.bo.IllegalParamBO;
import com.cycas.analyse.biz.model.dto.ExportDataDTO;
import com.cycas.analyse.biz.strategy.IllegalStrategyContext;
import com.cycas.common.biz.exception.BizException;
import com.cycas.common.biz.model.vo.RInfoVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
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
//                    .registerWriteHandler(new HorizontalCellStyleStrategy(getHeadStyle(), getContentStyle()))
                    .sheet(illegalType.getName())
                    .doWrite(dataList);
        } catch (IOException e) {
            throw new BizException("导出异常,请稍后再试");
        }
    }

    /**
     * 头部样式
     */
    private static WriteCellStyle getHeadStyle() {
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景颜色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);

        // 字体
        WriteFont headWriteFont = new WriteFont();
        //设置字体名字
        headWriteFont.setFontName("微软雅黑");
        //设置字体大小
        headWriteFont.setFontHeightInPoints((short) 10);
        //字体加粗
        headWriteFont.setBold(false);
        //在样式用应用设置的字体
        headWriteCellStyle.setWriteFont(headWriteFont);

        // 边框样式
        setBorderStyle(headWriteCellStyle);

        //设置自动换行
        headWriteCellStyle.setWrapped(true);

        //设置水平对齐的样式为居中对齐
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        //设置垂直对齐的样式为居中对齐
        headWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        //设置文本收缩至合适
        //        headWriteCellStyle.setShrinkToFit(true);

        return headWriteCellStyle;
    }

    /**
     * 内容样式
     */
    private static WriteCellStyle getContentStyle() {
        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();

        // 背景白色
        // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
        contentWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);

        // 设置字体
        WriteFont contentWriteFont = new WriteFont();
        //设置字体大小
        contentWriteFont.setFontHeightInPoints((short) 10);
        //设置字体名字
        contentWriteFont.setFontName("宋体");
        //在样式用应用设置的字体
        contentWriteCellStyle.setWriteFont(contentWriteFont);

        //设置样式
        setBorderStyle(contentWriteCellStyle);

        // 水平居中
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 垂直居中
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //设置自动换行
        contentWriteCellStyle.setWrapped(true);

        //设置单元格格式是：文本格式，方式长数字文本科学计数法
//        contentWriteCellStyle.setDataFormatData();

        //设置文本收缩至合适
        // contentWriteCellStyle.setShrinkToFit(true);

        return contentWriteCellStyle;
    }

    private static void setBorderStyle(WriteCellStyle cellStyle) {
        //设置底边框
        cellStyle.setBorderBottom(BorderStyle.THIN);
        //设置底边框颜色
        cellStyle.setBottomBorderColor(IndexedColors.BLACK1.getIndex());
        //设置左边框
        cellStyle.setBorderLeft(BorderStyle.THIN);
        //设置左边框颜色
        cellStyle.setLeftBorderColor(IndexedColors.BLACK1.getIndex());
        //设置右边框
        cellStyle.setBorderRight(BorderStyle.THIN);
        //设置右边框颜色
        cellStyle.setRightBorderColor(IndexedColors.BLACK1.getIndex());
        //设置顶边框
        cellStyle.setBorderTop(BorderStyle.THIN);
        //设置顶边框颜色
        cellStyle.setTopBorderColor(IndexedColors.BLACK1.getIndex());
    }


}
