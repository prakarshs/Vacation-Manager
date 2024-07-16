package com.frauscher.assignment.VacationService.Constants.Errors;

import lombok.Data;

@Data
public class ErrorConsts {
    public static final String INVALID_WORKER_ID = "!! The Given WorkerId Doesnt Exist !!";
    public static final String INVALID_MANAGER_ID = "!! The Given ManagerId Doesnt Exist !!";
    public static final String INVALID_VACATION_ID = "!! The Given VacationId Doesnt Exist !!";

    public static final String INVALID_WORKER_ID_RESOLUTION = "Try With A Different WorkerId.";
    public static final String INVALID_MANAGER_ID_RESOLUTION = "Try With A Different ManagerId";
    public static final String INVALID_VACATION_ID_RESOLUTION = "Try With A Different VacationId";
}
