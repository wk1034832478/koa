package hyywk.top.koa.controllers;
import hyywk.top.koa.entity.Account;
import hyywk.top.koa.message.ResponseCode;
import hyywk.top.koa.message.ResponseFactory;
import hyywk.top.koa.service.AccountService;
import hyywk.top.koa.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;


@RestController
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private ResponseFactory responseFactory;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private AccountService accountService;
    @Autowired
    private HttpSession session;

    private Date lastSendDate; // 上一次发送短信的时间
    // 定时器
    private Timer timer = new Timer( );
    private Map<String,String> codeMap = new HashMap<String,String>();

    @PostMapping("/api/account/save")
    public String save(Account account,@RequestParam(value = "isLogin", required = false) Boolean isLogin) {
        try {
            this.accountService.save( account );
            if ( isLogin != null && isLogin ) {
                this.request.login(account.getUsername(), account.getPassword());
            }
            return responseFactory.create("申请账户成功",ResponseCode.SUCCESS);
        }catch (org.springframework.dao.DataIntegrityViolationException e1) {
            return responseFactory.create("该账户已经被注册过了",ResponseCode.HAS_REGISTRIED);
        }catch ( Exception e ) {
            e.printStackTrace();
            return responseFactory.create("申请账户失败",ResponseCode.ERROR);
        }
    }

    /**
     * 获取当前登陆的用户，可以用来检查是否登陆
     * @return
     */
    @PostMapping("/api/user/get")
    public Object getRemoteUser() {
        String username = request.getRemoteUser();
        if ( username == null) {
            return responseFactory.create("还未登陆",ResponseCode.LOGINOUT);
        }
        return responseFactory.create(username,ResponseCode.SUCCESS);
    }
    @GetMapping("/api/principal/get")
    public Object getUserPrincipal() {
        return request.getUserPrincipal().toString();
    }
    @PostMapping("/api/user/logout")
    public Object logout() {
        this.logger.info( "用户退出");
        try {
            request.logout();
        } catch ( Exception e ) {
            this.logger.info( "退出失败！");
            return responseFactory.create("error",ResponseCode.LOGINOUT);
        }
        return responseFactory.create("error",ResponseCode.SUCCESS);
    }
    @PostMapping("/api/user/login")
    public Object login(@RequestParam("username") String username, @RequestParam("password") String password ) {
        this.logger.info( "用户登陆");
        try {
            request.login(username, password); // Cannot perform login for 'user' already authenticated as 'user'
            return responseFactory.createByMsg("ok" );
        }catch ( Exception e ) {
            this.logger.info("异常类型：{}", e.getClass().getName());
            this.logger.info( "登陆失败！{}  ", e.getMessage());
            if ( e.getMessage().contains( "already authenticated as" )) { // 已经登陆过
                return responseFactory.createByMsg("authenticated" );
            }
            return responseFactory.create("error" , ResponseCode.ERROR);
        }
    }

    /**
     * 发送短信验证码
     * @param phonenumber 手机号
     * @return 发送结果
     */
    @PostMapping("/api/user/capture")
    public Object message( String phonenumber ) {
        try {
            Date date = new Date();
            if ( this.lastSendDate != null ) {
                    long dis = (date.getTime() - this.lastSendDate.getTime()) / 1000;
                if ( dis < 60 ) { //距离上次发送不到60秒，所以不能再次发送
                        return responseFactory.create( "请在" + ( 60 - dis ) + "秒之后再次发送" , ResponseCode.WAITTING);
                    }
            }
            this.lastSendDate = date;
            String code = MessageUtil.generateCode();
            this.accountService.sendMessage( this.session, phonenumber, code);
            //请求成功
            this.codeMap.put(  MessageUtil.KEY, code );
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    LoginController.this.codeMap.remove( MessageUtil.KEY );
                    LoginController.this.logger.warn("验证码已经删除");
                }
            }, 1000 * 60 ); // 1分钟之类删除验证码
            return responseFactory.create( "发送成功" , ResponseCode.SUCCESS);
        } catch (Exception e ) {
            return responseFactory.create( "验证码发送失败：" + e .getMessage() , ResponseCode.ERROR);
        }
    }
    @PostMapping("/api/user/validate_capture")
    public String validateMessageCode(@RequestParam("code") String code ) {
        String saveCode = this.codeMap.get(MessageUtil.KEY);
        if (  saveCode != null && saveCode.equals( code )) {
            LoginController.this.codeMap.remove( MessageUtil.KEY );
            return responseFactory.create( "验证成功" , ResponseCode.SUCCESS);
        }
        return responseFactory.create( "验证失败" , ResponseCode.ERROR);
    }

}
