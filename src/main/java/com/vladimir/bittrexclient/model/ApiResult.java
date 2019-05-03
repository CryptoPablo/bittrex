package com.vladimir.bittrexclient.model;

import java.util.Optional;

public class ApiResult<T> {
    private boolean success;
    private String message;
    private T result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
    //    public Optional<T> getResultAsOptional() {
//        return Optional.ofNullable(result);
//    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
