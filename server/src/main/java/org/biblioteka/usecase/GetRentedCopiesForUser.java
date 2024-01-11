package org.biblioteka.usecase;

import org.biblioteka.http.JsonRequest;
import org.biblioteka.http.JsonResponse;
import org.biblioteka.repository.RentalRepository;
import org.biblioteka.shared.model.RentedCopy;
import org.biblioteka.thread.RequestContext;

import java.util.List;

public class GetRentedCopiesForUser implements UseCase<JsonRequest<Void>, JsonResponse<List<RentedCopy>>> {

    private final RentalRepository rentalRepository = RentalRepository.getInstance();

    @Override
    public JsonResponse<List<RentedCopy>> execute(RequestContext requestContext) {
        String userEmail = requestContext.getQueryParams().get("userEmail");

        return JsonResponse.ok(requestContext.getProtocol(),
                rentalRepository.getRentedBooksForUser(userEmail));
    }
}
