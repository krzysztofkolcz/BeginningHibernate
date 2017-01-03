package com.kkolcz.mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonObjectMapper extends ObjectMapper {
    public JacksonObjectMapper() {
        super();
        this.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
    }
}

