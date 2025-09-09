package jackie.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * 회원 Entity 생성 1
 */
@Entity
@Table(name = "member")
public class Member {

    //아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Integer memberId;

    //이름
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    //닉네임
    @Column(name = "nick_name", nullable = true, length = 100)
    private String nickName;

    //성별
    @Column(name = "sex", nullable = true, length = 1)
    private String sex;

    //등록 시간
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    //수정 시간
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Member() {}

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

