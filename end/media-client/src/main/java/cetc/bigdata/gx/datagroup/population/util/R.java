package cetc.bigdata.gx.datagroup.population.util;

import com.alibaba.fastjson.JSONObject;


public class R extends JSONObject {
    public static R ok() {
        R r = new R();
        r.put("code", 200);
        r.put("msg", "ok");
        return r;
    }
    public static R error(ErrorEnum err) {
        R r = new R();
        r.put("code", err.getErrorCode());
        r.put("msg", err.getErrorMsg());
        return r;
    }

    public R data(Object data) {
        this.put("data", data);
        return this;
    }
}
