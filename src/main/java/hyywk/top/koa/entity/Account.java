package hyywk.top.koa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 账户实体类
 * 默认不使用@Table注解，映射为account表
 */
@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    @Column( columnDefinition = "varchar(100) unique key not null" )
    private String username;
    private String password;
    @Column(columnDefinition = "CHAR(11) UNIQUE KEY COMMENT '手机号'", nullable = false)
    private String phonenumber;
    @Column( columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ")
    private Date createTime;

    public Account() {}
    public Account(Long id) { this.id = id;}
    public Account(Long id, String username, Date createTime) {
        this.id = id;
        this.username = username;
        this.createTime = createTime;
    }
    public Account(String username, String password, Date createTime) {
        this.username = username;
        this.password = password;
        this.createTime = createTime;
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Long getId() {
        return id;
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

    public String toString() {
        return String.format(
                "Account[username=%s]",
                id, this.username);
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPhonenumber() {
        return phonenumber;
    }
}
