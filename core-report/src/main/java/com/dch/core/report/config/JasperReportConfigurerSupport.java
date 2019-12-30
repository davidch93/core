package com.dch.core.report.config;

import com.dch.core.report.provider.JasperReportExportProvider;
import com.dch.core.report.provider.JasperReportFillProvider;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.SimpleJasperReportsContext;
import net.sf.jasperreports.repo.FileRepositoryPersistenceServiceFactory;
import net.sf.jasperreports.repo.FileRepositoryService;
import net.sf.jasperreports.repo.PersistenceServiceFactory;
import net.sf.jasperreports.repo.RepositoryService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;
import java.util.Collections;

/**
 * Class that provide JasperReport configuration support. This configuration
 * using {@link JasperReportFillProvider} and {@link JasperReportExportProvider}.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@ComponentScan("com.dch.core.report")
@EnableConfigurationProperties(ReportSetting.class)
public class JasperReportConfigurerSupport {

    private final DataSource dataSource;
    private final ReportSetting reportSetting;

    public JasperReportConfigurerSupport(DataSource dataSource, ReportSetting reportSetting) {
        this.dataSource = dataSource;
        this.reportSetting = reportSetting;
    }

    /**
     * Bean of Jasper Report Fill Provider.
     *
     * @return {@link JasperReportFillProvider}
     */
    @Bean
    public JasperReportFillProvider jasperReportFillProvider() {
        return new JasperReportFillProvider(dataSource, reportSetting, jasperReportsContext());
    }

    /**
     * Bean of Jasper Report Export Provider.
     *
     * @return {@link JasperReportExportProvider}
     */
    @Bean
    public JasperReportExportProvider jasperReportExportProvider() {
        return new JasperReportExportProvider(reportSetting, jasperReportsContext());
    }

    /**
     * Default config JasperReportsContext.
     *
     * @return {@link JasperReportsContext}
     */
    protected JasperReportsContext jasperReportsContext() {
        SimpleJasperReportsContext context = new SimpleJasperReportsContext();
        context.setExtensions(RepositoryService.class,
                Collections.singletonList(new FileRepositoryService(context, reportSetting.getReportPath(), false)));
        context.setExtensions(PersistenceServiceFactory.class,
                Collections.singletonList(FileRepositoryPersistenceServiceFactory.getInstance()));
        return context;
    }
}
