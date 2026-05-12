package com.bridgelabz.service;

import com.bridgelabz.IMeasurable;
import com.bridgelabz.Quantity;
import java.util.List;

public interface IQuantityMeasurementService {
    <T extends Enum<T> & IMeasurable> boolean compare(Quantity<T> first, Quantity<T> second);
    <T extends Enum<T> & IMeasurable> Quantity<T> convert(Quantity<T> quantity, T targetUnit);
    <T extends Enum<T> & IMeasurable> Quantity<T> add(Quantity<T> first, Quantity<T> second);
    <T extends Enum<T> & IMeasurable> Quantity<T> subtract(Quantity<T> first, Quantity<T> second);
    List<?> getAllRecords();
}
