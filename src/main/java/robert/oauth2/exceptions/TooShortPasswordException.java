package robert.oauth2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static robert.oauth2.exceptions.TooShortPasswordException.MIN_PASSWORD_LENGTH;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Password is too short. It should have at least " + MIN_PASSWORD_LENGTH + " marks.")
public class TooShortPasswordException extends RuntimeException {

    public static final transient int MIN_PASSWORD_LENGTH = 5;

    public TooShortPasswordException() {
        super("Password is too short");
    }
}
