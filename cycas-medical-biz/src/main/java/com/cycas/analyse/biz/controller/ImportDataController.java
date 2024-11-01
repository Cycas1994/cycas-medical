package com.cycas.analyse.biz.controller;

import com.cycas.analyse.biz.service.impl.ImportDataService;
import com.cycas.common.biz.model.vo.RInfoVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xin.na
 * @since 2024/7/19 13:48
 */
@Slf4j
@RestController
@RequestMapping("/importData")
public class ImportDataController {

    @Autowired
    private ImportDataService importDataService;

    @ApiOperation("住院主表文件导入")
    @PostMapping("/inHospital/import")
    public RInfoVO<String> inHospitalImport(@RequestParam("file") MultipartFile file) {
        return RInfoVO.ok(importDataService.inHospitalImport(file));
    }

    @ApiOperation("住院明细表文件导入")
    @PostMapping("/inHospitalDetail/import")
    public RInfoVO<Boolean> inHospitalDetailImport(@RequestParam("file") MultipartFile file) {
        importDataService.inHospitalDetailImport(file);
        return RInfoVO.ok(true);
    }


}
