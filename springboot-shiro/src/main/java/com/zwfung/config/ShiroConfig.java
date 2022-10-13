package com.zwfung.config;

import com.zwfung.shiro.MyRealm;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zw fung
 */
@Configuration
public class ShiroConfig {

    @Bean
    public Realm myRealm() {
        return new MyRealm();
    }

    @Bean
    public SessionsSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm());

        return securityManager;
    }
}
