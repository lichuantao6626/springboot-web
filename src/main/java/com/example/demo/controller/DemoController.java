package com.example.demo.controller;

import com.example.demo.mapper.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 控制层，控制每个接口的访问路径
 */

@RestController
@RequestMapping("/li")  //跟路径
public class DemoController {
    @Autowired
    private UserMapper userMapper;

    //他的访问路径：http://localhost:8080/li/hello,其他接口类似
    @RequestMapping("/hello")
    public String getString() {
        System.out.println("hello");
        return "hello word";
    }

    //    接收浏览器传进来的参数，然后把参数解析出来，传给usermapper，再对数据库进行操作
    @RequestMapping(value = "/test/insert", method = RequestMethod.GET)
    public String testInsert(String username, String password) {
        System.out.println("username=" + username + ", password=" + password);
//    插入数据
        userMapper.insert(username, Integer.parseInt(password));
//    查询数据
//    User u = userMapper.findByName("AAA");
//    更新数据
//    u.setAge(30);
//    userMapper.update(u);
//    删除数据
//    userMapper.delete(u.getId());
//    u = userMapper.findByName("AAA");
//    Assert.assertEquals(null, u);
//    返回一个状态，判断插入数据库是否成功。
        return "username=" + username + ", password=" + password;
    }

    @RequestMapping(value = "/test/query", method = RequestMethod.GET)
    public ArrayList<String> testQuery(String username, String password) {
        System.out.println("username=" + username + ", password=" + password);
//    查询数据
        List<User> u = userMapper.findByName(username);
//    查询结果以json格式返回
        ArrayList<String> name_list = new ArrayList<>();
        for (int i = 0; i < u.size(); i++) {
            String name = u.get(i).getName().toString();
            String rs_json = "[" + name + "]";
            name_list.add(rs_json);
            System.out.println(rs_json);
        }

        return name_list;
    }

    //    方法参数名即为请求参数名
    @RequestMapping(value = "/test/query1", method = RequestMethod.GET)
    public String testQuery1(String username, String password) {
        System.out.println("username=" + username + ", password=" + password);
        return "username=" + username + ", password=" + password;
    }


    // 从HttpServletRequest中提取参数
    @RequestMapping(value = "/test/query2", method = RequestMethod.GET)
    public String testQuery2(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username=" + username + ", password=" + password);
        return "username=" + username + ", password=" + password;
    }

    //    方法参数名和请求参数名可以不一样，通过@RequestParam注解来绑定参数
    @RequestMapping(value = "/test/query3", method = RequestMethod.GET)
    public String testQuery3(@RequestParam("username") String un, @RequestParam("password") String pw) {
        System.out.println("username=" + un + ", password=" + pw);
        return "username=" + un + ", password=" + pw;
    }

}
