## 스프링 예외 처리 전략

예외 응답은 항상 동일한 포맷을 가져야한다. 그렇지 않으면 클라이언트에서 예외 처리를 할 때 동일한 로직으로 처리하기 어렵다.

```java
@ExceptionHandler(FooException.class)
public ResponseEntity<ErrorResponse> handle(FooExcetpion e) {
    ...
}
```

반환 타입이 `ResponseEntity<ErrorResponse>` 처럼 무슨 데이터가 어떻게 있는지 쉽게 알 수 있게 구성하는 것이 좋다.

```java
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
    private String message;
    private int status;
    private List<FieldError> erros;
    private String code;
    ...
    
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FieldError {
        private String field;
        private String value;
        private String reason;
        ...
    }
}
```

예외 응답을 POJO 객체로 관리하면 errorResponse.getXXX()로 명확하게 객체에 있는 값을 가져 올 수 있다.

또한 특정 예외에 따라 예외에 맞는 응답 구조를 설계할 수 있다.

```java
@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(FooException.class)
    public ResponseEntity<ErrorReponse> handle(FooException e) {
        ...
    }
    ...
}
```

@ControllerAdvice(@RestControllerAdvice) 어노테이션으로 발생하는 예외를 한 곳에서 처리할 수 있다.

스프링 및 라이브러리 등 자체적으로 발생하는 예외는 적절한 ErrorResponse를 생성하고 @ExceptionHandler를 추가하면 된다.

비즈니스 로직에서 발생한 예외일 경우 상위 예외인 BusinessException를 정의해 통일성 있게 처리하는 것을 목표로 하는 것이 좋다.

상황에 따라 예외가 추가 될 수 있지만 최소한으로 추가될 수 있게 노력해야 한다.

예외 코드는 enum을 이용해 한 곳에서 관리하는 것이 좋다.

```java
public void validateEmail(Email email) {
    if (memberRepositody.existsByEmail(email)) {
        throw new EmailDuplicatedException("사용 중인 이메일입니다.");    
    }
}
```

위 예제 코드처럼 에러 메시지가 코드 전체에 흩어져 있을 경우 메시지의 중복을 방지하기 어렵고 관리도 매우 불편하다.

```java
public enum ErrorCode {
    EAMIL_DUPLICATION(...);
    
    private final String message;
    private final String code;
    private int status;
    
    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
    
}
```

enum을 이용하면 에러 코드를 한 곳에서 관리할 수 있다.

```java
public void validateEmail(Email email) {
    if (memberRepositody.existsByEmail(email)) {
        throw new EmailDuplicatedException(ErrorCode.EMAIL_DUPLICATION);    
    }
}
```

---

#### 📌 Reference

- <https://cheese10yun.github.io/spring-guide-exception/>