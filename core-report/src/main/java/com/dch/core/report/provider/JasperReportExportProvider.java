package com.dch.core.report.provider;

import java.io.ByteArrayOutputStream;

import com.dch.core.report.config.ReportSetting;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

/**
 * Class that provide function to convert JasperReport to various format.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Jun 18, 2017
 */
public class JasperReportExportProvider {

	private final ReportSetting reportSetting;

	public JasperReportExportProvider(ReportSetting reportSetting) {
		this.reportSetting = reportSetting;
	}

	/**
	 * Method used to export JasperReport to CSV format.
	 * 
	 * @param jasperPrint
	 *            {@link JasperPrint}
	 * @param fileName
	 *            {@link String} Output filename.
	 * @throws JRException
	 */
	public void exportToCsv(JasperPrint jasperPrint, String fileName) throws JRException {
		JRCsvExporter exporter = new JRCsvExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleWriterExporterOutput(reportSetting.getOutputPath() + fileName));
		exporter.exportReport();
	}

	/**
	 * Method used to export JasperReport to CSV format.
	 * 
	 * @param jasperPrint
	 *            {@link JasperPrint}
	 * @param outputStream
	 *            {@link ByteArrayOutputStream}
	 * @throws JRException
	 */
	public void exportToCsv(JasperPrint jasperPrint, ByteArrayOutputStream outputStream) throws JRException {
		JRCsvExporter exporter = new JRCsvExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleWriterExporterOutput(outputStream));
		exporter.exportReport();
	}

	/**
	 * Method used to export JasperReport to DOCX format.
	 * 
	 * @param jasperPrint
	 *            {@link JasperPrint}
	 * @param fileName
	 *            {@link String} Output filename.
	 * @throws JRException
	 */
	public void exportToDocx(JasperPrint jasperPrint, String fileName) throws JRException {
		JRDocxExporter exporter = new JRDocxExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(reportSetting.getOutputPath() + fileName));
		exporter.exportReport();
	}

	/**
	 * Method used to export JasperReport to DOCX format.
	 * 
	 * @param jasperPrint
	 *            {@link JasperPrint}
	 * @param outputStream
	 *            {@link ByteArrayOutputStream}
	 * @throws JRException
	 */
	public void exportToDocx(JasperPrint jasperPrint, ByteArrayOutputStream outputStream) throws JRException {
		JRDocxExporter exporter = new JRDocxExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
		exporter.exportReport();
	}

	/**
	 * Method used to export JasperReport to HTML format.
	 * 
	 * @param jasperPrint
	 *            {@link JasperPrint}
	 * @param fileName
	 *            {@link String} Output filename.
	 * @throws JRException
	 */
	public void exportToHtml(JasperPrint jasperPrint, String fileName) throws JRException {
		HtmlExporter exporter = new HtmlExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleHtmlExporterOutput(reportSetting.getOutputPath() + fileName));
		exporter.exportReport();
	}

	/**
	 * Method used to export JasperReport to HTML format.
	 * 
	 * @param jasperPrint
	 *            {@link JasperPrint}
	 * @param outputStream
	 *            {@link ByteArrayOutputStream}
	 * @throws JRException
	 */
	public void exportToHtml(JasperPrint jasperPrint, ByteArrayOutputStream outputStream) throws JRException {
		HtmlExporter exporter = new HtmlExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleHtmlExporterOutput(outputStream));
		exporter.exportReport();
	}

	/**
	 * Method used to export JasperReport to ODT format.
	 * 
	 * @param jasperPrint
	 *            {@link JasperPrint}
	 * @param fileName
	 *            {@link String} Output filename.
	 * @throws JRException
	 */
	public void exportToOdt(JasperPrint jasperPrint, String fileName) throws JRException {
		JROdtExporter exporter = new JROdtExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(reportSetting.getOutputPath() + fileName));
		exporter.exportReport();
	}

	/**
	 * Method used to export JasperReport to ODT format.
	 * 
	 * @param jasperPrint
	 *            {@link JasperPrint}
	 * @param outputStream
	 *            {@link ByteArrayOutputStream}
	 * @throws JRException
	 */
	public void exportToOdt(JasperPrint jasperPrint, ByteArrayOutputStream outputStream) throws JRException {
		JROdtExporter exporter = new JROdtExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
		exporter.exportReport();
	}

	/**
	 * Method used to export JasperReport to PDF format.
	 * 
	 * @param jasperPrint
	 *            {@link JasperPrint}
	 * @param fileName
	 *            {@link String} Output filename.
	 * @param author
	 *            {@link String} Author name.
	 * @throws JRException
	 */
	public void exportToPdf(JasperPrint jasperPrint, String fileName, String author) throws JRException {
		SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
		reportConfig.setSizePageToContent(true);
		reportConfig.setForceLineBreakPolicy(false);

		SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
		exportConfig.setMetadataAuthor(author);
		exportConfig.setEncrypted(true);
		exportConfig.setAllowedPermissionsHint("PRINTING");

		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(reportSetting.getOutputPath() + fileName));
		exporter.setConfiguration(reportConfig);
		exporter.setConfiguration(exportConfig);
		exporter.exportReport();
	}

	/**
	 * Method used to export JasperReport to PDF format.
	 * 
	 * @param jasperPrint
	 *            {@link JasperPrint}
	 * @param outputStream
	 *            {@link ByteArrayOutputStream}
	 * @param author
	 *            {@link String} Author name.
	 * @throws JRException
	 */
	public void exportToPdf(JasperPrint jasperPrint, ByteArrayOutputStream outputStream, String author)
			throws JRException {
		SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
		reportConfig.setSizePageToContent(true);
		reportConfig.setForceLineBreakPolicy(false);

		SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
		exportConfig.setMetadataAuthor(author);
		exportConfig.setEncrypted(true);
		exportConfig.setAllowedPermissionsHint("PRINTING");

		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
		exporter.setConfiguration(reportConfig);
		exporter.setConfiguration(exportConfig);
		exporter.exportReport();
	}

	/**
	 * Method used to export JasperReport to RTF format.
	 * 
	 * @param jasperPrint
	 *            {@link JasperPrint}
	 * @param fileName
	 *            {@link String} Output filename.
	 * @throws JRException
	 */
	public void exportToRtf(JasperPrint jasperPrint, String fileName) throws JRException {
		JRRtfExporter exporter = new JRRtfExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleWriterExporterOutput(reportSetting.getOutputPath() + fileName));
		exporter.exportReport();
	}

	/**
	 * Method used to export JasperReport to RTF format.
	 * 
	 * @param jasperPrint
	 *            {@link JasperPrint}
	 * @param outputStream
	 *            {@link ByteArrayOutputStream}
	 * @throws JRException
	 */
	public void exportToRtf(JasperPrint jasperPrint, ByteArrayOutputStream outputStream) throws JRException {
		JRRtfExporter exporter = new JRRtfExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleWriterExporterOutput(outputStream));
		exporter.exportReport();
	}

	/**
	 * Method used to export JasperReport to XLSX format.
	 * 
	 * @param jasperPrint
	 *            {@link JasperPrint}
	 * @param fileName
	 *            {@link String} Output filename.
	 * @param sheetName
	 *            {@link String} Sheet name.
	 * @throws JRException
	 */
	public void exportToXlsx(JasperPrint jasperPrint, String fileName, String sheetName) throws JRException {
		SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
		reportConfig.setSheetNames(new String[] { sheetName });

		JRXlsxExporter exporter = new JRXlsxExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(reportSetting.getOutputPath() + fileName));
		exporter.setConfiguration(reportConfig);
		exporter.exportReport();
	}

	/**
	 * Method used to export JasperReport to XLSX format.
	 * 
	 * @param jasperPrint
	 *            {@link JasperPrint}
	 * @param outputStream
	 *            {@link ByteArrayOutputStream}
	 * @param sheetName
	 *            {@link String} Sheet name.
	 * @throws JRException
	 */
	public void exportToXlsx(JasperPrint jasperPrint, ByteArrayOutputStream outputStream, String sheetName)
			throws JRException {
		SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
		reportConfig.setSheetNames(new String[] { sheetName });

		JRXlsxExporter exporter = new JRXlsxExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
		exporter.setConfiguration(reportConfig);
		exporter.exportReport();
	}
}
