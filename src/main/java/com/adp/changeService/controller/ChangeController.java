package com.adp.changeService.controller;

import com.adp.changeService.dto.ChangeRequest;
import com.adp.changeService.dto.ChangeResponse;
import com.adp.changeService.service.ChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/change")
public class ChangeController {

    @Autowired
    private ChangeService changeService;

    @PostMapping
    public ResponseEntity<ChangeResponse> postChange(@RequestBody ChangeRequest request) {
        return changeService.calculateChange(request);
    }
}
