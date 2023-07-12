package com.zerobase.fastlms.main.controller;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.model.BannerParam;
import com.zerobase.fastlms.banner.service.BannerService;
import com.zerobase.fastlms.components.MailComponents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MailComponents mailComponents;
    private final BannerService bannerService;
    @RequestMapping("/")
    public String index(Model model, BannerParam parameter) {

        List<BannerDto> list = bannerService.list(parameter);

        model.addAttribute("list", list);

        return "index";
    }

    @RequestMapping("/error/denied")
    public String errorDenied() {

        return "error/denied";
    }
}
