package com.example.testjasper.service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import org.springframework.stereotype.Service;

@Service
public class JasperExporter {

  public void exportPDF(JasperPrint jasperPrint, String fileName) throws JRException {
    JRPdfExporter exporter = new JRPdfExporter();

    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("%s.pdf".formatted(fileName)));

    SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
    reportConfig.setSizePageToContent(true);
    reportConfig.setForceLineBreakPolicy(false);

    SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
    exportConfig.setMetadataAuthor("AIT - Bintang");
    exportConfig.setEncrypted(true);
    exportConfig.setAllowedPermissionsHint("PRINTING");

    exporter.setConfiguration(reportConfig);
    exporter.setConfiguration(exportConfig);
    exporter.exportReport();
  }

  public void exportHTML(JasperPrint jasperPrint, String fileName) throws JRException {

    HtmlExporter htmlexporter = new HtmlExporter();

    htmlexporter.setExporterInput(new SimpleExporterInput(jasperPrint));
    htmlexporter.setExporterOutput(new SimpleHtmlExporterOutput("employeeReport.html"));

    htmlexporter.exportReport();
  }
}