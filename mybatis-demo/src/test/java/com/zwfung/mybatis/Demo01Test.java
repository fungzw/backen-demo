package com.zwfung.mybatis;

import com.zwfung.mybatis.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class Demo01Test {

    @Test
    public void testOriginalConnection() {
        String url = "jdbc:mysql://192.168.66.4:3306/shiro-demo?serverTimezone=UTC";
        String userName = "root";
        String password = "123456";

        User query = new User();
        query.setId(1L);

        try {
            // 第一步：加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 第二步：获得数据库的连接
            Connection conn = DriverManager.getConnection(url, userName, password);

            // 第三步：创建语句并执行
            PreparedStatement stmt = conn.prepareStatement("select * from tb_user where id = ?");
            stmt.setLong(1, query.getId());
            ResultSet resultSet = stmt.executeQuery();

            List<User> userList = new ArrayList<>();
            if (null != resultSet) {
                // 第四步：处理数据库操作结果
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    userList.add(user);
                }
            }

            // 第五步：关闭连接
            stmt.close();

            for (User user : userList) {
                log.info("查询用户：{}", user);
            }
        } catch (SQLException e) {
            log.error("sql异常", e);
        } catch (ClassNotFoundException e) {
            log.error("未找到驱动", e);
        }

    }
}
