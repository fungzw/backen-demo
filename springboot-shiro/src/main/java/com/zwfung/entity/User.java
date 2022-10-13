package com.zwfung.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zw fung
 */
@Data
@TableName("tb_user")
public class User {
    private Long id;
    private String username;
    private String password;
}
