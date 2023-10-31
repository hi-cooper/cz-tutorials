package com.taiwii.i18ndemo.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Slf4j
@Component
public class I18nHelper {

    private static MessageSource messageSource;

    public I18nHelper(MessageSource messageSource) {
        I18nHelper.messageSource = messageSource;
    }

    public static String getMessage(Locale locale, String key, Object... args) {
        String message = null;
        try {
            message = messageSource.getMessage(key, args, locale);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message = key;
        }
        return message;
    }

    public static String getMessage(String key, Object... args) {
        return getMessage(LocaleContextHolder.getLocale(), key, args);
    }
}
