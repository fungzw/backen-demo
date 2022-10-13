package com.zwfung.shiro;

import com.zwfung.entity.User;
import com.zwfung.service.IUserService;
import com.zwfung.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author zw fung
 */
@Slf4j
public class MyRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 转成UsernamePasswordToken
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        IUserService userService = SpringUtil.getBean(IUserService.class);
        User db = userService.getByUsername(((UsernamePasswordToken) token).getUsername());

        if (null == db) {
            throw new UnknownAccountException("查询不到账户信息");
        }

        String comparePwd = new String(usernamePasswordToken.getPassword());
        if (!db.getPassword().equals(comparePwd)) {
            throw new IncorrectCredentialsException("账户密码错误了");
        }

        return new SimpleAuthenticationInfo(usernamePasswordToken.getUsername(), usernamePasswordToken.getPassword(), getName());
    }
}
