package com.dch.core.report.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Class that contains parameter setting of Report configuration.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "core.report")
public class ReportSetting {

    private String identityPrefix = "REPORT";
    private String compilePath;
    private String outputPath;
    private String reportPath;

    /**
     * Get identity prefix.
     *
     * @return the identity prefix
     */
    public String getIdentityPrefix() {
        return identityPrefix;
    }

    /**
     * Set identity prefix.
     *
     * @param identityPrefix the identity prefix
     */
    public void setIdentityPrefix(String identityPrefix) {
        this.identityPrefix = identityPrefix;
    }

    /**
     * Get compile path.
     *
     * @return the compile path
     */
    public String getCompilePath() {
        return compilePath;
    }

    /**
     * Set compile path.
     *
     * @param compilePath the compile path
     */
    public void setCompilePath(String compilePath) {
        this.compilePath = compilePath;
    }

    /**
     * Get output path.
     *
     * @return the output path
     */
    public String getOutputPath() {
        return outputPath;
    }

    /**
     * Set output path.
     *
     * @param outputPath the output path
     */
    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    /**
     * Get report path.
     *
     * @return the report path
     */
    public String getReportPath() {
        return reportPath;
    }

    /**
     * Set report path.
     *
     * @param reportPath the report path
     */
    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }
}