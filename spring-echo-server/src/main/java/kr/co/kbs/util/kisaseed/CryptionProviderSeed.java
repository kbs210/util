package kr.co.kbs.util.kisaseed;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
@ConditionalOnProperty(value = "cryption.seed.enable", havingValue = "true", matchIfMissing = false)
@ConfigurationProperties(prefix = "cryption.seed")
public class CryptionProviderSeed implements CryptionProvider{

    private static byte[] pbszUserKey;
    private static byte[] pbszIV;

    public void setPbszUserKey(String pbszUserKey) throws UnsupportedEncodingException {
        System.out.println("생성자 pbszKey : " + pbszUserKey);
        String a = new String(pbszUserKey.getBytes(StandardCharsets.UTF_8), "UTF-8");
        System.out.println("생성자 pbszKey : " +  a);
        CryptionProviderSeed.pbszUserKey = pbszUserKey.getBytes(StandardCharsets.UTF_8);
    }

    public void setPbszIV(String pbszIv) {
        System.out.println("생성자 pbszIv : " + pbszIv);
        System.out.println("생성자 pbszIv : " + pbszIv.getBytes(StandardCharsets.UTF_8));
        CryptionProviderSeed.pbszIV = pbszIv.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public String encrypt(String plaintext) {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] textToByte = plaintext.getBytes(StandardCharsets.UTF_8);
        System.out.println("encrypt pbszUserKey : " + pbszUserKey.toString());
        System.out.println("encrypt pbszUserKey : " + pbszIV.toString());
        byte[] ciphertext = KISA_SEED_CBC.SEED_CBC_Encrypt(pbszUserKey, pbszIV, textToByte, 0, textToByte.length);

        return new String(encoder.encode(ciphertext), StandardCharsets.UTF_8);
    }

    @Override
    public String decrypt(String ciphertext) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] textToByte = ciphertext.getBytes();
        byte[] decodedText = decoder.decode(textToByte);
        System.out.println("decrypt pbszUserKey : " + pbszUserKey.toString());
        System.out.println("decrypt pbszUserKey : " + pbszIV.toString());
        byte[] plaintext = KISA_SEED_CBC.SEED_CBC_Decrypt(pbszUserKey, pbszIV, decodedText, 0, decodedText.length);

        return new String(plaintext, StandardCharsets.UTF_8);
    }
}
