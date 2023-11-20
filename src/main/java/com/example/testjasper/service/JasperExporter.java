package com.example.testjasper.service;

import java.util.List;
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

  public void exportPDF(List<JasperPrint> jasperPrint, String fileName) throws JRException {
    JRPdfExporter exporter = new JRPdfExporter();

    exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrint));
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

  public void exportHTML(List<JasperPrint> jasperPrint, String fileName) throws JRException {

    HtmlExporter htmlexporter = new HtmlExporter();

    htmlexporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrint));
    htmlexporter.setExporterOutput(new SimpleHtmlExporterOutput(fileName+".html"));

    htmlexporter.exportReport();
  }
}