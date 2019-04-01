package com.dch.core.report.service;

import com.dch.core.datastatic.ReportType;
import com.dch.core.report.details.ReportDetails;

import java.io.ByteArrayOutputStream;

/**
 * Generic Manager used to provide common report methods. Extend this interface
 * if you want typesafe (no casting necessary) managers for your report.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Jun 19, 2017
 * @since 1.0.0-SNAPSHOT
 */
public interface JasperReportService {

    /**
     * Method used to create JasperReport using Data Source connection.
     *
     * @param reportType    {@link ReportType}
     * @param reportDetails {@link reportDetails}
     */
    void createReportWithDataSourceConnection(ReportType reportType, ReportDetails reportDetails);

    /**
     * Method used to create JasperReport using JasperReport Bean Collection
     * Data Source.
     *
     * @param reportType    {@link ReportType}
     * @param reportDetails {@link ReportDetails}
     */
    void createReportWithJRBeanCollectionDataSource(ReportType reportType, ReportDetails reportDetails);

    /**
     * Method used to create stream JasperReport using Data Source connection.
     *
     * @param reportType    {@link ReportType}
     * @param reportDetails {@link ReportDetails}
     * @return {@link ByteArrayOutputStream} Output stream.
     */
    ByteArrayOutputStream createStreamReportWithDataSourceConnection(ReportType reportType,
                                                                     ReportDetails reportDetails);

    /**
     * Method used to create stream JasperReport using JasperReport Bean
     * Collection Data Source.
     *
     * @param reportType    {@link ReportType}
     * @param reportDetails {@link ReportDetails}
     * @return {@link ByteArrayOutputStream} Output stream.
     */
    ByteArrayOutputStream createStreamReportWithJRBeanCollectionDataSource(ReportType reportType,
                                                                           ReportDetails reportDetails);
}
