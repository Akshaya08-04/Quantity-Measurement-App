package com.bridgelabz;

import com.bridgelabz.dto.QuantityDTO;
import com.bridgelabz.repository.IQuantityRepository;
import com.bridgelabz.repository.RepositoryFactory;
import com.bridgelabz.service.QuantityServiceImpl;

public class QuantityMeasurementApp {

    public static void main(String[] args) {
        IQuantityRepository repository = RepositoryFactory.createRepository();
        QuantityServiceImpl service = new QuantityServiceImpl(repository);

        QuantityDTO first = new QuantityDTO(12, "INCHES", "LengthUnit");
        QuantityDTO second = new QuantityDTO(1, "FEET", "LengthUnit");

        boolean compareResult = service.compare(first, second);
        System.out.println("Compare Result: " + compareResult);

        QuantityDTO convertDto = new QuantityDTO(2, "FEET", "LengthUnit");
        double convertedValue = service.convert(convertDto, "INCHES");
        System.out.println("Converted Value: " + convertedValue);

        System.out.println("Total Count: " + service.getTotalCount());
        System.out.println("All Measurements: " + service.getAllMeasurements().size());
    }
}