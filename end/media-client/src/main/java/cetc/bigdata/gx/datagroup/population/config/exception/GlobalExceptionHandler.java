package cetc.bigdata.gx.datagroup.population.config.exception;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import cetc.bigdata.gx.datagroup.population.util.R;
import cetc.bigdata.gx.datagroup.population.util.ErrorEnum;


/***
 * 全局异常处理类只需要在类上标注@RestControllerAdvice，
 * 并在处理相应异常的方法上使用@ExceptionHandler注解，
 * 写明处理哪个异常即可。
 */


@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    // 处理 form data方式调用接口校验失败抛出的异常
    @ExceptionHandler(BindException.class)
    public R bindExceptionHandler(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream()
                .map(o -> o.getDefaultMessage())
                .collect(Collectors.toList());
        return R.error( ErrorEnum.E_400).data(collect);
    }

    //  处理 json 请求体调用接口校验失败抛出的异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException  e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream()
                .map(o -> o.getDefaultMessage())
                .collect(Collectors.toList());
        return R.error( ErrorEnum.E_400).data(collect);
    }

    // 处理单个参数校验失败抛出的异常
    @ExceptionHandler(ConstraintViolationException.class)
    public R constraintViolationExceptionHandler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        List<String> collect = constraintViolations.stream()
                .map(o -> o.getMessage())
                .collect(Collectors.toList());
        return R.error( ErrorEnum.E_400).data(collect);
    }

    @ExceptionHandler(value = Exception.class)
    public R defaultErrorHandler(HttpServletRequest req, Exception e) {
        String errorPosition = "";
        //如果错误堆栈信息存在
        if (e.getStackTrace().length > 0) {
            StackTraceElement element = e.getStackTrace()[0];
            String fileName = element.getFileName() == null ? "未找到错误文件" : element.getFileName();
            int lineNumber = element.getLineNumber();
            errorPosition = fileName + ":" + lineNumber;
        }
        logger.error("异常", e);
        JSONObject errorObject = new JSONObject();
        errorObject.put("errorLocation", e.toString() + "    错误位置:" + errorPosition);
        return R.error( ErrorEnum.E_500).data(errorObject);
    }

    /**
     * GET/POST请求方法错误的拦截器
     * 而且发生在进入controller之前,上面的拦截器拦截不到这个错误
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R httpRequestMethodHandler() {
        JSONObject errorObject = new JSONObject();
        errorObject.put("data", "请求方法类型错误");
        return R.error(ErrorEnum.E_400).data(errorObject);
    }

    /**
        代码内部运行错误
     */
    @ExceptionHandler(RuntimeException.class)
    public R commonJsonExceptionHandler(RuntimeException e) {
        String errorPosition = "";
        //如果错误堆栈信息存在
        if (e.getStackTrace().length > 0) {
            StackTraceElement element = e.getStackTrace()[0];
            String fileName = element.getFileName() == null ? "未找到错误文件" : element.getFileName();
            int lineNumber = element.getLineNumber();
            errorPosition = fileName + ":" + lineNumber;
        }
        logger.error("异常", e);
        JSONObject errorObject = new JSONObject();
        errorObject.put("errorLocation", e.toString() + "    错误位置:" + errorPosition);
        return R.error( ErrorEnum.E_500).data(errorObject);
    }
}
