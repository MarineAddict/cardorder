package com.sh.wxapp.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import com.sh.wxapp.Application;
import com.sh.wxapp.constant.Constants;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-02-21 11:41
 **/
@EnableTransactionManagement
@EnableConfigurationProperties(MySqlDataSource.class)
@Configuration
@MapperScan(basePackages = Constants.MAPPER_MYSQL_PATH, sqlSessionFactoryRef = "mysqlSqlSessionFactory")
public class MySQLDataSourceConfig {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private MySqlDataSource druidMysql;

    @Bean(name = "mysqlDataSource")
    @Primary
    public DataSource mysqlDataSource() {
        DataSource dataSource = getDataSource(druidMysql);
        return dataSource;
    }

    @Bean(name = "mysqlSqlSessionFactory")
    @Primary
    public SqlSessionFactory mysqlSqlSessionFactory() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(mysqlDataSource());
        bean.setTypeAliasesPackage(Constants.DO_MAIN_MYSQL_PATH);

        //添加插件
        PageHelper pageHelper = MybatisUtils.getPageHelper();
        bean.setPlugins(new Interceptor[]{pageHelper});

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources(Constants.MAPPER_MYSQL_XML_PATH));
            return bean.getObject();
        } catch (Exception e) {
            logger.error("build mysql SqlSessionFactory has error !");
            throw new RuntimeException(e);
        }
    }

    //配置事务管理器
    @Bean(name = "mysqlTransactionManager")
    @Primary
    public DataSourceTransactionManager mysqlTransactionManager() {
        return new DataSourceTransactionManager(mysqlDataSource());
    }

    //配置模板
    @Bean(name = "mysqlSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate mysqlSqlSessionTemplate(@Qualifier("mysqlSqlSessionFactory")SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }





    //获取连接池
    private static DataSource getDataSource(MySqlDataSource properties) {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUserName());
        dataSource.setPassword(properties.getPassword());
        dataSource.setDriverClassName(properties.getDriverClass());
        if (properties.getInitialSize() > 0) {
            dataSource.setInitialSize(properties.getInitialSize());
        }
        if (properties.getMinIdle() > 0) {
            dataSource.setMinIdle(properties.getMinIdle());
        }
        if (properties.getMaxActive() > 0) {
            dataSource.setMaxActive(properties.getMaxActive());
        }
        dataSource.setTestOnBorrow(properties.getTestOnBorrow());

        try {
            dataSource.init();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dataSource;
    }
}
