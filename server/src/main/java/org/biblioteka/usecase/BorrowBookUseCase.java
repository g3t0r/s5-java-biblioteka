package org.biblioteka.usecase;

import org.biblioteka.exceptions.NotFoundException;
import org.biblioteka.exceptions.ValidationException;
import org.biblioteka.http.JsonRequest;
import org.biblioteka.http.JsonResponse;
import org.biblioteka.model.Rental;
import org.biblioteka.model.User;
import org.biblioteka.repository.UserRepository;
import org.biblioteka.shared.model.RentalRequestDTO;
import org.biblioteka.shared.model.SimpleRental;
import org.biblioteka.repository.BookRepository;
import org.biblioteka.repository.RentalRepository;
import org.biblioteka.thread.RequestContext;

import java.time.LocalDate;

public class BorrowBookUseCase implements UseCase<JsonRequest<RentalRequestDTO>, JsonResponse<Void>> {
    private final RentalRepository rentalRepository = RentalRepository.getInstance();
    private final BookRepository bookRepository = BookRepository.getInstance();

    @Override
    public JsonResponse<Void> execute(RequestContext requestContext) {
        RentalRequestDTO form = JsonRequest
                .fromRawRequest(requestContext.getRequest(), RentalRequestDTO.class)
                .getBody();

        Rental rent = new Rental();

        User user = UserRepository.getInstance().findByEmail(form.getUserEmail());

        if(user == null) {
            throw ValidationException.badRequest("No user account found for given email");
        }

        rent.setToday(LocalDate.now());
        rent.setCopyId(form.getCopyId());
        rent.setUserId(user.getId());
        rent.setUntil(LocalDate.parse(form.getUntil()));

        rentalRepository.add(rent);
        bookRepository.markBookRented(form.getCopyId());

        return JsonResponse.noContent(requestContext.getProtocol());
    }
}
