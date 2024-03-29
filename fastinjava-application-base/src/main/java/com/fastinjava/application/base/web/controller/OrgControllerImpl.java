package com.fastinjava.application.base.web.controller;

import com.fastinjava.application.base.service.OrgService;
import com.fastinjava.framework.baseapplication.api.OrgController;
import com.fastinjava.framework.baseapplication.vo.*;
import com.fastinjava.framework.common.dto.Node;
import com.fastinjava.framework.common.res.JsonResult;
import com.fastinjava.framework.common.res.PageResult;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "组织管理", tags = {"组织管理api"})
@RestController
@RequestMapping("/org")
public class OrgControllerImpl implements OrgController {

    @Resource
    private OrgService orgService;

    @PostMapping("/detail")
    @Override
    public JsonResult<OrgListResVO> detail(@RequestBody OrgListReqVO orgListReqVO) {
        return JsonResult.<OrgListResVO>builder().success(orgService.detail(orgListReqVO)).build();
    }

    @ApiOperation("组织列表(表格视图数据)")
    @PostMapping("/list")
    @Override
    public JsonResult<PageResult<OrgListResVO>> list(@RequestBody OrgListReqVO orgListReqVO) {
        return JsonResult.<PageResult<OrgListResVO>>builder().success(orgService.list(orgListReqVO)).build();
    }

    @ApiOperation("组织列表(树形视图数据)")
    @PostMapping("/tree")
    @Override
    public JsonResult<List<Node>> tree(@RequestBody OrgTreeReqVO orgTreeReqVO) {
        List<Node> nodeList = orgService.tree(orgTreeReqVO);
        Node node = new Node();
        node.setChildren(nodeList);
        node.setId("0");
        node.setPid(null);
        node.setLabel("顶级组织");
        return JsonResult.<List<Node>>builder().success(Lists.newArrayList(node)).build();
    }

    @PostMapping("/update")
    @Override
    public JsonResult<Boolean> update(@RequestBody @Validated OrgUpdateReqVO orgUpdateReqVO) {
        Integer orgId = orgUpdateReqVO.getOrgId();
        Boolean result = orgService.update(orgUpdateReqVO);
        return result
                ? JsonResult.<Boolean>builder().success(true).build() :
                JsonResult.<Boolean>builder().failure("删除失败").build();
    }

    @PostMapping("/insert")
    @Override
    public JsonResult<Boolean> insert(@RequestBody OrgInsertReqVO orgInsertReqVO) {
        //如果没有勾选父组织，则默认挂在顶级组织下面，顶级组织的id为0;
        if (orgInsertReqVO.getOrgPid() == null) {
            orgInsertReqVO.setOrgPid(0);
        }
        Boolean result = orgService.insert(orgInsertReqVO);
        return result
                ? JsonResult.<Boolean>builder().success(true).build() :
                JsonResult.<Boolean>builder().failure("新增失败").build();
    }
}
