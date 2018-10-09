package cn.jasgroup.jasframework.acquisitiondata.utils;

import cn.jasgroup.framework.data.result.ErrorResult;
import cn.jasgroup.framework.data.result.SimpleResult;
import org.springframework.http.HttpStatus;

/**
 * description: 统一返回结果工具
 *
 * @author xiefayang
 * 2018/3/21 10:08
 */
public class ResultVOUtil {

    public static SimpleResult ofSuccess(Object data) {
        return new SimpleResult<>(data);
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


    public static ErrorResult ofEnum(ErrorEnum errorEnum) {
        return ofError(errorEnum.getCode(), errorEnum.getMessage());
    }

    public enum ErrorEnum {

        /**
         *
         */
        PARAMS_ERROR(4003, "参数错误"),

        ;

        private Integer code;

        private String message;


        ErrorEnum(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

}


