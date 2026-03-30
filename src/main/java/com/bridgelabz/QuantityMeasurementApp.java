package com.bridgelabz;

import com.bridgelabz.controller.QuantityMeasurementController;
import com.bridgelabz.repository.IQuantityRepository;
import com.bridgelabz.repository.QuantityRepositoryImpl;
import com.bridgelabz.service.IQuantityMeasurementService;
import com.bridgelabz.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {
    public static void main(String[] args) {

        IQuantityRepository repository = new QuantityRepositoryImpl();
        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);
        QuantityMeasurementController controller = new QuantityMeasurementController(service);

        Quantity<LengthUnit> length1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(12, LengthUnit.INCHES);

        System.out.println("Comparison Result: " + controller.compareQuantities(length1, length2));
        System.out.println("Converted Quantity: " + controller.convertQuantity(length1, LengthUnit.INCHES));
        System.out.println("Addition Result: " + controller.addQuantities(length1, length2));

        Quantity<TemperatureUnit> temp1 = new Quantity<>(0, TemperatureUnit.CELSIUS);
        System.out.println("Temperature Conversion: " + controller.convertQuantity(temp1, TemperatureUnit.FAHRENHEIT));

        controller.displayAllRecords();
    }
}