package my.pro.acommunity.controller;

import my.pro.acommunity.dto.AccessTokenDTO;
import my.pro.acommunity.dto.GithubUser;
import my.pro.acommunity.mapper.UserMapper;
import my.pro.acommunity.model.User;
import my.pro.acommunity.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * 登陆认证授权
 */
@Controller
public class AuthorizeController {

    @Resource
    private UserMapper userMapper;
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSceret;
    @Value("${github.redirect.uri}")
    private String redirectUrl;

    /**
     * code:您收到的作为对步骤1的响应的代码。
     * state:您在步骤1中提供的无法猜测的随机字符串。
     * @param code
     * @param state
     * @return
     */
    @GetMapping("/callback")
    public String callBack(@RequestParam(name = "code") String code, @RequestParam("state")
                           String state, HttpServletRequest request){

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setRedirect_uri(redirectUrl);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_secret(clientSceret);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if(githubUser!=null){
            User user=new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());//当前时间
            user.getGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            //登陆成功。写cookie 和session
            request.getSession().setAttribute("githubUser",githubUser);
           return "redirect:/";
        }else{
            //失败
            return "redirect:/";
        }
    }
}
