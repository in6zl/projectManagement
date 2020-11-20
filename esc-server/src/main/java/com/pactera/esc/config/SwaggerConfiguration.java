package com.pactera.esc.config;

import com.pactera.esc.core.common.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfiguration
 *
 * @author yayun
 * @date 2020/03/13
 */
@Configuration
@Slf4j
@EnableSwagger2
public class SwaggerConfiguration {
    private final ApplicationProperties.Swagger properties;

    public SwaggerConfiguration(ApplicationProperties applicationProperties) {
        this.properties = applicationProperties.getSwagger();
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.pactera.esc.controller"))
            .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title(properties.getTitle())
            .description(properties.getDescription())
            .contact(new Contact(properties.getContactName(), properties.getContactUrl(), properties.getContactEmail()))
            .version(properties.getVersion())
            .build();
    }
}
