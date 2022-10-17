package com.zwfung.mybatis;

import com.zwfung.mybatis.entity.User;
import com.zwfung.mybatis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class Demo02Test {

    @Test
    public void testMybatisConnection() {
        // 获取mybatis config
        String resource = "mybatis-config.xml";
        Configuration configuration = null;
        try {
            // 获取资源
            InputStream inputStream = Resources.getResourceAsStream(resource);
            // 构建xml配置builder
            XMLConfigBuilder parser = new XMLConfigBuilder(inputStream);
            // 构建实际的config实例类
            configuration = parser.parse();
        } catch (IOException e) {
            log.error("获取mybatis-config.xml失败", e);
        }

        // 得到DefaultSqlSessionFactory实例类
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(configuration);

        // 读写数据
        try (SqlSession session = sqlSessionFactory.openSession()) {
            // 找到接口对应的实现
            UserMapper userMapper = session.getMapper(UserMapper.class);
            // 组建查询参数
            User userParam = new User();
            userParam.setId(1L);
            // 调用接口展开数据库操作
            List<User> userList =  userMapper.selectById(userParam);
            // 打印查询结果
            for (User user : userList) {
                log.info("user:{}", user);
            }
        }
    }
}
