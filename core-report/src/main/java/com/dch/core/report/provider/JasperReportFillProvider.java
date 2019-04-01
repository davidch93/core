package com.dch.core.report.provider;

import com.dch.core.report.config.ReportSetting;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.FileResolver;
import net.sf.jasperreports.engine.util.JRSaver;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that provide function to compile and fill JasperReport.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Jun 18, 2017
 * @since 1.0.0-SNAPSHOT
 */
public class JasperReportFillProvider {

    private final DataSource dataSource;
    private final ReportSetting reportSetting;

    public JasperReportFillProvider(DataSource dataSource, ReportSetting reportSetting) {
        this.dataSource = dataSource;
        this.reportSetting = reportSetting;
    }

    private final FileResolver FILE_RESOLVER = new FileResolver() {

        @Override
        public File resolveFile(String fileName) {
            try {
                return ResourceUtils.getFile(reportSetting.getReportPath() + fileName);
            } catch (FileNotFoundException e) {
                return null;
            }
        }
    };

    /**
     * Method used to compile JasperReport.
     *
     * @param reportFileName {@link String} JasperReport filename.
     * @return {@link JasperReport}
     * @throws JRException
     * @throws IOException
     */
    public JasperReport compileReport(String reportFileName) throws JRException, IOException {
        InputStream inputStream = getClass().getResourceAsStream(reportSetting.getReportPath().concat(reportFileName));
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JRSaver.saveObject(jasperReport, reportSetting.getCompilePath() + reportFileName.replace(".jrxml", ".jasper"));
        inputStream.close();
        return jasperReport;
    }

    /**
     * Method used to fill report with data source connection.
     *
     * @param jasperReport {@link JasperReport}
     * @param parameters   {@link Map}&lt;{@link String}, {@link Object}&gt;
     * @return {@link JasperPrint}
     * @throws JRException
     * @throws SQLException
     */
    public JasperPrint fillReportDataSourceConnection(JasperReport jasperReport, Map<String, Object> parameters)
            throws JRException, SQLException {
        if (parameters == null)
            parameters = new HashMap<String, Object>();
        parameters.put("REPORT_FILE_RESOLVER", this.FILE_RESOLVER);

        return JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());
    }

    /**
     * Method used to fill report with bean collection.
     *
     * @param jasperReport             {@link JasperReport}
     * @param parameters               {@link Map}&lt;{@link String}, {@link Object}&gt;
     * @param beanCollectionDataSource {@link JRBeanCollectionDataSource}
     * @return {@link JasperPrint}
     * @throws JRException
     * @throws SQLException
     */
    public JasperPrint fillReportJRBeanCollection(JasperReport jasperReport, Map<String, Object> parameters,
                                                  JRBeanCollectionDataSource beanCollectionDataSource) throws JRException, SQLException {
        if (parameters == null)
            parameters = new HashMap<String, Object>();
        parameters.put("REPORT_FILE_RESOLVER", this.FILE_RESOLVER);

        return JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
    }

    /**
     * Method used to prepare JasperReport with data source connection. This
     * method call compile report and fill report data source connection.
     *
     * @param reportFileName {@link String} JasperReport filename.
     * @param parameters     {@link Map}&lt;{@link String}, {@link Object}&gt;
     * @return {@link JasperPrint}
     * @throws JRException
     * @throws SQLException
     * @throws IOException
     */
    public JasperPrint prepareReportDataSourceConnection(String reportFileName, Map<String, Object> parameters)
            throws JRException, SQLException, IOException {
        return fillReportDataSourceConnection(compileReport(reportFileName), parameters);
    }

    /**
     * Method used to prepare JasperReport with bean collection. This method
     * call compile report and fill report bean collection.
     *
     * @param reportFileName           {@link String} JasperReport filename.
     * @param parameters               {@link Map}&lt;{@link String}, {@link Object}&gt;
     * @param beanCollectionDataSource {@link JRBeanCollectionDataSource}
     * @return {@link JasperPrint}
     * @throws JRException
     * @throws SQLException
     * @throws IOException
     */
    public JasperPrint prepareReportJRBeanCollection(String reportFileName, Map<String, Object> parameters,
                                                     JRBeanCollectionDataSource beanCollectionDataSource) throws JRException, SQLException, IOException {
        return fillReportJRBeanCollection(compileReport(reportFileName), parameters, beanCollectionDataSource);
    }
}
