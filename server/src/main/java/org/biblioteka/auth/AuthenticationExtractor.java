package org.biblioteka.auth;

import org.biblioteka.config.PasswordEncoder;
import org.biblioteka.exceptions.Http4xxException;
import org.biblioteka.exceptions.ValidationException;
import org.biblioteka.http.Request;
import org.biblioteka.model.User;
import org.biblioteka.repository.UserRepository;

import java.util.Base64;

public class AuthenticationExtractor {

    private AuthenticationExtractor() {
    }

    private static final String AUTH_HEADER = "Authorization";

    public static UserAuthInfo extractAuthInfo(Request<?> request) {

        String authHeaderValue = request.getHeaders().get(AUTH_HEADER);
        if (authHeaderValue == null) {
            return null;
        }

        String authToken = authHeaderValue.replace("Basic ", "");
        String[] credentials;
        try {
            credentials = new String(Base64.getDecoder().decode(authToken)).split(":");
        } catch (Exception e) {
            throw Http4xxException.badRequest("Unsupported authentication: " + authHeaderValue);
        }

        if (credentials.length != 2) {
            throw Http4xxException.badRequest("Invalid credentials format");
        }

        User user = UserRepository.getInstance().findByEmail(credentials[0]);
        if (user == null) {
            throw ValidationException.badRequest("Invalid email or password");
        }

        if (!PasswordEncoder.getInstance().matches(credentials[1], user.getPassword())) {
            throw ValidationException.badRequest("Invalid email or password");
        }

        return new UserAuthInfo(-1, user.getRole());
    }
}
