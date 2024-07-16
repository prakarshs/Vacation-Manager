package com.frauscher.assignment.VacationService.Services.Worker;

import com.frauscher.assignment.VacationService.Constants.Enums.Status;
import com.frauscher.assignment.VacationService.Constants.Errors.ErrorConsts;
import com.frauscher.assignment.VacationService.Constants.VacationConst.Consts;
import com.frauscher.assignment.VacationService.Entities.Staff.Worker;
import com.frauscher.assignment.VacationService.Entities.Vacation.VacationRequest;
import com.frauscher.assignment.VacationService.Exceptions.NotExistException;
import com.frauscher.assignment.VacationService.Models.WorkerRequest;
import com.frauscher.assignment.VacationService.Repositories.VacationRepository;
import com.frauscher.assignment.VacationService.Repositories.WorkerRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class WorkerServiceIMPL implements WorkerService {
    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private VacationRepository vacationRepository;
    @Override
    public Worker createWorker(WorkerRequest workerRequest) {
        log.info("Creating Worker Entity...");
        Worker worker = Worker.builder()
                .employeeFirstName(workerRequest.getWorkerFirstName())
                .employeeLastName(workerRequest.getWorkerLastName())
                .employeeEmail(workerRequest.getWorkerEmail())
                .employeeDept(workerRequest.getWorkerDept())
                .vacationLeft(Consts.workerLeaves)
                .vacationRequestsMade(0L)
                .build();
        log.info("Saving Worker In Repository...");
        try {
            workerRepository.save(worker);
        }catch (Exception e){
            log.error("Worker Entity Could Not Be Saved");
            throw new RuntimeException();
        }
        return worker;
    }

    @Override
    public Worker findWorker(Long workerId) {
        Worker worker = workerRepository.findById(workerId).orElseThrow(()-> new NotExistException(ErrorConsts.INVALID_WORKER_ID,ErrorConsts.INVALID_WORKER_ID_RESOLUTION));
        return worker;
    }

    @Override
    public List<VacationRequest> findApproved(Long workerId) {
        log.info("Checking if valid workerId");
        Worker worker = workerRepository.findByEmployeeId(workerId).orElseThrow(()-> new NotExistException(ErrorConsts.INVALID_WORKER_ID,ErrorConsts.INVALID_WORKER_ID_RESOLUTION));
        List<VacationRequest> allRequestsById = showAll(workerId);

        List<VacationRequest> approvedRequests = allRequestsById.stream()
                .filter(request -> request.getStatus() == Status.APPROVED)
                .toList();
        return approvedRequests;
    }

    @Override
    public List<VacationRequest> showAll(Long workerId) {
        log.info("Checking if valid workerId");
        Worker worker = workerRepository.findByEmployeeId(workerId).orElseThrow(()-> new NotExistException(ErrorConsts.INVALID_WORKER_ID,ErrorConsts.INVALID_WORKER_ID_RESOLUTION));
        List<VacationRequest> allRequestsById = vacationRepository.findByAuthorEmployeeId(workerId);
        if(allRequestsById.isEmpty())
            log.error("There Are No Requests By Given Worker Id");

        return allRequestsById;
    }

    @Override
    public List<VacationRequest> findPending(Long workerId) {
        log.info("Checking if valid workerId");
        Worker worker = workerRepository.findByEmployeeId(workerId).orElseThrow(()-> new NotExistException(ErrorConsts.INVALID_WORKER_ID,ErrorConsts.INVALID_WORKER_ID_RESOLUTION));
        List<VacationRequest> allRequestsById = showAll(workerId);

        List<VacationRequest> pendingRequests = allRequestsById.stream()
                .filter(request -> request.getStatus() == Status.PENDING)
                .toList();
        return pendingRequests;
    }

    @Override
    public List<VacationRequest> findDenied(Long workerId) {
        log.info("Checking if valid workerId");
        Worker worker = workerRepository.findByEmployeeId(workerId).orElseThrow(()-> new NotExistException(ErrorConsts.INVALID_WORKER_ID,ErrorConsts.INVALID_WORKER_ID_RESOLUTION));
        List<VacationRequest> allRequestsById = showAll(workerId);

        List<VacationRequest> deniedRequests = allRequestsById.stream()
                .filter(request -> request.getStatus() == Status.DENIED)
                .toList();
        return deniedRequests;
    }
}
