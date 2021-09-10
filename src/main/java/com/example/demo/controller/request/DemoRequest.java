package com.example.demo.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(description = "测试请求")
public class DemoRequest {

    @ApiModelProperty(value = "测试必填项目 ", required = true)
    private String shit;

    @ApiModelProperty(value = "测试非必填项目 ")
    private String fuck;

}
