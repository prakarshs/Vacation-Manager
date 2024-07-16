package com.frauscher.assignment.VacationService.Models;

import com.frauscher.assignment.VacationService.Constants.Enums.Department;
import lombok.Data;

@Data
public class ManagerRequest {
    private Long managerId;
    private String managerFirstName;
    private String managerLastName;
    private String managerEmail;
    private Department managerDept;
}
