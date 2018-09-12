package com.richard.utils;

import lombok.Data;

/**
 * @author: richard 
 * @date: 2018年9月7日 下午10:56:54
 * @version:
 * @Description: 接口数据返回封装
 */
@Data
public class JsonResult {
 
	private String status = null;
 
	private Object result = null;
 
}