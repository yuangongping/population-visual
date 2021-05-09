package cetc.bigdata.gx.datagroup.population.config.exception;

import cetc.bigdata.gx.datagroup.population.util.R;
import cetc.bigdata.gx.datagroup.population.util.ErrorEnum;
import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: 系统错误拦截, 主要是针对404的错误
 */

@Controller
public class MainsiteErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";

    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public JSONObject handleError() {
        return R.error(ErrorEnum.E_404);
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}
