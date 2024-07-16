package com.frauscher.assignment.VacationService.Services.Manager;

import com.frauscher.assignment.VacationService.Entities.Staff.Manager;
import com.frauscher.assignment.VacationService.Entities.Vacation.VacationRequest;
import com.frauscher.assignment.VacationService.Models.ManagerRequest;

import java.util.List;

public interface ManagerService {
    Manager createManager(ManagerRequest managerRequest);

    Manager findManager(Long managerId);

    List<VacationRequest> findAll(Long managerId);
}
