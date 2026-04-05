package cn.wlih.test.model;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TestA extends BaseEntity {

    /**
     * 主键ID
     */
    private Long id;
    /**
     * 描述
     */
    private String description;

}
