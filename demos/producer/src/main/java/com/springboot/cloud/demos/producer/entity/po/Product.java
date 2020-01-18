package com.springboot.cloud.demos.producer.entity.po;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.springboot.cloud.common.web.entity.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BasePo {
    private String name;
    private String description;
    @TableLogic // 表示逻辑删除 delete->update
    private String deleted = "N";
}
