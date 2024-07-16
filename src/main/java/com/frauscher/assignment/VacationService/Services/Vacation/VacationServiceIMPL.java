package com.frauscher.assignment.VacationService.Services.Vacation;

import com.frauscher.assignment.VacationService.Constants.Enums.Status;
import com.frauscher.assignment.VacationService.Constants.Errors.ErrorConsts;
import com.frauscher.assignment.VacationService.Constants.VacationConst.Consts;
import com.frauscher.assignment.VacationService.Entities.Staff.Manager;
import com.frauscher.assignment.VacationService.Entities.Staff.Worker;
import com.frauscher.assignment.VacationService.Entities.Vacation.VacationRequest;
import com.frauscher.assignment.VacationService.Exceptions.NotExistException;
import com.frauscher.assignment.VacationService.Models.VacationQuery;
import com.frauscher.assignment.VacationService.Repositories.ManagerRepository;
import com.frauscher.assignment.VacationService.Repositories.VacationRepository;
import com.frauscher.assignment.VacationService.Repositories.WorkerRepository;
import lombok.extern.log4j.Log4j2;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Log4j2
public class VacationServiceIMPL implements VacationService {
    @Autowired
    private VacationRepository vacationRepository;
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private ManagerRepository managerRepository;
    @Override
    public VacationRequest createRequest(VacationQuery vacationQuery) {
        log.info("Checking Validity Of Vacation..");

        Long workerId = vacationQuery.getAuthor();
        log.info("Checking If Valid Worker...");
        Worker worker = workerRepository.findByEmployeeId(workerId).orElseThrow(()-> new NotExistException(ErrorConsts.INVALID_WORKER_ID,ErrorConsts.INVALID_WORKER_ID_RESOLUTION));

        log.info("Checking If Valid Manager...");
        Long managerId = vacationQuery.getTaggedManager();
        Manager manager = managerRepository.findByEmployeeId(managerId).orElseThrow(()-> new NotExistException(ErrorConsts.INVALID_MANAGER_ID,ErrorConsts.INVALID_MANAGER_ID_RESOLUTION));

        log.info("Checking If Requests Exceeded...");
        if(worker.getVacationRequestsMade()>Consts.workerRequests){
            log.error("Per Year Requests Exceeded");
            return null;
        }

        log.info("Checking If Holidays Exceeded...");
        if(worker.getVacationLeft()> Consts.workerLeaves){
            log.error("Per Year Holidays Exceeded");
            return null;
        }

        log.info("Checking If Valid Request...");
        if(vacationQuery.getVacationStart().getTime()>vacationQuery.getVacationEnd().getTime()){
            log.error("Invalid Vacation Dates...");
            return null;
        }

        log.info("Increasing Request By One...");
        worker.setVacationRequestsMade(worker.getVacationRequestsMade() + 1);
        workerRepository.save(worker);

        log.info("Creating Vacation Request...");
        VacationRequest vacationRequest = VacationRequest.builder()
                .author(worker)
                .status(Status.PENDING)
                .vacationStart(vacationQuery.getVacationStart())
                .vacationEnd(vacationQuery.getVacationEnd())
                .requestDate(new Date())
                .resolvedBy(manager)
                .build();
        log.info("Saving Vacation Request...");
        vacationRepository.save(vacationRequest);
        return vacationRequest;
    }

    @Override
    public String deleteRequest(Long vacationId) {
        VacationRequest vacationRequest = vacationRepository.findById(vacationId).orElseThrow(()-> new NotExistException(ErrorConsts.INVALID_VACATION_ID,ErrorConsts.INVALID_VACATION_ID_RESOLUTION));
        return "Deleted vacation Request.";
    }

    @Override
    public VacationRequest changeStatus(Long vacationId, Status requestStatus) {
        log.info("Checking If Valid Vacation Id");
        VacationRequest vacationRequest = vacationRepository.findById(vacationId).orElseThrow(()-> new NotExistException(ErrorConsts.INVALID_VACATION_ID,ErrorConsts.INVALID_VACATION_ID_RESOLUTION));
        if(requestStatus.equals(Status.APPROVED)){
            log.info("Approved!! Reducing Vacation Days From Total...");
            Worker worker = vacationRequest.getAuthor();
            Long vacationDays = (vacationRequest.getVacationEnd().getTime() - vacationRequest.getVacationStart().getTime()) / (1000 * 60 * 60 * 24);
            worker.setVacationLeft(worker.getVacationLeft() - vacationDays);
            workerRepository.save(worker);

        }
        if(requestStatus.equals(Status.DENIED)){
            log.info("Denied!!");
        }
        log.info("Changing Status...");
        vacationRequest.setStatus(requestStatus);
        log.info("Updating Status In Repository");
        vacationRepository.save(vacationRequest);
        return vacationRequest;
    }

    @Override
    public List<VacationRequest> allRequest() {
        return vacationRepository.findAll();
    }
}
