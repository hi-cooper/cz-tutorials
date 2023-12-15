package com.taiwii.axiosdemojava;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.taiwii.axiosdemojava.bizerror.BizError;
import com.taiwii.axiosdemojava.exception.BizException;
import com.taiwii.axiosdemojava.response.HttpApiResponse;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class BizExceptionHandler {
    @ResponseBody
    @ExceptionHandler(BizException.class)
    public HttpApiResponse<Object> handle(HttpServletResponse httpServletResponse, BizException ex) {
        httpServletResponse.setStatus(HttpStatus.OK.value());
        return HttpApiResponse.newErrorInstance(ex.getBizError());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public HttpApiResponse handle(HttpServletResponse httpServletResponse, Exception ex) {
        httpServletResponse.setStatus(HttpStatus.OK.value());
        if (ex.getCause() instanceof BizException) {
            BizException bex = (BizException) ex.getCause();
            return HttpApiResponse.newErrorInstance(bex.getBizError());
        }
        return HttpApiResponse.newErrorInstance(BizError.SYSTEM__UNCATCH_ERROR);
    }
}

