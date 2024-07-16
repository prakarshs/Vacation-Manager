package com.frauscher.assignment.VacationService.Services.Vacation;

import com.frauscher.assignment.VacationService.Constants.Enums.Status;
import com.frauscher.assignment.VacationService.Entities.Vacation.VacationRequest;
import com.frauscher.assignment.VacationService.Models.VacationQuery;

import java.util.List;

public interface VacationService {
    VacationRequest createRequest(VacationQuery vacationQuery);

    String deleteRequest(Long vacationId);

    VacationRequest changeStatus(Long vacationId, Status requestStatus);

    List<VacationRequest> allRequest();
}
