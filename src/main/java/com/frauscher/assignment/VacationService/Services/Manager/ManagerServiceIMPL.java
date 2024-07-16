package com.frauscher.assignment.VacationService.Services.Manager;

import com.frauscher.assignment.VacationService.Constants.Errors.ErrorConsts;
import com.frauscher.assignment.VacationService.Constants.VacationConst.Consts;
import com.frauscher.assignment.VacationService.Entities.Staff.Manager;
import com.frauscher.assignment.VacationService.Entities.Staff.Worker;
import com.frauscher.assignment.VacationService.Entities.Vacation.VacationRequest;
import com.frauscher.assignment.VacationService.Exceptions.NotExistException;
import com.frauscher.assignment.VacationService.Models.ManagerRequest;
import com.frauscher.assignment.VacationService.Repositories.ManagerRepository;
import com.frauscher.assignment.VacationService.Repositories.VacationRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class ManagerServiceIMPL implements ManagerService {
    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private VacationRepository vacationRepository;

    @Override
    public Manager createManager(ManagerRequest managerRequest) {
        log.info("Creating Manager Entity...");
        Manager manager = Manager.builder()
                .employeeFirstName(managerRequest.getManagerFirstName())
                .employeeLastName(managerRequest.getManagerLastName())
                .employeeEmail(managerRequest.getManagerEmail())
                .employeeDept(managerRequest.getManagerDept())
                .vacationLeft(Consts.managerLeaves)
                .vacationRequestsMade(0L)
                .build();
        log.info("Saving Manager In Repository...");
        try {
            managerRepository.save(manager);
        } catch (Exception e) {
            log.error("Manager Entity Could Not Be Saved");
            throw new RuntimeException();
        }
        return manager;
    }

    @Override
    public Manager findManager(Long managerId) {
        log.info("Checking if valid managerId...");
        Manager manager = managerRepository.findByEmployeeId(managerId).orElseThrow(()-> new NotExistException(ErrorConsts.INVALID_MANAGER_ID,ErrorConsts.INVALID_MANAGER_ID_RESOLUTION));
        return manager;
    }

    @Override
    public List<VacationRequest> findAll(Long managerId) {
        Manager manager = findManager(managerId);
        List<VacationRequest> allResolvedRequests = vacationRepository.findByResolvedByEmployeeId(managerId);
        if(allResolvedRequests.isEmpty())
            log.error("No Resolved Requests By Manager id.");
        return allResolvedRequests;
    }
}
