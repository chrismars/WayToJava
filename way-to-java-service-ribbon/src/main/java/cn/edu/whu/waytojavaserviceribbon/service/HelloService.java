package cn.edu.whu.waytojavaserviceribbon.service;

/**
 * @author Jimmy
 * @version 2018/12/21
 * 测试服务接口
 */

public interface HelloService {
    /**
     * 打招呼
     * @param name 姓名
     * @return 打招呼内容
     */
    String sayHello(String name);
}
