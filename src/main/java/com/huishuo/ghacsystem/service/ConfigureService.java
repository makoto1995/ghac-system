package com.huishuo.ghacsystem.service;

import com.huishuo.ghacsystem.model.Factory;
import com.huishuo.ghacsystem.model.IOPoint;
import com.huishuo.ghacsystem.model.Line;
import com.huishuo.ghacsystem.model.Place;

import java.util.List;

public interface ConfigureService {
    void addFactory(Factory factory);

    void deleteFactory(int factoryId);

    List<Factory> listFactory();

    void addLine(Line line);

    void deleteLine(int lineId);

    List<Line> listLine();

    void addPlace(Place place);

    void deletePlace(int placeId);

    List<Place> listPlace();

    void addIOPoint(IOPoint ioPoint);

    void deleteIOPoint(int iOPointId);

    List<IOPoint> listIOPoint();
}
