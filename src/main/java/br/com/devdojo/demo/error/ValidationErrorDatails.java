package br.com.devdojo.demo.error;

import java.time.LocalDateTime;

public class ValidationErrorDatails extends ErrorDetail {

    private String field;
    private String fieldMessage;

    public static final class Builder {
        private String title;
        private int status;
        private String detail;
        private LocalDateTime timestamp;
        private String developerMessage;
        private String field;
        private String fieldMessage;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public Builder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public Builder field(String field) {
            this.field = field;
            return this;
        }

        public Builder fieldMessage(String fieldMessage) {
            this.fieldMessage = fieldMessage;
            return this;
        }

        public ValidationErrorDatails build() {
            ValidationErrorDatails validationErrorDatails = new ValidationErrorDatails();
            validationErrorDatails.setDeveloperMessage(developerMessage);
            validationErrorDatails.setTitle(title);
            validationErrorDatails.setStatus(status);
            validationErrorDatails.setTimestamp(timestamp);
            validationErrorDatails.setDetail(detail);
            validationErrorDatails.field = field;
            validationErrorDatails.fieldMessage = fieldMessage;
            return validationErrorDatails;
        }
    }

    public String getField() {
        return field;
    }

    public String getFieldMessage() {
        return fieldMessage;
    }
}
