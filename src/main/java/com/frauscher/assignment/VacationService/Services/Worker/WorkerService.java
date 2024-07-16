package com.frauscher.assignment.VacationService.Services.Worker;

import com.frauscher.assignment.VacationService.Entities.Staff.Worker;
import com.frauscher.assignment.VacationService.Entities.Vacation.VacationRequest;
import com.frauscher.assignment.VacationService.Models.WorkerRequest;

import java.util.List;

public interface WorkerService {
    Worker createWorker(WorkerRequest workerRequest);

    Worker findWorker(Long workerId);

    List<VacationRequest> findApproved(Long workerId);

    List<VacationRequest> showAll(Long workerId);

    List<VacationRequest> findPending(Long workerId);

    List<VacationRequest> findDenied(Long workerId);
}
