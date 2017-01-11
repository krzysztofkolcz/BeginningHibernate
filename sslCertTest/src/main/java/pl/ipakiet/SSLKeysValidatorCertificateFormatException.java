package pl.ipakiet;

public class SSLKeysValidatorCertificateFormatException extends Exception {
    private String message;

    SSLKeysValidatorCertificateFormatException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

