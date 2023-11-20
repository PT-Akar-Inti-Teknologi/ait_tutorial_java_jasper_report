package com.example.testjasper.service;

import com.example.testjasper.utils.ReportTypeEnum;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JasperGenerator {

  private final JasperExporter jasperExporter;

  private List<String> bundlingPagesJasper = List.of("book/page1.jasper", "book/page2.jasper");

  public void generateReport(ReportTypeEnum type, Map<String, Object> param)
      throws IOException, JRException {

    List<JasperPrint> jasperPrint = new ArrayList<>();

    for (String path : bundlingPagesJasper) {
      jasperPrint.add(generateJasperPrint(path, param));
    }

    switch (type) {
      case PDF:
        jasperExporter.exportPDF(jasperPrint, "Blank_A4");
        break;
      case HTML:
        jasperExporter.exportHTML(jasperPrint, "Blank_A4");
        break;
    }
  }

  private JasperPrint generateJasperPrint(String filePath, Map<String, Object> param)
      throws IOException, JRException {
    ClassPathResource jasperResource = new ClassPathResource(filePath);
    InputStream jasperStream = jasperResource.getInputStream();
    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
    JasperPrint jasperPrint =
        JasperFillManager.fillReport(jasperReport, param, new JREmptyDataSource());
    return jasperPrint;
  }
}
