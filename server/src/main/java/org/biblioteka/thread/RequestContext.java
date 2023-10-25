package org.biblioteka.thread;

import org.biblioteka.auth.UserAuthInfo;
import org.biblioteka.http.RawRequest;

public class RequestContext {
    private final RawRequest request;
    private final UserAuthInfo userAuthInfo;

    public RequestContext(RawRequest request, UserAuthInfo userAuthInfo) {
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

    public RawRequest getRequest() {
        return request;
    }

    public UserAuthInfo getUserAuthInfo() {
        return userAuthInfo;
    }
}
