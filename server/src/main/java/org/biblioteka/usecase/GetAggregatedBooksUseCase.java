package org.biblioteka.usecase;

import org.biblioteka.http.*;
import org.biblioteka.model.AggregatedBooks;
import org.biblioteka.repository.BookRepository;
import org.biblioteka.thread.RequestContext;

import java.util.List;

public class GetAggregatedBooksUseCase implements UseCase<Request<Void>, JsonResponse<List<AggregatedBooks>>> {
    @Override
    public JsonResponse<List<AggregatedBooks>> execute(RequestContext requestContext) {
        List<AggregatedBooks> books = BookRepository.getInstance().searchAggregatedBooks(null);
        return JsonResponse.ok(requestContext.getProtocol(), books);
    }
}
