package com.example.testjasper.controller;

import com.example.testjasper.service.JasperGenerator;
import com.example.testjasper.service.dto.JasperParam;
import com.example.testjasper.service.dto.LainnyaParam;
import com.example.testjasper.utils.ObjectUtils;
import com.example.testjasper.utils.ReportTypeEnum;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

  private final JasperGenerator jasperGenerator;
  @GetMapping("/generate")
  public ResponseEntity<String> test() {
    try {
      jasperGenerator.generateReport(ReportTypeEnum.PDF, prepareParam());
      jasperGenerator.generateReport(ReportTypeEnum.HTML, prepareParam());
      return ResponseEntity.ok("Success");
    } catch (Exception e) {
      log.error(e.getMessage());
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  private Map<String,Object> prepareParam(){
    LainnyaParam lainnyaParam = LainnyaParam.builder()
        .data1("Love Java")
        .data2("Love My Family")
        .build();

    JasperParam jasperParam = JasperParam.builder()
        .nama("Aditya Bintang Pratama")
        .alamat("Jalan Maju terus pantang mundur")
        .lainnya(ObjectUtils.objectToMap(lainnyaParam))
        .build();

    return ObjectUtils.objectToMap(jasperParam);
  }
}
