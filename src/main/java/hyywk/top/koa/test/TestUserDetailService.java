package hyywk.top.koa.test;

import hyywk.top.koa.entity.Account;
import hyywk.top.koa.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TestUserDetailService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger( TestUserDetailService.class);
    // @Autowired
    private AccountService accountService;
    // @Autowired
    private PasswordEncoder passwordEncoder;
    // @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        this.logger.info("正在验证信息是否正确");
        Account account = accountService.getAccountByUsername(username);
        if ( account == null ) {
            this.logger.warn("信息错误");
            throw new UsernameNotFoundException("没有发现该账户信息");
        }
        return new User( account.getUsername(), account.getPassword() , AuthorityUtils.createAuthorityList("ADMIN"));
    }
}
