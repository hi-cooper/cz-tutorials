package com.taiwii.i18ndemo.config;

import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component("messageSource")
public class MemoryMessageSource extends AbstractMessageSource {
    private static final Map<String, String> MESSAGES = new HashMap<>();
    private static final Map<String, String> EN_CN = new HashMap<>();

    static {
        MESSAGES.put("en_US:language.zh_cn", "Chinese{0}, {1}");
        MESSAGES.put("en_US:language.en_us", "US");
        MESSAGES.put("zh_CN:language.zh_cn", "简体中文{0}, {1}");
        MESSAGES.put("zh_CN:language.en_us", "英语（美国）");
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String message = MESSAGES.get(String.format("%s_%s:%s", locale.getLanguage(), locale.getCountry(), code));
        return new MessageFormat(message, locale);
    }
}