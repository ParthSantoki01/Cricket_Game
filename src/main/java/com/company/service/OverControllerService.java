package com.company.service;

import com.company.response.OverStatsResponse;

import java.util.List;

public interface OverControllerService {
    List<OverStatsResponse> getOverDetails(int matchId);
}
