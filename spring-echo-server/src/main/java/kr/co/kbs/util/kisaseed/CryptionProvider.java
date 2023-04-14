package kr.co.kbs.util.kisaseed;

public interface CryptionProvider {

    String encrypt(String plaintext);

    String decrypt(String ciphertext);

}
