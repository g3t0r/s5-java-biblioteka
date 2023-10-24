package org.biblioteka.auth;

import org.biblioteka.exceptions.Http4xxException;
import org.biblioteka.http.Request;

import java.util.Base64;

public class AuthenticationExtractor {

    private AuthenticationExtractor() {}

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

        if(credentials.length != 2) {
            throw Http4xxException.badRequest("Invalid credentials format");
        }

        /*
         * TODO: Sprawdzanie w bazie czy istnieje user o takim username i czy hasło pasuje
         * Hasło powinno być jakoś zahashowane podczas sprawdzenia bo nie powinniśmy trzymać
         * Haseł w plain tekście w bazie
         * */

        return new UserAuthInfo(-1, Role.CUSTOMER);
    }
}
