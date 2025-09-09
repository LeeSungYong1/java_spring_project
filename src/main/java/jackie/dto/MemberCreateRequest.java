package jackie.dto;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * 회원 저장 시, dto 클래스
 */
public class MemberCreateRequest {
    @NotBlank(message = "msg는 비어 있을 수 없습니다.")

    private String name;
    private String nick_name;
    private String sex;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {

        this.nick_name = nick_name;
    }

    public String getSex() {

        return sex;
    }

    public void setSex(String sex) {

        this.sex = sex;
    }

    public LocalDateTime getCreated_at() {

        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {

        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {

        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {

        this.updated_at = updated_at;
    }
}
