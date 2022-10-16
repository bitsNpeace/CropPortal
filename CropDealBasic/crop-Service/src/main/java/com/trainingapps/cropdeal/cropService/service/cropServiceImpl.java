package com.trainingapps.cropdeal.cropService.service;

import com.trainingapps.cropdeal.cropService.Exception.CropNotFoundException;
import com.trainingapps.cropdeal.cropService.Model.Crop;
import com.trainingapps.cropdeal.cropService.Repo.CropRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class cropServiceImpl implements CropService {


    Logger logger = Logger.getLogger(cropServiceImpl.class.getName());


    @Autowired
    public CropRepo cropRepo;


    public cropServiceImpl(CropRepo cropRepo) {
        super();
        this.cropRepo = cropRepo;
    }


    @Override
    public List<Crop> getCrops(int farmerId) {
        List<Crop> allcrop = null;
        try {
            allcrop = cropRepo.findByFarmerId(farmerId);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return allcrop;
    }

    @Override
    public Crop getoneCrop(int cropId) throws CropNotFoundException {
        Optional<Crop> optional = cropRepo.findById(cropId);
        try {

            if (optional.isPresent()) {
                return optional.get();
            } else {
                throw new CropNotFoundException("cropId not found ");
            }

        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new CropNotFoundException("id is not persent");
        }
    }


    @Override
    public Crop addCrop(Crop crop) {
        try {
            cropRepo.save(crop);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return crop;
    }

    @Override
    public Crop updateCrop(int cropId,Crop crop) throws CropNotFoundException {
        Optional<Crop> optional = cropRepo.findById(cropId);
        if (optional.isPresent()) {
        	Crop crop1=optional.get();
        	crop1=crop;
            cropRepo.save(crop1);
            return optional.get();
        } else {
            throw new CropNotFoundException("crop not updated");
        }

    }

    @Override
    public String deleteCrop(int cropId) throws CropNotFoundException {
        Optional<Crop> crop = cropRepo.findById(cropId);
        if (crop.isPresent()) {
            cropRepo.deleteById(cropId);
            return "Deleted";
        } else {
            throw new CropNotFoundException("crop not found for delete operation!");
        }
    }
    
    @Override
    public List<Crop> getAllCrops(){
    	return cropRepo.findAll();
    }
    
//    @Override
//    public List<Crop> findByName(String CropName) throws CropNotFoundException {
//        List<Crop> crop = null;
//        try {
//            crop = cropRepo.findByName(CropName);
//        } catch (Exception e) {
//            logger.info(e.getMessage());
//        }
//        return crop;
//    }
//}


//    @Override
//    public List<Crop> findByType(String CropType) throws CropNotFoundException {
//        List<Crop> crop = null;
//        try {
//            crop = cropRepo.findByType(CropType);
//        } catch (Exception e) {
//            logger.info(e.getMessage());
//        }
//        return crop;
//    }
//
//
}

