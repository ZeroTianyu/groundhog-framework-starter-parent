package com.groundhog.mybatis.plus.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @Description: MyBatisPlus配置类
 * @Author Created by yan.x on 2020-01-16 .
 **/
@Slf4j
@EnableTransactionManagement
@ConditionalOnClass(MybatisPlusAutoConfiguration.class)
@AutoConfigureBefore(MybatisPlusAutoConfiguration.class)
public class MybatisPlusConfig {

    @Bean(name = "mybatisTransactionManager")
    @Primary //事务默认使用mysql数据库
    public DataSourceTransactionManager testTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 分页插件+防止全表更新与删除插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptors = new MybatisPlusInterceptor();
        interceptors.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        interceptors.addInnerInterceptor(new BlockAttackInnerInterceptor());
        return interceptors;
    }


}