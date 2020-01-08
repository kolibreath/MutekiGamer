package com.guineap_pig_329.guinea_pig.error;


//import com.sun.org.apache.xpath.internal.operations.Mod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
/**
 * 全局异常处理
 **/

@ControllerAdvice
public class ErrorExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ErrorExceptionHandler.class);


    /**
     * 处理运行时异常
     */

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView processException1(RuntimeException exception){
        logger.error("运行时异常");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message",exception.getMessage());
        modelAndView.addObject("cause",exception.getCause());
        modelAndView.setViewName("runtimeException");
        return modelAndView;
    }

    /**
     * 处理空指针异常
     */

    @ExceptionHandler({NullPointerException.class})
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView processException2(RuntimeException exception){
        logger.error("空指针异常");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message",exception.getMessage());
        modelAndView.addObject("cause",exception.getCause());
        modelAndView.setViewName("nullPointerException");
        return modelAndView;
    }


    /**
     * 处理UnknownAccountException
     **/
}
