package com.fastinjava.application.base.web.controller;

import com.fastdevelopinjava.framework.codegen.api.dto.GenCodePreviewReqDTO;
import com.fastdevelopinjava.framework.codegen.api.dto.TableDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import com.fastinjava.application.base.client.GeneratorFeginClient;
import com.fastinjava.application.base.service.GenDsConfService;
import com.fastinjava.framework.codegen.api.GenDsConfController;
import com.fastinjava.framework.codegen.vo.GenDatasourceConfListDetailVO;
import com.fastinjava.framework.codegen.vo.GenDatasourceConfReqVO;
import com.fastinjava.framework.common.res.JsonResult;
import com.fastinjava.framework.common.res.PageResult;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/genDsConf")
@RestController
public class GenDsConfControllerImpl implements GenDsConfController {

//    @Autowired
//    private HttpServletRequest request;
//
//    @Autowired
//    private HttpServletResponse response;


    @Resource
    private GenDsConfService genDsConfService;

    @Resource
    private GeneratorFeginClient generatorFeginClient;


    @Value("${codegen.output.dir}")
    private String codeGenOutputDir;

    @Override
    @PostMapping("/list")
    public JsonResult<PageResult<GenDatasourceConfListDetailVO>> list(@RequestBody GenDatasourceConfReqVO genDatasourceConfReqVO) {
        PageResult<GenDatasourceConfListDetailVO> pageResult = genDsConfService.list(genDatasourceConfReqVO);
        return JsonResult.<PageResult<GenDatasourceConfListDetailVO>>builder().success(pageResult).build();
    }

    @Override
    @GetMapping("/getTablesByDsName")
    public JsonResult<List<TableDTO>> getTablesByDsName(String dsName) {
        return JsonResult.<List<TableDTO>>builder().success(genDsConfService.getTablesByDsName(dsName)).build();
    }

    @Override
    @PostMapping("/preview")
    public JsonResult<Map<String, Object>> preview(@RequestBody GenCodePreviewReqDTO genCodePreviewReqDTO) {
        return JsonResult.<Map<String, Object>>builder().success(genDsConfService.preview(genCodePreviewReqDTO)).build();
    }

    @SneakyThrows
    @PostMapping("/generatorCodeV1")
    public JsonResult<String> generatorCodeV1(@RequestBody GenCodePreviewReqDTO genCodePreviewReqDTO) {

        ResultDTO<String> resultDTO = generatorFeginClient.generatorCodeV1(genCodePreviewReqDTO);

        if (resultDTO.getSuccess()) {

            String fileUrl = resultDTO.getData();

            log.info("fileUrl = {} ", fileUrl);

            return JsonResult.<String>builder().success(fileUrl).build();
        } else {
            return JsonResult.<String>builder().failure("代码生成失败").build();
        }


    }


}
