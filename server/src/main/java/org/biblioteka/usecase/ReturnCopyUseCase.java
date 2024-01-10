package org.biblioteka.usecase;

import org.biblioteka.exceptions.ValidationException;
import org.biblioteka.http.JsonRequest;
import org.biblioteka.http.JsonResponse;
import org.biblioteka.repository.BookCopyRepository;
import org.biblioteka.repository.RentalRepository;
import org.biblioteka.repository.UserRepository;
import org.biblioteka.thread.RequestContext;

public class ReturnCopyUseCase implements UseCase<JsonRequest<Void>, JsonResponse<Void>> {

    private final UserRepository userRepository = UserRepository.getInstance();
    private final RentalRepository rentalRepository = RentalRepository.getInstance();
    private final BookCopyRepository copyRepository = BookCopyRepository.getInstance();

    @Override
    public JsonResponse<Void> execute(RequestContext requestContext) {
        Integer copyId;
        try {
             copyId = Integer.parseInt(requestContext.getPathParams().get(0));
        } catch(NumberFormatException e) {
            throw ValidationException.badRequest("Incorrect copyId");
        }

        if(copyRepository.isAvailable(copyId)) {
            throw ValidationException.badRequest("Book not rented");
        }
        copyRepository.updateAvailability(copyId, true);
        rentalRepository.updateReturnDate(copyId);

        return JsonResponse.noContent(requestContext.getProtocol());
    }
}
