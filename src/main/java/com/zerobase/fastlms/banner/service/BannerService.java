package com.zerobase.fastlms.banner.service;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.model.BannerParam;

import java.util.List;

public interface BannerService {

    List<BannerDto> list(BannerParam parameter);

    boolean add(BannerInput parameter);

    BannerDto getById(long id);

    String[] getNewSaveFile(String baseLocalPath
            , String baseUrlPath
            , String originalFilename);

    boolean set(BannerInput parameter);

    boolean del(String idList);
}
