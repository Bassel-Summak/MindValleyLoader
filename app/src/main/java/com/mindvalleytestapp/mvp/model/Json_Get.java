package com.mindvalleytestapp.mvp.model;

import java.io.Serializable;

public class Json_Get implements Serializable {

    private long pageNum;

    public Json_Get(){
    }

    public long getPageNum() {
        return pageNum;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }
}
