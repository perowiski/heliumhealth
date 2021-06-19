package com.heliumhealth.dtos;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter @Setter
public class RequestDTO {
    @NotBlank(message = "iPAddress is mandatory")
    public String iPAddress;
}
