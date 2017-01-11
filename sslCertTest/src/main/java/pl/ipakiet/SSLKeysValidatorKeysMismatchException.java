package pl.ipakiet;

public class SSLKeysValidatorKeysMismatchException extends Exception {

    private String message;

    SSLKeysValidatorKeysMismatchException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

