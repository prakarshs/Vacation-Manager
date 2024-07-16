package com.frauscher.assignment.VacationService.Controllers;

import com.frauscher.assignment.VacationService.Entities.Vacation.VacationRequest;
import com.frauscher.assignment.VacationService.Models.VacationQuery;
import com.frauscher.assignment.VacationService.Services.Vacation.VacationService;
import com.oracle.svm.core.annotate.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacation")
public class VacationController {
    @Autowired
    private VacationService vacationService;

    @PostMapping("/create")
    private ResponseEntity<VacationRequest> create(@RequestBody VacationQuery vacationQuery){
        return new ResponseEntity<>(vacationService.createRequest(vacationQuery), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{vacationId}")
    private ResponseEntity<String> create(@PathVariable Long vacationId){
        return new ResponseEntity<>(vacationService.deleteRequest(vacationId), HttpStatus.OK);
    }

    @GetMapping("/allRequests")
    private ResponseEntity<List<VacationRequest>> allRequest(){
        return new ResponseEntity<>(vacationService.allRequest(), HttpStatus.OK);
    }
}
