package com.veterinary.management.services;

import java.util.List;

import com.veterinary.management.entities.WorkingDay;
import com.veterinary.management.repositories.WorkingDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkingDayService {

    @Autowired
    private WorkingDayRepository workingDayRepository;

    public List<WorkingDay> getWorkingDays() {
        return workingDayRepository.findAll();
    }

    public void addNewWorkingDay(WorkingDay workingDay) {
        workingDayRepository.save(workingDay);
    }

    public void deleteWorkingDay(Long workingDayId) {
        workingDayRepository.deleteById(workingDayId);
    }

    public void updateWorkingDay(Long workingDayId, WorkingDay workingDay) {
        workingDayRepository.save(workingDay);
    }

    public WorkingDay getWorkingDayById(Long workingDayId) {
        return workingDayRepository.findById(workingDayId).orElse(null);
    }

}
