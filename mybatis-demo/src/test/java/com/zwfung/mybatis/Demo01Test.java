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

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, userName, password);

            stmt = conn.prepareStatement("select * from tb_user where id = ?");
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

            for (User user : userList) {
                log.info("查询用户：{}", user);
            }
        } catch (SQLException e) {
            log.error("sql异常", e);
        } catch (ClassNotFoundException e) {
            log.error("未找到驱动", e);
        } finally {
            try {
                if (null != stmt) {
                    stmt.close();
                }
                if (null != conn) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
