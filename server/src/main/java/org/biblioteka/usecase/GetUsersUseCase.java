package org.biblioteka.usecase;

import org.biblioteka.http.JsonRequest;
import org.biblioteka.http.JsonResponse;
import org.biblioteka.model.User;
import org.biblioteka.repository.UserRepository;
import org.biblioteka.shared.model.Role;
import org.biblioteka.shared.model.UserDTO;
import org.biblioteka.thread.RequestContext;

import java.util.List;

public class GetUsersUseCase implements UseCase<JsonRequest<Void>, JsonResponse<List<UserDTO>>> {
    private final UserRepository userRepository = UserRepository.getInstance();

    @Override
    public JsonResponse<List<UserDTO>> execute(RequestContext requestContext) {
        String query = requestContext.getQueryParams().get("query");
        String stringRole = requestContext.getQueryParams().get("role");
        Role role = stringRole == null ? null : Role.fromString(stringRole);

        List<User> users;

        if(query != null && role != null) {
            users = userRepository.searchUserByRole(role, query);
        } else if(query != null) {
            users = userRepository.searchUsers(query);
        } else if(role != null) {
            users = userRepository.findUserByRole(role);
        } else {
            users = userRepository.getAllUsers();
        }

        return JsonResponse.ok(requestContext.getProtocol(),
                users
                        .stream()
                        .map(User::toDto)
                        .toList()
        );
    }
}
