package com.fastinjava.application.base.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.fastdevelopinjava.framework.system.api.dto.MenuDTO;
import com.fastdevelopinjava.framework.system.api.dto.MenuInsertDTO;
import com.fastdevelopinjava.framework.system.api.dto.MenuNodeDTO;
import com.fastdevelopinjava.framework.system.api.dto.MenuReqDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.PageDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import com.fastinjava.application.base.client.MenuFeginClient;
import com.fastinjava.application.base.convert.MenuConverter;
import com.fastinjava.application.base.service.MenuService;
import com.fastinjava.application.base.stragey.MenuTypePreOperateStragey;
import com.fastinjava.application.base.stragey.MenuTypePreOperateStrageyContext;
import com.fastinjava.framework.baseapplication.vo.MenuInsertVO;
import com.fastinjava.framework.baseapplication.vo.MenuListDetailVO;
import com.fastinjava.framework.baseapplication.vo.MenuReqVO;
import com.fastinjava.framework.common.res.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuFeginClient menuFeginClient;

    @Resource
    private MenuConverter menuConverter;


    @Resource
    private MenuTypePreOperateStrageyContext menuTypePreOperateStrageyContext;

    @Override
    public PageResult<MenuListDetailVO> list(MenuReqVO menuReqVO) {

        MenuReqDTO menuReqDTO = menuConverter.menuReqVO2MenuReqDTO(menuReqVO);

        ResultDTO<PageDTO<MenuDTO>> resultDTO = menuFeginClient.getList(menuReqDTO);

        if (!resultDTO.getSuccess()) {
            throw new RuntimeException(resultDTO.getMsg());
        } else {
            PageDTO<MenuDTO> pageDTO = resultDTO.getData();

            List<MenuDTO> menuDTOList = pageDTO.getList();

            List<MenuListDetailVO> menuListDetailVOList = menuDTOList.stream().map(menuDTO -> menuConverter.menuDTO2MenuListDetailVO(menuDTO)).collect(Collectors.toList());

            PageResult<MenuListDetailVO> pageResult = new PageResult<>();
            pageResult.setList(menuListDetailVOList);
            pageResult.setTotal(pageDTO.getTotal());
            pageResult.setPageNum(menuReqVO.getPageNum());
            pageResult.setPageSize(menuReqVO.getPageSize());
            return pageResult;

        }

    }

    @Override
    public Boolean insert(MenuInsertVO menuInsertVO) {
        MenuInsertDTO menuInsertDTO = menuConverter.menuInsertVO2MenuInsertDTO(menuInsertVO);

        String menuType = menuInsertDTO.getMenuType();
        if (StrUtil.isNotBlank(menuType))
        {
            MenuTypePreOperateStragey menuTypePreOperateStragey = menuTypePreOperateStrageyContext.getMenuTypePreOperateStragey(menuType);
            String result = menuTypePreOperateStragey.operate(JSONUtil.toJsonStr(menuInsertVO));
            menuInsertDTO = JSONUtil.toBean(result,MenuInsertDTO.class);
        }
        else {
            throw new RuntimeException("菜单类型必须选择");
        }

        ResultDTO<Boolean> resultDTO = menuFeginClient.insert(menuInsertDTO);

        if (!resultDTO.getSuccess()) {
            throw new RuntimeException(resultDTO.getMsg());
        } else {
            return true;
        }
    }

    @Override
    public MenuListDetailVO detail(MenuReqVO menuReqVO) {
        ResultDTO<MenuDTO> resultDTO = menuFeginClient.getOne(menuConverter.menuReqVO2MenuReqDTO(menuReqVO));
        if (!resultDTO.getSuccess()) {
            throw new RuntimeException(resultDTO.getMsg());
        } else {
            return menuConverter.menuDTO2MenuListDetailVO(resultDTO.getData());
        }
    }

    @Override
    public List<MenuNodeDTO> listTree(MenuReqDTO menuReqDTO) {
        ResultDTO<List<MenuNodeDTO>> resultDTO = menuFeginClient.listTree(menuReqDTO);
        if (!resultDTO.getSuccess()) {
            throw new RuntimeException(resultDTO.getMsg());
        } else {
            log.info(JSONUtil.toJsonPrettyStr(resultDTO.getData()));
            List<MenuNodeDTO> nodeDTOList = clear(resultDTO.getData(), menuReqDTO);
            return nodeDTOList;
        }
    }


    private List<MenuNodeDTO> clear(List<MenuNodeDTO> menuNodeDTOList, MenuReqDTO menuReqDTO) {
        String conditionCliendId = menuReqDTO.getClientId();
        if (!CollectionUtil.isEmpty(menuNodeDTOList)) {
            Iterator<MenuNodeDTO> menuNodeDTOIterator = menuNodeDTOList.iterator();
            while (menuNodeDTOIterator.hasNext()) {
                MenuNodeDTO menuNodeDTO = menuNodeDTOIterator.next();
                String clientId = menuNodeDTO.getClientId();

                if (StrUtil.isBlank(conditionCliendId)) {

                } else {
                    if (!conditionCliendId.equalsIgnoreCase(clientId)) {
                        menuNodeDTOIterator.remove();
                        continue;
                    }else {

                    }

                }

                //格式整理
                List<MenuNodeDTO> subMenuNodeList = menuNodeDTO.getChildren();
                if (CollectionUtil.isEmpty(subMenuNodeList)) {
                    menuNodeDTO.setChildren(null);
                } else {
                    menuNodeDTO.setHasChildren("hasChildren");
                    clear(subMenuNodeList, menuReqDTO);
                }

            }
        }
        return menuNodeDTOList;
    }

}
