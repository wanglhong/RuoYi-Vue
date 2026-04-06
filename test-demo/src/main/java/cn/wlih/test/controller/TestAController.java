package cn.wlih.test.controller;

import cn.wlih.test.model.TestA;
import cn.wlih.test.service.TestAService;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.PageUtils;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/testA")
public class TestAController {

    private final TestAService testAService;

    @Anonymous
    @GetMapping("/save")
    public TableDataInfo test() {
        TestA testA = new TestA();
//        testA.setId(1L);
        testA.setDescription("新增测试：" + DateUtils.getTime());
        // TODO 测试
        testAService.save(testA);
//        Page<TestA> page = testAService.page();
//        testAService.pageAs();
        PageUtils.startPage();
        List<TestA> list = testAService.list();
        return TableDataInfo.of(list);
    }

}
