package com.yc.controllers;

import com.yc.bean.Resuser;
import com.yc.biz.ResuserBiz;
import com.yc.utils.ZhwConstans;
import com.yc.vo.JsonModel;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
@Api(description = "小萌神网络订餐系统操作接口", tags = {"用户操作部分", "控制层"})
public class ResuserController {
    @Autowired
    private ResuserBiz resuserBiz;

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public JsonModel loginOp(HttpSession session, JsonModel jm, String valcode, String username, String pwd) {
        if (valcode == null || "".equals(valcode)) {
            jm.setCode(0);
            jm.setMsg("验证码不能为空");
            return jm;
        }
        String validateCode = (String) session.getAttribute("validateCode");
        if (!valcode.equalsIgnoreCase(validateCode)) {
            jm.setCode(0);
            jm.setMsg("验证码输入错误");
            return jm;
        }

        Resuser u = new Resuser();
        u.setUsername(username);
        u.setPwd(pwd);
        Resuser users = resuserBiz.login(u);
        if (users != null) {
            session.setAttribute(ZhwConstans.LOGINUSER, users);  //保存这个用户:  在数据库中保存用户状态  目前，都是将当前用户的状态信息(登录状态，购物车，菜品)存到session
            //TODO: 更好的处理方案是用一个数据库/redis来保存
            jm.setCode(1);
            //再看地址
            if (session.getAttribute(ZhwConstans.LASTVISITEDADDR) != null) {
                jm.setUrl((String) session.getAttribute(ZhwConstans.LASTVISITEDADDR));
            } else {
                jm.setUrl(ZhwConstans.HOMEPAGE);  //没有历史页面，则登录成功后到首页
            }
        } else {
            jm.setCode(0);
            jm.setMsg("用户名或密码错误，请重新输入");
        }
        return jm;
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public JsonModel logout(JsonModel jm, HttpSession session) {
        session.removeAttribute(ZhwConstans.LOGINUSER);
        jm.setCode(1);
        return jm;
    }

    @RequestMapping(value = "/checkLogin", method = {RequestMethod.GET, RequestMethod.POST})
    public JsonModel checkLoginOp(JsonModel jm, HttpSession session) {
        if (session.getAttribute(ZhwConstans.LOGINUSER) == null) {
            jm.setCode(0);
            jm.setMsg("用户没有登录");
        } else {
            jm.setCode(1);
            Resuser user = (Resuser) session.getAttribute(ZhwConstans.LOGINUSER);
            jm.setObj(user);
        }
        return jm;
    }
}
