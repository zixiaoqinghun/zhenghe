package com.richard.myactuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * 自定义的安全检查指示器
 * @author: richard 
 * @date: 2018年9月6日 下午10:43:12
 * @version:
 * @Description: 定制自定义的安全检查指示器,可以对自己的服务组件进行安全信息检查配置
 * 		1. 定义xxxHealthIndicator指示器类实现HealthIndicator接口的health(),注意类名称
 * 		2. 定义自定义的安全检查指示器
 * 		3. 将类注册为容器组件
 */

//健康页面显示的名字默认为类的除开HealthIndicator后缀后的首字母小写
@Component
public class MyAppHealthIndicator implements HealthIndicator {

	@Override
	public Health health() {
		//Health.up().build(); //服务正常
		return Health.down().withDetail("error msg", "服务异常！").build();
	}

}
