package jackie.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jackie.dto.MemberUpdateRequest;
import jackie.entity.Member;
import jackie.dto.MemberCreateRequest;
import jackie.entity.MemberLog;
import jackie.exception.ResourceNotFoundException;
import jackie.dao.MemberRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jackie.dao.MemberLogRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Tag(name = "회원 API", description = "회원 관련 API")
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberLogRepository memberLogRepository;

    public MemberController(MemberRepository memberRepository, MemberLogRepository memberLogRepository) {
        this.memberRepository = memberRepository;
        this.memberLogRepository = memberLogRepository;
    }


    // 회원 전체 조회
    @GetMapping("/api/member/list")
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    // 회원 조회
    @GetMapping("/api/member/{id}")
    public Member findById(@PathVariable Integer id) {
        return memberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Member not found:" + id));
    }

    // 회원 등록
    @PostMapping("/api/member/create")
    public ResponseEntity<String> createMember(@Valid @RequestBody MemberCreateRequest request) {

        Member member = new Member();
        member.setName(request.getName());
        member.setNickName(request.getNick_name());
        member.setSex(request.getSex());
        member.setPhone(request.getPhone());
        member.setDeviceCode(request.getDevice_code());
        member.setMemberStatus(request.getMember_status());

        // created_at은 서버 기준 현재 시간 저장
        final LocalDateTime created_date = LocalDateTime.now();


        member.setCreatedAt(created_date);
        member.setUpdatedAt(created_date);

        Member saved = memberRepository.save(member);
        System.out.println("MemberId="+saved.getMemberId());

        MemberLog member_log = new MemberLog();
        member_log.setMemberId(saved.getMemberId());
        member_log.setName(saved.getName());
        member_log.setNickName(saved.getNickName());
        member_log.setSex(saved.getSex());
        member_log.setPhone(saved.getPhone());
        member_log.setDeviceCode(saved.getDeviceCode());
        member_log.setMemberStatus(saved.getMemberStatus());
        member_log.setCreatedAt(created_date);
        member_log.setUpdatedAt(created_date);
        member_log.setLogCreatedAt(LocalDateTime.now());
        memberLogRepository.save(member_log);


        return ResponseEntity.ok("회원 등록 완료");
    }

    // 회원 수정
    @PutMapping("/api/member/update/{id}")
    public ResponseEntity<String> updateMember(@PathVariable Integer id, @Valid @RequestBody MemberUpdateRequest request) {

        // 1. DB 조회
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found: " + id));

        // 2. 값 수정
        member.setName(request.getName());
        member.setNickName(request.getNick_name());
        member.setSex(request.getSex());
        member.setPhone(request.getPhone());
        member.setDeviceCode(request.getDevice_code());
        member.setMemberStatus(request.getMember_status());
        member.setUpdatedAt(LocalDateTime.now()); // 수정 시각 갱신

        // 3. 저장
        memberRepository.save(member);

        return ResponseEntity.ok("회원 수정 완료");
    }

    
    // 회원 삭제
    @DeleteMapping("api/member/delete/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable("id") Integer id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found: " + id));
        memberRepository.delete(member);
        return ResponseEntity.ok("삭제 성공");
    }

}
