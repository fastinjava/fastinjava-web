package com.fastinjava.framework.common.res;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 8793161657611669922L;
    /**
     * 页码
     */
    private int pageNum;
    /**
     * 每页结果数
     */
    private int pageSize;

    /**
     * 总数
     */
    private long total;


    private List<T> list;

    public PageResult() {
    }

    public PageResult(int pageNum, int pageSize, long total, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.list = list;
    }

}
