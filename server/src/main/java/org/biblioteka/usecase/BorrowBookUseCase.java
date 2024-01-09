package org.biblioteka.usecase;

import org.biblioteka.http.JsonRequest;
import org.biblioteka.http.JsonResponse;
import org.biblioteka.model.Rental;
import org.biblioteka.model.SimpleRental;
import org.biblioteka.repository.BookRepository;
import org.biblioteka.repository.RentalRepository;
import org.biblioteka.thread.RequestContext;

import java.time.LocalDate;

public class BorrowBookUseCase implements UseCase<JsonRequest<SimpleRental>, JsonResponse<SimpleRental>> {
    private final RentalRepository rentalRepository = RentalRepository.getInstance();
    private final BookRepository bookRepository = BookRepository.getInstance();

    @Override
    public JsonResponse<SimpleRental> execute(RequestContext requestContext) {
        SimpleRental form = JsonRequest
                .fromRawRequest(requestContext.getRequest(), SimpleRental.class)
                .getBody();

        Rental rent = new Rental();

        rent.setToday(LocalDate.now());
        rent.setCopyId(form.getCopyId());
        rent.setUserId(form.getUserId());
        rent.setUntil(LocalDate.parse(form.getUntil()));

        Rental rental = rentalRepository.add(rent);
        bookRepository.markBookRented(form.getCopyId());


        SimpleRental result = new SimpleRental();
        result.setCopyId(rental.getCopyId());
        result.setUserId(rental.getUserId());
        result.setUntil(rental.getUntil().toString());

        return JsonResponse.ok(requestContext.getProtocol(), result);
    }
}
