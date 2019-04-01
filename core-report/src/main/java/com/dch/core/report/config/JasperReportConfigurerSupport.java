package com.dch.core.report.config;

import com.dch.core.report.provider.JasperReportExportProvider;
import com.dch.core.report.provider.JasperReportFillProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Class that provide JasperReport configuration support. This configuration
 * using {@link JasperReportFillProvider} and {@link JasperReportExportProvider}
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Jun 18, 2017
 * @since 1.0.0-SNAPSHOT
 */
@Configuration
public class JasperReportConfigurerSupport {

    @Autowired
    protected DataSource dataSource;

    @Autowired
    protected ReportSetting reportSetting;

    /**
     * Bean of Jasper Report Fill Provider.
     *
     * @return {@link JasperReportFillProvider}
     */
    @Bean
    public JasperReportFillProvider jasperReportFillProvider() {
        return new JasperReportFillProvider(dataSource, reportSetting);
    }

    /**
     * Ban of Jasper Report Export Provider.
     *
     * @return {@link JasperReportExportProvider}
     */
    @Bean
    public JasperReportExportProvider jasperReportExportProvider() {
        return new JasperReportExportProvider(reportSetting);
    }
}
