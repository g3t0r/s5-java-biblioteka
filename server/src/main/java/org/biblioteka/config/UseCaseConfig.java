package org.biblioteka.config;

import org.biblioteka.http.HttpMethod;
import org.biblioteka.usecase.GetAggregatedBooksUseCase;
import org.biblioteka.usecase.GetTestDtoUseCase;
import org.biblioteka.usecase.PostTestDtoUseCase;
import org.biblioteka.usecase.UseCaseController;

public class UseCaseConfig {

    public static void init() {
        UseCaseController useCaseController = UseCaseController.getInstance();
        useCaseController.registerUseCase("/test", HttpMethod.POST, new PostTestDtoUseCase());
        useCaseController.registerUseCase("/test", HttpMethod.GET, new GetTestDtoUseCase());
        useCaseController.registerUseCase("/books", HttpMethod.GET, new GetAggregatedBooksUseCase());
    }
}
