package org.biblioteka.usecase;

import org.biblioteka.config.PasswordEncoder;
import org.biblioteka.exceptions.ValidationException;
import org.biblioteka.http.JsonRequest;
import org.biblioteka.http.JsonResponse;
import org.biblioteka.http.Response;
import org.biblioteka.model.Role;
import org.biblioteka.model.SignUpDto;
import org.biblioteka.model.User;
import org.biblioteka.repository.UserRepository;
import org.biblioteka.thread.RequestContext;

public class SignUpUseCase implements UseCase<JsonRequest<SignUpDto>, Response<Void>>{

    private final PasswordEncoder encoder = PasswordEncoder.getInstance();
    private final UserRepository userRepository = UserRepository.getInstance();

    @Override
    public Response<Void> execute(RequestContext requestContext) {
       SignUpDto form = JsonRequest
               .fromRawRequest(requestContext.getRequest(), SignUpDto.class)
               .getBody();

       if(userRepository.findByEmail(form.email) != null) {
          throw new ValidationException("Email already taken");
       }

        User user = new User();
        user.setName(form.name);
        user.setSurname(form.surname);
        user.setEmail(form.email);
        user.setAddress(form.address);

        String rawPassword = form.password;
        if(rawPassword == null || rawPassword.isBlank() || rawPassword.isEmpty()) {
            throw new ValidationException("Password must not be empty");
        }

        try {
            user.setRole(Role.fromString(form.role));
        } catch(IllegalArgumentException e) {
            throw new ValidationException("Incorrect role: " + form.role);
        }

        user.setPassword(encoder.encode(rawPassword));
        userRepository.add(user);

       return JsonResponse.noContent(requestContext.getProtocol());
    }
}
