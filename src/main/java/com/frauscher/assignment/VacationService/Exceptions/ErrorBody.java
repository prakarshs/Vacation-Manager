package com.frauscher.assignment.VacationService.Exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorBody {
    private String message;
    private String resolution;
}
