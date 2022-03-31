package com.unobank.servicehub.platform.commonlib.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

/** @author ankur.goel */
public class CommonParser {

  private CommonParser() {}

  public static Object convertStringToPojo(final Class responseClass, final String input)
      throws JsonProcessingException {
    final ObjectMapper mapper = new ObjectMapper();
    mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return mapper.readValue(input, responseClass);
  }

  public static String parseToJsonString(Object data) {
    final ObjectMapper mapper = new ObjectMapper();
    mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    String dataAsJson = StringUtils.EMPTY;
    if(data == null) {
      return dataAsJson;
    }
    try {
      dataAsJson = mapper.writeValueAsString(data);
    } catch (Exception e) {
      dataAsJson =
          MessageFormat.format("could not parse Request: {0}. Exception {1}", data, e.getMessage());
    }
    return dataAsJson;
  }

  public static <T> T convertStringToCollectionPojo(
      final TypeReference<T> responseClass, final String input) throws JsonProcessingException {
    final ObjectMapper mapper = new ObjectMapper();
    mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return mapper.readValue(input, responseClass);
  }

  public  static Object convertJsonFileToPojo( String filePath, final Class responseClass)
          throws IOException {
    final ObjectMapper mapper = new ObjectMapper();
    File fileName = new File(filePath);
    mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return mapper.readValue(fileName, responseClass);
  }

  public static <T> T convertCollectionToCollectionPojo(
          final TypeReference<T> responseClass, final List input) throws JsonProcessingException {
    final ObjectMapper mapper = new ObjectMapper();
    mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return mapper.convertValue(input, responseClass);
  }

}
