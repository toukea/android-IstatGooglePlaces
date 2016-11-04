package istat.android.tools.geography.gplaces;

import java.util.ArrayList;
import java.util.List;

import istat.android.tools.geography.gplaces.Place.Location;
/*
 * Copyright (C) 2014 Istat Dev.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * 
 * @author Toukea Tatsi (Istat)
 *
 */
public class PlaceFilter {
public static String PLACE_KEYWORD="keyword",
PLACE_MINPRICE="minprice",
PLACE_MAXPRICE="maxprice",
PLACE_NAME="name",
PLACE_OPENNOW="opennow",
PLACE_TYPES="types",
PLACE_RADIUS="radius",
PLACE_LOCATION="location",
PLACE_LANGUAGE="language",
PLACE_RANKBY="rankBy",
PLACE_RANKBY_ENABLE="rankby",
PLACE_PAGE_TOKEN="pagetoken"
	;

	
	
	
//--------------------------------------
String type[];
Location location;
int radius=1000;//max 50.000
String keyword;
String name;
String opennow;
String minprice,maxprice,pagetoken;
boolean rankBy=false;

public PlaceFilter setLocation(Location location) {
	this.location = location;
	return this;
}
public PlaceFilter setLocation(Double lat, Double lng) {
	this.location = new Location(lat,lng);
	return this;
}
public PlaceFilter setName(String name) {
	this.name = name;
	return this;
}
public PlaceFilter setRadius(int radius) {
	if(radius>50000) radius=50000;
	if(radius<0) radius=100;
	this.radius = radius;
	return this;
}
public PlaceFilter setKeyword(String keyword) {
	this.keyword = keyword;
	return this;
}
public PlaceFilter setMaxprice(String maxprice) {
	this.maxprice = maxprice;
	return this;
}
public PlaceFilter setMinprice(String minprice) {
	this.minprice = minprice;
	return this;
}
public PlaceFilter setPriceInterval(String maxprice,String minprice){
	this.maxprice = maxprice;
	this.minprice = minprice;
	
	return this;
}
public PlaceFilter setType(String[] type) {
	this.type = type;
	return this;
}

public PlaceFilter setType(TypeFactoty factory) {
	setType(factory.toString());
	return this;
}
public PlaceFilter setType(String type) {
	this.type = type.split("\\|");
	return this;
}

public String typesToString(){
	if(type==null || type.length<0) return null;
	String out="";
	for(int i=0;i<type.length;i++){
		out+=type[i];
		if(i<type.length-1)
		out+="|";		
	}
	
	return out;
}
public String locationToString(){
	return location.lat+","+location.lng;
}
public boolean isRankByEnable() {
	return rankBy;
}
public PlaceFilter setRankByEnable(boolean rankBy) {
	this.rankBy = rankBy;
	return this;
}
public PlaceFilter setPageToken(String token) {
	this.pagetoken=token;
	return this;
}
public int getRadius() {
	return radius;
}
public Location getLocation() {
	return location;
}

public static class TypeFactoty extends ArrayList<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String[] type=new String[size()];
		type=toArray(type);
		if(type==null || type.length<0) return null;
		String out="";
		for(int i=0;i<type.length;i++){
			out+=type[i];
			if(i<type.length-1)
			out+="|";		
		}
		return out;
	}
	public static String
	TYPE_ACCOUNTING ="accounting",
	TYPE_AIRPORT ="airport",
	TYPE_AMUSEMENT_PARK ="amusement_park",
	TYPE_AQUARIUM ="aquarium",
	TYPE_ART_GALLERY ="art_gallery",
	TYPE_ATM ="atm",
	TYPE_BAKERY ="bakery",
	TYPE_BANK ="bank",
	TYPE_BAR ="bar",
	TYPE_BEAUTY_SALON ="beauty_salon",
	TYPE_BICYCLE_STORE ="bicycle_store",
	TYPE_BOOK_STORE ="book_store",
	TYPE_BOWLING_ALLEY ="bowling_alley",
	TYPE_BUS_STATION ="bus_station",
	TYPE_CAFE ="cafe",
	TYPE_CAMPGROUND ="campground",
	TYPE_CAR_DEALER ="car_dealer",
	TYPE_CAR_RENTAL ="car_rental",
	TYPE_CAR_REPAIR ="car_repair",
	TYPE_CAR_WASH ="car_wash",
	TYPE_CASINO ="casino",
	TYPE_CEMETERY ="cemetery",
	TYPE_CHURCH ="church",
	TYPE_CITY_HALL ="city_hall",
	TYPE_CLOTHING_STORE ="clothing_store",
	TYPE_CONVENIENCE_STORE ="convenience_store",
	TYPE_COURTHOUSE ="courthouse",
	TYPE_DENTIST ="dentist",
	TYPE_DEPARTMENT_STORE ="department_store",
	TYPE_DOCTOR ="doctor",
	TYPE_ELECTRICIAN ="electrician",
	TYPE_ELECTRONICS_STORE ="electronics_store",
	TYPE_EMBASSY ="embassy",
	TYPE_ESTABLISHMENT ="establishment",
	TYPE_FINANCE ="finance",
	TYPE_FIRE_STATION ="fire_station",
	TYPE_FLORIST ="florist",
	TYPE_FOOD ="food",
	TYPE_FUNERAL_HOME ="funeral_home",
	TYPE_FURNITURE_STORE ="furniture_store",
	TYPE_GAS_STATION ="gas_station",
	TYPE_GENERAL_CONTRACTOR ="general_contractor",
	TYPE_GROCERY_OR_SUPERMARKET ="grocery_or_supermarket",
	TYPE_GYM ="gym",
	TYPE_HAIR_CARE ="hair_care",
	TYPE_HARDWARE_STORE ="hardware_store",
	TYPE_HEALTH ="health",
	TYPE_HINDU_TEMPLE ="hindu_temple",
	TYPE_HOME_GOODS_STORE ="home_goods_store",
	TYPE_HOSPITAL ="hospital",
	TYPE_INSURANCE_AGENCY ="insurance_agency",
	TYPE_JEWELRY_STORE ="jewelry_store",
	TYPE_LAUNDRY ="laundry",
	TYPE_LAWYER ="lawyer",
	TYPE_LIBRARY ="library",
	TYPE_LIQUOR_STORE ="liquor_store",
	TYPE_LOCAL_GOVERNMENT_OFFICE ="local_government_office",
	TYPE_LOCKSMITH ="locksmith",
	TYPE_LODGING ="lodging",
	TYPE_MEAL_DELIVERY ="meal_delivery",
	TYPE_MEAL_TAKEAWAY ="meal_takeaway",
	TYPE_MOSQUE ="mosque",
	TYPE_MOVIE_RENTAL ="movie_rental",
	TYPE_MOVIE_THEATER ="movie_theater",
	TYPE_MOVING_COMPANY ="moving_company",
	TYPE_MUSEUM ="museum",
	TYPE_NIGHT_CLUB ="night_club",
	TYPE_PAINTER ="painter",
	TYPE_PARK ="park",
	TYPE_PARKING ="parking",
	TYPE_PET_STORE ="pet_store",
	TYPE_PHARMACY ="pharmacy",
	TYPE_PHYSIOTHERAPIST ="physiotherapist",
	TYPE_PLACE_OF_WORSHIP ="place_of_worship",
	TYPE_PLUMBER ="plumber",
	TYPE_POLICE ="police",
	TYPE_POST_OFFICE ="post_office",
	TYPE_REAL_ESTATE_AGENCY ="real_estate_agency",
	TYPE_RESTAURANT ="restaurant",
	TYPE_ROOFING_CONTRACTOR ="roofing_contractor",
	TYPE_RV_PARK ="rv_park",
	TYPE_SCHOOL ="school",
	TYPE_SHOE_STORE ="shoe_store",
	TYPE_SHOPPING_MALL ="shopping_mall",
	TYPE_SPA ="spa",
	TYPE_STADIUM ="stadium",
	TYPE_STORAGE ="storage",
	TYPE_STORE ="store",
	TYPE_SUBWAY_STATION ="subway_station",
	TYPE_SYNAGOGUE ="synagogue",
	TYPE_TAXI_STAND ="taxi_stand",
	TYPE_TRAIN_STATION ="train_station",
	TYPE_TRAVEL_AGENCY ="travel_agency",
	TYPE_UNIVERSITY ="university",
	TYPE_VETERINARY_CARE ="veterinary_care",
	TYPE_ZOO ="zoo";

	
	
	
}





}
