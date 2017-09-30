package com.recruit.zejuxin.recruit.Bean;

import java.util.List;

/**
 * Created by 10942 on 2017/9/29 0029.
 */

public class HomeBean {
    public HomeBean(List<DataBean> data) {
        this.data = data;
    }

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }


}