package cn.wlih.test.controller;

import cn.wlih.test.model.TestA;
import cn.wlih.test.service.impl.TestAServiceImpl;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/testA")
public class TestAController {

    private final TestAServiceImpl testAService;

    @Anonymous
    @GetMapping("/save")
    public AjaxResult test() {
        TestA testA = new TestA();
        testA.setDescription("新增测试：" + DateUtils.getTime());
        // TODO 测试
//        testAService.save(testA);
        return AjaxResult.success(testA);
    }

}
