package com.richard.config.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final String ERROR_PAGE = "error";
	
	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = Exception.class)
	public Object exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
		// 打印异常
		logger.error("捕获系统异常：--->",e);

		if (isAjax(request)) {
			// 自定义的ajax异常处理，并返回;
			return null;
		} else {
			ModelAndView view = new ModelAndView();
			view.addObject("url", request.getRequestURI());
			view.addObject("exception", e);
			view.setViewName(ERROR_PAGE);
			return view;
		}

	}

	public boolean isAjax(HttpServletRequest request) {
		return false;
	}
}
