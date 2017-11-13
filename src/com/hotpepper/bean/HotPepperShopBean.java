
package com.hotpepper.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class HotPepperShopBean{
    
    public HotPepperShopBean() {}

    private String id;
    private String name;
    private String logo_image;
    private String name_kana;
    private String address;
    private String station_name;
    private String ktai_coupon;
    private HotPepperGourmetCommonBean large_service_area;
    private HotPepperGourmetCommonBean service_area;
    private HotPepperGourmetCommonBean large_area;
    private HotPepperGourmetCommonBean middle_area;
    private HotPepperGourmetCommonBean small_area;
    private String lat;
    private String lng;
    private HotPepperGourmetGenreBean genre;
    private HotPepperGourmetCommonBean sub_genre;
    private HotPepperGourmetCommonBean food;
    private HotPepperGourmetCommonBean sub_food;
    private String budget_memo;
    private String catchCopy;
    private String capacity;
    private String access;
    private String mobile_access;
    private HotPepperGourmetUrlBean urls;
    private String open;
    private String close;
    private String party_capacity;
    private String wifi;
    private String other_memo;
    private String shop_detail_memo;
    private String wedding;
    private String free_drink;
    private String free_food;
    private String private_room;
    private String horigotatsu;
    private String tatami;
    private String card;
    private String non_smoking;
    private String charter;
    private String parking;
    private String barrier_free;
    private String show;
    private String karaoke;
    private String band;
    private String tv;
    private String english;
    private String pet;
    private String child; 
    private HotPepperGourmetCouponUrlBean coupon_urls; 
    private String course; 
    private HotPepperGourmetPhotoBean photo; 
    private String lunch;
    private String midnight;
    
    
    @XmlElement(name="id")
    public String getId() {
    	return this.id;
    }

    public void setId(String id) {
    	this.id = id;
    }
    
    @XmlElement(name="name")
    public String getName() {
    	return this.name;
    }

    public void setName(String name) {
    	this.name = name;
    }
    
    @XmlElement(name="logo_image")
    public String getLogo_image() {
    	return this.logo_image;
    }

    public void setLogo_image(String logo_image) {
    	this.logo_image = logo_image;
    }
    
    @XmlElement(name="name_kana")
    public String getName_kana() {
    	return this.name_kana;
    }

    public void setName_kana(String name_kana) {
    	this.name_kana = name_kana;
    }

    @XmlElement(name="address")
    public String getAddress() {
    	return this.address;
    }

    public void setAddress(String address) {
    	this.address = address;
    }
    
    @XmlElement(name="station_name")
    public String getStation_name() {
    	return this.station_name;
    }

    public void setStation_name(String station_name) {
    	this.station_name = station_name;
    }
    
    @XmlElement(name="ktai_coupon")
    public String getKtai_coupon() {
    	return this.ktai_coupon;
    }

    public void setKtai_coupon(String ktai_coupon) {
    	this.ktai_coupon = ktai_coupon;
    }
    
    @XmlElement(name="large_service_area")
    public HotPepperGourmetCommonBean getLarge_service_area() {
    	return this.large_service_area;
    }

    public void setLarge_service_area(HotPepperGourmetCommonBean large_service_area) {
    	this.large_service_area = large_service_area;
    }
    
    @XmlElement(name="service_area")
    public HotPepperGourmetCommonBean getService_area() {
    	return this.service_area;
    }

    public void setService_area(HotPepperGourmetCommonBean service_area) {
    	this.service_area = service_area;
    }
    
    @XmlElement(name="large_area")
    public HotPepperGourmetCommonBean getLarge_area() {
    	return this.large_area;
    }

    public void setLarge_area(HotPepperGourmetCommonBean large_area) {
    	this.large_area = large_area;
    }
    
    @XmlElement(name="middle_area")
    public HotPepperGourmetCommonBean getMiddle_area() {
    	return this.middle_area;
    }

    public void setMiddle_area(HotPepperGourmetCommonBean middle_area) {
    	this.middle_area = middle_area;
    }
    
    @XmlElement(name="small_area")
    public HotPepperGourmetCommonBean getSmall_area() {
    	return this.small_area;
    }

    public void setSmall_area(HotPepperGourmetCommonBean small_area) {
    	this.small_area = small_area;
    }
    
    @XmlElement(name="lat")
    public String getLat() {
    	return this.lat;
    }

    public void setLat(String lat) {
    	this.lat = lat;
    }
    
    @XmlElement(name="lng")
    public String getLng() {
    	return this.lng;
    }

    public void setLng(String lng) {
    	this.lng = lng;
    }
    
    @XmlElement(name="genre")
    public HotPepperGourmetGenreBean getGenre() {
    	return this.genre;
    }

    public void setGenre(HotPepperGourmetGenreBean genre) {
    	this.genre = genre;
    }
    
    @XmlElement(name="sub_genre")
    public HotPepperGourmetCommonBean getSub_genre() {
    	return this.sub_genre;
    }

    public void setSub_genre(HotPepperGourmetCommonBean sub_genre) {
    	this.sub_genre = sub_genre;
    }
    
    @XmlElement(name="food")
    public HotPepperGourmetCommonBean getFood() {
    	return this.food;
    }

    public void setFood(HotPepperGourmetCommonBean food) {
    	this.food = food;
    }
    
    @XmlElement(name="sub_food")
    public HotPepperGourmetCommonBean getSub_food() {
    	return this.sub_food;
    }

    public void setSub_food(HotPepperGourmetCommonBean sub_food) {
    	this.sub_food = sub_food;
    }
    
    @XmlElement(name="budget_memo")
    public String getBudget_memo() {
    	return this.budget_memo;
    }

    public void setBudget_memo(String budget_memo) {
    	this.budget_memo = budget_memo;
    }
    
    @XmlElement(name="catch")
    public String getCatch() {
    	return this.catchCopy;
    }

    public void setCatch(String catchCopy) {
    	this.catchCopy = catchCopy;
    }
    
    @XmlElement(name="capacity")
    public String getCapacity() {
    	return this.capacity;
    }

    public void setCapacity(String capacity) {
    	this.capacity = capacity;
    }
    
    @XmlElement(name="access")
    public String getAccess() {
    	return this.access;
    }

    public void setAccess(String access) {
    	this.access = access;
    }
    
    
    @XmlElement(name="mobile_access")
    public String getMobile_access() {
    	return this.mobile_access;
    }

    public void setMobile_access(String mobile_access) {
    	this.mobile_access = mobile_access;
    }
    
    @XmlElement(name="urls")
    public HotPepperGourmetUrlBean getUrls() {
    	return this.urls;
    }

    public void setUrls(HotPepperGourmetUrlBean urls) {
    	this.urls = urls;
    }
    
    @XmlElement(name="open")
    public String getOpen() {
    	return this.open;
    }

    public void setOpen(String open) {
    	this.open = open;
    }
    
    @XmlElement(name="close")
    public String getClose() {
    	return this.close;
    }

    public void setClose(String close) {
    	this.close = close;
    }
    
    @XmlElement(name="party_capacity")
    public String getParty_capacity() {
    	return this.party_capacity;
    }

    public void setParty_capacity(String party_capacity) {
    	this.party_capacity = party_capacity;
    }
    
    @XmlElement(name="wifi")
    public String getWifi() {
    	return this.wifi;
    }

    public void setWifi(String wifi) {
    	this.wifi = wifi;
    }
    
    @XmlElement(name="other_memo")
    public String getOther_memo() {
    	return this.other_memo;
    }

    public void setOther_memo(String other_memo) {
    	this.other_memo = other_memo;
    }
    
    @XmlElement(name="shop_detail_memo")
    public String getShop_detail_memo() {
    	return this.shop_detail_memo;
    }

    public void setShop_detail_memo(String shop_detail_memo) {
    	this.shop_detail_memo = shop_detail_memo;
    }
    
    @XmlElement(name="wedding")
    public String getWedding() {
    	return this.wedding;
    }

    public void setWedding(String wedding) {
    	this.wedding = wedding;
    }
    
    @XmlElement(name="free_drink")
    public String getFree_drink() {
    	return this.free_drink;
    }

    public void setFree_drink(String free_drink) {
    	this.free_drink = free_drink;
    }
    
    @XmlElement(name="free_food")
    public String getFree_food() {
    	return this.free_food;
    }

    public void setFree_food(String free_food) {
    	this.free_food = free_food;
    }
    
    @XmlElement(name="private_room")
    public String getPrivate_room() {
    	return this.private_room;
    }

    public void setPrivate_room(String private_room) {
    	this.private_room = private_room;
    }
    
    @XmlElement(name="horigotatsu")
    public String getHorigotatsu() {
    	return this.horigotatsu;
    }

    public void setHorigotatsu(String horigotatsu) {
    	this.horigotatsu = horigotatsu;
    }
    
    @XmlElement(name="tatami")
    public String getTatami() {
    	return this.tatami;
    }

    public void setTatami(String tatami) {
    	this.tatami = tatami;
    }
    
    @XmlElement(name="card")
    public String getCard() {
    	return this.card;
    }

    public void setCard(String card) {
    	this.card = card;
    }
    
    @XmlElement(name="non_smoking")
    public String getNon_smoking() {
    	return this.non_smoking;
    }

    public void setNon_smoking(String non_smoking) {
    	this.non_smoking = non_smoking;
    }
    
    @XmlElement(name="charter")
    public String getCharter() {
    	return this.charter;
    }

    public void setCharter(String charter) {
    	this.charter = charter;
    }
    
    @XmlElement(name="parking")
    public String getParking() {
    	return this.parking;
    }

    public void setParking(String parking) {
    	this.parking = parking;
    }
    
    @XmlElement(name="barrier_free")
    public String getBarrier_free() {
    	return this.barrier_free;
    }

    public void setBarrier_free(String barrier_free) {
    	this.barrier_free = barrier_free;
    }
    
    @XmlElement(name="show")
    public String getShow() {
    	return this.show;
    }

    public void setShow(String show) {
    	this.show = show;
    }
    
    @XmlElement(name="karaoke")
    public String getKaraoke() {
    	return this.karaoke;
    }

    public void setKaraoke(String karaoke) {
    	this.karaoke = karaoke;
    }
    
    @XmlElement(name="band")
    public String getBand() {
    	return this.band;
    }

    public void setBand(String band) {
    	this.band = band;
    }
    
    @XmlElement(name="tv")
    public String getTv() {
    	return this.tv;
    }

    public void setTv(String tv) {
    	this.tv = tv;
    }
    
    @XmlElement(name="english")
    public String getEnglish() {
    	return this.english;
    }

    public void setEnglish(String english) {
    	this.english = english;
    }
    
    @XmlElement(name="pet")
    public String getPet() {
    	return this.pet;
    }

    public void setPet(String pet) {
    	this.pet = pet;
    }
    
    @XmlElement(name="child")
    public String getChild() {
    	return this.child;
    }

    public void setChild(String child) {
    	this.child = child;
    }
    
    @XmlElement(name="coupon_urls")
    public HotPepperGourmetCouponUrlBean getCoupon_urls() {
    	return this.coupon_urls;
    }

    public void setCoupon_urls(HotPepperGourmetCouponUrlBean coupon_urls) {
    	this.coupon_urls = coupon_urls;
    }
    
    @XmlElement(name="course")
    public String getCourse() {
    	return this.course;
    }

    public void setCourse(String course) {
    	this.course = course;
    }
    
    @XmlElement(name="photo")
    public HotPepperGourmetPhotoBean getPhoto() {
    	return this.photo;
    }

    public void setPhoto(HotPepperGourmetPhotoBean photo) {
    	this.photo = photo;
    }
    
    @XmlElement(name="lunch")
    public String getLunch() {
    	return this.lunch;
    }

    public void setLunch(String lunch) {
    	this.lunch = lunch;
    }
    
    @XmlElement(name="midnight")
    public String getMidnight() {
    	return this.midnight;
    }

    public void setMidnight(String midnight) {
    	this.midnight = midnight;
    }
    
}
