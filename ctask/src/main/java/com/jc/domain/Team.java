package com.jc.domain;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: zhaojingchun
 * Date: 15-4-24
 * Time: 下午3:58
 * To change this template use File | Settings | File Templates.
 */
public class Team implements Serializable {
    private long id;
    private long userId;

    public String toString() {
        return "Team{" +
                "id=" + id +
                ", userId=" + userId +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
