package pl.ipakiet;

public class SSLKeysValidatorKeyFormatException extends Exception {
    private String message;

    SSLKeysValidatorKeyFormatException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

