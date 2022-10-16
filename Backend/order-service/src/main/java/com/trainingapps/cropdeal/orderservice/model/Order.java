package com.trainingapps.cropdeal.orderservice.model;

import javax.persistence.*;

@Entity
@Table(name="order1")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long orderId;
    private String quantity;
    private String cropName;
    private double price;
    private int dealerId;
    private String status;
    private int cropId;

    public int getCropId() {
		return cropId;
	}
	public void setCropId(int cropId) {
		this.cropId = cropId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Order() {

    }
    public Order(String quantity, String cropName, double price,int dealerId,String status,int cropId) {
        super();
        this.quantity = quantity;
        this.cropName = cropName;
        this.price = price;
        this.dealerId=dealerId;
        this.status=status;
        this.cropId=cropId;
    }
    public long getOrderId() {
        return orderId;
    }
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public String getCropName() {
        return cropName;
    }
    public void setCropName(String cropName) {
        this.cropName = cropName;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
	public int getDealerId() {
		return dealerId;
	}
	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}


}
