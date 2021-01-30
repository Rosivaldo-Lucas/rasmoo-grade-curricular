package com.rasmoo.cliente.escola.gradecurricular.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ErrorMapResponse {

    private int httpStatus;

    private Map<String, String> erros = new HashMap<>();

    private Long timestamp;

}
