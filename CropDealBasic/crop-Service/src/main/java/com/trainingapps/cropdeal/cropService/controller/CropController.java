package com.trainingapps.cropdeal.cropService.controller;

import com.trainingapps.cropdeal.cropService.Exception.CropNotFoundException;
import com.trainingapps.cropdeal.cropService.Model.Crop;
import com.trainingapps.cropdeal.cropService.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/Crop")
public class CropController {

    @Autowired
    private CropService cropServices;

    @GetMapping("/allCrop/{id}")
    public List<Crop> getCrops(@PathVariable int id){
    	int farmerId=id;
        return this.cropServices.getCrops(farmerId);
    }

    @GetMapping("/getCrop/{cropId}")
    public Crop getCrop(@PathVariable Integer cropId){
        try {
            return this.cropServices.getoneCrop(cropId);
        } catch (CropNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
    @GetMapping("/getAllCrops")
    public List<Crop> getAllCrop() {
    	return this.cropServices.getAllCrops();
    }
    
//    @GetMapping("/CropName")
//    public List<Crop> findByName(@PathVariable String CropName){
//        try {
//            return this.cropServices.findByName(CropName);
//        } catch (CropNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @GetMapping("/CropType")
//    public List<Crop> findByType(@PathVariable String CropType){
//        try {
//            return this.cropServices.findByType(CropType);
//        } catch (CropNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @PostMapping("/addCrop")
    public Crop addCrop(@RequestBody Crop crop){
         this.cropServices.addCrop(crop);
         return crop;
    }
    @PutMapping("/updateCrop/{id}")
    public  Crop updateCrop(@PathVariable("id")int cropId,@RequestBody Crop crop){
        try {
            return  this.cropServices.updateCrop(cropId,crop);
        } catch (CropNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @DeleteMapping("/deleteCrop/{cropId}")
    public String deleteCrop(@PathVariable("cropId") int CropId) throws CropNotFoundException{
        return cropServices.deleteCrop(CropId);
    }






}
