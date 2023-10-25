package org.biblioteka.usecase;

import org.biblioteka.dto.TestDto;
import org.biblioteka.http.JsonRequest;
import org.biblioteka.http.JsonResponse;
import org.biblioteka.thread.RequestContext;

public class PostTestDtoUseCase implements UseCase<JsonRequest<TestDto>, JsonResponse<TestDto>> {
    @Override
    public JsonResponse<TestDto> execute(RequestContext requestContext) {
        JsonRequest<TestDto> castedRequest = JsonRequest.fromRawRequest(requestContext.getRequest(), TestDto.class);
        TestDto dto = castedRequest.getBody();
        dto.name = "Response from the server";
        dto.integer = 1410;
        return JsonResponse.ok(castedRequest.getProtocol(), castedRequest.getBody());
    }
}
