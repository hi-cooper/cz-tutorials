package com.taiwii.axiosdemojava.exception;

import com.taiwii.axiosdemojava.bizerror.IBizError;
import lombok.Data;

import java.io.Serial;

@Data
public class BizException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 6577165386030770043L;
    /** 错误代码 */
    private IBizError bizError;
    /** 扩展信息 */
    private String extendMessage;

    public BizException(IBizError bizError) {
        super(String.format("[code=%s, message=%s]", bizError.getCode(), bizError.getMessage()));
        this.bizError = bizError;
    }

    public BizException(IBizError bizError, String extendMessage) {
        super(String.format("[code=%s, message=%s, extendMessage=%s]", bizError.getCode(), bizError.getMessage()));
        this.bizError = bizError;
        this.extendMessage = extendMessage;
    }
}

