
package com.rakuten.bean.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="Item")
public class RakutenIchibaItemBean{
    
    public RakutenIchibaItemBean() {}
    private String itemName;
    private String catchcopy;
    private String itemCode;
    private String itemPrice;
    private String itemCaption;
    private String itemUrl;
    private String shopUrl;
    private List<String> smallImageUrls;
    private List<String> mediumImageUrls;
    private String affiliateUrl;
    private String shopAffiliateUrl;
    private String imageFlag;
    private String availability;
    private String taxFlag;
    private String postageFlag;
    private String creditCardFlag;
    private String shopOfTheYearFlag;
    private String shipOverseasFlag;
    private String shipOverseasArea;
    private String asurakuFlag;
    private String asurakuClosingTime;
    private String asurakuArea;
    private String affiliateRate;
    private String startTime;
    private String endTime;
    private String reviewCount;
    private String reviewAverage;
    private String pointRate;
    private String pointRateStartTime;
    private String pointRateEndTime;
    private String giftFlag;
    private String shopName;
    private String shopCode;
    private String genreId;
    

    @XmlElement(name="itemName")
    public String getItemName() {
    	return this.itemName;
    }

    public void setItemName(String itemName) {
    	this.itemName = itemName;
    }
 
    @XmlElement(name="catchcopy")
    public String getCatchcopy() {
    	return this.catchcopy;
    }

    public void setCatchcopy(String catchcopy) {
    	this.catchcopy = catchcopy;
    }
    
    @XmlElement(name="itemCode")
    public String getItemCode() {
    	return this.itemCode;
    }

    public void setItemCode(String itemCode) {
    	this.itemCode = itemCode;
    }
    
    @XmlElement(name="itemPrice")
    public String getItemPrice() {
    	return this.itemPrice;
    }

    public void setItemPrice(String itemPrice) {
    	this.itemPrice = itemPrice;
    }
    
    @XmlElement(name="itemCaption")
    public String getItemCaption() {
    	return this.itemCaption;
    }

    public void setItemCaption(String itemCaption) {
    	this.itemCaption = itemCaption;
    }
    
    @XmlElement(name="itemUrl")
    public String getItemUrl() {
    	return this.itemUrl;
    }

    public void setItemUrl(String itemUrl) {
    	this.itemUrl = itemUrl;
    }
    
    @XmlElement(name="shopUrl")
    public String getShopUrl() {
    	return this.shopUrl;
    }

    public void setShopUrl(String shopUrl) {
    	this.shopUrl = shopUrl;
    }
    
    @XmlElementWrapper(name="smallImageUrls")
    @XmlElement(name="imageUrl")
    public List<String> getSmallImageUrls() {
        return smallImageUrls;
    }
    
    public void setSmallImageUrls( List<String> smallImageUrls) {
        this.smallImageUrls = smallImageUrls;
    }
    
    @XmlElementWrapper(name="mediumImageUrls")
    @XmlElement(name="imageUrl")
    public List<String> getMediumImageUrls() {
        return mediumImageUrls;
    }
    
    public void setMediumImageUrls( List<String> mediumImageUrls) {
        this.mediumImageUrls = mediumImageUrls;
    }
    
    @XmlElement(name="affiliateUrl")
    public String getAffiliateUrl() {
    	return this.affiliateUrl;
    }

    public void setAffiliateUrl(String affiliateUrl) {
    	this.affiliateUrl = affiliateUrl;
    }
    
    @XmlElement(name="shopAffiliateUrl")
    public String getShopAffiliateUrl() {
    	return this.shopAffiliateUrl;
    }

    public void setShopAffiliateUrl(String shopAffiliateUrl) {
    	this.shopAffiliateUrl = shopAffiliateUrl;
    }
    
    @XmlElement(name="imageFlag")
    public String getImageFlag() {
    	return this.imageFlag;
    }

    public void setImageFlag(String imageFlag) {
    	this.imageFlag = imageFlag;
    }
    
    @XmlElement(name="availability")
    public String getAvailability() {
    	return this.availability;
    }

    public void setAvailability(String availability) {
    	this.availability = availability;
    }
    
    
    @XmlElement(name="taxFlag")
    public String getTaxFlag() {
    	return this.taxFlag;
    }

    public void setTaxFlag(String taxFlag) {
    	this.taxFlag = taxFlag;
    }
    

    @XmlElement(name="postageFlag")
    public String getPostageFlag() {
    	return this.postageFlag;
    }

    public void setPostageFlag(String postageFlag) {
    	this.postageFlag = postageFlag;
    }
    
