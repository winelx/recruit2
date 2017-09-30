package com.recruit.zejuxin.recruit.Bean;

import java.util.List;

/**
 * Created by 10942 on 2017/9/29 0029.
 */

public class DataBean {

    /**
     * address : 贵阳
     * date : 2017-09-25
     * education : 大专
     * experience : 3年以下
     * jobid : e2676a97-43c0-45d2-b69a-b71950f9a9f3
     * list : [{"companyWelfare":[],"ids":"15b25c67-6b82-4c4d-a205-915d0fadacb1","industry":"国企","name":"百度有限公司","size":"1000-9999"}]
     * name : 销售总监
     * salary : 56000
     */

    private String address;
    private String date;
    private String education;
    private String experience;
    private String jobid;
    private String name;
    private String salary;
    private List<ListBean> list;

    public DataBean() {

    }

    public DataBean(String address, String date, String education, String experience, String jobid, String name, String salary, List<ListBean> list) {
        this.address = address;
        this.date = date;
        this.education = education;
        this.experience = experience;
        this.jobid = jobid;
        this.name = name;
        this.salary = salary;
        this.list = list;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

}