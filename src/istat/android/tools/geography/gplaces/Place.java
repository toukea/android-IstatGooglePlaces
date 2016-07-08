package istat.android.tools.geography.gplaces;


import java.util.HashMap;

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
public class Place  {

	
	public String id;
	
	
	public String name;
	
	
	public String reference;
	
	
	public String icon;
	
	
	public String vicinity;
	
	
	public Geometry geometry;
	
	
	public String formatted_address;
	
	
	public String formatted_phone_number;
	
	public String[] types;

	@Override
	public String toString() {
		try{
		return name + " - " + id + " - " + reference+"-"+icon+"-"+types.toString()+"-"+vicinity;
		}catch(Exception e){
			return null;
		}
		}
	
	public Place(){}
	public Place(HashMap<String, String> map){
		
		name=map.get("place_name");
		vicinity=map.get("place_name");
		
		
		
		geometry=new Geometry().
		setLocation(Double.valueOf(map.get("place_name"))
					,Double.valueOf(map.get("place_name")));
		
	}
	
	public static class Geometry
	{
		
		public Location location;
		public Geometry setLocation(double lat,double lng) {
			this.location.lat=lat;
			this.location.lng=lng;
			return this;
		}
		public Geometry setLocation(Location location ) {
			this.location=location;
			
			return this;
		}
	}
	
	public static class Location 
	{
		
		public Location(Double lat2, Double lng2) {
			// TODO Auto-generated constructor stub
			lat=lat2;lng=lng2;
		}
		public Location(){}


		public double lat;
		
		
		public double lng;
	}
	public static class photo
	{
		
		public String reference;
		
		
		public int width;
		
		public int height;
	}
	
	
	
	
}
