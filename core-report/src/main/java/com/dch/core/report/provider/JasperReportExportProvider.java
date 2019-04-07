package com.dch.core.report.provider;

import com.dch.core.report.config.ReportSetting;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;

import java.io.ByteArrayOutputStream;

/**
 * Class that provide function to convert JasperReport to various format.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public class JasperReportExportProvider {

    private final ReportSetting reportSetting;
    private final JasperReportsContext reportsContext;

    public JasperReportExportProvider(ReportSetting reportSetting, JasperReportsContext reportsContext) {
        this.reportSetting = reportSetting;
        this.reportsContext = reportsContext;
    }

    /**
     * Method used to export JasperReport to CSV format.
     *
     * @param jasperPrint {@link JasperPrint}
     * @param fileName    {@code String} Output filename.
     * @throws JRException If error occurred while exporting report to CSV.
     */
    public void exportToCsv(JasperPrint jasperPrint, String fileName) throws JRException {
        JRCsvExporter exporter = new JRCsvExporter(reportsContext);
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleWriterExporterOutput(reportSetting.getOutputPath() + fileName));
        exporter.exportReport();
    }

    /**
     * Method used to export JasperReport to CSV format with OutputStream.
     *
     * @param jasperPrint {@link JasperPrint}
     * @return {@link ByteArrayOutputStream} Output stream.
     * @throws JRException If error occurred while exporting report to CSV.
     */
    public ByteArrayOutputStream exportToCsv(JasperPrint jasperPrint) throws JRException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JRCsvExporter exporter = new JRCsvExporter(reportsContext);
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleWriterExporterOutput(outputStream));
        exporter.exportReport();
        return outputStream;
    }

    /**
     * Method used to export JasperReport to DOCX format.
     *
     * @param jasperPrint {@link JasperPrint}
     * @param fileName    {@code String} Output filename.
     * @throws JRException If error occurred while exporting report to Docx.
     */
    public void exportToDocx(JasperPrint jasperPrint, String fileName) throws JRException {
        JRDocxExporter exporter = new JRDocxExporter(reportsContext);
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(reportSetting.getOutputPath() + fileName));
        exporter.exportReport();
    }

    /**
     * Method used to export JasperReport to DOCX format with OutputStream.
     *
     * @param jasperPrint {@link JasperPrint}
     * @return {@link ByteArrayOutputStream} Output stream.
     * @throws JRException If error occurred while exporting report to Docx.
     */
    public ByteArrayOutputStream exportToDocx(JasperPrint jasperPrint) throws JRException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JRDocxExporter exporter = new JRDocxExporter(reportsContext);
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
        exporter.exportReport();
        return outputStream;
    }

    /**
     * Method used to export JasperReport to HTML format.
     *
     * @param jasperPrint {@link JasperPrint}
     * @param fileName    {@code String} Output filename.
     * @throws JRException If error occurred while exporting report to HTML.
     */
    public void exportToHtml(JasperPrint jasperPrint, String fileName) throws JRException {
        HtmlExporter exporter = new HtmlExporter(reportsContext);
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(reportSetting.getOutputPath() + fileName));
        exporter.exportReport();
    }

    /**
     * Method used to export JasperReport to HTML format with OutputStream.
     *
     * @param jasperPrint {@link JasperPrint}
     * @return {@link ByteArrayOutputStream} Output stream.
     * @throws JRException If error occurred while exporting report to HTML.
     */
    public ByteArrayOutputStream exportToHtml(JasperPrint jasperPrint) throws JRException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        HtmlExporter exporter = new HtmlExporter(reportsContext);
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(outputStream));
        exporter.exportReport();
        return outputStream;
    }

    /**
     * Method used to export JasperReport to ODT format.
     *
     * @param jasperPrint {@link JasperPrint}
     * @param fileName    {@code String} Output filename.
     * @throws JRException If error occurred while exporting report to ODT.
     */
    public void exportToOdt(JasperPrint jasperPrint, String fileName) throws JRException {
        JROdtExporter exporter = new JROdtExporter(reportsContext);
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(reportSetting.getOutputPath() + fileName));
        exporter.exportReport();
    }

    /**
     * Method used to export JasperReport to ODT format with OutputStream.
     *
     * @param jasperPrint {@link JasperPrint}
     * @return {@link ByteArrayOutputStream} Output stream.
     * @throws JRException If error occurred while exporting report to ODT.
     */
    public ByteArrayOutputStream exportToOdt(JasperPrint jasperPrint) throws JRException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JROdtExporter exporter = new JROdtExporter(reportsContext);
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
        exporter.exportReport();
        return outputStream;
    }

    /**
     * Method used to export JasperReport to PDF format.
     *
     * @param jasperPrint {@link JasperPrint}
     * @param fileName    {@code String} Output filename.
     * @param author      {@code String} Author name.
     * @throws JRException If error occurred while exporting report to PDF.
     */
    public void exportToPdf(JasperPrint jasperPrint, String fileName, String author) throws JRException {
        SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
        reportConfig.setSizePageToContent(true);
        reportConfig.setForceLineBreakPolicy(false);

        SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
        exportConfig.setMetadataAuthor(author);
        exportConfig.setEncrypted(true);
        exportConfig.setAllowedPermissionsHint("PRINTING");

        JRPdfExporter exporter = new JRPdfExporter(reportsContext);
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(reportSetting.getOutputPath() + fileName));
        exporter.setConfiguration(reportConfig);
        exporter.setConfiguration(exportConfig);
        exporter.exportReport();
    }

    /**
     * Method used to export JasperReport to PDF format with OutputStream.
     *
     * @param jasperPrint {@link JasperPrint}
     * @param author      {@code String} Author name.
     * @return {@link ByteArrayOutputStream} Output stream.
     * @throws JRException If error occurred while exporting report to PDF.
     */
    public ByteArrayOutputStream exportToPdf(JasperPrint jasperPrint, String author) throws JRException {
        SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
        reportConfig.setSizePageToContent(true);
        reportConfig.setForceLineBreakPolicy(false);

        SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
        exportConfig.setMetadataAuthor(author);
        exportConfig.setEncrypted(true);
        exportConfig.setAllowedPermissionsHint("PRINTING");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JRPdfExporter exporter = new JRPdfExporter(reportsContext);
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
        exporter.setConfiguration(reportConfig);
        exporter.setConfiguration(exportConfig);
        exporter.exportReport();
        return outputStream;
    }

    /**
     * Method used to export JasperReport to RTF format.
     *
     * @param jasperPrint {@link JasperPrint}
     * @param fileName    {@code String} Output filename.
     * @throws JRException If error occurred while exporting report to RTF.
     */
    public void exportToRtf(JasperPrint jasperPrint, String fileName) throws JRException {
        JRRtfExporter exporter = new JRRtfExporter(reportsContext);
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleWriterExporterOutput(reportSetting.getOutputPath() + fileName));
        exporter.exportReport();
    }

    /**
     * Method used to export JasperReport to RTF format with OutputStream.
     *
     * @param jasperPrint {@link JasperPrint}
     * @return {@link ByteArrayOutputStream} Output stream.
     * @throws JRException If error occurred while exporting report to RTF.
     */
    public ByteArrayOutputStream exportToRtf(JasperPrint jasperPrint) throws JRException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JRRtfExporter exporter = new JRRtfExporter(reportsContext);
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleWriterExporterOutput(outputStream));
        exporter.exportReport();
        return outputStream;
    }

    /**
     * Method used to export JasperReport to XLSX format.
     *
     * @param jasperPrint {@link JasperPrint}
     * @param fileName    {@code String} Output filename.
     * @param sheetName   {@code String} Sheet name.
     * @throws JRException If error occurred while exporting report to XLSX.
     */
    public void exportToXlsx(JasperPrint jasperPrint, String fileName, String sheetName) throws JRException {
        SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
        reportConfig.setSheetNames(new String[]{sheetName});

        JRXlsxExporter exporter = new JRXlsxExporter(reportsContext);
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(reportSetting.getOutputPath() + fileName));
        exporter.setConfiguration(reportConfig);
        exporter.exportReport();
    }

    /**
     * Method used to export JasperReport to XLSX format with OutputStream.
     *
     * @param jasperPrint {@link JasperPrint}
     * @param sheetName   {@code String} Sheet name.
     * @return {@link ByteArrayOutputStream} Output stream.
     * @throws JRException If error occurred while exporting report to XLSX.
     */
    public ByteArrayOutputStream exportToXlsx(JasperPrint jasperPrint, String sheetName) throws JRException {
        SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
        reportConfig.setSheetNames(new String[]{sheetName});

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JRXlsxExporter exporter = new JRXlsxExporter(reportsContext);
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
        exporter.setConfiguration(reportConfig);
        exporter.exportReport();
        return outputStream;
    }
}
