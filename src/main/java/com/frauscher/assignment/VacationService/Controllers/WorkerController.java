package com.frauscher.assignment.VacationService.Controllers;

import com.frauscher.assignment.VacationService.Entities.Staff.Worker;
import com.frauscher.assignment.VacationService.Entities.Vacation.VacationRequest;
import com.frauscher.assignment.VacationService.Models.WorkerRequest;
import com.frauscher.assignment.VacationService.Services.Worker.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    private WorkerService workerService;


    @PostMapping("/create")
    private ResponseEntity<Worker> createWorker(@RequestBody WorkerRequest workerRequest){
        return new ResponseEntity<>(workerService.createWorker(workerRequest), HttpStatus.CREATED);
    }

    @GetMapping("/find/{workerId}")
    private ResponseEntity<Worker> findWorker(@PathVariable Long workerId){
        return new ResponseEntity<>(workerService.findWorker(workerId), HttpStatus.OK);
    }

    @GetMapping("/showApproved/{workerId}")
    private ResponseEntity<List<VacationRequest>> findApproved(@PathVariable Long workerId){
        return new ResponseEntity<>(workerService.findApproved(workerId), HttpStatus.OK);
    }
    @GetMapping("/showPending/{workerId}")
    private ResponseEntity<List<VacationRequest>> findPending(@PathVariable Long workerId){
        return new ResponseEntity<>(workerService.findPending(workerId), HttpStatus.OK);
    }
    @GetMapping("/showDenied/{workerId}")
    private ResponseEntity<List<VacationRequest>> findDenied(@PathVariable Long workerId){
        return new ResponseEntity<>(workerService.findDenied(workerId), HttpStatus.OK);
    }

    @GetMapping("/showAll/{workerId}")
    private ResponseEntity<List<VacationRequest>> showAll(@PathVariable Long workerId){
        return new ResponseEntity<>(workerService.showAll(workerId), HttpStatus.OK);
    }
}
