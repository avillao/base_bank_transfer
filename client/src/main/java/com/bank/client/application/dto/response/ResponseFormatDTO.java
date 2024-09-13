package com.bank.client.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFormatDTO<T> {
    private T data;
    private String message;
    private boolean error;
    private int statusCode;
}
