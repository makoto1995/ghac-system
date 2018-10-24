package com.huishuo.ghacsystem.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.huishuo.ghacsystem.mapper.staticdata", sqlSessionTemplateRef = "staticSqlSessionTemplate")
public class StaticDataSourceConfig {
    @Bean(name = "staticDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.static")
    @Primary
    public DataSource staticDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "staticDataSqlSessionFactory")
    @Primary
    public SqlSessionFactory staticSqlSessionFactory(@Qualifier("staticDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "staticDataTransactionManager")
    @Primary
    public DataSourceTransactionManager staticTransactionManager(@Qualifier("staticDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "staticDataSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate staticSqlSessionTemplate(@Qualifier("staticDataSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
