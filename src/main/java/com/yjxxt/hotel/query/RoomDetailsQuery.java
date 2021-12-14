package com.yjxxt.hotel.query;

import com.yjxxt.hotel.base.BaseQuery;

public class RoomDetailsQuery extends BaseQuery {
    private Integer roomArea;
    private String roomIntro;
    private String roomType;

    public RoomDetailsQuery() {
    }

    public Integer getRoomArea() {
        return roomArea;
    }

    public void setRoomArea(Integer roomArea) {
        this.roomArea = roomArea;
    }

    public String getRoomIntro() {
        return roomIntro;
    }

    public void setRoomIntro(String roomIntro) {
        this.roomIntro = roomIntro;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "RoomDetailsQuery{" +
                "roomArea=" + roomArea +
                ", roomIntro='" + roomIntro + '\'' +
                ", roomType='" + roomType + '\'' +
                '}';
    }
}
