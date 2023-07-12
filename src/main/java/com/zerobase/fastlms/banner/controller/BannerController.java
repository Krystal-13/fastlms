package com.zerobase.fastlms.banner.controller;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.entity.DisplayType;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.model.BannerParam;
import com.zerobase.fastlms.banner.service.BannerService;
import com.zerobase.fastlms.course.controller.BaseController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BannerController  extends BaseController {

    private final BannerService bannerService;
    @GetMapping("/admin/banner/list.do")
    public String list(Model model, BannerParam parameter) {

        List<BannerDto> list = bannerService.list(parameter);

        long totalCount = 0;
        if (!CollectionUtils.isEmpty(list)) {
            totalCount = list.get(0).getTotalCount();
        }

        model.addAttribute("totalCount", totalCount);
        model.addAttribute("list", list);

        return "admin/banner/list";
    }

    @GetMapping(value = {"/admin/banner/add.do", "/admin/banner/edit.do"})
    public String add(Model model, HttpServletRequest request
                                     , BannerInput parameter) {

        boolean editMode = request.getRequestURI().contains("/edit.do");
        BannerDto detail = new BannerDto();

        if (editMode) {
            long id = parameter.getId();
            BannerDto existBanner = bannerService.getById(id);
            if (existBanner == null) {
                model.addAttribute("message", "배너정보가 존개하지 않습니다.");
                return "common/error";
            }
            detail = existBanner;

        }

        List<String> typeList = Stream.of(DisplayType.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        model.addAttribute("detail", detail);
        model.addAttribute("editMode", editMode);
        model.addAttribute("typeList", typeList);

        return "/admin/banner/add";
    }
    @PostMapping(value = {"/admin/banner/add.do", "/admin/banner/edit.do"})
    public String addSubmit(Model model, HttpServletRequest request
                             ,MultipartFile file ,BannerInput parameter) {

        String saveFilename = "";
        String urlFilename = "";

        if (file != null) {

            String originalFilename = file.getOriginalFilename();
            String baseLocalPath = "/Users/krystal/IdeaProjects/fastlms/files";
            String baseUrlPath = "/files";


            String[] arrFilename = bannerService.getNewSaveFile(baseLocalPath
                    , baseUrlPath
                    , originalFilename);

            saveFilename = arrFilename[0];
            urlFilename = arrFilename[1];

            try {
                File newFile = new File(saveFilename);
                FileCopyUtils.copy(file.getInputStream()
                        , new FileOutputStream(newFile));
            } catch (IOException e) {
                log.info(e.getMessage());
            }
        }

        parameter.setFilename(saveFilename);
        parameter.setUrlFilename(urlFilename);

        boolean editMode = request.getRequestURI().contains("/edit.do");

        if (editMode) {
            long id = parameter.getId();
            BannerDto existBanner = bannerService.getById(id);
            if (existBanner == null) {
                model.addAttribute("message", "배너정보가 존개하지 않습니다.");
                return "common/error";
            }

            boolean result = bannerService.set(parameter);
        } else {
            boolean result = bannerService.add(parameter);
        }

        return "redirect:/admin/banner/list.do";
    }

    @PostMapping("/admin/banner/delete.do")
    public String del(BannerInput parameter) {

        boolean result = bannerService.del(parameter.getIdList());

        return "redirect:/admin/banner/list.do";
    }

}