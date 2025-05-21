package com.converter.unitconverter.dto;

public class ConversionResponse {
    private double convertedValue;
    public ConversionResponse(double convertedValue){
        this.convertedValue = convertedValue;
    }

    public double getConvertedValue() {
        return convertedValue;
    }

    public void setConvertedValue(double convertedValue) {
        this.convertedValue = convertedValue;
    }
}
