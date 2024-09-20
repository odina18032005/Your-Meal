package uz.pdp.website_yourmeal.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.website_yourmeal.dto.ErrorBodyDto;
import uz.pdp.website_yourmeal.exception.UsernameOrPasswordWrong;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({UsernameOrPasswordWrong.class})
    public ErrorBodyDto usernameOrPasswordWrong(HttpServletRequest request, UsernameOrPasswordWrong exception){
        return new ErrorBodyDto(
                exception.getHttpStatus().value(),
                request.getRequestURI(),
                request.getRequestURL().toString(),
                exception.getClass().toString(),
                exception.getMessage(),
                LocalDateTime.now());
    }
}
