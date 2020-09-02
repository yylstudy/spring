package com.yyl.page;

import lombok.ToString;

import java.util.ArrayList;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2020/3/4 14:11
 * @Version: 1.0
 */

public class PageDto<T> extends ArrayList<T> {
    private long total;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
