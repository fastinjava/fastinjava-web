package com.fastinjava.application.base.client;

import com.fastdevelopinjava.framework.codegen.api.dto.GenDatasourceConfDTO;
import com.fastdevelopinjava.framework.codegen.api.dto.GenDatasourceConfReqDTO;
import com.fastdevelopinjava.framework.codegen.api.dto.TableDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.PageDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "fastdevelopinjava-service-gateway", path = "/gateway/fastdevelopinjava-service-codegen/dsconf")
public interface GenDsConfFeginClient {

    @PostMapping("/list")
    public ResultDTO<PageDTO<GenDatasourceConfDTO>> getList(@RequestBody GenDatasourceConfReqDTO genDatasourceConfReqDTO);

    @GetMapping("/getTablesByDsName")
    public ResultDTO<List<TableDTO>> getTablesByDsName(@RequestParam("dsName") String dsName);
}
