package miao.videoquestionbank.Util;

public class EmptyCheckUtil {
    public static boolean isEmpty(String s){
        return s == null || s.trim().equals("");
    }

    public static boolean isEmpty(Object o){
        if(o == null)
            return true;
        if(o instanceof Integer)
            return (Integer) o == 0;
        else{
            return isEmpty(o.toString());
        }
    }
}
