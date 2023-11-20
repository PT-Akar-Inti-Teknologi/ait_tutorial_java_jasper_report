package com.example.testjasper.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class ObjectUtils {
  public static Map<String, Object> objectToMap(Object object) {
    return new ObjectMapper().convertValue(object, new TypeReference<>() {
    });
  }
}
