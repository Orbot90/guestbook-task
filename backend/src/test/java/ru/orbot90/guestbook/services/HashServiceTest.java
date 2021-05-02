package ru.orbot90.guestbook.services;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Iurii Plevako orbot90@gmail.com
 **/
class HashServiceTest {

    private HashService hashService = new HashService();

    @Test
    public void shouldGenerateHash() throws NoSuchAlgorithmException {
        String hash = hashService.calculateHash("testdata".getBytes());
        assertEquals("810ff2fb242a5dee4220f2cb0e6a519891fb67f2f828a6cab4ef8894633b1f50",
                hash, "Wrong hash generated");
    }

    @Test
    public void shouldGenerateUniqueHashForEach() throws NoSuchAlgorithmException {
        String firstHash = hashService.calculateHash("testdata1".getBytes());
        String secondHash = hashService.calculateHash("testdata2".getBytes());
        assertNotEquals(firstHash, secondHash, "Hashes collide");
    }

}