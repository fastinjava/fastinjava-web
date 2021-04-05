package com.fastinjava.framework.baseapplication.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GrantRoleMenuVO implements Serializable {
    private List<String> roleIdList;
    private List<String> menuIdList;
}
