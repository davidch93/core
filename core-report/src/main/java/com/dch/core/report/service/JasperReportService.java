package com.dch.core.report.service;

import com.dch.core.datastatic.ReportType;
import com.dch.core.report.details.ReportDetails;

import java.io.ByteArrayOutputStream;

/**
 * Generic Manager used to provide common report methods. Extend this interface
 * if you want typesafe (no casting necessary) managers for your report.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public interface JasperReportService {

    /**
     * Create JasperReport using Data Source connection.
     *
     * @param reportType    the {@link ReportType} such as CSV, DOCX, HTML, ODT, PDF, RTF, XLSX.
     * @param reportDetails the {@link ReportDetails}
     */
    void createReportWithDataSourceConnection(ReportType reportType, ReportDetails reportDetails);

    /**
     * Create JasperReport using JasperReport Bean Collection Data Source.
     *
     * @param reportType    the {@link ReportType} such as CSV, DOCX, HTML, ODT, PDF, RTF, XLSX.
     * @param reportDetails the {@link ReportDetails}
     */
    void createReportWithJRBeanCollectionDataSource(ReportType reportType, ReportDetails reportDetails);

    /**
     * Create stream JasperReport using Data Source connection.
     *
     * @param reportType    the {@link ReportType} such as CSV, DOCX, HTML, ODT, PDF, RTF, XLSX.
     * @param reportDetails the {@link ReportDetails}
     * @return the {@link ByteArrayOutputStream Output stream}.
     */
    ByteArrayOutputStream createStreamReportWithDataSourceConnection(ReportType reportType,
                                                                     ReportDetails reportDetails);

    /**
     * Create stream JasperReport using JasperReport Bean Collection Data Source.
     *
     * @param reportType    the {@link ReportType} such as CSV, DOCX, HTML, ODT, PDF, RTF, XLSX.
     * @param reportDetails the {@link ReportDetails}
     * @return the {@link ByteArrayOutputStream Output stream}.
     */
    ByteArrayOutputStream createStreamReportWithJRBeanCollectionDataSource(ReportType reportType,
                                                                           ReportDetails reportDetails);
}
