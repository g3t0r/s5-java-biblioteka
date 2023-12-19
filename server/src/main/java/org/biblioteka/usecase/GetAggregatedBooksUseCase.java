package org.biblioteka.usecase;

import org.biblioteka.http.*;
import org.biblioteka.shared.model.AggregatedBooks;
import org.biblioteka.repository.BookRepository;
import org.biblioteka.thread.RequestContext;

import java.util.List;

public class GetAggregatedBooksUseCase implements UseCase<Request<Void>, JsonResponse<List<AggregatedBooks>>> {
    private final static String SEARCH_QUERY_PARAM = "search";
    @Override
    public JsonResponse<List<AggregatedBooks>> execute(RequestContext requestContext) {
        List<AggregatedBooks> books;
        if(requestContext.getQueryParams().containsKey(SEARCH_QUERY_PARAM)) {
            String text = requestContext.getQueryParams().get(SEARCH_QUERY_PARAM);
            books = BookRepository.getInstance().searchAggregatedBooks(text);
        } else {
            books = BookRepository.getInstance().getAggregatedBooks();
        }
        return JsonResponse.ok(requestContext.getProtocol(), books);
    }
}
