package org.biblioteka.usecase;

import org.biblioteka.http.JsonRequest;
import org.biblioteka.http.JsonResponse;
import org.biblioteka.model.User;
import org.biblioteka.repository.UserRepository;
import org.biblioteka.shared.model.Role;
import org.biblioteka.shared.model.UserDTO;
import org.biblioteka.thread.RequestContext;

import java.util.List;

public class GetUsersByRoleUseCase implements UseCase<JsonRequest<Void>, JsonResponse<List<UserDTO>>> {
    private final UserRepository userRepository = UserRepository.getInstance();

    @Override
    public JsonResponse<List<UserDTO>> execute(RequestContext requestContext) {
        String query = requestContext.getQueryParams().get("query");
        Role role = Role.fromString(requestContext.getQueryParams().get("role"));

        List<User> users;

        if (query == null) {
            users = userRepository.findUserByRole(role);
        } else {
            users = userRepository.searchUserByRole(role, query);
        }

        return JsonResponse.ok(requestContext.getProtocol(),
                users
                        .stream()
                        .map(User::toDto)
                        .toList()
        );
    }
}
