package org.biblioteka.usecase;

import org.biblioteka.http.JsonRequest;
import org.biblioteka.http.JsonResponse;
import org.biblioteka.thread.RequestContext;

public class ReturnBookUseCase implements UseCase<JsonRequest<Void>, JsonResponse<Void>> {
    @Override
    public JsonResponse<Void> execute(RequestContext requestContext) {

        return null;
    }
}
