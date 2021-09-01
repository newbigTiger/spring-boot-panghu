package com.example.demo.Utils;

import com.google.common.base.Predicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Configuration //配置类
@EnableSwagger2
public class SwaggerAutoConfiguration {
    @Value("${swagger.api.title:}")
    private String swaggerApiTitle;
    @Value("${swagger.api.scan.packages}")
    private String swaggerApiScanPackages;

    @Bean
    public Docket api() {
        List<Predicate<RequestHandler>> apiSelectors = Collections.singletonList(RequestHandlerSelectors.any());
        if (StringUtils.isNotBlank(swaggerApiScanPackages)) {
            List<Predicate<RequestHandler>> antPathSelectors = Arrays.stream(swaggerApiScanPackages.split(","))
                    .filter(StringUtils::isNotBlank)
                    .map(RequestHandlerSelectors::basePackage)
                    .collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(antPathSelectors)) {
                apiSelectors = antPathSelectors;
            }

        }
        // 使用spring-web兼容postman
        ApiSelectorBuilder apiSelectorBuilder = new Docket(DocumentationType.SWAGGER_2).select();
        apiSelectors.forEach(apiSelectorBuilder::apis);

        return apiSelectorBuilder.build()
                .apiInfo(new ApiInfoBuilder()
                        .title(swaggerApiTitle)
                        .build());
    }
}
