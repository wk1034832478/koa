package hyywk.top.koa.jpa;
import hyywk.top.koa.entity.PersonAccount;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonAccountJpa extends CrudRepository<PersonAccount,Long> {
    /**
     * 根据账号匹配关键信息和账号所属人id查询拥有账户的数量和对应的类型
     * @param username
     * @param id
     * @return
     */
    @Query(value = "select count(*) as number,person_account_general_type_id as typeId,name as typeName from person" +
            "_account t1 left join person_account_general_type t2 on t1.person_account_genera" +
            "l_type_id = t2.id where t1.username LIKE :username AND t1.account_id = :id  group by person_account_general_type_id", nativeQuery = true)
    List<String[]> presearch(@Param( "username" ) String username,@Param( "id" ) Long id );

    /**
     * 查询账号分布
     * @param id 所属人id
     * @return
     */
    @Query(value = "select count(*) as number,person_account_general_type_id as typeId,name as typeName from person" +
            "_account t1 left join person_account_general_type t2 on t1.person_account_genera" +
            "l_type_id = t2.id where t1.account_id = :id  group by person_account_general_type_id", nativeQuery = true)
    List<String[]> distribution(@Param( "id" ) Long id );

    @Query(value = "select new PersonAccount( id, username,  password, canUse , phonenumber,  email,  createTime, personAccountGeneralType) from " +
            "PersonAccount as pa where pa.username LIKE :username and pa.account.id = :id")
    List<PersonAccount> search(@Param( "username" ) String username, @Param( "id" ) Long id, Pageable pageable);

    @Query(value = "select new PersonAccount(id, username,  password, canUse ,  phonenumber,  email,  createTime, personAccountGeneralType) from " +
            "PersonAccount as pa where pa.username LIKE :username and pa.account.id = :id and email LIKE :email")
    List<PersonAccount> search(@Param( "username" ) String username, @Param( "email" ) String email, @Param( "id" ) Long id, Pageable pageable);

    @Query(value = "select new PersonAccount( id, username,  password, canUse ,  phonenumber,  email,  createTime, personAccountGeneralType) from " +
            "PersonAccount as pa where pa.username LIKE :username and pa.account.id = :id and phonenumber LIKE :phonenumber")
    List<PersonAccount> search( @Param( "id" ) Long id, @Param( "username" ) String username, @Param( "phonenumber" ) String phonenumber, Pageable pageable);

    @Query(value = "select new PersonAccount( id,username,  password, canUse ,  phonenumber,  email,  createTime, personAccountGeneralType) from " +
            "PersonAccount as pa where pa.username LIKE :username and pa.account.id = :id and email LIKE :email and phonenumber LIKE :phonenumber")
    List<PersonAccount> search(@Param( "username" ) String username, @Param( "email" ) String email,@Param( "phonenumber" ) String phonenumber, @Param( "id" ) Long id, Pageable pageable);

    @Query(value = "SELECT ( ( SELECT count(*) FROM person_account  WHERE account_id = :id  and can_use = 'yes' ) / ( SELECT count(*) FROM person_account  WHERE account_id = :id ) )", nativeQuery = true)
    Double getCanUsePercent(@Param("id") Long id);
}
