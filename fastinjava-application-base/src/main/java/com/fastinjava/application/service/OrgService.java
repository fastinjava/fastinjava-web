package com.fastinjava.application.service;

import com.fastinjava.framework.baseapplication.vo.OrgListReqVO;
import com.fastinjava.framework.baseapplication.vo.OrgListResVO;
import com.fastinjava.framework.baseapplication.vo.OrgTreeReqVO;
import com.fastinjava.framework.baseapplication.vo.OrgUpdateReqVO;
import com.fastinjava.framework.common.dto.Node;
import com.fastinjava.framework.common.res.PageResult;

import java.util.List;

public interface OrgService {
    PageResult<OrgListResVO> list(OrgListReqVO orgListReqVO);

    OrgListResVO detail(OrgListReqVO orgListReqVO);

    List<Node> tree(OrgTreeReqVO orgTreeReqVO);

    Boolean update(OrgUpdateReqVO orgUpdateReqVO);
}
