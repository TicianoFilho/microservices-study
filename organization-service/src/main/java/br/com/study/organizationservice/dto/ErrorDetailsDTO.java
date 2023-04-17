package br.com.study.organizationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetailsDTO {

    private LocalDateTime timestamp;

    private String message;

    private String path;

    private String errorCode;
}
