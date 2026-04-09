package com.bridgelabz.repository;

public class RepositoryFactory {

    public static IQuantityRepository createRepository() {
        return new QuantityDatabaseRepositoryImpl();
    }
}