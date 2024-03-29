package com.sh.wxapp.config;

import com.github.pagehelper.PageHelper;

import java.util.Properties;

/**
 * @author zhangjianjun
 * @date 2018年7月6日
 *  Mybatis工具类
 * 
 */
public class MybatisUtils {

	public static PageHelper getPageHelper() {
		
		//分页插件
		PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        //设置方言
        properties.setProperty("dialect", "mysql");
        //启用合理化时，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页
        //禁用合理化时，如果pageNum<1或pageNum>pages会返回空数据
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        return pageHelper;
	}
	
}
