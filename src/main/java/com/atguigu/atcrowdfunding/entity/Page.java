package com.atguigu.atcrowdfunding.entity;

import java.util.List;

/**
 * @Auther: yzy
 * @Date: 2019/1/27 14:01
 * @Description:
 */
public class Page<T> {
    private List<T> datas;
    private int pageNo;
    private int totalNo;
    private int totalSize;

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalNo() {
        return totalNo;
    }

    public void setTotalNo(int totalNo) {
        this.totalNo = totalNo;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }
}
