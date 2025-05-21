package com.converter.unitconverter.controller;

import com.converter.unitconverter.dto.ConversionResponse;
import com.converter.unitconverter.dto.ConversionRequest;
import com.converter.unitconverter.service.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ConversionController {

    private final ConversionService conversionService;


    public ConversionController(ConversionService conversionService){
        this.conversionService = conversionService;
    }

    @PostMapping("/convert")
    public ResponseEntity<ConversionResponse> convert(@RequestBody ConversionRequest request){
        double result = conversionService.convertValue(request.getType(),request.getFromUnit(),request.getToUnit(),request.getValue());

        return ResponseEntity.ok(new ConversionResponse(result));
    }
}
