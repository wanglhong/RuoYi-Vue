package cn.wlih.test;

import com.ruoyi.RuoYiApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 若依框架测试项目-启动程序
 */
@MapperScan({"cn.wlih.test.mapper"})
@SpringBootApplication
public class RuoYiTestApplication {

    public static void main(String[] args) {
        RuoYiApplication.run(RuoYiTestApplication.class, args);
    }

}
