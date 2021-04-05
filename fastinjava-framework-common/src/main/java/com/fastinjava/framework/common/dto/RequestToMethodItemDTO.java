package com.fastinjava.framework.common.dto;

import lombok.Data;

@Data
public class RequestToMethodItemDTO {

    private String requestUrl;
    private String requestType;
    private String controllerName;
    private String requestMethodName;
    private Class<?>[] methodParamTypes;

    public RequestToMethodItemDTO(String requestUrl, String requestType, String controllerName, String requestMethodName, Class<?>[] methodParamTypes) {
        this.requestUrl = requestUrl;
        this.requestType = requestType;
        this.controllerName = controllerName;
        this.requestMethodName = requestMethodName;
        this.methodParamTypes = methodParamTypes;
    }
}
