package org.biblioteka.config;

import org.biblioteka.http.HttpMethod;
import org.biblioteka.usecase.*;

public class UseCaseConfig {

    public static void init() {
        UseCaseController useCaseController = UseCaseController.getInstance();
        useCaseController.registerUseCase("/test", HttpMethod.POST, new PostTestDtoUseCase());
        useCaseController.registerUseCase("/test", HttpMethod.GET, new GetTestDtoUseCase());
        useCaseController.registerUseCase("/books", HttpMethod.GET, new GetAggregatedBooksUseCase());
        useCaseController.registerUseCase("/signup", HttpMethod.POST, new SignUpUseCase());
        useCaseController.registerUseCase("/login", HttpMethod.POST, new LogInUseCase());
        useCaseController.registerUseCase("/rental", HttpMethod.POST, new BorrowBookUseCase());
    }
}
