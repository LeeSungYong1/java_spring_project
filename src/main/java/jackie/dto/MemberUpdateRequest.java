package jackie.dto;

import jakarta.validation.constraints.NotBlank;

public class MemberUpdateRequest {
    @NotBlank(message = "이름은 비어 있을 수 없습니다.")
    private String name;
    private String nick_name;
    private String sex;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNick_name() { return nick_name; }
    public void setNick_name(String nick_name) { this.nick_name = nick_name; }

    public String getSex() { return sex; }
    public void setSex(String sex) { this.sex = sex; }
}
