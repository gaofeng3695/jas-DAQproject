package cn.jasgroup.jasframework.acquisitiondata.utils;

import cn.jasgroup.framework.data.result.ErrorResult;
import cn.jasgroup.framework.data.result.SimpleResult;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;

/**
 * description: 统一返回结果工具
 *
 * @author xiefayang
 * 2018/3/21 10:08
 */
public class ResultVOUtil {

    public static void main(String[] args) {
        Gson gson = new Gson();
        System.out.println(gson.toJson(ofSuccess()));
    }

    public static SimpleResult ofSuccess(Object data) {
        return new SimpleResult(data);
    }

    public static SimpleResult ofSuccess() {
        return ofSuccess(null);
    }

    public static ErrorResult ofError(Integer code, String message) {
        return ofError(String.valueOf(code), message);
    }

    public static ErrorResult ofError(String code, String message) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setStatus(-1);
        errorResult.setCode(code);
        errorResult.setMsg(message);
        return errorResult;
    }


    /**
     * @param httpStatus 借用下HTTP状态码
     */
    public static SimpleResult ofStatus(HttpStatus httpStatus) {
        return new SimpleResult(httpStatus.is2xxSuccessful() ? 1:-1,
                String.valueOf(httpStatus.value()), httpStatus.getReasonPhrase());
    }

}