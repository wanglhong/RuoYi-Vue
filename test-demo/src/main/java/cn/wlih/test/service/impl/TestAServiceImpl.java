package cn.wlih.test.service.impl;

import cn.wlih.test.mapper.TestAMapper;
import cn.wlih.test.service.TestAService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TestAServiceImpl implements TestAService {

    private final TestAMapper testAMapper;

}
