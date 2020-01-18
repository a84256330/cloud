package com.springboot.cloud.demos.producer.entity.form;

import com.springboot.cloud.common.web.entity.form.BaseForm;
import com.springboot.cloud.demos.producer.entity.po.Product;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel// swagger注解 标记解析类
@Data
public class ProductForm extends BaseForm<Product> {

    @NotBlank(message = "产品名称不能为空")
    @ApiModelProperty(value = "产品名称")// swagger注解 配合@ApiModel标记属性
    private String name;

    @ApiModelProperty(value = "产品描述")// swagger注解 配合@ApiModel标记属性
    private String description;
}
