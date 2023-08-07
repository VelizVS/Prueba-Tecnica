package com.mx.CrudUsuarios1.serviceSecurity;

public class SuccesResponse {
    private String message;

    public void SuccessResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}