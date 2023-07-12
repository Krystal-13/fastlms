package com.zerobase.fastlms.banner.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BannerInput {

    long id;

    String name;
    String filename;
    String urlFilename;
    LocalDateTime regDt;
    String linkUrl;
    String displayType;
    int sortValue;
    boolean displayYn;

    String idList;
}
