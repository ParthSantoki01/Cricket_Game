package com.company.controller;

import com.company.repository.entity.OverStats;
import com.company.service.OverControllerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/over")
public class OverController {

    @Autowired
    private OverControllerServiceImpl overControllerService;

    @GetMapping("/{matchId}")
    public List<OverStats> getMatchOver(@PathVariable int matchId)
    {
        return overControllerService.getOverDetails(matchId);
    }
}
