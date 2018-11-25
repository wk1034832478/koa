package hyywk.top.koa.entity;
import javax.persistence.*;
import java.util.Date;

@Entity
public class BasicInfo {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Byte age;
    private Date birthday;
    private String hometown;
    @Column( columnDefinition = "CHAR(1) DEFAULT '男'")
    private Character gender;
    private String photo;
    @OneToOne( targetEntity = Account.class, cascade = CascadeType.REMOVE , fetch = FetchType.LAZY)
    @JoinColumn( referencedColumnName = "id" )
    private Account account;
    protected BasicInfo() {}
    public BasicInfo(String name, Byte age, Date birthday, String hometown, Character gender, String photo, Account account) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.hometown = hometown;
        this.gender = gender;
        this.photo = photo;
        this.account = account;
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

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
