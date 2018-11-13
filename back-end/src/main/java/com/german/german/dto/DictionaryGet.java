package com.german.german.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Getter
@NoArgsConstructor
@Accessors(chain = true)
@Validated
@Setter
public class DictionaryGet extends AuditableDto{
    @NotBlank
    @ApiModelProperty(required = true)
    private String german;

    @NotBlank
    @ApiModelProperty(required = true)
    private String albanian;
}
