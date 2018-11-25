package hyywk.top.koa.entity;

import javax.persistence.*;
import java.util.Date;
@Entity
public class PersonAccount {
    @Id
    @GeneratedValue
    private Long id;
    @Column( nullable = false )
    private String username;
    private String password;
    private String phonenumber; // 关联手机号
    private String email; // 关联email
    @Column( columnDefinition = "ENUM('yes','no') DEFAULT 'yes' COMMENT '账户是否可用'")
    private String canUse;
    @Column( columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ")
    private Date createTime; // 创建时间
    @ManyToOne
    @JoinColumn( referencedColumnName = "id" )
    private PersonAccountGeneralType personAccountGeneralType;
    @ManyToOne
    @JoinColumn( referencedColumnName = "id" )
    private Account account;
    public PersonAccount(){}
    public PersonAccount(Long id, String username, String password, String phonenumber, String email, Date createTime ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phonenumber = phonenumber;
        this.email = email;
        this.createTime = createTime;
    }
    public PersonAccount(Long id,String username, String password, String phonenumber, String email, Date createTime, PersonAccountGeneralType personAccountGeneralType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phonenumber = phonenumber;
        this.email = email;
        this.createTime = createTime;
        this.personAccountGeneralType = personAccountGeneralType;
    }

    public PersonAccount(Long id,String username, String password,String canUse, String phonenumber, String email, Date createTime, PersonAccountGeneralType personAccountGeneralType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.canUse = canUse;
        this.phonenumber = phonenumber;
        this.email = email;
        this.createTime = createTime;
        this.personAccountGeneralType = personAccountGeneralType;
    }

    public PersonAccount(String username, String password, String phonenumber, String email, Date createTime ) {
        this.username = username;
        this.password = password;
        this.phonenumber = phonenumber;
        this.email = email;
        this.createTime = createTime;
    }

    public PersonAccount(String username, String password, String phonenumber, String email, Date createTime, PersonAccountGeneralType personAccountGeneralType,Account account) {
        this.username = username;
        this.password = password;
        this.phonenumber = phonenumber;
        this.email = email;
        this.createTime = createTime;
        this.personAccountGeneralType = personAccountGeneralType;
        this.account = account;
    }

    public void setCanUse(String canUse) {
        this.canUse = canUse;
    }

    public String getCanUse() {
        return canUse;
    }

    public Long getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public PersonAccountGeneralType getPersonAccountGeneralType() {
        return personAccountGeneralType;
    }

    public void setPersonAccountGeneralType(PersonAccountGeneralType personAccountGeneralType) {
        this.personAccountGeneralType = personAccountGeneralType;
    }
}
