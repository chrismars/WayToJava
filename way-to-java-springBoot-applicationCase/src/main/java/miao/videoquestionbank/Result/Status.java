package miao.videoquestionbank.Result;

public class Status {

    public static final int SUCCESS = 200;//成功返回

    public static final int PART_SUCCESS = 201;

    public static final int NOT_FOUND = 404;

    public static final int ERROR = 498;

    public static final int DUPLICATE = 406; //重复操作

    public static final int NO_AUTH=401;  //权限不足，未授权

    public static final int NOT_ENOUGH_PRIVILEGE = 403;

    public static final int EXPIRE = 405;// 过期

}
