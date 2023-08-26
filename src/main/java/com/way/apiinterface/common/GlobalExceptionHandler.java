package com.way.apiinterface.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * 全局异常处理器
 *
 * @author Way
 */
@ControllerAdvice(annotations = {RestController.class, ComponentScan.Filter.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 处理自定义异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public String handleDuplicate(CustomException ex) {
        return ex.getMessage();
    }
    /**
     * 处理自定义异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public String handleGlobal(Exception ex) {
        log.error(ex.getMessage());
        log.error(Arrays.toString(ex.getStackTrace()));
        return ex.getMessage();
    }


}
