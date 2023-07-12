package com.zerobase.fastlms.login;

import com.zerobase.fastlms.admin.dto.CategoryDto;
import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.entity.Category;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.member.entity.Member;
import com.zerobase.fastlms.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginHistoryService {

    private final LoginHistoryRepository loginHistoryRepository;
    private final MemberRepository memberRepository;
    private final LoginHistoryMapper loginHistoryMapper;
    public void saveLoginHistory(String userId, String clientIp, String userAgent) {

        LocalDateTime now = LocalDateTime.now();

        LoginHistory loginHistory = LoginHistory.builder()
                .userId(userId)
                .loginDt(now)
                .clientIp(clientIp)
                .userAgent(userAgent)
                .build();

        loginHistoryRepository.save(loginHistory);

        Optional<Member> optionalMember =
                memberRepository.findById(userId);
        if (!optionalMember.isPresent()) {
            log.info("[로그인 기록 실패]" + userId + " ::: " + clientIp + " ::: " + userAgent);
        }

        Member member = optionalMember.get();
        member.setLastLoginDt(now);
        memberRepository.save(member);

    }

    public List<LoginHistoryDto> list(MemberParam parameter) {

        List<LoginHistoryDto> list = loginHistoryMapper.selectList(parameter);

        return list;
    }

}
