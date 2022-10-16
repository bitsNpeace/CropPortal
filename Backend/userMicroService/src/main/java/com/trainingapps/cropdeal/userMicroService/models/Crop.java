package com.trainingapps.cropdeal.userMicroService.models;

public class Crop {
    private int CropId;
    private String CropName;
    private String CropType;
    private String CropDescription;
    private String CropQuantity;
    private String CropLoaction;
    private double Price;
    private int farmerId;

    public int getFarmerId() {
		return farmerId;
	}

	public void setFarmerId(int farmerId) {
		this.farmerId = farmerId;
	}

	public Crop(int cropId, String cropName, String cropType, String cropDescription, String cropQuantity, String cropLoaction, double price,int farmerId) {
        CropId = cropId;
        CropName = cropName;
        CropType = cropType;
        CropDescription = cropDescription;
        CropQuantity = cropQuantity;
        CropLoaction = cropLoaction;
        Price = price;
        this.farmerId=farmerId;
    }

    @Override
    public String toString() {
        return "Crop{" +
                "CropId=" + CropId +
                ", CropName='" + CropName + '\'' +
                ", CropType='" + CropType + '\'' +
                ", CropDescription='" + CropDescription + '\'' +
                ", CropQuantity='" + CropQuantity + '\'' +
                ", CropLoaction='" + CropLoaction + '\'' +
                ", Price=" + Price +
                '}';
    }


    public Crop(){

    }

    public int getCropId() {
        return CropId;
    }

    public void setCropId(int cropId) {
        CropId = cropId;
    }

    public String getCropName() {
        return CropName;
    }

    public void setCropName(String cropName) {
        CropName = cropName;
    }

    public String getCropType() {
        return CropType;
    }

    public void setCropType(String cropType) {
        CropType = cropType;
    }

    public String getCropDescription() {
        return CropDescription;
    }

    public void setCropDescription(String cropDescription) {
        CropDescription = cropDescription;
    }

    public String getCropQuantity() {
        return CropQuantity;
    }

    public void setCropQuantity(String cropQuantity) {
        CropQuantity = cropQuantity;
    }
    public String getCropLoaction() {
        return CropLoaction;
    }

    public void setCropLoaction(String cropLoaction) {
        CropLoaction = cropLoaction;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }
}