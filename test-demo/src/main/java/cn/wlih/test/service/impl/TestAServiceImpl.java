package cn.wlih.test.service.impl;

import cn.wlih.test.mapper.TestAMapper;
import cn.wlih.test.model.TestA;
import cn.wlih.test.service.TestAService;
import com.ruoyi.common.core.service.BaseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TestAServiceImpl extends BaseServiceImpl<TestAMapper, TestA> implements TestAService {

}
