package hyywk.top.koa.jpa;

import hyywk.top.koa.entity.Account;
import hyywk.top.koa.entity.BasicInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BasicInfoJpa extends CrudRepository<BasicInfo,Long> {
    BasicInfo findByAccount(Account account);

    @Modifying
    @Query( value = "UPDATE BasicInfo bi SET bi.photo = :photo WHERE bi.account.id = :id")
    int updatePhoto(@Param("photo") String photo, @Param("id") Long id);
}
