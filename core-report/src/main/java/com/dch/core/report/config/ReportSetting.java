package com.dch.core.report.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Class that contains parameter setting of Report configuration.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@Configuration
@PropertySource("classpath:config/report/core-report-config.properties")
@ConfigurationProperties(prefix = "core.report")
public class ReportSetting {

    private String identityPrefix = "REPORT";
    private String compilePath;
    private String outputPath;
    private String reportPath;

    /**
     * @return the identityPrefix
     */
    public String getIdentityPrefix() {
        return identityPrefix;
    }

    /**
     * @param identityPrefix the identityPrefix to set
     */
    public void setIdentityPrefix(String identityPrefix) {
        this.identityPrefix = identityPrefix;
    }

    /**
     * @return the compilePath
     */
    public String getCompilePath() {
        return compilePath;
    }

    /**
     * @param compilePath the compilePath to set
     */
    public void setCompilePath(String compilePath) {
        this.compilePath = compilePath;
    }

    /**
     * @return the outputPath
     */
    public String getOutputPath() {
        return outputPath;
    }

    /**
     * @param outputPath the outputPath to set
     */
    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    /**
     * @return the reportPath
     */
    public String getReportPath() {
        return reportPath;
    }

    /**
     * @param reportPath the reportPath to set
     */
    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }
}