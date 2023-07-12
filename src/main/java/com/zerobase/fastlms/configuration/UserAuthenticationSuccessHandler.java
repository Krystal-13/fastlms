package com.zerobase.fastlms.configuration;

import com.zerobase.fastlms.login.LoginHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class UserAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final LoginHistoryService loginHistoryService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request
                                        , HttpServletResponse response
                                        , Authentication authentication)
                                        throws IOException, ServletException {

        String userId = authentication.getName();
        String clientIp = request.getRemoteHost();
        String userAgent = request.getHeader("user-agent");
        loginHistoryService.saveLoginHistory(userId, clientIp, userAgent);

        log.info("[로그인 정보]" + userId + " ::: " + clientIp + " ::: " + userAgent);

        super.onAuthenticationSuccess(request, response, authentication);

    }
}
