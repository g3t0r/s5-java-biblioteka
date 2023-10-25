package org.biblioteka.usecase;

import org.biblioteka.dto.TestDto;
import org.biblioteka.http.JsonResponse;
import org.biblioteka.http.Request;
import org.biblioteka.thread.RequestContext;

public class GetTestDtoUseCase implements UseCase<Request<Void>, JsonResponse<TestDto>> {
    @Override
    public JsonResponse<TestDto> execute(RequestContext requestContext) {
        TestDto body = new TestDto();
        body.name = "Returned body";
        body.integer = 1410;
        return JsonResponse.ok(requestContext.getRequest().getProtocol(), body);
    }
}
