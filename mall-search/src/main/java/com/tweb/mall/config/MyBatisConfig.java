package com.tweb.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis相关配置
 */
@Configuration
@MapperScan({"com.tweb.mall.mapper","com.tweb.mall.dao"})
public class MyBatisConfig {
}
