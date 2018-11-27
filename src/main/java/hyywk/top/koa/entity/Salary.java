package hyywk.top.koa.entity;
import javax.persistence.*;
import java.util.Date;
/**
 * 工资单
 */
@Entity
public class Salary {
    @Id
    @GeneratedValue
    private Long id;
    @Column( columnDefinition = "BIGINT DEFAULT 0 COMMENT '该月发放的工资'")
    private Long mount;
    @Column( columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '工资发放日期' ")
    private Date deliverDate;
    @JoinColumn( referencedColumnName = "id")
    @ManyToOne( cascade = CascadeType.REMOVE )
    private WorkCompany workCompany;
    @JoinColumn( referencedColumnName = "id")
    @ManyToOne( cascade = CascadeType.REMOVE )
    private Account account;

    public Salary(Long id,Long mount, Date deliverDate, WorkCompany workCompany, Account account) {
        this.id = id;
        this.mount = mount;
        this.deliverDate = deliverDate;
        this.workCompany = workCompany;
        this.account = account;
    }
    public Salary(Long id,Long mount, Date deliverDate, WorkCompany workCompany) {
        this.mount = mount;
        this.deliverDate = deliverDate;
        this.workCompany = workCompany;
    }

    public Salary() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMount() {
        return mount;
    }

    public void setMount(Long mount) {
        this.mount = mount;
    }

    public Date getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }

    public WorkCompany getWorkCompany() {
        return workCompany;
    }

    public void setWorkCompany(WorkCompany workCompany) {
        this.workCompany = workCompany;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
