package com.hospital.dao;

import com.hospital.entity.Room;

public interface RoomDao extends GenericDao<Room, Integer> {
    Room getByName(String name);
}