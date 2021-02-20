package com.fastinjava.framework.baseapplication.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AppListReqVO implements Serializable {
    private Integer pageNum = 0;
    private Integer pageSize = 10;
    private Boolean pageable = true;
}
