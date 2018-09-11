package com.richard.utils.mybatis;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * @author: richard 
 * @date: 2018年9月1日 下午12:45:26
 * @version:
 * @Description: mybatis逆向工程从数据库反向生成pojo和mapper
 * 
 * 步骤：
 * 	1. 创建数据库表结构和注释说明
 *  2. 配置好generatorConfig.xml文件中的包路径
 *  3. 执行该类，刷新项目
 * 
 */
public class MybatisAuto {
	private void generator() throws Exception {
		List<String> warnings = new ArrayList<>();
		boolean overwrite = true;
		// 指定逆向工程配置文件
		java.io.File configFile = new File("generatorConfig.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration configuration = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration, callback, warnings);
		myBatisGenerator.generate(null);

	}

	//public static void main(String[] args) throws Exception {\
	@Test
	public void create() throws Exception{
		MybatisAuto auto = new MybatisAuto();
		auto.generator();
	}
}
