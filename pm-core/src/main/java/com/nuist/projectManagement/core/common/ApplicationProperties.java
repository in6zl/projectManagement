package com.pactera.esc.core.common;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ApplicationProperties
 *
 * @author yayun
 * @date 2020/03/13
 */
@ConfigurationProperties(prefix = "application")
@Getter
@Setter
public class ApplicationProperties {
    private final Swagger swagger = new Swagger();
    private final UploadProperties upload = new UploadProperties();

    @Getter
    @Setter
    public static class Swagger {

        private String title = "End-Supply-Chain Api";

        private String description = "Api";

        private String version = "0.0.1";

        private String termsOfServiceUrl;

        private String contactName = "王亚运";

        private String contactUrl;

        private String contactEmail = "yayun.wang2@pactera.com";

        private String license;

        private String licenseUrl;

        private String defaultIncludePattern = "/api/.*";

        private String host;

        private String[] protocols;

        private boolean useDefaultResponseMessages;

    }

    @Getter
    @Setter
    public static class UploadProperties {
        private String filePath;
        private String pathPatterns;
        private String webPath;
    }
}
