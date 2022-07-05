package com.template.model.dto.response;

import com.template.model.enums.GenericConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GenericResponse {
    private GenericConstants message;
    public String getMessage() {
        return message.getCodes();
    }
}