package com.zwfung.controller;

import com.zwfung.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zw fung
 */
@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

    @PostMapping
    public ModelAndView login(User user) {
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        String url = "";
        try {
            subject.login(token);
            url = "index";
        } catch (UnknownAccountException e) {
            log.error("未知账户：{}", token.getUsername());
            url = "error";
        } catch (IncorrectCredentialsException e) {
            log.error("账户：{}，密码错误", token.getUsername());
            url = "error";
        } catch (Exception e) {
            log.error("登录未知错误", e);
            url = "error";
        }

        return new ModelAndView(url);
    }
}
