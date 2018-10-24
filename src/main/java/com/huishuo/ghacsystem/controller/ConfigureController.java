package com.huishuo.ghacsystem.controller;

import com.huishuo.ghacsystem.dto.Result;
import com.huishuo.ghacsystem.model.Factory;
import com.huishuo.ghacsystem.model.IOPoint;
import com.huishuo.ghacsystem.model.Line;
import com.huishuo.ghacsystem.model.Place;
import com.huishuo.ghacsystem.service.ConfigureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/configure")
public class ConfigureController {
    private final ConfigureService configureService;
    @Autowired
    public ConfigureController(ConfigureService configureService){
        this.configureService = configureService;
    }

    @PutMapping(value = "/factory", produces = {"application/json; charset=utf-8"})
    @ResponseBody
    public void addFactory(@RequestBody Factory factory) {
        this.configureService.addFactory(factory);
    }

    @DeleteMapping(value = "/factory", produces = {"application/json; charset=utf-8"})
    @ResponseBody
    public void deleteFactory(@RequestBody int factoryId) {
        this.configureService.deleteFactory(factoryId);
    }

    @GetMapping(value = "/factory", produces = {"application/json; charset=utf-8"})
    @ResponseBody
    public Result<List<Factory>> listFactory() {
        return new Result<>(true, this.configureService.listFactory());
    }

    @PutMapping(value = "/line", produces = {"application/json; charset=utf-8"})
    @ResponseBody
    public void addLine(@RequestBody Line line) {
        this.configureService.addLine(line);
    }

    @DeleteMapping(value = "/line", produces = {"application/json; charset=utf-8"})
    @ResponseBody
    public void deleteLine(@RequestBody int lineId) {
        this.configureService.deleteLine(lineId);
    }

    @GetMapping(value = "/line", produces = {"application/json; charset=utf-8"})
    @ResponseBody
    public Result<List<Line>> listLine() {
        return new Result<>(true, this.configureService.listLine());
    }

    @PutMapping(value = "/place", produces = {"application/json; charset=utf-8"})
    @ResponseBody
    public void addPlace(@RequestBody Place place) {
        this.configureService.addPlace(place);
    }

    @DeleteMapping(value = "/place", produces = {"application/json; charset=utf-8"})
    @ResponseBody
    public void deletePlace(@RequestBody int placeId) {
        this.configureService.deletePlace(placeId);
    }

    @GetMapping(value = "/place", produces = {"application/json; charset=utf-8"})
    @ResponseBody
    public Result<List<Place>> listPlace() {
        return new Result<>(true, this.configureService.listPlace());
    }

    @PutMapping(value = "/iopoint", produces = {"application/json; charset=utf-8"})
    @ResponseBody
    public void addIOPoint(@RequestBody IOPoint iOPoint) {
        this.configureService.addIOPoint(iOPoint);
    }

    @DeleteMapping(value = "/iopoint", produces = {"application/json; charset=utf-8"})
    @ResponseBody
    public void deleteIOPoint(@RequestBody int iOPointId) {
        this.configureService.deleteIOPoint(iOPointId);
    }

    @GetMapping(value = "/iopoint", produces = {"application/json; charset=utf-8"})
    @ResponseBody
    public Result<List<IOPoint>> listIOPoint() {
        return new Result<>(true, this.configureService.listIOPoint());
    }

}
