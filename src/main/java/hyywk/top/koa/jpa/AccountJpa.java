package hyywk.top.koa.jpa;

import hyywk.top.koa.entity.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountJpa extends CrudRepository<Account, Long> {
    @Override
    Optional<Account> findById(Long aLong);

    Account findByUsername(String username);

    Account findByUsernameAndPassword(String username, String password);

    /**
     * 通过该方法获取无密码的用户信息
     * @param username
     * @return
             */
    @Query(value = "SELECT new Account(id,username,createTime) FROM Account WHERE username = :username" )
    Account getAccount(@Param("username") String username);


}
