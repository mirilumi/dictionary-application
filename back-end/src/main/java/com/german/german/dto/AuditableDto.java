package com.german.german.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Getter
@NoArgsConstructor
@Accessors(chain = true)
@Validated
@Setter
public abstract class AuditableDto extends IdentifiableDto{
    private Date createdDate;
    private Date lastModifiedDate;
}
