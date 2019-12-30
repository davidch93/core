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
import org.springframework.context.annotation.ComponentScan;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;

/**
 * This class serves as the Base class for all other Managers - namely to hold
 * common report methods that they might all use. You should only need to extend
 * this class when your require custom report service.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see JasperReportService
 * @since 1.0.0
 */
@ComponentScan("com.dch.core.report")
public abstract class JasperReportServiceImpl implements JasperReportService {

    protected static final Logger logger = LoggerFactory.getLogger(JasperReportServiceImpl.class);

    protected final JasperReportFillProvider jasperReportFillProvider;
    protected final JasperReportExportProvider jasperReportExportProvider;
    protected final ReportSetting reportSetting;

    protected JasperReportServiceImpl(JasperReportFillProvider jasperReportFillProvider,
                                      JasperReportExportProvider jasperReportExportProvider,
                                      ReportSetting reportSetting) {
        this.jasperReportFillProvider = jasperReportFillProvider;
        this.jasperReportExportProvider = jasperReportExportProvider;
        this.reportSetting = reportSetting;
    }

    @Override
    public void createReportWithDataSourceConnection(ReportType reportType, ReportDetails reportDetails) {
        try {
            JasperPrint jasperPrint = jasperReportFillProvider.prepareReportDataSourceConnection(
                    reportDetails.getReportFileName(), reportDetails.getParameters());
            exportReport(reportType, reportDetails, jasperPrint);
        } catch (JRException | SQLException | IOException ex) {
            logger.error(String.format("[%s] Error occurred when creating report with DataSource connection!",
                    reportSetting.getIdentityPrefix()), ex);
            throw new ReportException("Error occurred when creating report with DataSource connection!", ex);
        }
    }

    @Override
    public void createReportWithJRBeanCollectionDataSource(ReportType reportType, ReportDetails reportDetails) {
        try {
            JasperPrint jasperPrint = jasperReportFillProvider.prepareReportJRBeanCollection(
                    reportDetails.getReportFileName(), reportDetails.getParameters(),
                    reportDetails.getBeanCollectionDataSource());
            exportReport(reportType, reportDetails, jasperPrint);
        } catch (JRException | IOException ex) {
            logger.error(String.format("[%s] Error occurred when creating report with JRBean Collection!",
                    reportSetting.getIdentityPrefix()), ex);
            throw new ReportException("Error occurred when creating report with JRBean Collection!", ex);
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
            logger.error(String.format("[%s] Error occurred when creating stream report with DataSource connection!",
                    reportSetting.getIdentityPrefix()), ex);
            throw new ReportException("Error occurred when creating stream report with DataSource connection!", ex);
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
        } catch (JRException | IOException ex) {
            logger.error(String.format("[%s] Error occurred when creating stream report with JRBean Collection!",
                    reportSetting.getIdentityPrefix()), ex);
            throw new ReportException("Error occurred when creating stream report with JRBean Collection!", ex);
        }
    }

    /**
     * Export report to desired format.
     *
     * @param reportType    the {@link ReportType} such as CSV, DOCX, HTML, ODT, PDF, RTF, XLSX
     * @param reportDetails the {@link ReportDetails}
     * @param jasperPrint   the {@link JasperPrint}
     * @throws JRException If error occurred while exporting report.
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
     * Export stream report to desired format.
     *
     * @param reportType    the {@link ReportType} such as CSV, DOCX, HTML, ODT, PDF, RTF, XLSX
     * @param reportDetails the {@link ReportDetails}
     * @param jasperPrint   the {@link JasperPrint}
     * @return the {@link ByteArrayOutputStream Output stream}.
     * @throws JRException If error occurred while exporting stream report.
     */
    private ByteArrayOutputStream exportStreamReport(ReportType reportType, ReportDetails reportDetails,
                                                     JasperPrint jasperPrint) throws JRException {
        switch (reportType) {
            case CSV:
                return jasperReportExportProvider.exportToCsv(jasperPrint);
            case DOCX:
                return jasperReportExportProvider.exportToDocx(jasperPrint);
            case HTML:
                return jasperReportExportProvider.exportToHtml(jasperPrint);
            case ODT:
                return jasperReportExportProvider.exportToOdt(jasperPrint);
            case PDF:
                return jasperReportExportProvider.exportToPdf(jasperPrint, reportDetails.getAuthor());
            case RTF:
                return jasperReportExportProvider.exportToRtf(jasperPrint);
            case XLSX:
                return jasperReportExportProvider.exportToXlsx(jasperPrint, reportDetails.getSheetName());
            default:
                throw new ReportException(String.format("[%s] Unsupported report type: '%s'",
                        reportSetting.getIdentityPrefix(), reportType));
        }
    }
}
