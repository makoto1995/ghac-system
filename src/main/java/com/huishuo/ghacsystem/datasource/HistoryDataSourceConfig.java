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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.huishuo.ghacsystem.mapper.historydata", sqlSessionTemplateRef = "historyDataSqlSessionTemplate")
public class HistoryDataSourceConfig {
    @Bean(name = "historyDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.history")
    public DataSource historyDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "historyDataSqlSessionFactory")
    public SqlSessionFactory historySqlSessionFactory(@Qualifier("historyDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "historyDataTransactionManager")
    public DataSourceTransactionManager historyTransactionManager(@Qualifier("historyDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "historyDataSqlSessionTemplate")
    public SqlSessionTemplate historySqlSessionTemplate(@Qualifier("historyDataSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
