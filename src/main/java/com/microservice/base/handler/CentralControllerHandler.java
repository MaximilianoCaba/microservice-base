package com.microservice.base.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RestController
public class CentralControllerHandler {

    private static final Logger logger = LoggerFactory.getLogger(CentralControllerHandler.class);

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class})
    public String handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.info("Status:[{}], Mensaje de error:[{}], Exception: [{}]", HttpStatus.CONFLICT.value(),
                ex.getMessage(), ex);
        return ex.getMessage();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({NullPointerException.class})
    public String handleNullPointerException(NullPointerException ex) {
        logger.error("Status:[{}], Mensaje de error:[{}], Exception: [{}]", new Object[]{HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(), ex}, ex);
        return "Ocurrio un error interno del servidor";
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public String handleException(Exception ex) {
        logger.error("Status:[{}], Mensaje de error:[{}], Exception: [{}]", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(), ex);
        return "Ocurrio un error interno del servidor";
    }
}
