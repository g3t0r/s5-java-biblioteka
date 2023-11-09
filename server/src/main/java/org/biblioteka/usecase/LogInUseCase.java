package org.biblioteka.usecase;

import org.biblioteka.config.PasswordEncoder;
import org.biblioteka.exceptions.UnauthorizedException;
import org.biblioteka.http.JsonRequest;
import org.biblioteka.http.JsonResponse;
import org.biblioteka.model.LogInDto;
import org.biblioteka.model.User;
import org.biblioteka.repository.UserRepository;
import org.biblioteka.thread.RequestContext;

public class LogInUseCase implements UseCase<JsonRequest<LogInDto>, JsonResponse<User>> {
    private final PasswordEncoder encoder = PasswordEncoder.getInstance();
    private final UserRepository userRepository = UserRepository.getInstance();
    @Override
    public JsonResponse<User> execute(RequestContext requestContext) {
        LogInDto form = JsonRequest
                .fromRawRequest(requestContext.getRequest(), LogInDto.class)
                .getBody();

        User user = userRepository.findByEmail(form.email);

        if(user == null || !encoder.matches(form.password, user.getPassword())) {
            throw new UnauthorizedException("Incorrect email or password");
        }

        user.setPassword(null);

        return JsonResponse.ok(requestContext.getProtocol(), user);
    }
}
