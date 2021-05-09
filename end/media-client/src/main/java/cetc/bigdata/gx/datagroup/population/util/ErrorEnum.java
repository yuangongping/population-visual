package cetc.bigdata.gx.datagroup.population.util;

public enum ErrorEnum {
    E_400("400", "缺少必填参数或者请求方法错误"),
    E_403("403", "权限不足"),
    E_404("404", "请求路径不存在"),
    E_500("500", "服务器内部运行出错"),
    E_10000("10009", "账户已存在"),
    E_10001("20011", "登陆已过期,请重新登陆");

    private String errorCode;

    private String errorMsg;

    ErrorEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

}
