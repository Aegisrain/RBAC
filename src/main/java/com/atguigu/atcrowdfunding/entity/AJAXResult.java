package com.atguigu.atcrowdfunding.entity;

/**
 * @Auther: yzy
 * @Date: 2019/1/21 13:49
 * @Description:
 */
public class AJAXResult {
    private boolean success;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private Object data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
