package com.adp.changeService.service;

import com.adp.changeService.dto.ChangeRequest;
import com.adp.changeService.dto.ChangeResponse;
import org.springframework.http.ResponseEntity;

public interface ChangeService {
    ResponseEntity<ChangeResponse> calculateChange(ChangeRequest changeRequest);
}
