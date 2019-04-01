package com.dch.core.report.service.impl;

import com.dch.core.datastatic.ReportType;
import com.dch.core.report.config.ReportSetting;
import com.dch.core.report.details.ReportDetails;
import com.dch.core.report.exception.ReportException;
import com.dch.core.report.provider.JasperReportExportProvider;
import com.dch.core.report.provider.JasperReportFillProvider;
import com.dch.core.report.service.JasperReportService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;

/**
 * This class serves as the Base class for all other Managers - namely to hold
 * common report methods that they might all use. You should only need to extend
 * this class when your require custom report service. This class implements
 * {@link JasperReportService}.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Jun 19, 2017
 * @since 1.0.0-SNAPSHOT
 */
@ComponentScan("com.dch.core.report")
public abstract class JasperReportServiceImpl implements JasperReportService {

    protected static final Logger LOGGER = LoggerFactory.getLogger(JasperReportServiceImpl.class);

    @Autowired
    protected JasperReportFillProvider jasperReportFillProvider;

    @Autowired
    protected JasperReportExportProvider jasperReportExportProvider;

    @Autowired
    protected ReportSetting reportSetting;

    @Override
    public void createReportWithDataSourceConnection(ReportType reportType, ReportDetails reportDetails) {
        try {
            JasperPrint jasperPrint = jasperReportFillProvider.prepareReportDataSourceConnection(
                    reportDetails.getReportFileName(), reportDetails.getParameters());
            exportReport(reportType, reportDetails, jasperPrint);
        } catch (JRException | SQLException | IOException ex) {
            LOGGER.error(String.format("[%s] %s", reportSetting.getIdentityPrefix(), ex.getMessage()), ex);
            throw new ReportException("Error occured when create report!", ex);
        }
    }

    @Override
    public void createReportWithJRBeanCollectionDataSource(ReportType reportType, ReportDetails reportDetails) {
        try {
            JasperPrint jasperPrint = jasperReportFillProvider.prepareReportJRBeanCollection(
                    reportDetails.getReportFileName(), reportDetails.getParameters(),
                    reportDetails.getBeanCollectionDataSource());
            exportReport(reportType, reportDetails, jasperPrint);
        } catch (JRException | SQLException | IOException ex) {
            LOGGER.error(String.format("[%s] %s", reportSetting.getIdentityPrefix(), ex.getMessage()), ex);
            throw new ReportException("Error occured when create report!", ex);
        }
    }

    @Override
    public ByteArrayOutputStream createStreamReportWithDataSourceConnection(ReportType reportType,
                                                                            ReportDetails reportDetails) {
        try {
            JasperPrint jasperPrint = jasperReportFillProvider.prepareReportDataSourceConnection(
                    reportDetails.getReportFileName(), reportDetails.getParameters());

            return exportStreamReport(reportType, reportDetails, jasperPrint);
        } catch (JRException | SQLException | IOException ex) {
            LOGGER.error(String.format("[%s] %s", reportSetting.getIdentityPrefix(), ex.getMessage()), ex);
            throw new ReportException("Error occured when create report!", ex);
        }
    }

    @Override
    public ByteArrayOutputStream createStreamReportWithJRBeanCollectionDataSource(ReportType reportType,
                                                                                  ReportDetails reportDetails) {
        try {
            JasperPrint jasperPrint = jasperReportFillProvider.prepareReportJRBeanCollection(
                    reportDetails.getReportFileName(), reportDetails.getParameters(),
                    reportDetails.getBeanCollectionDataSource());

            return exportStreamReport(reportType, reportDetails, jasperPrint);
        } catch (JRException | SQLException | IOException ex) {
            LOGGER.error(String.format("[%s] %s", reportSetting.getIdentityPrefix(), ex.getMessage()), ex);
            throw new ReportException("Error occured when create report!", ex);
        }
    }

    /**
     * Method used to export report to desired format.
     *
     * @param reportType    {@link ReportType}
     * @param reportDetails {@link ReportDetails}
     * @param jasperPrint   {@link JasperPrint}
     * @throws JRException
     */
    private void exportReport(ReportType reportType, ReportDetails reportDetails, JasperPrint jasperPrint)
            throws JRException {
        switch (reportType) {
            case CSV:
                jasperReportExportProvider.exportToCsv(jasperPrint, reportDetails.getOutputFileName());
                break;
            case DOCX:
                jasperReportExportProvider.exportToDocx(jasperPrint, reportDetails.getOutputFileName());
                break;
            case HTML:
                jasperReportExportProvider.exportToHtml(jasperPrint, reportDetails.getOutputFileName());
                break;
            case ODT:
                jasperReportExportProvider.exportToOdt(jasperPrint, reportDetails.getOutputFileName());
                break;
            case PDF:
                jasperReportExportProvider.exportToPdf(jasperPrint, reportDetails.getOutputFileName(),
                        reportDetails.getAuthor());
                break;
            case RTF:
                jasperReportExportProvider.exportToRtf(jasperPrint, reportDetails.getOutputFileName());
                break;
            case XLSX:
                jasperReportExportProvider.exportToXlsx(jasperPrint, reportDetails.getOutputFileName(),
                        reportDetails.getSheetName());
                break;
        }
    }

    /**
     * Method used to export stream report to desired format.
     *
     * @param reportType    {@link ReportType}
     * @param reportDetails {@link ReportDetails}
     * @param jasperPrint   {@link JasperPrint}
     * @return {@link ByteArrayOutputStream} Output stream.
     * @throws JRException
     */
    private ByteArrayOutputStream exportStreamReport(ReportType reportType, ReportDetails reportDetails,
                                                     JasperPrint jasperPrint) throws JRException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        switch (reportType) {
            case CSV:
                jasperReportExportProvider.exportToCsv(jasperPrint, outputStream);
                break;
            case DOCX:
                jasperReportExportProvider.exportToDocx(jasperPrint, outputStream);
                break;
            case HTML:
                jasperReportExportProvider.exportToHtml(jasperPrint, outputStream);
                break;
            case ODT:
                jasperReportExportProvider.exportToOdt(jasperPrint, outputStream);
                break;
            case PDF:
                jasperReportExportProvider.exportToPdf(jasperPrint, outputStream, reportDetails.getAuthor());
                break;
            case RTF:
                jasperReportExportProvider.exportToRtf(jasperPrint, outputStream);
                break;
            case XLSX:
                jasperReportExportProvider.exportToXlsx(jasperPrint, outputStream, reportDetails.getSheetName());
                break;
        }

        return outputStream;
    }
}
