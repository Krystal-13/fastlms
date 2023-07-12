package com.zerobase.fastlms.banner.dto;

import com.zerobase.fastlms.banner.entity.Banner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BannerDto {

    private Long id;

    String name;
    String filename;
    String urlFilename;
    LocalDateTime regDt;
    String linkUrl;
    String displayType;
    int sortValue;
    boolean displayYn;

    long totalCount;

    public static BannerDto of(Banner banner) {

        return BannerDto.builder()
                .name(banner.getName())
                .filename(banner.getFilename())
                .urlFilename(banner.getUrlFilename())
                .regDt(banner.getRegDt())
                .linkUrl(banner.getLinkUrl())
                .displayType(banner.getDisplayType())
                .sortValue(banner.getSortValue())
                .displayYn(banner.isDisplayYn())
                .build();
    }


}
