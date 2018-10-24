package com.huishuo.ghacsystem.mapper.staticdata;

import com.huishuo.ghacsystem.model.Factory;
import com.huishuo.ghacsystem.model.IOPoint;
import com.huishuo.ghacsystem.model.Line;
import com.huishuo.ghacsystem.model.Place;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ConfigureMapper {
    @Insert("INSERT INTO TBL_STATIC_FACTORY(factory_name) VALUES(#{factoryName})")
    void addFactory(@Param("factoryName") String factoryName);

    @Delete("DELETE FROM TBL_STATIC_FACTORY WHERE factory_id = #{factoryID}")
    void deleteFactory(@Param("factoryID") int factoryID);

    @Select("SELECT * FROM TBL_STATIC_FACTORY")
    @Results({
            @Result(property = "factoryId", column = "factory_id"),
            @Result(property = "factoryName", column = "factory_name")
    })
    List<Factory> listFactory();

    @Insert("INSERT INTO TBL_STATIC_LINE(line_name, factory_id) VALUES(#{lineName}, #{factoryID})")
    void addLine(@Param("lineName") String lineName, @Param("factoryID") int factoryID);

    @Delete("DELETE FROM TBL_STATIC_LINE WHERE line_id = #{lineID}")
    void deleteLine(@Param("lineID") int lineID);

    @Select("SELECT * FROM TBL_STATIC_LINE")
    @Results({
            @Result(property = "lineId", column = "line_id"),
            @Result(property = "lineName", column = "line_name"),
            @Result(property = "factoryId", column = "factory_id")
    })
    List<Line> listLine();

    @Insert("INSERT INTO TBL_STATIC_PLACE(place_name, line_id) VALUES(#{placeName}, #{lineID})")
    void addPlace(@Param("placeName") String placeName, @Param("lineID") int lineID);

    @Delete("DELETE FROM TBL_STATIC_PLACE WHERE place_id = #{placeID}")
    void deletePlace(@Param("placeID") int placeID);

    @Select("SELECT * FROM TBL_STATIC_PLACE")
    @Results({
            @Result(property = "placeId", column = "place_id"),
            @Result(property = "placeName", column = "place_name"),
            @Result(property = "lineId", column = "line_id")
    })
    List<Place> listPlace();

    @Insert("INSERT INTO TBL_STATIC_IOPOINT(iopoint_address, iopoint_type, place_id) VALUES(#{address}, #{type}, #{placeID})")
    void addIOPoint(@Param("address") String address, @Param("type") String type, @Param("placeID") int placeID);

    @Delete("DELETE FROM TBL_STATIC_IOPOINT WHERE iopoint_id = #{iOPointID}")
    void deleteIOPoint(@Param("iOPointID") int iOPointID);

    @Select("SELECT a.iopoint_id as iopoint_id, a.iopoint_address as iopoint_address, a.iopoint_type as iopoint_type, b.place_id as place_id, c.line_id as line_id, c.factory_id as factory_id " +
            "FROM tbl_static_iopoint a, tbl_static_place b, tbl_static_line c " +
            "WHERE a.place_id = b.place_id and b.line_id = c.line_id")
    @Results({
            @Result(property = "id", column = "iopoint_id"),
            @Result(property = "address", column = "iopoint_address"),
            @Result(property = "type", column = "iopoint_type"),
            @Result(property = "placeId", column = "place_id"),
            @Result(property = "lineId", column = "line_id"),
            @Result(property = "placeId", column = "place_id")
    })
    List<IOPoint> listIOPoint();
}
