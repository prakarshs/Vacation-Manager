package com.frauscher.assignment.VacationService.Models;

import com.frauscher.assignment.VacationService.Constants.Enums.Department;
import lombok.Data;

@Data
public class WorkerRequest {
    private String workerFirstName;
    private String workerLastName;
    private String workerEmail;
    private Department workerDept;
}
