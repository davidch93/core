package com.dch.core.report.provider;

import com.dch.core.report.config.ReportSetting;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRSaver;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Map;

/**
 * Class that provide function to compile and fill JasperReport.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public class JasperReportFillProvider {

    private final DataSource dataSource;
    private final ReportSetting reportSetting;
    private final JasperReportsContext reportsContext;

    public JasperReportFillProvider(DataSource dataSource, ReportSetting reportSetting,
                                    JasperReportsContext reportsContext) {
        this.dataSource = dataSource;
        this.reportSetting = reportSetting;
        this.reportsContext = reportsContext;
    }

    /**
     * Compile JasperReport.
     *
     * @param fileName the JasperReport filename.
     * @return the {@link JasperReport}
     * @throws JRException If error occurred while compiling report.
     * @throws IOException If error occurred while creating input stream.
     */
    private JasperReport compileReport(String fileName) throws JRException, IOException {
        try (InputStream is = new ClassPathResource(reportSetting.getReportPath().concat(fileName)).getInputStream()) {
            JasperReport jasperReport = JasperCompileManager.getInstance(reportsContext).compile(is);
            JRSaver.saveObject(jasperReport, reportSetting.getCompilePath().concat(fileName.replace(".jrxml",
                    ".jasper")));
            return jasperReport;
        }
    }

    /**
     * Fill report with data source connection.
     *
     * @param jasperReport the {@link JasperReport}
     * @param parameters   the {@link Map} of report parameters.
     * @return the {@link JasperPrint}
     * @throws JRException  If error occurred while creating Jasper report.
     * @throws SQLException If error occurred while connecting to data source.
     */
    private JasperPrint fillReportDataSourceConnection(JasperReport jasperReport, Map<String, Object> parameters)
            throws JRException, SQLException {
        return JasperFillManager.getInstance(reportsContext).fill(jasperReport, parameters, dataSource.getConnection());
    }

    /**
     * Fill report with bean collection.
     *
     * @param jasperReport             the {@link JasperReport}
     * @param parameters               the {@link Map} of report parameters.
     * @param beanCollectionDataSource the {@link JRBeanCollectionDataSource}
     * @return the {@link JasperPrint}
     * @throws JRException If error occurred while creating Jasper report.
     */
    private JasperPrint fillReportJRBeanCollection(JasperReport jasperReport, Map<String, Object> parameters,
                                                   JRBeanCollectionDataSource beanCollectionDataSource) throws JRException {
        return JasperFillManager.getInstance(reportsContext).fill(jasperReport, parameters, beanCollectionDataSource);
    }

    /**
     * Prepare JasperReport with data source connection.
     * This method call compile report and fill report data source connection.
     *
     * @param reportFileName the JasperReport filename.
     * @param parameters     the {@link Map} of report parameters.
     * @return the {@link JasperPrint}
     * @throws JRException  If error occurred while creating Jasper report.
     * @throws SQLException If error occurred while connecting to data source.
     * @throws IOException  If error occurred while creating input stream.
     */
    public JasperPrint prepareReportDataSourceConnection(String reportFileName, Map<String, Object> parameters)
            throws JRException, SQLException, IOException {
        return fillReportDataSourceConnection(compileReport(reportFileName), parameters);
    }

    /**
     * Prepare JasperReport with bean collection. This method call compile report and fill report bean collection.
     *
     * @param reportFileName           the JasperReport filename.
     * @param parameters               the {@link Map} of report parameters.
     * @param beanCollectionDataSource the {@link JRBeanCollectionDataSource}
     * @return {@link JasperPrint}
     * @throws JRException If error occurred while creating Jasper report.
     * @throws IOException If error occurred while creating input stream.
     */
    public JasperPrint prepareReportJRBeanCollection(String reportFileName, Map<String, Object> parameters,
                                                     JRBeanCollectionDataSource beanCollectionDataSource) throws JRException, IOException {
        return fillReportJRBeanCollection(compileReport(reportFileName), parameters, beanCollectionDataSource);
    }
}
