package com.client.login;


import org.apache.log4j.Logger;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Wan CC
 * @create 2020-09-08 15:15
 * @Description:
 */
@Controller
public class LoginController
{
    Logger logger = Logger.getLogger(this.getClass().getName());
    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        AttributePrincipal userPrincipal = (AttributePrincipal)request.getUserPrincipal();
        return userPrincipal.getAttributes().get("loginStatus").toString();
//        ModelAndView mv = new ModelAndView("menu");//指定视图
//        mv.addObject("name", userName);
//        return mv;
    }
    @RequestMapping("/logout1")
    public String logout1(HttpSession session) {
        session.invalidate();
        logger.info("logout1");
        return "redirect:" + "https://cas.example.com:8443/cas/logout";
    }
    @RequestMapping("/logout2")
    public String logout2(HttpSession session) {
        session.removeAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
        session.invalidate();
        logger.info("logout2");
        return "redirect:https://cas.example.com:8443/cas/logout?service=https://www.taobao.com";
    }
}
