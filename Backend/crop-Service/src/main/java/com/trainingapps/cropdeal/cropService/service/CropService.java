package com.trainingapps.cropdeal.cropService.service;

import com.trainingapps.cropdeal.cropService.Exception.CropNotFoundException;
import com.trainingapps.cropdeal.cropService.Model.Crop;

import java.util.List;

public interface CropService {
    public List<Crop> getCrops(int farmerId);
    
    public List<Crop> getAllCrops();

    public Crop getoneCrop(int CropId) throws CropNotFoundException;

    public  Crop addCrop( Crop crop);

    public Crop updateCrop(int cropId,Crop crop) throws CropNotFoundException;

    public String deleteCrop(int CropId) throws CropNotFoundException;

//    public List<Crop> findByName(String CropName) throws CropNotFoundException;

//    public List<Crop> findByType(String CropType) throws CropNotFoundException;

}
