package hyywk.top.koa.jpa;

import hyywk.top.koa.entity.Account;
import hyywk.top.koa.entity.BasicInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountJpaTest {
    private static final Logger logger = LoggerFactory.getLogger(AccountJpaTest.class);
    @Autowired
    private AccountJpa accountJpa;
    @Autowired
    private BasicInfoJpa basicInfoJpa;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    public void test1() {
        Optional<Account> optionalAccount = this.accountJpa.findById(1l);
        if ( optionalAccount.isPresent() ) {
            this.logger.info( optionalAccount.get().getUsername() );
            } else{ System.out.println("无");
        }
    }

    @Test
    public void test2() {
        Account account = this.accountJpa.findByUsername("root");
        if ( account == null ) { //
            this.logger.info("不存在root账户");
            this.logger.info("新建root账户");
            this.accountJpa.save( new Account("root","root"));
        } else {
            this.logger.info("存在");
            this.logger.info("username:{}, password:{}", account.getUsername(), account.getPassword());
        }
    }

    @Test
    public void test3() {
        Account account = new Account();
        account.setId(3l);
        try {
            this.basicInfoJpa.save( new BasicInfo("王凯",(byte)22, new Date(2018,11,19),"河南",'男',"none",account ));
        } catch (Exception e ) {
            this.logger.info("当前存在wk用户");
        }
    }


    @Test
    public void test4() {
        this.basicInfoJpa.deleteById(2l); // 会级联删除account表
    }

    @Test
    public void test() {
        this.logger.info( this.passwordEncoder.encode("root") );
    }

    @Test
    public void test6() {
       this.logger.info( "用户名：" + this.accountJpa.getAccount("root").getUsername() );
    }
    @Test
    public void test7() {
        Account account = new Account();
        account.setId( 3l );
        BasicInfo basicInfo = this.basicInfoJpa.findByAccount( account );
        if ( basicInfo == null ) {
            this.logger.info( "没有该账户信息" );
        } else {
            this.logger.info( "用户姓名：{}", basicInfo.getName() );
        }
    }

    @Test
    public void test8() {
        try {
            this.logger.info( new SimpleDateFormat("").parse("Nov 22, 2018 8:00:00 AM").toString() );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
