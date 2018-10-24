package com.huishuo.ghacsystem.service.impl;

import com.huishuo.ghacsystem.mapper.staticdata.ConfigureMapper;
import com.huishuo.ghacsystem.model.Factory;
import com.huishuo.ghacsystem.model.IOPoint;
import com.huishuo.ghacsystem.model.Line;
import com.huishuo.ghacsystem.model.Place;
import com.huishuo.ghacsystem.service.ConfigureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigureServiceImpl implements ConfigureService {
    private final ConfigureMapper configureMapper;

    @Autowired
    public ConfigureServiceImpl(ConfigureMapper configureMapper) {
        this.configureMapper = configureMapper;
    }

    @Override
    public void addFactory(Factory factory) {
        this.configureMapper.addFactory(factory.getFactoryName());
    }

    @Override
    public void deleteFactory(int factoryId) {
        this.configureMapper.deleteFactory(factoryId);
    }

    @Override
    public List<Factory> listFactory() {
        return this.configureMapper.listFactory();
    }

    @Override
    public void addLine(Line line) {
        this.configureMapper.addLine(line.getLineName(), line.getFactoryId());
    }

    @Override
    public void deleteLine(int lineId) {
        this.configureMapper.deleteLine(lineId);
    }

    @Override
    public List<Line> listLine() {
        return this.configureMapper.listLine();
    }

    @Override
    public void addPlace(Place place) {
        this.configureMapper.addPlace(place.getPlaceName(), place.getLineId());
    }

    @Override
    public void deletePlace(int placeId) {
        this.configureMapper.deletePlace(placeId);
    }

    @Override
    public List<Place> listPlace() {
        return this.configureMapper.listPlace();
    }

    @Override
    public void addIOPoint(IOPoint ioPoint) {
        this.configureMapper.addIOPoint(ioPoint.getAddress(), ioPoint.getType(), ioPoint.getPlaceId());
    }

    @Override
    public void deleteIOPoint(int iOPointId) {
        this.configureMapper.deleteIOPoint(iOPointId);
    }

    @Override
    public List<IOPoint> listIOPoint() {
        return this.configureMapper.listIOPoint();
    }
}
