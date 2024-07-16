package com.frauscher.assignment.VacationService.Models;

import com.frauscher.assignment.VacationService.Constants.Enums.Status;
import com.frauscher.assignment.VacationService.Entities.Staff.Manager;
import com.frauscher.assignment.VacationService.Entities.Staff.Worker;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class VacationQuery {
    private Date vacationStart;
    private Date vacationEnd;
    private Long author;
    private Long taggedManager;
}
