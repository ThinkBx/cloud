package com.cloud.base;

import com.cloud.util.ToolMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回结果类
 *
 * @author fjj
 * @date 2020/6/3 22:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResult {

    /**
     * 状态码
     */
    public int code = BaseStatus.STATUS_SUCCESS;
    /**
     * 成功为success，其他为失败原因
     */
    public String message = ToolMessage.message("i18n.operation.success");
    /**
     * 数据结果集
     */
    public Object data = null;

    public static BaseResult success() {
        return new BaseResult();
    }

    public static BaseResult success(String message) {
        return new BaseResult(BaseStatus.STATUS_SUCCESS, message, null);
    }

    public static BaseResult success(Object data) {
        return new BaseResult(BaseStatus.STATUS_SUCCESS, ToolMessage.message("i18n.operation.success"), data);
    }

    public static BaseResult success(String message, Object data) {
        return new BaseResult(BaseStatus.STATUS_SUCCESS, message, data);
    }

    public static BaseResult fail() {
        return new BaseResult(BaseStatus.STATUS_FAIL, ToolMessage.message("i18n.operation.fail"), null);
    }

    public static BaseResult fail(String message) {
        return new BaseResult(BaseStatus.STATUS_FAIL, message, null);
    }

    public static BaseResult fail(Object data) {
        return new BaseResult(BaseStatus.STATUS_FAIL, ToolMessage.message("i18n.operation.fail"), data);
    }

    public static BaseResult fail(int code, String message) {
        return new BaseResult(code, message, null);
    }

    public static void main(String[] args) {
        int code = 0;
        String message = "";
        Object data = null;
        BaseResult baseResult = new BaseResult(code, message, data);
    }

    public static BaseResult fail(int code, String message, Object data) {
        return new BaseResult(code, message, data);
    }

}
