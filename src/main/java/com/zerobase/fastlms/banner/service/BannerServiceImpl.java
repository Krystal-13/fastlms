package com.zerobase.fastlms.banner.service;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.banner.mapper.BannerMapper;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.model.BannerParam;
import com.zerobase.fastlms.banner.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {

    private final BannerRepository bannerRepository;
    private final BannerMapper bannerMapper;

    @Override
    public String[] getNewSaveFile(String baseLocalPath
            , String baseUrlPath
            , String originalFilename) {

        LocalDate now = LocalDate.now();
        String[] dirs = {
                String.format("%s/%d/", baseLocalPath
                        , now.getYear()),
                String.format("%s/%d/%02d/", baseLocalPath
                        , now.getYear()
                        , now.getMonthValue()),
                String.format("%s/%d/%02d/%02d/", baseLocalPath
                        , now.getYear()
                        , now.getMonthValue()
                        , now.getDayOfMonth())
        };

        String urlDir = String.format("%s/%d/%02d/%02d/", baseUrlPath
                , now.getYear()
                , now.getMonthValue()
                , now.getDayOfMonth());

        for(String dir : dirs) {
            File file = new File(dir);
            if (!file.isDirectory()) {
                file.mkdir();
            }
        }

        String fileExtension = "";
        if (originalFilename != null) {
            int dotPos = originalFilename.lastIndexOf(".");
            if (dotPos > -1) {
                fileExtension = originalFilename.substring(dotPos + 1);
            }
        }

        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        String newFilename = String.format("%s%s", dirs[2], uuid);
        String newUrlFilename = String.format("%s%s", urlDir, uuid);
        if (fileExtension.length() > 0) {
            newFilename += "." + fileExtension;
            newUrlFilename += "." + fileExtension;
        }

        return new String[]{newFilename, newUrlFilename};
    }

    @Override
    public boolean set(BannerInput parameter) {

        Optional<Banner> optionalBanner =
                            bannerRepository.findById(parameter.getId());
        if (!optionalBanner.isPresent()) {
            return false;
        }

        Banner banner = optionalBanner.get();
        banner.setName(parameter.getName());
        banner.setFilename(parameter.getFilename());
        banner.setUrlFilename(parameter.getUrlFilename());
        banner.setLinkUrl(parameter.getLinkUrl());
        banner.setSortValue(parameter.getSortValue());
        banner.setDisplayType(parameter.getDisplayType().replaceAll(",", "").trim());
        banner.setDisplayYn(parameter.isDisplayYn());
        bannerRepository.save(banner);

        return true;
    }

    @Override
    public boolean del(String idList) {

        if (idList != null && idList.length() > 0) {
            String[] ids = idList.split(",");
            for(String x : ids) {
                long id = 0L;
                try {
                    id = Long.parseLong(x);
                } catch (Exception e) {
                }

                if (id > 0) {
                    bannerRepository.deleteById(id);
                }
            }
        }

        return true;
    }


    @Override
    public List<BannerDto> list(BannerParam parameter) {

        long totalCount = bannerMapper.selectListCount(parameter);

        List<BannerDto> list = bannerMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            for(BannerDto x : list) {
                x.setTotalCount(totalCount);
            }
        }

        return list;
    }

    @Override
    public boolean add(BannerInput parameter) {

        Banner banner = new Banner();
        banner.setName(parameter.getName());
        banner.setFilename(parameter.getFilename());
        banner.setUrlFilename(parameter.getUrlFilename());
        banner.setLinkUrl(parameter.getLinkUrl());
        banner.setSortValue(parameter.getSortValue());
        banner.setRegDt(LocalDateTime.now());
        banner.setDisplayType(parameter.getDisplayType().replaceAll(",", "").trim());
        banner.setDisplayYn(parameter.isDisplayYn());
        bannerRepository.save(banner);

        return true;
    }


    @Override
    public BannerDto getById(long id) {

        return bannerRepository.findById(id)
                .map(BannerDto::of)
                .orElse(null);
    }
}
