package org.biblioteka.usecase;

import org.biblioteka.http.RawRequest;
import org.biblioteka.http.Request;
import org.biblioteka.http.Response;
import org.biblioteka.thread.RequestContext;

public interface UseCase<T extends Request<?>, R extends Response<?>> {
    R execute(RequestContext requestContext);
}
