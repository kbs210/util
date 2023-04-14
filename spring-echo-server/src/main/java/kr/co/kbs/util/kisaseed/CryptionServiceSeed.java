package kr.co.kbs.util.kisaseed;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "cryption.seed.enable", havingValue = "true", matchIfMissing = false)
public class CryptionServiceSeed implements CryptionService{

    private CryptionProvider cryptionProvider;

    public CryptionServiceSeed() {
        this.cryptionProvider = new CryptionProviderSeed();
    }

    public String encrypt(String plaintext) {
        return cryptionProvider.encrypt(plaintext);
    }

    public String decrypt(String ciphertext) {
        return cryptionProvider.decrypt(ciphertext);
    }

}
