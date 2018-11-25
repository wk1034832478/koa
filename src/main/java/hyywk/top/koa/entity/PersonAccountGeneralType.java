package hyywk.top.koa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * 通用个人账户类型，所有用户均可用
 * person_account_general_type
 */
@Entity
public class PersonAccountGeneralType {
    @Id
    @GeneratedValue
    private Long id;
    @Column( nullable = false, unique = true)
    private String name; // 账户类型名称，类型名称唯一且不可为空
    public PersonAccountGeneralType(){}
    public PersonAccountGeneralType(String name) {
        this.name = name;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
