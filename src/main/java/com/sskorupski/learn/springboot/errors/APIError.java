package com.sskorupski.learn.springboot.errors;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain = true)
public class APIError {
    private String title;
    private Integer status;
    private String detail;
    private String resource;
    private String see;
}