    @XmlElement(name="creditCardFlag")
    public String getCreditCardFlag() {
    	return this.creditCardFlag;
    }

    public void setCreditCardFlag(String creditCardFlag) {
    	this.creditCardFlag = creditCardFlag;
    }
    
    @XmlElement(name="shopOfTheYearFlag")
    public String getShopOfTheYearFlag() {
    	return this.shopOfTheYearFlag;
    }

    public void setShopOfTheYearFlag(String shopOfTheYearFlag) {
    	this.shopOfTheYearFlag = shopOfTheYearFlag;
    }
    
    @XmlElement(name="shipOverseasFlag")
    public String getShipOverseasFlag() {
    	return this.shipOverseasFlag;
    }

    public void setShipOverseasFlag(String shipOverseasFlag) {
    	this.shipOverseasFlag = shipOverseasFlag;
    }
    
    @XmlElement(name="shipOverseasArea")
    public String getShipOverseasArea() {
    	return this.shipOverseasArea;
    }

    public void setShipOverseasArea(String shipOverseasArea) {
    	this.shipOverseasArea = shipOverseasArea;
    }


    @XmlElement(name="asurakuFlag")
    public String getAsurakuFlag() {
    	return this.asurakuFlag;
    }

    public void setAsurakuFlag(String asurakuFlag) {
    	this.asurakuFlag = asurakuFlag;
    }
    
    @XmlElement(name="asurakuClosingTime")
    public String getAsurakuClosingTime() {
    	return this.asurakuClosingTime;
    }

    public void setAsurakuClosingTime(String asurakuClosingTime) {
    	this.asurakuClosingTime = asurakuClosingTime;
    }
    
    @XmlElement(name="asurakuArea")
    public String getAsurakuArea() {
    	return this.asurakuArea;
    }

    public void setAsurakuArea(String asurakuArea) {
    	this.asurakuArea = asurakuArea;
    }
    
    @XmlElement(name="affiliateRate")
    public String getAffiliateRate() {
    	return this.affiliateRate;
    }

    public void setAffiliateRate(String affiliateRate) {
    	this.affiliateRate = affiliateRate;
    }
    
    @XmlElement(name="startTime")
    public String getStartTime() {
    	return this.startTime;
    }

    public void setStartTime(String startTime) {
    	this.startTime = startTime;
    }
    
    @XmlElement(name="endTime")
    public String getEndTime() {
    	return this.endTime;
    }

    public void setEndTime(String endTime) {
    	this.endTime = endTime;
    }
    
    @XmlElement(name="reviewCount")
    public String getReviewCount() {
    	return this.reviewCount;
    }

    public void setReviewCount(String reviewCount) {
    	this.reviewCount = reviewCount;
    }
    
    
    @XmlElement(name="reviewAverage")
    public String getReviewAverage() {
    	return this.reviewAverage;
    }

    public void setReviewAverage(String reviewAverage) {
    	this.reviewAverage = reviewAverage;
    }
    
    @XmlElement(name="pointRate")
    public String getPointRate() {
    	return this.pointRate;
    }

    public void setPointRate(String pointRate) {
    	this.pointRate = pointRate;
    }
    
    @XmlElement(name="pointRateStartTime")
    public String getPointRateStartTime() {
    	return this.pointRateStartTime;
    }

    public void setPointRateStartTime(String pointRateStartTime) {
    	this.pointRateStartTime = pointRateStartTime;
    }
    
    @XmlElement(name="pointRateEndTime")
    public String getPointRateEndTime() {
    	return this.pointRateEndTime;
    }

    public void setPointRateEndTime(String pointRateEndTime) {
    	this.pointRateEndTime = pointRateEndTime;
    }
    
    @XmlElement(name="giftFlag")
    public String getGiftFlag() {
    	return this.giftFlag;
    }

    public void setGiftFlag(String giftFlag) {
    	this.giftFlag = giftFlag;
    }
    
    @XmlElement(name="shopName")
    public String getShopName() {
    	return this.shopName;
    }

    public void setShopName(String shopName) {
    	this.shopName = shopName;
    }
    
    @XmlElement(name="shopCode")
    public String getShopCode() {
    	return this.shopCode;
    }

    public void setShopCode(String shopCode) {
    	this.shopCode = shopCode;
    }
    
    @XmlElement(name="genreId")
    public String getGenreId() {
    	return this.genreId;
    }

    public void setGenreId(String genreId) {
    	this.genreId = genreId;
    }
    
    
}