package com.example.testjasper.service;

import com.example.testjasper.service.dto.JasperParam;
import com.example.testjasper.utils.ReportTypeEnum;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
@RequiredArgsConstructor
public class JasperGenerator {

  private final JasperExporter jasperExporter;
  public void generateReport(ReportTypeEnum type, Map<String,Object> param)
      throws FileNotFoundException, JRException {
    File jasperFile = ResourceUtils.getFile("classpath:Blank_A4.jasper");
    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperFile);

    JasperPrint
        jasperPrint = JasperFillManager.fillReport(jasperReport,param,new JREmptyDataSource());

    switch (type){
      case PDF:
        jasperExporter.exportPDF(jasperPrint, "Blank_A4");
        break;
      case HTML:
        jasperExporter.exportHTML(jasperPrint, "Blank_A4");
        break;
    }
  }
}