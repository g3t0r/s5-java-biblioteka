package org.biblioteka.usecase;

import org.biblioteka.exceptions.ConfigError;
import org.biblioteka.http.HttpMethod;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UseCaseController {

    private static UseCaseController instance = null;

    private Map<String, Map<HttpMethod, UseCase<?, ?>>> uriMethodUseCaseMap = new HashMap<>(10);

    public static UseCaseController getInstance() {
       if(instance == null) {
           instance = new UseCaseController();
       }
       return instance;
    }

    public void registerUseCase(final String uri, final HttpMethod method, final UseCase<?,?> useCase) {
        uriMethodUseCaseMap.putIfAbsent(uri, new HashMap<>());
        if(uriMethodUseCaseMap.get(uri).containsKey(method)) {
            throw new ConfigError("Use case path and method duplicate");
        }
        uriMethodUseCaseMap.get(uri).put(method, useCase);
    }

    public UseCase<?, ?> getUseCase(final String uri, final HttpMethod method) {
        return uriMethodUseCaseMap.getOrDefault(uri, Collections.emptyMap()).get(method);
    }

}
