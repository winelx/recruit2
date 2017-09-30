package com.recruit.zejuxin.recruit.Bean;

import java.util.List;

/**
 * Created by 10942 on 2017/9/29 0029.
 */

public class ListBean {
    /**
     * companyWelfare : []
     * ids : 15b25c67-6b82-4c4d-a205-915d0fadacb1
     * industry : 国企
     * name : 百度有限公司
     * size : 1000-9999
     */

    private String ids;
    private String industry;
    private String name;
    private String size;
    private List<?> companyWelfare;

    public ListBean(String ids, String industry, String name, String size, List<?> companyWelfare) {
        this.ids = ids;
        this.industry = industry;
        this.name = name;
        this.size = size;
        this.companyWelfare = companyWelfare;
    }

    public ListBean() {

    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<?> getCompanyWelfare() {
        return companyWelfare;
    }

    public void setCompanyWelfare(List<?> companyWelfare) {
        this.companyWelfare = companyWelfare;
    }
}