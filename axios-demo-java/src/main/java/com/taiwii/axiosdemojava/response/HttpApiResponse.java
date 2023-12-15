package com.taiwii.axiosdemojava.response;

import com.taiwii.axiosdemojava.bizerror.BizError;
import com.taiwii.axiosdemojava.bizerror.IBizError;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
public class HttpApiResponse<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 466609991138097833L;

    //region 属性
    /** traceId。全局唯一 */
    private String traceId;
    /** 错误编码 */
    private long code;
    /** 提示信息 */
    private String message;
    /** 扩展提示信息 */
    private String extendMessage;
    /** 数据 */
    private T data;
    //endregion

    //region 构造方法
    private HttpApiResponse() {
    }

    /**
     * 构造一个成功且带数据的返回实例
     *
     * @param data
     */
    private HttpApiResponse(T data) {
        this.traceId = UUID.randomUUID().toString();
        this.code = BizError.SUCCESS.getCode();
        this.message = BizError.SUCCESS.getMessage();
        this.data = data;
    }

    /**
     * @param bizError
     */
    private HttpApiResponse(IBizError bizError) {
        this.traceId = UUID.randomUUID().toString();
        this.code = bizError.getCode();
        this.message = bizError.getMessage();
    }

    /**
     * 构建包含 extendMessage 的对象
     *
     * @param bizError
     * @param extendMessage
     */
    private HttpApiResponse(IBizError bizError, String extendMessage) {
        this(bizError);
        this.extendMessage = extendMessage;
    }
    //endregion

    //region methods

    /**
     * 构造一个成功且不带数据的实例
     *
     * @param <T>
     * @return
     */
    public static <T> HttpApiResponse<T> newSuccessInstance() {
        return new HttpApiResponse<>(BizError.SUCCESS);
    }

    /**
     * 构造一个成功且带数据的实例
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> HttpApiResponse<T> newSuccessInstance(T data) {
        return new HttpApiResponse<>(data);
    }

    /**
     * 构造一个失败的实例
     *
     * @param bizError
     * @return
     */
    public static <T> HttpApiResponse<T> newErrorInstance(IBizError bizError) {
        return new HttpApiResponse<>(bizError);
    }

    public static <T> HttpApiResponse<T> newErrorInstance(IBizError bizError, String extendMessage) {
        return new HttpApiResponse<>(bizError, extendMessage);
    }

    /**
     * 检查是否成功
     *
     * @return
     */
    public boolean success() {
        return this.code == BizError.SUCCESS.getCode();
    }
    //endregion
}
