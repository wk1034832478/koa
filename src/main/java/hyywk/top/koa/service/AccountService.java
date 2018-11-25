package hyywk.top.koa.service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import hyywk.top.koa.controllers.LoginController;
import hyywk.top.koa.entity.Account;
import hyywk.top.koa.jpa.AccountJpa;
import hyywk.top.koa.message.ResponseCode;
import hyywk.top.koa.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

@Service
public class AccountService {

    private AccountJpa accountJpa;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void save( Account account ) {
        account.setPassword( this.passwordEncoder.encode( account.getPassword() ) );
        this.accountJpa.save( account );
    }

    public Account getAccountByUsernameAndPassword(String username, String password) {
        if ( username == null || password == null) {
            return null;
        }
        return accountJpa.findByUsernameAndPassword(  username,  password );
     }
     public Account getAccountByUsername( String username) {
        return this.accountJpa.findByUsername( username);
     }

    public Account getAccountNoPassword(String username) {
        return this.accountJpa.getAccount( username );
    }

    @Autowired
    public void setAccountJpa(AccountJpa accountJpa) {
        this.accountJpa = accountJpa;
    }

    public void sendMessage ( HttpSession session, String phonenumber , String code) throws Exception {
        if ( !phonenumber.matches("^[0-9]{11}$") ) {
            throw new Exception("手机号格式不正确，请输入11位手机号");
        }
        try {
            SendSmsResponse sendSmsResponse =  MessageUtil.sendMessage( phonenumber, code );
            if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                //
            } else {
                throw new Exception( "发送失败," + sendSmsResponse.getMessage() );
            }
        } catch ( Exception e ) {
            throw new Exception( "发送失败:"+ e.getMessage() );
        }
    }
}
