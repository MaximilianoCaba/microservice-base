package com.microservice.base.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base")
@Api(value = "Base", description = "Controlador de ejemplo para el uso de swagger")
public class ControllerBase {

    private Logger logger = LoggerFactory.getLogger(ControllerBase.class);

    @GetMapping("")
    public String helloWorld() {

        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");

        return "Hello World";
    }
}
