package com.ruoyi.framework.aspectj;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import com.ruoyi.common.utils.PageUtils;

/**
 * 清理分页处理信息
 * 确保每个接口执行完毕后清理 ThreadLocal 中的分页数据，防止内存泄漏
 *
 * @author wlih
 */
@Aspect
@Component
public class ClearPageAspect {

    /**
     * 切入点：所有接口方法（通过方法映射注解判断）
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping) || @annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.PutMapping) || @annotation(org.springframework.web.bind.annotation.DeleteMapping) || @annotation(org.springframework.web.bind.annotation.RequestMapping) || @annotation(org.springframework.web.bind.annotation.PatchMapping)")
    public void controllerPointcut() {
    }

    /**
     * 后置通知：接口执行完毕后清理分页数据
     * 无论接口正常返回还是抛出异常，都会执行
     */
    @After("controllerPointcut()")
    public void clearPage() {
        PageUtils.clearPage();
    }
}
