package com.demo.onlineLibraryAnaMariaDoroftei.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Utils {
    public static String toJson(Object object){
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try{
            return ow.writeValueAsString(object);
        } catch(JsonProcessingException jsonProcessingException){
            throw new RuntimeException(jsonProcessingException.getMessage());
        }
    }
}
