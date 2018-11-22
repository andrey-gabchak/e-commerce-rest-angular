package com.gabchak.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ExceptionHandlingController extends RuntimeException {

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such page")
    @ExceptionHandler(value = Exception.class)
    public ModelAndView handlerPageNotFound(HttpServletRequest request, Exception ex) {
        ModelAndView vm = new ModelAndView();
        vm.setViewName("404");
        vm.addObject("exception", ex);
        vm.addObject("url", request.getRequestURI());
        return vm;
    }
}
