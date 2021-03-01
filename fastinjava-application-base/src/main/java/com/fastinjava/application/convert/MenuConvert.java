package com.fastinjava.application.convert;

import com.fastdevelopinjava.framework.system.api.dto.MenuDTO;
import com.fastdevelopinjava.framework.system.api.dto.MenuInsertDTO;
import com.fastdevelopinjava.framework.system.api.dto.MenuReqDTO;
import com.fastinjava.framework.baseapplication.vo.MenuInsertVO;
import com.fastinjava.framework.baseapplication.vo.MenuListDetailVO;
import com.fastinjava.framework.baseapplication.vo.MenuReqVO;

public interface MenuConvert {
    MenuListDetailVO menuDTO2MenuListDetailVO(MenuDTO menuDTO);
    /**
     * MenuReqVO menuReqVO ----> MenuReqDTO
     */
    MenuReqDTO menuReqVO2MenuReqDTO(MenuReqVO menuReqVO);

    MenuInsertDTO menuInsertVO2MenuInsertDTO(MenuInsertVO menuInsertVO);
}
