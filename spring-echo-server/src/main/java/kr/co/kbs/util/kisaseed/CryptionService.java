package kr.co.kbs.util.kisaseed;

public interface CryptionService {

    public String encrypt(String plaintext);

    public String decrypt(String ciphertext);
}
