package com.zwfung.mybatis.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @author zw fung
 */
@Data
public class User {
    private Long id;
    private String username;
    private String password;
}
