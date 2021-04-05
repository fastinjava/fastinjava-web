package com.fastinjava.framework.baseapplication.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GrantClientApiVO implements Serializable {

    private String clientId;
    private List<String> apiCodeList;

}
