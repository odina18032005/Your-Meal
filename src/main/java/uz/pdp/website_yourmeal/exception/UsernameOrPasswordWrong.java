package uz.pdp.website_yourmeal.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UsernameOrPasswordWrong extends RuntimeException{
    private HttpStatus httpStatus;
    public UsernameOrPasswordWrong(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
