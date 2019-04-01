package com.dch.core.report.details;

import com.dch.core.datastatic.builder.GenericBuilder;
import com.dch.core.util.TextUtil;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.Map;

/**
 * Class that provides various parameters for the report.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Jun 19, 2017
 * @since 1.0.0-SNAPSHOT
 */
public class ReportDetails {

    private final String reportFileName;
    private final String outputFileName;
    private final String author;
    private final String sheetName;
    private final JRBeanCollectionDataSource beanCollectionDataSource;
    private final Map<String, Object> parameters;

    private ReportDetails(ReportDetailsBuilder builder) {
        this.reportFileName = builder.reportFileName;
        this.outputFileName = builder.outputFileName;
        this.author = builder.author;
        this.sheetName = builder.sheetName;
        this.beanCollectionDataSource = builder.beanCollectionDataSource;
        this.parameters = builder.parameters;
    }

    /**
     * @return the reportFileName
     */
    public String getReportFileName() {
        return reportFileName;
    }

    /**
     * @return the outputFileName
     */
    public String getOutputFileName() {
        return outputFileName;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @return the sheetName
     */
    public String getSheetName() {
        return sheetName;
    }

    /**
     * @return the beanCollectionDataSource
     */
    public JRBeanCollectionDataSource getBeanCollectionDataSource() {
        return beanCollectionDataSource;
    }

    /**
     * @return the parameters
     */
    public Map<String, Object> getParameters() {
        return parameters;
    }

    /**
     * Method used to get report details builder.
     *
     * @param reportFileName Jasper report file name.
     * @param outputFileName Output report file name.
     * @return {@link ReportDetailsBuilder}
     */
    public static ReportDetailsBuilder getReportDetailsBuilder(String reportFileName, String outputFileName) {
        return new ReportDetailsBuilder(reportFileName, outputFileName);
    }

    /**
     * Class builder used to create {@link ReportDetails}. This class implements
     * {@link GenericBuilder}.
     *
     * @author David.Christianto
     * @version 1.0.0-SNAPSHOT
     * @updated Jun 19, 2017
     * @since 1.0.0-SNAPSHOT
     */
    public static class ReportDetailsBuilder implements GenericBuilder<ReportDetails> {

        private String reportFileName;
        private String outputFileName;
        private String author;
        private String sheetName;
        private JRBeanCollectionDataSource beanCollectionDataSource;
        private Map<String, Object> parameters;

        public ReportDetailsBuilder(String reportFileName, String outputFileName) {
            this.reportFileName = reportFileName;
            this.outputFileName = outputFileName;
        }

        /**
         * @param reportFileName the reportFileName to set
         */
        public ReportDetailsBuilder setReportFileName(String reportFileName) {
            this.reportFileName = reportFileName;
            return this;
        }

        /**
         * @param author the author to set
         */
        public ReportDetailsBuilder setAuthor(String author) {
            this.author = author;
            return this;
        }

        /**
         * @param beanCollectionDataSource the beanCollectionDataSource to set
         */
        public ReportDetailsBuilder setBeanCollectionDataSource(JRBeanCollectionDataSource beanCollectionDataSource) {
            this.beanCollectionDataSource = beanCollectionDataSource;
            return this;
        }

        /**
         * @param parameters the parameters to set
         */
        public ReportDetailsBuilder setParameters(Map<String, Object> parameters) {
            this.parameters = parameters;
            return this;
        }

        /**
         * Method used to build report parameter.
         *
         * @return {@link ReportDetails}
         */
        @Override
        public ReportDetails build() {
            if (TextUtil.isEmpty(reportFileName) || TextUtil.isEmpty(outputFileName))
                throw new IllegalStateException("Report file name or output file name can't be empty");

            return new ReportDetails(this);
        }
    }
}
