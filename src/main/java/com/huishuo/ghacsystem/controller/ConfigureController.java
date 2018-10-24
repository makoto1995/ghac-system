package com.huishuo.ghacsystem.controller;

import com.huishuo.ghacsystem.service.ConfigureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/configure")
public class ConfigureController {
    private final ConfigureService configureService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    public ConfigureController(ConfigureService configureService){
        this.configureService = configureService;
    }
}
