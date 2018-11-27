package hyywk.top.koa.entity;

import javax.persistence.*;
import java.util.Date;
@Entity
public class PersonDynamic {
    @Id
    @GeneratedValue
    private Long id;
    @Column( nullable = false )
    private String title; //  内容标题
    @Column( columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ")
    private Date deliverTime; // 发表时间
    @Column( columnDefinition = "ENUM ('private','public','friend') NOT NULL DEFAULT 'public' COMMENT '查看权限'")
    private String watchPriority;//查看权限
    @Column( columnDefinition = "TEXT NOT NULL COMMENT '富文本内容'")
    private String contentOfRichText; // 富文本内容
    private String mainPicture;
    @Column( columnDefinition = "BIGINT NOT NULL DEFAULT 0")
    private Long lookTime; // 观看次数
    @ManyToOne
    @JoinColumn( referencedColumnName = "id" )
    private Account account;

    public PersonDynamic() {
    }

    public PersonDynamic(String title, Date deliverTime, String watchPriority, String contentOfRichText, Long lookTime) {
        this.title = title;
        this.deliverTime = deliverTime;
        this.watchPriority = watchPriority;
        this.contentOfRichText = contentOfRichText;
        this.lookTime = lookTime;
    }

    public PersonDynamic(String title, Date deliverTime, String watchPriority, String contentOfRichText, Long lookTime, Account account) {
        this.deliverTime = deliverTime;
        this.watchPriority = watchPriority;
        this.contentOfRichText = contentOfRichText;
        this.lookTime = lookTime;
        this.account = account;
    }

    public void setMainPicture(String mainPicture) {
        this.mainPicture = mainPicture;
    }

    public String getMainPicture() {
        return mainPicture;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    public String getWatchPriority() {
        return watchPriority;
    }

    public void setWatchPriority(String watchPriority) {
        this.watchPriority = watchPriority;
    }

    public String getContentOfRichText() {
        return contentOfRichText;
    }

    public void setContentOfRichText(String contentOfRichText) {
        this.contentOfRichText = contentOfRichText;
    }

    public Long getLookTime() {
        return lookTime;
    }

    public void setLookTime(Long lookTime) {
        this.lookTime = lookTime;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
