package com.recruit.zejuxin.recruit.Bean;

/**
 * Created by 10942 on 2017/9/28 0028.
 */

public class homeBanner {
    private String id;
    private String name;
    private String pic;
    private String time;
    private String state;

    public homeBanner(String id, String name, String pic, String time, String state) {
        this.id = id;
        this.name = name;
        this.pic = pic;
        this.time = time;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
