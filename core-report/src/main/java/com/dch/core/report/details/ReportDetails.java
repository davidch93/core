package com.dch.core.report.details;

import com.dch.core.datastatic.builder.GeneralBuilder;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Class that provides various parameters for the report.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
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
     * Get report file name.
     *
     * @return the report file name
     */
    public String getReportFileName() {
        return reportFileName;
    }

    /**
     * Get output file name.
     *
     * @return the output file name
     */
    public String getOutputFileName() {
        return outputFileName;
    }

    /**
     * Get author.
     *
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Get sheet name.
     *
     * @return the sheet name
     */
    public String getSheetName() {
        return sheetName;
    }

    /**
     * Get bean collection data source.
     *
     * @return the bean collection data source
     */
    public JRBeanCollectionDataSource getBeanCollectionDataSource() {
        return beanCollectionDataSource;
    }

    /**
     * Get parameters.
     *
     * @return the parameters
     */
    public Map<String, Object> getParameters() {
        return parameters;
    }

    /**
     * Get report details builder.
     *
     * @param reportFileName the Jasper report file name.
     * @param outputFileName the output report file name.
     * @return the {@link ReportDetailsBuilder}
     */
    public static ReportDetailsBuilder getReportDetailsBuilder(String reportFileName, String outputFileName) {
        return new ReportDetailsBuilder(reportFileName, outputFileName);
    }

    /**
     * Class builder used to create {@link ReportDetails}.
     *
     * @author David.Christianto
     * @version 2.0.0
     * @see com.dch.core.datastatic.builder.GeneralBuilder
     * @since 1.0.0
     */
    public static class ReportDetailsBuilder implements GeneralBuilder<ReportDetails> {

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
         * Method to build report file name.
         *
         * @param reportFileName the reportFileName to set
         * @return the {@link ReportDetailsBuilder}
         */
        public ReportDetailsBuilder setReportFileName(String reportFileName) {
            this.reportFileName = reportFileName;
            return this;
        }

        /**
         * Method to build author.
         *
         * @param author the author to set
         * @return the {@link ReportDetailsBuilder}
         */
        public ReportDetailsBuilder setAuthor(String author) {
            this.author = author;
            return this;
        }

        /**
         * Method to build sheet name.
         *
         * @param sheetName the sheetName to set
         * @return the {@link ReportDetailsBuilder}
         */
        public ReportDetailsBuilder setSheetName(String sheetName) {
            this.sheetName = sheetName;
            return this;
        }

        /**
         * Method to build bean collection data source.
         *
         * @param beanCollectionDataSource the beanCollectionDataSource to set
         * @return the {@link ReportDetailsBuilder}
         */
        public ReportDetailsBuilder setBeanCollectionDataSource(JRBeanCollectionDataSource beanCollectionDataSource) {
            this.beanCollectionDataSource = beanCollectionDataSource;
            return this;
        }

        /**
         * Method to build parameters.
         *
         * @param parameters the parameters to set
         * @return the {@link ReportDetailsBuilder}
         */
        public ReportDetailsBuilder setParameters(Map<String, Object> parameters) {
            this.parameters = parameters;
            return this;
        }

        /**
         * Method used to build report parameter.
         *
         * @return the {@link ReportDetails}
         */
        @Override
        public ReportDetails build() {
            if (StringUtils.isEmpty(reportFileName) || StringUtils.isEmpty(outputFileName))
                throw new IllegalStateException("Report file name or output file name can't be empty");

            return new ReportDetails(this);
        }
    }
}
