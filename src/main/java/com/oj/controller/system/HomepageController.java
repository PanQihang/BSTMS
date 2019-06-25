package com.oj.controller.system;

import com.oj.service.system.HomepageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by panqihang on 2019/4/9 14:40
 */
@Controller
@RequestMapping("/homepage")
public class HomepageController {
    private Logger log = (Logger) LoggerFactory.getLogger(this.getClass());

    //依赖注入IpService
    @Autowired
    private HomepageService homepageService;
    //返回首页界面
    @RequestMapping("/")
    public String index(HttpServletRequest request)
    {
        return "system/adminhomepage";
    }
}
