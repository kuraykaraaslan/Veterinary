/*
 * This class is responsible for handling the requests related to the available dates for appointments.
 * It is a parameterized class as it is annotated with the @RestController annotation.
 * 
 * Routes:
 * GET /api/days
 * GET /api/days/{id}
 * POST /api/days
 * PUT /api/days/{id}
 * DELETE /api/days/{id}
 */

package com.veterinary.management.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.veterinary.management.request.AvailableDateRequestDto;
import com.veterinary.management.entity.AvailableDate;
import com.veterinary.management.service.AvailableDateService;

import java.util.List;

@RestController
@RequestMapping("/api/days")
@RequiredArgsConstructor
public class AvailableDateController {

    public final AvailableDateService availableDateService;

    @GetMapping
    public List<AvailableDate> findAllAvailableDates() {
        return availableDateService.findAllAvailableDates();
    }

    @GetMapping("/{id}")
    public AvailableDate findAvailableDateById(@PathVariable Long id) {
        return availableDateService.findAvailableDateById(id);
    }

    @PostMapping
    public AvailableDate createAvailableDate(@RequestBody AvailableDateRequestDto availableDateRequestDto) {
        return availableDateService.createAvailableDate(availableDateRequestDto);
    }

    @PutMapping("/{id}")
    public AvailableDate updateAvailableDate(@PathVariable Long id,
            @RequestBody AvailableDateRequestDto availableDateRequestDto) {
        return availableDateService.updateAvailableDate(id, availableDateRequestDto);
    }

    @DeleteMapping("/{id}")
    public String deleteAvailableDate(@PathVariable Long id) {
        return availableDateService.deleteAvailableDate(id);
    }
}
