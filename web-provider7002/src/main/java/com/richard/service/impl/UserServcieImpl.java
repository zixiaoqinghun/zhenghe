package com.richard.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.richard.entity.User;
import com.richard.mapper.UserMapper;
import com.richard.service.UserServcie;

import tk.mybatis.mapper.entity.Example;

@Service
//@Cacheable(cacheManager="myRedisCacheManager")
public class UserServcieImpl implements UserServcie{

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UserMapper userMapper;
	
	//不指定的话默认按照原始的redisCacheManager加载
	@Qualifier("myRedisCacheManager")
	@Autowired
	CacheManager redisCacheManager;

	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addUser(User user) {
		userMapper.insert(user);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteUser(Integer userId) {
		int code = userMapper.deleteByPrimaryKey(userId);
		logger.info(code+"");
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateUser(User user) {
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	@Cacheable(cacheNames="list",key="user")
	public List<User> getUser(User user,int page,int size) {
		logger.info("....查询用户列表的数据库操作....");
		PageHelper.startPage(page, size);
		Example example = new Example(User.class);

		Example.Criteria criteria = example.createCriteria();
		if (user != null && user.getUsername() != null && !"".equals(user.getUsername().trim().toString())) {
			criteria.andLike("username", "%" + user.getUsername().trim().toString() + "%");
		}

		example.orderBy("registtime").desc();
		List<User> list = userMapper.selectByExample(example);
		return list;
	}

	/**
	 * 此种情况，当redis缓存中没有目标缓存数据，则有多少的并发就发多少次数据库请求，应对小并发可以，高并发则需考虑缓存击穿问题
	 */
	/*@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	@Cacheable(cacheNames="user",key="#id")
	public User getUserById(Integer id) {
		logger.info("....通过ID查询数据库操作....");
		return this.userMapper.selectByPrimaryKey(id);
	}*/
	
	
	//代码版（双重判断）,解决上面注解存在缓存击穿的问题
	@Override
	public User getUserById(Integer id) {
		User user = redisCacheManager.getCache("dept").get(id, User.class);
		if(null == user){
			synchronized(this){
				user = redisCacheManager.getCache("dept").get(id, User.class);
				if(null == user){
					logger.info("数据库查询id= " + id + " 的User记录");
					user = this.userMapper.selectByPrimaryKey(id);
					redisCacheManager.getCache("dept").put(id, user);
				}
			}
		}
		return user;
	}

}
