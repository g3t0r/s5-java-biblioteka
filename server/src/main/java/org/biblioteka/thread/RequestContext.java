package org.biblioteka.thread;

import org.biblioteka.auth.UserAuthInfo;
import org.biblioteka.http.RawRequest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RequestContext {
    private final RawRequest request;
    private final UserAuthInfo userAuthInfo;

    private List<String> pathParams;
    private final Map<String, String> queryParams;

    public RequestContext(RawRequest request, UserAuthInfo userAuthInfo) {
        this.request = request;
        this.userAuthInfo = userAuthInfo;
        String queryString = request.getUri().getQuery();
        if(queryString == null) {
            queryParams = Collections.emptyMap();
        } else {
            queryParams = Arrays.stream(queryString
                    .split("&"))
                    .map(s -> s.split("="))
                    .collect(Collectors.toMap(array -> array[0], array -> array[1]));
        }

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

    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    public List<String> getPathParams() {
        return pathParams;
    }

    public String getProtocol() {
        return request.getProtocol();
    }
}
