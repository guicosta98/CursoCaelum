package br.com.alura.forum.validation.dto;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorsOutputDto {
    private List<FieldErrorOutputDto> fieldErrors = new ArrayList<>();
    private List<String> globalErrors = new ArrayList<>();

    public void addFieldError(String field, String message) {
        FieldErrorOutputDto fieldError = new FieldErrorOutputDto(field, message);
        fieldErrors.add(fieldError);
    }

    public void addGlobalError(String message) {
        globalErrors.add(message);
    }

    public List<FieldErrorOutputDto> getErrors() {
        return fieldErrors;
    }

    public int getNumberOfErrors() {
        return this.fieldErrors.size();
    }
}
