package org.biblioteka.thread;

import org.biblioteka.auth.UserAuthInfo;
import org.biblioteka.http.Request;

public class RequestContext<R extends Request<?>> {
    private final R request;
    private final UserAuthInfo userAuthInfo;

    public RequestContext(R request, UserAuthInfo userAuthInfo) {
        this.request = request;
        this.userAuthInfo = userAuthInfo;
    }

    @Override
    public String toString() {
        return "RequestContext{" +
                "request=" + request +
                ", userAuthInfo=" + userAuthInfo +
                '}';
    }
}
