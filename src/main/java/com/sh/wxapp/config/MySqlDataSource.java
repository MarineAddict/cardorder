package com.sh.wxapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-02-21 11:49
 **/
@ConfigurationProperties(prefix = "druid.mysql")
public class MySqlDataSource extends BaseDruid {
}
