package miao.videoquestionbank.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
    public static <T> T getSession(HttpServletRequest request, String key, Class<T> clazz) {
        HttpSession session = request.getSession();
        Object o = session.getAttribute(key);
        return o == null ? null :clazz.cast(o);
    }

    public static void setSessionAttribute(HttpServletRequest request, String key, Object value) {
        HttpSession session = request.getSession();
        session.setAttribute(key, value);
    }

    public static void removeSessionAttribute(HttpServletRequest request, String key) {
        HttpSession session = request.getSession();
        session.removeAttribute(key);
    }


    public static boolean hasAttribute(HttpServletRequest request, String key) {
        HttpSession session = request.getSession();
        Object o = session.getAttribute(key);
        return o != null && !EmptyCheckUtil.isEmpty(o.toString());
    }
}
