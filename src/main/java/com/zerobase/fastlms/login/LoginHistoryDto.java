package com.zerobase.fastlms.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginHistoryDto {

    private long id;
    private String userId;
    private LocalDateTime loginDt;
    private String clientIp;
    private String userAgent;


}
