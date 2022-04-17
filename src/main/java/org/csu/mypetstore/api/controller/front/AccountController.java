package org.csu.mypetstore.api.controller.front;

import org.csu.mypetstore.api.common.CommonResponse;
import org.csu.mypetstore.api.common.ResponseCode;
import org.csu.mypetstore.api.entity.User;
import org.csu.mypetstore.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private UserService userService;

    @GetMapping("users/{id}")
    @ResponseBody
    public CommonResponse<User> findUserByUsername(@PathVariable("id")String username){
        return userService.findUserByUsername(username);
    }

    @PostMapping("/login")
    @ResponseBody
    public CommonResponse<User> login(@RequestParam String username, @RequestParam String password, HttpSession session){
        CommonResponse<User> response=userService.findUser(username,password);
        if(response.isSuccess()){//若成功则将值存入session中
            session.setAttribute("login_account",response.getData());
        }
        return response;
    }

    //获取登录成功的用户信息,只需要取到session中的值即可
    @PostMapping("get_login_account_info")
    @ResponseBody
    public CommonResponse<User> getLoginUserInfo(HttpSession session){
        User loginUser=(User)session.getAttribute("login_account");
        if(loginUser!=null){//表明用户登录成功
            return CommonResponse.createForSuccess(loginUser);
        }
        return CommonResponse.createForError(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，不能获取用户信息");
        //return CommonResponse.createForError("用户未登录，不能获取用户信息");
    }

    //退出登录
    @GetMapping("/signout")
    @ResponseBody
    public CommonResponse<User> signout(HttpServletRequest request){
        if(request.getSession().getAttribute("username")!=null){//若用户登录成功则可以进行退出操作
            request.getSession().removeAttribute("login_account");
            return CommonResponse.createForSuccessMessage("用户退出登录");
        }
        return CommonResponse.createForError("用户未登录，请登陆后再进行此操作");
    }

    //判断用户名是否存在
    @GetMapping("/usernameIsExist")
    //@ResponseBody
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        CommonResponse<User> user = userService.findUserByUsername(username);

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        if(username==""){
            out.print("Empty");
        }
        else if(user != null){
            out.print("Exist");
        }
        else {
            out.print("Not Exist");
        }
        out.flush();
        out.close();
    }

}


