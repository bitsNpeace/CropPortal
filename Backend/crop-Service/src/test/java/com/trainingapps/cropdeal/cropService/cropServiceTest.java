package com.trainingapps.cropdeal.cropService;

import com.trainingapps.cropdeal.cropService.Exception.CropNotFoundException;
import com.trainingapps.cropdeal.cropService.Model.Crop;
import com.trainingapps.cropdeal.cropService.Repo.CropRepo;
import com.trainingapps.cropdeal.cropService.service.CropService;
import com.trainingapps.cropdeal.cropService.service.cropServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class cropServiceTest {

    CropRepo cropRepo;

    private static CropService cropService;


    private static AutoCloseable ac;

    @BeforeEach
    public void doinit() {
        cropRepo = mock(CropRepo.class);
        cropService = new cropServiceImpl(cropRepo);
        ac = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void doAtEnd()throws Exception
    {
        ac.close();
    }

    @Test
    @DisplayName("Test-Save-Farmer")
    void testSaveCrop() {
        Crop cropInput = new Crop(10,"Mango","fruit","this is fruit","12kg","sds",123f,12);
        when(cropRepo.save(cropInput)).thenReturn(cropInput);
        Crop crop_test = cropService.addCrop(cropInput);
        assertEquals(cropInput, crop_test);

    }

    @Test
    @DisplayName("Test-Get All Crop")
    void testGetAllCrop() {
        List<Crop> cropList = mock(List.class);
        when(cropRepo.findAll()).thenReturn(cropList);
        List<Crop> outputCropList = cropService.getAllCrops();
        assertEquals(cropList, outputCropList);
    }

    @Test
    @DisplayName("Test-Get Farmer by Id")
    void testGetCropById() throws CropNotFoundException {
        int input = 10;
        Crop crop = mock(Crop.class);
        Optional<Crop> optional_crop = Optional.of(crop);
        when(cropRepo.findById(input)).thenReturn(optional_crop);
        Optional<Crop> crop_test = Optional.of(cropService.getoneCrop(input));
        assertEquals(optional_crop, crop_test);
    }

    @Test
    @DisplayName("Test-Delete Cr0p")
    void testDeleteCrop(){
        Crop cropInput = new Crop(10,"Mango","fruit","this is fruit","12kg","sds",123f,12);


        Crop output = new Crop(10,"Mango","fruit","this is fruit","12kg","sds",123f,12);

        try {
            doNothing().when(cropRepo).delete(cropInput);

            cropService.deleteCrop(10);

            verify(cropRepo).delete(cropInput);
            assertEquals(cropInput, output);

        } catch (CropNotFoundException e) {

        }



    }

    @Test
    @DisplayName("Test-Update crop")
    public void testUpdate(){
        Crop crop =new Crop(10,"Mango","fruit","this is test","12kg","sds",123f,12);
        Crop crop1 =new Crop(12,"Banana","fruit","this is test","12kg","sds",123f,12);
        when(cropRepo.findById(crop.getCropId())).thenReturn(Optional.of(crop));
        when(cropRepo.save(crop)).thenReturn(crop);

    }

    }



