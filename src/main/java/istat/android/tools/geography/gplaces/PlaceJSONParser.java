package istat.android.tools.geography.gplaces;

import istat.android.tools.geography.gplaces.GooglePlaces.GooglePlaceException;
import istat.android.tools.geography.gplaces.Place.Geometry;
import istat.android.tools.geography.gplaces.Place.Location;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
class PlaceJSONParser {
 
    /** Receives a JSONObject and returns a list 
     * @throws Exception */
	 PlacesList placesList=new PlacesList();;
    public  PlacesList parse(JSONObject jObject) throws GooglePlaceException{
 
        JSONArray jPlaces = null;
        try {
            /** Retrieves all the elements in the 'places' array */
        	//JSONObject status=jObject.getJSONObject("status");
        	if(!jObject.getString("status").equals(GooglePlaces.GooglePlaceException.OK)) throw new GooglePlaceException (jObject.getString("status"));
        	 placesList.next_place_token=jObject.optString("next_page_token");       	
            jPlaces = jObject.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        /** Invoking getPlaces with the array of json object
        * where each json object represent a place
        */
        return getPlacesList(jPlaces);
    }
 
    private  PlacesList getPlacesList(JSONArray jPlaces){
        int placesCount = jPlaces.length();
        
       // List<Place> placesList = new ArrayList<Place>();
        Place place = null;
 
        /** Taking each place, parses and adds to list object */
        for(int i=0; i<placesCount;i++){
            try {
                /** Call getPlace with place JSON object to parse the place */
                place = getPlace((JSONObject)jPlaces.get(i));
                placesList.getPlaces().add(place);
 
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
 
        return placesList;
    }
 
    /** Parsing the Place JSON object */
    private Place getPlace(JSONObject jPlace){
 
    	Place place=new Place();
 
        try {
            // Extracting Place name, if available
            if(!jPlace.isNull("name")){
                place.name = jPlace.getString("name");
            }
 
            // Extracting Place Vicinity, if available
            if(!jPlace.isNull("vicinity")){
                place.vicinity = jPlace.getString("vicinity");
            }
 
            String latitude = jPlace.getJSONObject("geometry").getJSONObject("location").getString("lat"),
            longitude = jPlace.getJSONObject("geometry").getJSONObject("location").getString("lng");
            
            if(latitude!=null && longitude!=null){
            	 place.geometry=new Geometry().setLocation(new Location(Double.valueOf(latitude),Double.valueOf(longitude)));
            	 
            }
            if(!jPlace.isNull("icon")){
                place.icon = jPlace.getString("types");
            }
            if(!jPlace.isNull("place_id")){
                place.id = jPlace.getString("place_id");
            }
            if(!jPlace.isNull("reference")){
                place.reference = jPlace.getString("reference");
            }
            if(!jPlace.isNull("types")){
            	JSONArray jArray=jPlace.getJSONArray("types");
            	String tmp[] =new String[jArray.length()];
            	for(int i=0;i<jArray.length();i++){
            		tmp[i]=jArray.getString(i);
            		//Log.d("JSParser.getPlace_type", tmp[i]);
            	}
            	place.types=tmp;
            	
            }//else Log.e("JSParser.getPlace_type", "nooo type found");
            
            
           
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return place;
    }
   
}