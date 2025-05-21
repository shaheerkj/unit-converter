package com.converter.unitconverter.service;

import com.converter.unitconverter.dto.ConversionRequest;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ConversionService {
    public double convertValue(String type, String fromUnit, String toUnit, double value) {
        return switch (type) {
            case "length" -> convertLength(fromUnit, toUnit, value);
            case "weight" -> convertWeight(fromUnit, toUnit, value);
            case "temperature" -> convertTemperature(fromUnit, toUnit, value);
            default -> 0.0;
        };

    }
    private double convertLength(String fromUnit, String toUnit, double value){
        Map<String, Double> lengthConversionRates = Map.of(
                "millimeter", 0.001,
                "centimeter", 0.01,
                "meter", 1.0,
                "kilometer", 1000.0,
                "inch", 0.0254,
                "foot", 0.3048,
                "yard", 0.9144,
                "mile", 1609.34
        );
        if (!lengthConversionRates.containsKey(fromUnit) || !lengthConversionRates.containsKey(toUnit)) {
            throw new IllegalArgumentException("Invalid length units");
        }
        double valueInMeters = value * lengthConversionRates.get(fromUnit);
        return valueInMeters / lengthConversionRates.get(toUnit);
    }


    private double convertWeight(String fromUnit, String toUnit, double value){
        Map<String,Double> weightConversionRates = Map.of(
                "milligram",0.001,
                "gram",1.0,
                "kilogram",1000.0,
                "ounce",28.3495,
                "pound",453.592
        );
        if(!weightConversionRates.containsKey(fromUnit)||!weightConversionRates.containsKey(toUnit)){
            throw new IllegalArgumentException("Invalid weight units.");
        }
        double valueInGrams = value * weightConversionRates.get(fromUnit);
        return valueInGrams / weightConversionRates.get(toUnit);
    }
    private double convertTemperature(String fromUnit, String toUnit, double value) {
        if (fromUnit.equals(toUnit)) {
            return value;
        }
        double valueInCelsius = switch (fromUnit.toLowerCase()) {
            case "celsius" -> value;
            case "fahrenheit" -> (value - 32) * 5 / 9;
            case "kelvin" -> value - 273.15;
            default -> throw new IllegalArgumentException("Invalid from-unit for temperature: " + fromUnit);
        };
        return switch (toUnit.toLowerCase()) {
            case "celsius" -> valueInCelsius;
            case "fahrenheit" -> (valueInCelsius * 9 / 5) + 32;
            case "kelvin" -> valueInCelsius + 273.15;
            default -> throw new IllegalArgumentException("Invalid to-unit for temperature: " + toUnit);
        };
    }

}
