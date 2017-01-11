package pl.ipakiet;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

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

public class App{
        public static void main(String[] args) throws Exception{
                Path path = Paths.get("/home/kkolcz/84352669-4abx.pl.cert");
                byte[] data = Files.readAllBytes(path);
                String sslCertificate = new String(data, "UTF-8");
                System.out.println(sslCertificate);

                /* Path pathKey = Paths.get("/home/kkolcz/84352669-4abx.pl.key"); */
                Path pathKey = Paths.get("/home/kkolcz/84352669-4abx.pl.pkcs8.key");
                byte[] dataKey = Files.readAllBytes(pathKey);
                String sslCertificateKey = new String(dataKey, "UTF-8");
                System.out.println(sslCertificateKey);
                SSLKeysValidator validator = new SSLKeysValidator();
                validator.checkSSLKeys(sslCertificateKey, sslCertificate);
        }
}
