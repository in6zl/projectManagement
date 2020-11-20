package com.pactera.esc.config;

import com.pactera.esc.core.common.ApplicationProperties;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author luwen
 * @date 2019/6/10 16:17
 */
@Configuration
@AllArgsConstructor
public class MvcConfigurer implements WebMvcConfigurer {
    private final ApplicationProperties applicationProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        ApplicationProperties.UploadProperties uploadProperties = applicationProperties.getUpload();
        registry.addResourceHandler(uploadProperties.getPathPatterns())
            .addResourceLocations("file:" + uploadProperties.getFilePath());
    }
}
