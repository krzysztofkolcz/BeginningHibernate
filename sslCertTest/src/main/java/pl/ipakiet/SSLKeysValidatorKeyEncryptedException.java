package pl.ipakiet;

public class SSLKeysValidatorKeyEncryptedException extends Exception {

    private String message;

    SSLKeysValidatorKeyEncryptedException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

