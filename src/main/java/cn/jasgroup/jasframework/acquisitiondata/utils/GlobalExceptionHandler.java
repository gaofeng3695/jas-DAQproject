package cn.jasgroup.jasframework.acquisitiondata.utils;

import cn.jasgroup.framework.automation.base.utils.ResultVOUtil;
import cn.jasgroup.framework.data.exception.BusinessException;
import cn.jasgroup.framework.data.result.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * description: 控制器的异常通知处理Advice
 *
 * @author xiefayang
 * 2018/4/3 11:31
 */
//@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public BaseResult handlerBusinessException(BusinessException e) {
        logger.error("系统业务逻辑异常, 异常码[{}], 异常信息:{}", e.getCode(),  e.getMessage());
        return ResultVOUtil.ofError(e.getCode(), e.getMessage());
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResult handlerException(Exception e) {
        logger.error("系统内部错误, 异常码[{}], 异常信息:{}", HttpStatus.INTERNAL_SERVER_ERROR.value(),  e);
        return ResultVOUtil.ofError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }


    public ModelAndView handlerException() {
        return null;
    }
}
