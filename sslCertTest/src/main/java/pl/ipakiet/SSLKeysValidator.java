package pl.ipakiet;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.*;

public class SSLKeysValidator {

    private RSAPrivateCrtKeySpec privateKeySpec;
    private RSAPublicKeySpec publicKeySpec;
    private Logger log = LogManager.getLogger(SSLKeysValidator.class);

    public void checkSSLKeys(String sslCertificateKey, String sslCertificate) throws SSLKeysValidatorKeyFormatException, SSLKeysValidatorCertificateFormatException, SSLKeysValidatorKeysMismatchException, SSLKeysValidatorKeyEncryptedException {

        try {
            privateKeySpec = getPrivateKeySpec(decodePemPrivateKey(sslCertificateKey), "RSA");
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new SSLKeysValidatorKeyFormatException("Key exception " + e.getMessage());
        }

        try {
            publicKeySpec = getPublicKeyFromCertificate(decodePemCertificate(sslCertificate), "X.509", "RSA");
        } catch (CertificateException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new SSLKeysValidatorCertificateFormatException("Certificate exception " + e.getMessage());
        }

        checkCertificateMatch(privateKeySpec, publicKeySpec);
    }

    private void checkCertificateMatch(RSAPrivateCrtKeySpec privateKeySpec, RSAPublicKeySpec publicKeySpec) throws SSLKeysValidatorKeysMismatchException {
        if (privateKeySpec.getModulus().equals(publicKeySpec.getModulus()) && privateKeySpec.getPublicExponent().equals(publicKeySpec.getPublicExponent())) {
            return;
        }
        throw new SSLKeysValidatorKeysMismatchException("Keys mismatch");
    }

    public byte[] decodePemPrivateKey(String keyBytes) throws SSLKeysValidatorKeyEncryptedException {
        String privKeyPEM = keyBytes.replaceFirst("-----BEGIN (RSA ){0,1}PRIVATE KEY-----\n", "");
        privKeyPEM = privKeyPEM.replace("-----END (RSA ){0,1}PRIVATE KEY-----", "");

        if (privKeyPEM.contains("Proc-Type") && privKeyPEM.contains("ENCRYPTED")) {
            throw new SSLKeysValidatorKeyEncryptedException("Key encrypted");
        }

        Base64 b64 = new Base64();
        return b64.decode(privKeyPEM);
    }

    public RSAPrivateCrtKeySpec getPrivateKeySpec(byte[] decoded, String algorithm) throws NoSuchAlgorithmException, InvalidKeySpecException {// throws Exception {
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance(algorithm);

        return kf.getKeySpec(kf.generatePrivate(spec), RSAPrivateCrtKeySpec.class);
    }

    public byte[] decodePemCertificate(String keyBytes) {
        String cerificatePEM = keyBytes.replace("-----BEGIN CERTIFICATE-----\n", "");
        cerificatePEM = cerificatePEM.replace("-----END CERTIFICATE-----", "");

        Base64 b64 = new Base64();
        return b64.decode(cerificatePEM);
    }

    public RSAPublicKeySpec getPublicKeyFromCertificate(byte[] decoded, String algorithm, String ksAlgorithm) throws CertificateException, NoSuchAlgorithmException, InvalidKeySpecException {
        ByteArrayInputStream bais = new ByteArrayInputStream(decoded);

        CertificateFactory cf = CertificateFactory.getInstance(algorithm);
        Certificate cert = cf.generateCertificate(bais);
        PublicKey publicKey = cert.getPublicKey();

        X509EncodedKeySpec spec = new X509EncodedKeySpec(cert.getPublicKey().getEncoded());

        KeyFactory kf = KeyFactory.getInstance(ksAlgorithm);
        kf.generatePublic(spec);

        return kf.getKeySpec(publicKey, RSAPublicKeySpec.class);
    }
}

