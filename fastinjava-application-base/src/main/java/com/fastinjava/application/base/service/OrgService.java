package com.fastinjava.application.base.service;

import com.fastinjava.framework.baseapplication.vo.*;
import com.fastinjava.framework.common.dto.Node;
import com.fastinjava.framework.common.res.PageResult;

import java.util.List;

public interface OrgService {
    PageResult<OrgListResVO> list(OrgListReqVO orgListReqVO);

    OrgListResVO detail(OrgListReqVO orgListReqVO);

    List<Node> tree(OrgTreeReqVO orgTreeReqVO);

    Boolean update(OrgUpdateReqVO orgUpdateReqVO);

    Boolean insert(OrgInsertReqVO orgInsertReqVO);
}
