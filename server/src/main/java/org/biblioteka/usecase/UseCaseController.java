package org.biblioteka.usecase;

import org.biblioteka.exceptions.ConfigError;
import org.biblioteka.http.HttpMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UseCaseController {

    private static UseCaseController instance = null;

    private final PathTreeNode root = new PathTreeNode();

    public static UseCaseController getInstance() {
        if (instance == null) {
            instance = new UseCaseController();
        }
        return instance;
    }

    public void registerUseCase(final String uri, final HttpMethod method, final UseCase<?, ?> useCase) {
        PathTreeNode node = root;
        String[] segments = uri
                .replaceFirst("^/", "")
                .split("/", -1);

        for (int i = 0; i < segments.length; i++) {
            PathTreeNode child = new PathTreeNode();
            child.segment = segments[i];
            child = node.addChild(child);
            node = child;
        }

        node.addUseCase(method, useCase);
    }

    public UseCase<?, ?> getUseCase(final String uri, final HttpMethod method, List<String> pathParams) {
        PathTreeNode node = root;
        String[] segments = uri
                .replaceFirst("^/", "")
                .split("/", -1);

        for (int i = 0; i < segments.length; i++) {

            PathTreeNode child = node.children.get(segments[i]);

            if (child == null) {
                child = node.children.get("*");
                if(child != null) {
                    pathParams.add(segments[i]);
                }
            }

            if (child == null) {
                return null;
            }

            node = child;
        }

        return node.methodUseCaseMap.get(method);
    }

    private static class PathTreeNode {

        String segment;
        Map<String, PathTreeNode> children = new HashMap<>(0);

        Map<HttpMethod, UseCase<?, ?>> methodUseCaseMap = new HashMap<>();

        public PathTreeNode addChild(PathTreeNode child) {
            children.putIfAbsent(child.segment, child);
            return children.get(child.segment);
        }

        public void addUseCase(HttpMethod method, UseCase<?, ?> useCase) {
            if (methodUseCaseMap.containsKey(method)) {
                throw new ConfigError("Duplicate useCase for method and path");
            }
            methodUseCaseMap.put(method, useCase);
        }
    }

}
