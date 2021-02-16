package com.fastinjava.framework.baseapplication.api;

import com.fastinjava.framework.baseapplication.vo.OrgListReqVO;
import com.fastinjava.framework.baseapplication.vo.OrgListResVO;
import com.fastinjava.framework.baseapplication.vo.OrgTreeReqVO;
import com.fastinjava.framework.baseapplication.vo.OrgUpdateReqVO;
import com.fastinjava.framework.common.dto.Node;
import com.fastinjava.framework.common.res.JsonResult;
import com.fastinjava.framework.common.res.PageResult;

import java.util.List;

public interface OrgController {
    JsonResult<PageResult<OrgListResVO>> list(OrgListReqVO orgListReqVO);
    JsonResult<OrgListResVO> detail(OrgListReqVO orgListReqVO);
    JsonResult<List<Node>> tree(OrgTreeReqVO orgTreeReqVO);

    /**
     * 删除组织软删除
     * @param orgUpdateReqVO
     * @return
     */
    JsonResult<Boolean> update(OrgUpdateReqVO orgUpdateReqVO);
}
