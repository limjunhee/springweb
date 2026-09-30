package example.day12;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor
public class MemberSerivce {
    @Autowired private final MemberRepository memberRepository;

    // 비크립트(암호화사용) 라이브러리 객체 주입
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // [1] 회원가입 = 등록 = C(Create)
    public boolean signUp(MemberDto memberDto){
        // 1. 등록할 정보들을 컨트롤러에게 받아
        // 2. 엔티티로 변환
        MemberEntity entity = memberDto.toEntity();

        // 3. 비밀번호는 암호화하기.
        String 암호문 = passwordEncoder.encode(memberDto.getMpwd()); // 입력받은 비밀번호 평문 -> 암호화함
        entity.setMpwd(암호문);

        // 4. 저장
        MemberEntity savedEntity = memberRepository.save(entity);

        if (savedEntity.getMno() >= 1) {
            return true;
        }

        return false;
    }


    // [2] 로그인(비크립트, 세션 동시 사용) = 조회 = R(Read)
    public MemberDto login( MemberDto memberDto){
        // 1. 로그인 시 입력받은 ID, 비밀번호를 컨트롤러에게 받기

        // 2. 입력받은 아이디가 존재하는지 검증하기 -> *메소드 쿼리 findByMid() 사용*
        MemberEntity memberEntity = memberRepository.findByMid(memberDto.getMid());
        if (memberEntity == null) { return null; }

        // 3. 아이디가 존재하면, [평문 비밀번호]와 [암호문 비밀번호] 비교하기
        // passwordEncoder.matches.("평문", "암호문")
        boolean 비밀번호일치 = passwordEncoder.matches(memberDto.getMpwd(), memberEntity.getMpwd());
        if (비밀번호일치 == false) { return null; }
        System.out.println(memberDto.getMid() + "님 로그인함");
        // 4. entity -> dto 변환, 로그인 전용 DTO가 있으면 더 좋음!
        return MemberDto.from(memberEntity);
    }
    
    // [3] 내정보 조회 (PK(회원번호) 응용하여 찾기)
    public MemberDto getMyInfo( Long mno ){
        // 1. 컨트롤러에게 조회할 회원번호를 받기
        // 2. findbyId 이용하여 회원번호 찾기
        Optional<MemberEntity> optional = memberRepository.findById(mno);
        if (optional.isPresent()) { // 3. 조회 결과가 존재한다면?
            MemberEntity memberEntity = optional.get(); // 엔티티 꺼내고
            return MemberDto.from(memberEntity);        // dto 변환한 후 반환
        }

        return null; // 4. 조회 결과가 없으면 null
    }
}
