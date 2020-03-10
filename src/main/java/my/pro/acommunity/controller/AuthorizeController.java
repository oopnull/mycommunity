package my.pro.acommunity.controller;

import lombok.extern.slf4j.Slf4j;
import my.pro.acommunity.dto.AccessTokenDTO;
import my.pro.acommunity.dto.GithubUser;
import my.pro.acommunity.mapper.UserMapper;
import my.pro.acommunity.model.User;
import my.pro.acommunity.provider.GithubProvider;
import my.pro.acommunity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 登陆认证授权
 */
@Controller
@Slf4j
public class AuthorizeController {

    @Resource
    private UserMapper userMapper;
    @Resource
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSceret;
    @Value("${github.redirect.uri}")
    private String redirectUrl;

    @Resource
    private UserService userService;
    /**
     * code:您收到的作为对步骤1的响应的代码。
     * state:您在步骤1中提供的无法猜测的随机字符串。
     * @param code
     * @param state
     * @return
     */
    @GetMapping("/callback")
    public String callBack(@RequestParam(name = "code") String code, @RequestParam("state")
                           String state, HttpServletRequest request,
                           HttpServletResponse response){

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setRedirect_uri(redirectUrl);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_secret(clientSceret);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if(githubUser!=null&&githubUser.getId()!=null){
            User user=new User();
            String token=UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setAvatarUrl(githubUser.getAvatarUrl());
            userService.createOrUpdate(user);
            response.addCookie(new Cookie("token",token));
            //登陆成功。写cookie 和session
            request.getSession().setAttribute("githubUser",githubUser);
           return "redirect:/";
        }else{
            log.error("callback get github error,{}",githubUser);
            //失败
            return "redirect:/";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
       request.getSession().removeAttribute("user");
       Cookie cookie= new Cookie("token",null);
       cookie.setMaxAge(0);
       response.addCookie(cookie);
        return "redirect:/";
    }
}
