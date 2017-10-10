package com.dch.core.report.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dch.core.report.provider.JasperReportExportProvider;
import com.dch.core.report.provider.JasperReportFillProvider;

/**
 * Class that provide JasperReport configuration support. This configuration
 * using {@link JasperReportFillProvider} and {@link JasperReportExportProvider}
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Jun 18, 2017
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
