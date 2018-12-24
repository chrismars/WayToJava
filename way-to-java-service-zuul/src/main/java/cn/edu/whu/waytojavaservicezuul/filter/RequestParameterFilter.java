package cn.edu.whu.waytojavaservicezuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Jimmy
 * @version 2018/12/22
 * 请求参数过滤器
 */
@Component
public class RequestParameterFilter extends ZuulFilter {
    /**
     * 返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
     *
     * @return pre：路由之前
     * routing：路由之时
     * post： 路由之后
     * error：发送错误调用
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤的顺序
     * @return int
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 逻辑判断
     * @return 是否要过滤
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问
     * @return Object
     * @throws ZuulException 异常
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        Object accessPassword = request.getParameter("password");
        if (accessPassword == null) {
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            try {
                context.getResponse().getWriter().write("password cannot be null");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
