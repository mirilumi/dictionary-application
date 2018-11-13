package com.german.german.exception;

import com.german.german.services.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ControllerExceptionHandler {

    private final MessageService messageService;

    @ExceptionHandler(value = {Throwable.class})
    public ResponseEntity<CustomError> handleAnyException(Exception ex) {
        log.error("Intercepted a Throwable", ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomError(ErrorCode.SERVER_ERROR, ex.getMessage()));
    }

    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<CustomError> handleCustomException(CustomException ex) {
        log.error("Intercepted an CustomException", ex);
        return respond(ex);
    }



    private ResponseEntity<CustomError> respond(ErrorCode errorCode, MessageKey messageKey, Object... messageTokens) {
        CustomError test = new CustomError(errorCode, messageService.get(messageKey.getName(), messageTokens));
        return ResponseEntity.status(errorCode.getHttpStatus()).body(test);
    }

    private ResponseEntity<CustomError> respond(CustomException ex) {
        return respond(ex.getErrorCode(), ex.getMessageKey(), ex.getMessageTokens());
    }

}
