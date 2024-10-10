package top.yaohc.lease.common.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.yaohc.lease.common.result.Result;

/**
 * ClassName: GlobalExceptionHandler
 * Description:
 *
 * @Author 所谓独醉
 * @Create 2024/10/10 上午10:31
 * @Version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail();
    }
}
