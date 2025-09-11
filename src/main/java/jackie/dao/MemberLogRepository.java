package jackie.dao;
import jackie.entity.MemberLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DB연결을 위한 인터페이스 선언
 * ORM 필요한 JpaRepository 상속 받아서 처리 하는 인터페이스 생성
 */
public interface MemberLogRepository extends JpaRepository<MemberLog, Integer> {
}
