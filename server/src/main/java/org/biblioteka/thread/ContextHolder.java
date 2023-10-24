package org.biblioteka.thread;

import java.util.HashMap;
import java.util.Map;

public class ContextHolder {

    private static ContextHolder instance;

    public static ContextHolder getInstance() {
        if(instance == null) {
            instance = new ContextHolder();
        }
        return instance;
    }


    public void putContext(RequestContext<?> requestContext) {
        threadContextMap.put(Thread.currentThread().threadId(), requestContext);
    }

    public RequestContext<?> getContext() {
        RequestContext<?> context = threadContextMap.get(Thread.currentThread().threadId());
        if(context == null) {
            throw new IllegalStateException("Context not present");
        }
        return context;
    }

    public void removeContext(RequestContext<?> requestContext) {
        threadContextMap.remove(Thread.currentThread().threadId());
    }

    private Map<Long, RequestContext<?>> threadContextMap = new HashMap<>(5);
}
