package com.fastinjava.application.base.convert;

import com.fastdevelopinjava.framework.ucenter.api.dto.RoleCreateDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.RoleDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.RoleReqDTO;
import com.fastinjava.framework.baseapplication.vo.RoleInsertVO;
import com.fastinjava.framework.baseapplication.vo.RoleListDetailVO;
import com.fastinjava.framework.baseapplication.vo.RoleListReqVO;

public interface RoleConvert {
    RoleReqDTO roleListReqVO2RoleReqDTO(RoleListReqVO roleListReqVO);

    RoleListDetailVO roleDTO2RoleListDetailVO(RoleDTO roleDTO);

    RoleCreateDTO roleInsertVO2RoleCreateDTO(RoleInsertVO roleInsertVO);

}
