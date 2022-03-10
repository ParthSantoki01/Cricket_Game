package com.company.service;

import com.company.repository.entity.OverStats;

import java.util.List;

public interface OverControllerService {
    List<OverStats> getOverDetails(int matchId);
}
