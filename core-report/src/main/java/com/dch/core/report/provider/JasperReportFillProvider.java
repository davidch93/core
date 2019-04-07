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
     * Method used to compile JasperReport.
     *
     * @param fileName {@code String} JasperReport filename.
     * @return {@link JasperReport}
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
     * Method used to fill report with data source connection.
     *
     * @param jasperReport {@link JasperReport}
     * @param parameters   {@link Map} Report parameters.
     * @return {@link JasperPrint}
     * @throws JRException  If error occurred while creating Jasper report.
     * @throws SQLException If error occurred while connecting to data source.
     */
    private JasperPrint fillReportDataSourceConnection(JasperReport jasperReport, Map<String, Object> parameters)
            throws JRException, SQLException {
        return JasperFillManager.getInstance(reportsContext).fill(jasperReport, parameters, dataSource.getConnection());
    }

    /**
     * Method used to fill report with bean collection.
     *
     * @param jasperReport             {@link JasperReport}
     * @param parameters               {@link Map} Report parameters.
     * @param beanCollectionDataSource {@link JRBeanCollectionDataSource}
     * @return {@link JasperPrint}
     * @throws JRException If error occurred while creating Jasper report.
     */
    private JasperPrint fillReportJRBeanCollection(JasperReport jasperReport, Map<String, Object> parameters,
                                                   JRBeanCollectionDataSource beanCollectionDataSource) throws JRException {
        return JasperFillManager.getInstance(reportsContext).fill(jasperReport, parameters, beanCollectionDataSource);
    }

    /**
     * Method used to prepare JasperReport with data source connection. This
     * method call compile report and fill report data source connection.
     *
     * @param reportFileName {@code String} JasperReport filename.
     * @param parameters     {@link Map} Report parameters.
     * @return {@link JasperPrint}
     * @throws JRException  If error occurred while creating Jasper report.
     * @throws SQLException If error occurred while connecting to data source.
     * @throws IOException  If error occurred while creating input stream.
     */
    public JasperPrint prepareReportDataSourceConnection(String reportFileName, Map<String, Object> parameters)
            throws JRException, SQLException, IOException {
        return fillReportDataSourceConnection(compileReport(reportFileName), parameters);
    }

    /**
     * Method used to prepare JasperReport with bean collection. This method
     * call compile report and fill report bean collection.
     *
     * @param reportFileName           {@code String} JasperReport filename.
     * @param parameters               {@link Map} Report parameters.
     * @param beanCollectionDataSource {@link JRBeanCollectionDataSource}
     * @return {@link JasperPrint}
     * @throws JRException If error occurred while creating Jasper report.
     * @throws IOException If error occurred while creating input stream.
     */
    public JasperPrint prepareReportJRBeanCollection(String reportFileName, Map<String, Object> parameters,
                                                     JRBeanCollectionDataSource beanCollectionDataSource) throws JRException, IOException {
        return fillReportJRBeanCollection(compileReport(reportFileName), parameters, beanCollectionDataSource);
    }
}
