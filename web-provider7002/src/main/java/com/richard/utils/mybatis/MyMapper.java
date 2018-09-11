package com.richard.utils.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author: richard 
 * @date: 2018年9月1日 下午12:46:32
 * @version:
 * @Description: 我的通用mapper
 */
public interface MyMapper<T> extends Mapper<T>,MySqlMapper<T> {

}
