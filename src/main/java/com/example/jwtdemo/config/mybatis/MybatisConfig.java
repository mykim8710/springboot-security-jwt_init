package com.example.jwtdemo.config.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.example.jwtdemo.**.repository")	// 프로젝트 패키지 내의 모든 폴더 안에 @mapper를 scan
public class MybatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();

        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setTypeAliasesPackage("com.example.jwtdemo.**.domain"); 											// 프로젝트 패키지 내의 모든 Domain class 경로설정
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("mappers/*.xml"));	// mapper.xml(SQL Query)파일들의 경로설정
        sqlSessionFactory.setVfs(SpringBootVFS.class);
        return sqlSessionFactory.getObject();
    }
}
