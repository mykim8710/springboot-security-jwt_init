package com.example.jwtdemo.global.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;

import java.util.Objects;
@Slf4j
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ErrorResponse {
    private int status; 		// http Status(4XX, 5XX....)
    private String code; 		// 지정한 error code
    private String message; 	// error 메세지

    public ErrorResponse(ErrorCode code) {
        this.status = code.getStatus();
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public ErrorResponse(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public static ErrorResponse of(ErrorCode code) {
        return new ErrorResponse(code);
    }

    public static ErrorResponse makeErrorResponse(BindingResult bindingResult) {
        int status = 0;
        String code= "";
        String message = "";
        String validErrorMessage="";

        //에러가 있다면
        if (bindingResult.hasErrors()) {
            // dto에 설정한 message값을 가져온다.
            validErrorMessage = bindingResult.getFieldError().getDefaultMessage();

            // dto에 유효성체크를 걸어놓은 어노테이션명을 가져온다.
            String bindResultCode = bindingResult.getFieldError().getCode();
            log.error("bindResultCode : " +bindResultCode);

            switch (Objects.requireNonNull(bindResultCode)) {
                case "NotBlank":
                    status = ErrorCode.NOT_BLANK.getStatus();
                    code = ErrorCode.NOT_BLANK.getCode();
                    message = validErrorMessage;
                    break;

                case "Email":
                    status = ErrorCode.EMAIL.getStatus();
                    code = ErrorCode.EMAIL.getCode();
                    message = validErrorMessage;
                    break;
            }
        }

        return new ErrorResponse(status, code, message);
    }

}
