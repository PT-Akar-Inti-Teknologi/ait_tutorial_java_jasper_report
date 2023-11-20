package com.example.testjasper.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JasperParam {
  @JsonProperty("nama")
  private String nama;
  @JsonProperty("alamat")
  private String alamat;
  @JsonProperty("page2")
  private String page2;


  @JsonProperty("BookTitle")
  private String bookTitle;
  @JsonProperty("BookSubTitle")
  private String bookSubTitle;



  @JsonProperty("lainnya")
  private Map<String, Object> lainnya;
}
