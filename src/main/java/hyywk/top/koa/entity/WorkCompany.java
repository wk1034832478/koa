package hyywk.top.koa.entity;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 任职公司,也可以是自己创建的
 */
@Entity
public class WorkCompany {
    @Id
    @GeneratedValue
    private Long id;
    @Column( unique = true, nullable = false)
    private String name; // 公司名称

    @Column( columnDefinition = " ENUM( '是', '否') NOT NULL DEFAULT '是'" )
    private String isWorking; // 是否仍在入职

    @Column( columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ")
    private Date enterDate; // 入职时间, 默认是当前的录入时间

    private Date leaveDate; // 离职时间

    private String address; // 公司地址

    @ManyToOne
    @JoinColumn( referencedColumnName = "id" )
    private Account account; // 所属账户

    public WorkCompany() {}

    public WorkCompany(Long id, String name, Date enterDate, String isWorking, Date leaveDate, String address) {
        this.id = id;
        this.name = name;
        this.enterDate = enterDate;
        this.isWorking = isWorking;
        this.leaveDate = leaveDate;
        this.address = address;
    }

    public WorkCompany(Long id, String name, Date enterDate, String isWorking, Date leaveDate, String address, Account account) {
        this.id = id;
        this.name = name;
        this.enterDate = enterDate;
        this.isWorking = isWorking;
        this.leaveDate = leaveDate;
        this.address = address;
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

    public Date getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(Date enterDate) {
        this.enterDate = enterDate;
    }

    public String getIsWorking() {
        return isWorking;
    }

    public void setIsWorking(String isWorking) {
        this.isWorking = isWorking;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
