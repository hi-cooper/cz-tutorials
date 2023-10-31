package com.taiwii.i18ndemo.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * 自定义Locale header key为HEADER_LOCALE，值为zh_CN、en_US
 */
@Slf4j
@Component("localeResolver")
public class CustomLocaleResolver extends AcceptHeaderLocaleResolver {
    public final static String HEADER_LOCALE = "language";

    private static final Locale DEFAULT_LOCALE = Locale.SIMPLIFIED_CHINESE;
    private static final List SUPPORTED_LOCALES = Arrays.asList(Locale.US, Locale.SIMPLIFIED_CHINESE);


    public CustomLocaleResolver() {
        setDefaultLocale(DEFAULT_LOCALE);
        setSupportedLocales(SUPPORTED_LOCALES);
    }

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String lang = request.getHeader(HEADER_LOCALE);
        return getLocale(lang);
    }

    private Locale getLocale(String lang) {
        if (StringUtils.isEmpty(lang)) {
            return getDefaultLocale();
        }

        try {
            String[] items = lang.split("_");
            if (items.length != 2) {
                return getDefaultLocale();
            }
            return new Locale(items[0], items[1]);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return getDefaultLocale();
        }
    }
}