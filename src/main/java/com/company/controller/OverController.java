package com.company.controller;

import com.company.response.OverStatsResponse;
import com.company.service.OverControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OverController {

    @Autowired
    private OverControllerService overControllerService;

    @GetMapping("/over/{matchId}")
    public List<OverStatsResponse> getMatchOver(@PathVariable int matchId) {
        return overControllerService.getOverDetails(matchId);
    }
}
