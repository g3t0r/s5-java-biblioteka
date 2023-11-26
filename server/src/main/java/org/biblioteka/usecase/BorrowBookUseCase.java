package org.biblioteka.usecase;

import org.biblioteka.http.JsonRequest;
import org.biblioteka.http.JsonResponse;
import org.biblioteka.model.Rental;
import org.biblioteka.model.SimpleRental;
import org.biblioteka.repository.RentalRepository;
import org.biblioteka.thread.RequestContext;

import java.time.LocalDate;

public class BorrowBookUseCase implements UseCase<JsonRequest<SimpleRental>, JsonResponse<SimpleRental>> {
    private final RentalRepository rentalRepository = RentalRepository.getInstance();

    @Override
    public JsonResponse<SimpleRental> execute(RequestContext requestContext) {
        SimpleRental form = JsonRequest
                .fromRawRequest(requestContext.getRequest(), SimpleRental.class)
                .getBody();

        Rental rent = new Rental();

        rent.setToday(LocalDate.now());
        rent.setBookId(form.bookId);
        rent.setUserId(form.userId);
        rent.setUntil(LocalDate.parse(form.until));

        Rental rental = rentalRepository.add(rent);

        SimpleRental result = new SimpleRental();
        result.bookId = rental.getBookId();
        result.userId = rental.getUserId();
        result.until = rental.getUntil().toString();

        return JsonResponse.ok(requestContext.getProtocol(), result);
    }
}
