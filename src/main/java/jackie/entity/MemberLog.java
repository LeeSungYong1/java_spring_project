package jackie.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="member_log")
public class MemberLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_log_id")
    private Integer memberLogId;

    @Column(name="member_id")
    private Integer memberId;

    @Column(name="name")
    private String name;

    @Column(name="nick_name")
    private String nickName;

    @Column(name="sex")
    private String sex;

    @Column(name="phone")
    private String phone;

    @Column(name="device_code")
    private String deviceCode;

    @Column(name="member_status")
    private String memberStatus;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="log_created_at")
    private LocalDateTime logCreatedAt;

    public Integer getMemberLogId() {
        return memberLogId;
    }

    public void setMemberLogId(Integer memberLogId) {
        this.memberLogId = memberLogId;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
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

    public LocalDateTime getLogCreatedAt() {
        return logCreatedAt;
    }

    public void setLogCreatedAt(LocalDateTime logCreatedAt) {
        this.logCreatedAt = logCreatedAt;
    }
}
