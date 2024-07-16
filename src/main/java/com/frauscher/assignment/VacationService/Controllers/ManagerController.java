package com.frauscher.assignment.VacationService.Controllers;

import com.frauscher.assignment.VacationService.Constants.Enums.Status;
import com.frauscher.assignment.VacationService.Entities.Staff.Manager;
import com.frauscher.assignment.VacationService.Entities.Staff.Worker;
import com.frauscher.assignment.VacationService.Entities.Vacation.VacationRequest;
import com.frauscher.assignment.VacationService.Models.ManagerRequest;
import com.frauscher.assignment.VacationService.Models.WorkerRequest;
import com.frauscher.assignment.VacationService.Services.Manager.ManagerService;
import com.frauscher.assignment.VacationService.Services.Vacation.VacationService;
import com.frauscher.assignment.VacationService.Services.Worker.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;
    @Autowired
    private VacationService vacationService;

    @PostMapping("/create")
    private ResponseEntity<Manager> createManager(@RequestBody ManagerRequest managerRequest){
        return new ResponseEntity<>(managerService.createManager(managerRequest), HttpStatus.CREATED);
    }

    @GetMapping("/find/{managerId}")
    private ResponseEntity<Manager> findManager(@PathVariable Long managerId){
        return new ResponseEntity<>(managerService.findManager(managerId), HttpStatus.OK);
    }

    @GetMapping("/findAll/{managerId}")
    private ResponseEntity<List<VacationRequest>> findAll(@PathVariable Long managerId){
        return new ResponseEntity<>(managerService.findAll(managerId), HttpStatus.OK);
    }

    @PutMapping("/changeStatus/{vacationId}")
    private ResponseEntity<VacationRequest> changeStatus(@PathVariable Long vacationId, @RequestParam Status requestStatus){
        return new ResponseEntity<>(vacationService.changeStatus(vacationId,requestStatus), HttpStatus.OK);
    }

}
