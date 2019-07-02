package com.oj.controller.system;

import com.oj.frameUtil.LogUtil;
import com.oj.mapper.system.AuthMapper;
import com.oj.service.system.AuthService;
import com.oj.service.system.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author lixu
 * @Time 2019年3月13日 15点53分
 * @Description 登入登出控制类
 */
@Controller
public class LoginController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LoginService loginService;
    @Autowired
    AuthService authService;

    @RequestMapping("/login/")
    //返回login.html页面
    public String login(HttpServletRequest request) {
        return "login";
    }

    //用户登陆验证方法
    @PostMapping("/login/UserLogin")
    @ResponseBody
    public boolean userLogin(@RequestBody Map<String, String> loginInfo, HttpServletRequest request){
        List<Map<String, String>> userList = loginService.getUserByLoginInfo(loginInfo);
        //通过用户登录名和密码查询数据库看是否有该用户
        if (userList.size()==1){
            //若有该用户，向session中注入当前用户的信息和功能权限
            request.getSession().setAttribute("user_id",userList.get(0).get("id"));
            String name = loginService.selectName(userList.get(0).get("Identify"));
            System.out.println(name);
            request.getSession().setAttribute("user_name",name);
            request.getSession().setAttribute("user_account",userList.get(0).get("CardID"));
            //32超管 30普通
            if(name.equals("admin"))
            {
                request.getSession().setAttribute("user_auth", authService.getAuthListByRole("32"));
            }
            else
            {
                request.getSession().setAttribute("user_auth", authService.getAuthListByRole("30"));
            }
            log.info("IP为："+LogUtil.getIpAddr(request) +",用户登录名为："+userList.get(0).get("account")+"的用户登入成功");
            return true;
        }else{
            return false;
        }
    }

    //用户开户
    @PostMapping("/login/Register")
    @ResponseBody
    public String Register(HttpServletRequest request)
    {
        String userName = request.getParameter("userName");
        String userID = request.getParameter("userID");
        String userPhone = request.getParameter("userPhone");
        String Address = request.getParameter("Address");
        String Type = request.getParameter("Type");
        String Password = request.getParameter("Password");
        return loginService.register(userName,userID,userPhone,Address,Type,Password);
    }


    //用户注销功能
    @RequestMapping("/login/Logout")
    public void loginLogout(HttpServletRequest request, HttpServletResponse response)throws Exception{
        //进入用户注销功能后清除session中的用户信息
        String user_account = request.getSession().getAttribute("user_account").toString();
        request.getSession().setAttribute("user_id",null);
        request.getSession().setAttribute("user_name",null);
        request.getSession().setAttribute("user_account",null);
        request.getSession().setAttribute("user_role",null);
        request.getSession().setAttribute("user_auth",null);
        response.sendRedirect("/login/");
        log.info("IP为："+LogUtil.getIpAddr(request) +",用户登录名为："+user_account+"的用户登出成功");
    }

    @RequestMapping("/index")
    //返回index.html页面
    public String index(HttpServletRequest request) {
        return "new/index";
    }

    //重置密码功能
    @PostMapping("/resetPassword")
    @ResponseBody
    public boolean resetPassword(HttpServletRequest request){
        String newPassword = request.getParameter("newPassword");
        String id = request.getSession().getAttribute("user_account").toString();
        try {
            //通过请求中的新密码并结合session中的用户ID对用户密码进行更新
            loginService.resetPassword(id, newPassword);
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

}
