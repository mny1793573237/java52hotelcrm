package com.yjxxt.hotel.query;

import com.yjxxt.hotel.base.BaseQuery;

public class RoomTypeQuery extends BaseQuery {
    private Integer roomTypeId;
    private String roomType;
    private String roomRemark;

    public RoomTypeQuery() {
    }

    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomRemark() {
        return roomRemark;
    }

    public void setRoomRemark(String roomRemark) {
        this.roomRemark = roomRemark;
    }

    @Override
    public String toString() {
        return "RoomTypeQuery{" +
                "roomTypeId=" + roomTypeId +
                ", roomType='" + roomType + '\'' +
                ", roomRemark='" + roomRemark + '\'' +
                '}';
    }
}
