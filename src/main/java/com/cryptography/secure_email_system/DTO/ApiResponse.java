package com.cryptography.secure_email_system.DTO;

public class ApiResponse<T> {

    private String status;
    private String message;
    private T data;
    private Object errors;

    // Başarıyla dönecek yanıt yapısı
    public static <T> ApiResponse<T> success(String message, T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    // Hata durumunda dönecek yanıt yapısı
    public static ApiResponse<Object> error(String message, Object errors) {
        ApiResponse<Object> response = new ApiResponse<>();
        response.setStatus("error");
        response.setMessage(message);
        response.setErrors(errors);
        return response;
    }

    // Getter ve Setter metodları
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }
}
