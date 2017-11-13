
package com.rakuten.bean.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="Item")
public class RakutenCdItemBean{
    
    public RakutenCdItemBean() {}
    private String title;
    private String titleKana;
    private String artistName;
    private String artistNameKana;
    private String label;
    private String size;
    private String jan;
    private String makerCode;
    private String itemCaption;
    private String playList;
    private String salesDate;
    private String itemPrice;
    private String listPrice;   
    private String discountRate;
    private String discountPrice;
    private String itemUrl;
    private String affiliateUrl;
    private String smallImageUrl;
    private String mediumImageUrl;
    private String largeImageUrl;
    private String availability;
    private String postageFlag;
    private String limitedFlag;
    private String reviewCount;
    private String reviewAverage;
    private String booksGenreId;
    
    @XmlElement(name="title")
    public String getTitle() {
    	return this.title;
    }

    public void setTitle(String title) {
    	this.title = title;
    }
 
    @XmlElement(name="titleKana")
    public String getTitleKana() {
    	return this.titleKana;
    }

    public void setTitleKana(String titleKana) {
    	this.titleKana = titleKana;
    }
    
    @XmlElement(name="artistName")
    public String getArtistName() {
    	return this.artistName;
    }

    public void setArtistName(String artistName) {
    	this.artistName = artistName;
    }
    
    @XmlElement(name="artistNameKana")
    public String getArtistNameKana() {
    	return this.artistNameKana;
    }

    public void setArtistNameKana(String artistNameKana) {
    	this.artistNameKana = artistNameKana;
    }
    
    @XmlElement(name="label")
    public String getLabel() {
    	return this.label;
    }

    public void setLabel(String label) {
    	this.label = label;
    }
    
    @XmlElement(name="size")
    public String getSize() {
    	return this.size;
    }

    public void setSize(String size) {
    	this.size = size;
    }
    
    @XmlElement(name="jan")
    public String getJan() {
    	return this.jan;
    }

    public void setJan(String jan) {
    	this.jan = jan;
    }
    
    @XmlElement(name="makerCode")
    public String getMakerCode() {
    	return this.makerCode;
    }

    public void setMakerCode(String makerCode) {
    	this.makerCode = makerCode;
    }
    
    @XmlElement(name="itemCaption")
    public String getItemCaption() {
    	return this.itemCaption;
    }

    public void setItemCaption(String itemCaption) {
    	this.itemCaption = itemCaption;
    }
    
    @XmlElement(name="playList")
    public String getPlayList() {
    	return this.playList;
    }

    public void setPlayList(String playList) {
    	this.playList = playList;
    }
    
    @XmlElement(name="salesDate")
    public String getSalesDate() {
    	return this.salesDate;
    }

    public void setSalesDate(String salesDate) {
    	this.salesDate = salesDate;
    }
    
    @XmlElement(name="itemPrice")
    public String getItemPrice() {
    	return this.itemPrice;
    }

    public void setItemPrice(String itemPrice) {
    	this.itemPrice = itemPrice;
    }
    
    @XmlElement(name="listPrice")
    public String getListPrice() {
    	return this.listPrice;
    }

    public void setListPrice(String listPrice) {
    	this.listPrice = listPrice;
    }
    
    @XmlElement(name="discountRate")
    public String getDiscountRate() {
    	return this.discountRate;
    }

    public void setDiscountRate(String discountRate) {
    	this.discountRate = discountRate;
    }
    
    @XmlElement(name="discountPrice")
    public String getDiscountPrice() {
    	return this.discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
    	this.discountPrice = discountPrice;
    }
    
    @XmlElement(name="itemUrl")
    public String getItemUrl() {
    	return this.itemUrl;
    }

    public void setItemUrl(String itemUrl) {
    	this.itemUrl = itemUrl;
    }
    
    @XmlElement(name="affiliateUrl")
    public String getAffiliateUrl() {
    	return this.affiliateUrl;
    }

    public void setAffiliateUrl(String affiliateUrl) {
    	this.affiliateUrl = affiliateUrl;
    }
    
    @XmlElement(name="smallImageUrl")
    public String getSmallImageUrl() {
    	return this.smallImageUrl;
    }

    public void setSmallImageUrl(String smallImageUrl) {
    	this.smallImageUrl = smallImageUrl;
    }
    
    @XmlElement(name="mediumImageUrl")
    public String getMediumImageUrl() {
    	return this.mediumImageUrl;
    }

    public void setMediumImageUrl(String mediumImageUrl) {
    	this.mediumImageUrl = mediumImageUrl;
    }
    
    @XmlElement(name="largeImageUrl")
    public String getLargeImageUrl() {
    	return this.largeImageUrl;
    }

    public void setLargeImageUrl(String largeImageUrl) {
    	this.largeImageUrl = largeImageUrl;
    }
    
    @XmlElement(name="availability")
    public String getAvailability() {
    	return this.availability;
    }

    public void setAvailability(String availability) {
    	this.availability = availability;
    }
    
    @XmlElement(name="postageFlag")
    public String getPostageFlag() {
    	return this.postageFlag;
    }

    public void setPostageFlag(String postageFlag) {
    	this.postageFlag = postageFlag;
    }
    
    @XmlElement(name="limitedFlag")
    public String getLimitedFlag() {
    	return this.limitedFlag;
    }

    public void setLimitedFlag(String limitedFlag) {
    	this.limitedFlag = limitedFlag;
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
    
    @XmlElement(name="booksGenreId")
    public String getBooksGenreId() {
    	return this.booksGenreId;
    }

    public void setBooksGenreId(String booksGenreId) {
    	this.booksGenreId = booksGenreId;
    }
    
}