package ru.orbot90.guestbook.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.orbot90.guestbook.dao.ImageDao;
import ru.orbot90.guestbook.entities.ImageEntity;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.AdditionalMatchers.aryEq;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * @author Iurii Plevako orbot90@gmail.com
 **/
@ExtendWith(MockitoExtension.class)
class DatabaseImageServiceImplTest {

    @Mock
    private ImageDao imageDao;
    @Mock
    private HashService hashService;
    @InjectMocks
    private DatabaseImageServiceImpl service;

    @Test
    public void shouldStoreImageUsingDao() throws NoSuchAlgorithmException {

        byte[] mockBytes = "testcontent".getBytes();
        when(hashService.calculateHash(aryEq(mockBytes))).thenReturn("mockhash");

        ImageEntity expectedEntity = new ImageEntity();

        expectedEntity.setName("123_mockhash");
        expectedEntity.setImage(mockBytes);
        service.saveImage(mockBytes, 123L);
        verify(imageDao).save(eq(expectedEntity));
    }

    @Test
    public void shouldNotStoreImageWhenWasAlreadyUploadedByUser() throws NoSuchAlgorithmException {

        byte[] mockBytes = "testcontent".getBytes();
        when(hashService.calculateHash(aryEq(mockBytes))).thenReturn("mockhash");
        when(imageDao.findByName(eq("123_mockhash"))).thenReturn(Optional.of(new ImageEntity()));

        ImageEntity expectedEntity = new ImageEntity();

        expectedEntity.setName("123_mockhash");
        expectedEntity.setImage(mockBytes);
        service.saveImage(mockBytes, 123L);
        verifyNoMoreInteractions(imageDao);
    }

    @Test
    public void shouldFindImageByName() {
        ImageEntity expectedEntity = new ImageEntity();
        expectedEntity.setImage("test".getBytes());
        expectedEntity.setName("za_warudo");
        expectedEntity.setId(1L);
        when(imageDao.findByName("za_warudo")).thenReturn(Optional.of(expectedEntity));
        byte[] returnedData = service.getImageByName("za_warudo");
        assertArrayEquals(expectedEntity.getImage(), returnedData, "Returned data is wrong");
    }
}