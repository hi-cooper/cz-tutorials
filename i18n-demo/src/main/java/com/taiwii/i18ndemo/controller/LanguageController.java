package com.taiwii.i18ndemo.controller;

import com.taiwii.i18ndemo.helper.I18nHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/language")
public class LanguageController {

    @GetMapping("/test")
    public String test() {
        return I18nHelper.getMessage("language.zh_cn");
    }

    @GetMapping("/test2")
    public String test2() {
        return I18nHelper.getMessage("language.zh_cn", new String[] {"参数A", "参数B"});
    }
}
