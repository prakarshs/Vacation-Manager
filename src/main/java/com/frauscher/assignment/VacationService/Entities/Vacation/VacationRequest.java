package com.frauscher.assignment.VacationService.Entities.Vacation;

import com.frauscher.assignment.VacationService.Constants.Enums.Status;
import com.frauscher.assignment.VacationService.Entities.Staff.Manager;
import com.frauscher.assignment.VacationService.Entities.Staff.Worker;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VacationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;
    private Status status;
    private Date vacationStart;
    private Date vacationEnd;
    private Date requestDate;
    @ManyToOne
    @JoinColumn(name = "workerId", nullable = false)
    private Worker author;
    @ManyToOne
    @JoinColumn(name = "managerId", nullable = false)
    private Manager resolvedBy;

}
