package org.biblioteka.usecase;

import org.biblioteka.config.PasswordEncoder;
import org.biblioteka.exceptions.ValidationException;
import org.biblioteka.http.JsonRequest;
import org.biblioteka.http.JsonResponse;
import org.biblioteka.http.Response;
import org.biblioteka.shared.model.Role;
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

       if(userRepository.findByEmail(form.getEmail()) != null) {
          throw new ValidationException("Email already taken");
       }

        User user = new User();
        user.setName(form.getName());
        user.setSurname(form.getSurname());
        user.setEmail(form.getEmail());
        user.setAddress(form.getAddress());
        user.setPesel(form.getPesel());
        user.setPhone(form.getPhone());

        String rawPassword = form.getPassword();
        if(rawPassword == null || rawPassword.isBlank() || rawPassword.isEmpty()) {
            throw new ValidationException("Password must not be empty");
        }

        try {
            user.setRole(form.getRole());
        } catch(IllegalArgumentException e) {
            throw new ValidationException("Incorrect role: " + form.getRole());
        }

        if(user.getPesel().length() != 11 || !user.getPesel().matches("[0-9]+")) {
            throw new ValidationException("Błędny format peselu");
        }

        if(userRepository.findUserByPesel(form.getPesel()).size() > 0) {
            throw new ValidationException("Pesel już zajęty");
        }

        if(user.getPhone().length() != 9 || !user.getPhone().matches("[0-9]+")) {
            throw new ValidationException("Błędny format numeru telefonu");
        }

        if(userRepository.findUserByPhone(form.getPhone()).size() > 0) {
            throw new ValidationException("Numer telefonu już zajęty");
        }

        user.setPassword(encoder.encode(rawPassword));
        userRepository.add(user);

       return JsonResponse.noContent(requestContext.getProtocol());
    }
}
