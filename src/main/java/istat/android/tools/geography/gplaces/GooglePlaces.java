package istat.android.tools.geography.gplaces;

import java.io.IOException;
import java.net.URISyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import istat.android.network.util.ToolKits;
import istat.android.network.http.SimpleHttpQuery;



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
 * @author Toukea Tatsi (Istat)
 */
public class GooglePlaces {
    public static final String PLACES_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/search/json";
    public static final String PLACES_TEXT_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/search/json";
    public static final String PLACES_DETAILS_URL = "https://maps.googleapis.com/maps/api/place/details/json";
    private SimpleHttpQuery http;
    private final static String
            PLACE_KEY = "key",
            PLACE_SENSOR = "sensor";
    String lastPageToken = "";
    PlaceFilter lastPlaceFilter;
    private String key = "";


    public GooglePlaces(String key) {
        http = new SimpleHttpQuery();
        this.key = key;
    }

    public PlacesList search(PlaceFilter filter) throws URISyntaxException, IOException, JSONException, GooglePlaceException {
        http.clearExtraData();
        addFilterParams(filter);
        JSONObject jObject = new JSONObject(
                ToolKits.Stream.streamToString(
                        http.doGet(PLACES_SEARCH_URL))
        );
        Log.d("JsonResult", http.getURL(PLACES_SEARCH_URL));
        PlacesList placesList = new PlaceJSONParser().parse(jObject);
        ;
        lastPageToken = placesList.getPagetoken();
        lastPlaceFilter = filter;
        return placesList;
    }

    public PlacesList nextPageSearch() throws URISyntaxException, IOException, JSONException, GooglePlaceException {
        //addFilterParams(lastPlaceFilter);
        http.addParam(PlaceFilter.PLACE_PAGE_TOKEN, lastPageToken);
        JSONObject jObject = new JSONObject(
                ToolKits.Stream.streamToString(
                        http.doGet(PLACES_SEARCH_URL))
        );
        http.removeParam(PlaceFilter.PLACE_PAGE_TOKEN);
        Log.d("JsonResult", http.getURL(PLACES_SEARCH_URL));
        PlacesList placesList = new PlaceJSONParser().parse(jObject);
        ;
        lastPageToken = placesList.getPagetoken();
        return placesList;
    }

    /*public PlacesList getPlaceDetails(String reference) throws ClientProtocolException, URISyntaxException, IOException, JSONException, GooglePlaceException{
        http.addParam("reference",reference);
        JSONObject jObject=new JSONObject(
                ToolKits.Stream.streamToString(
                http.doGet(PLACES_DETAILS_URL))

                );
        Log.d("JsonResult", http.getURL(PLACES_DETAILS_URL));
        return new PlaceJSONParser().parse(jObject);
    }*/
    public PlacesList getPlaceDetails(String place_id) throws URISyntaxException, IOException, JSONException, GooglePlaceException {
        http.clearExtraData();
        http.addParam("placeid", place_id);
        JSONObject jObject = new JSONObject(
                ToolKits.Stream.streamToString(
                        http.doGet(PLACES_DETAILS_URL))

        );
        Log.d("JsonResult", http.getURL(PLACES_DETAILS_URL));
        return new PlaceJSONParser().parse(jObject);
    }

    public void stopSearching() {
        http.shutDownConnection();
    }

    public void setTimeOut(int time) {
        http.setConnectionTimeOut(time);
    }

    private void preparPlaceQuery() {
        http.addParam(PLACE_KEY, key);
        http.addParam(PLACE_SENSOR, "false");
    }

    private void addFilterParams(PlaceFilter filter) {
        preparPlaceQuery();
        if (filter.keyword != null)
            http.addParam(PlaceFilter.PLACE_KEYWORD, filter.keyword);
        if (filter.maxprice != null)
            http.addParam(PlaceFilter.PLACE_MAXPRICE, filter.maxprice);
        if (filter.minprice != null)
            http.addParam(PlaceFilter.PLACE_MINPRICE, filter.minprice);
        if (filter.name != null)
            http.addParam(PlaceFilter.PLACE_NAME, filter.name);
        if (filter.opennow != null)
            http.addParam(PlaceFilter.PLACE_OPENNOW, filter.opennow);
        if (filter.location != null)
            http.addParam(PlaceFilter.PLACE_LOCATION, filter.locationToString());
        if (filter.type != null && filter.type.length > 0)
            http.addParam(PlaceFilter.PLACE_TYPES, filter.typesToString());

        if (filter.pagetoken != null)
            http.addParam(PlaceFilter.PLACE_PAGE_TOKEN, filter.pagetoken);


        if (filter.isRankByEnable()) {
            http.addParam(PlaceFilter.PLACE_RANKBY_ENABLE, "distance");
            http.addParam(PlaceFilter.PLACE_RANKBY, "" + filter.radius);

        } else {
            http.removeParam(PlaceFilter.PLACE_RANKBY_ENABLE);
            http.addParam(PlaceFilter.PLACE_RADIUS, "" + filter.radius);
        }


    }

    public static class GooglePlaceException extends Exception {

        /**
         *
         */
        public static String
                OK = "OK",
                UNKNOWN_ERROR = "UNKNOWN_ERROR",
                ZERO_RESULTS = "ZERO_RESULTS",
                OVER_QUERY_LIMIT = "OVER_QUERY_LIMIT",
                REQUEST_DENIED = "REQUEST_DENIED",
                INVALID_REQUEST = "INVALID_REQUEST",
                NOT_FOUND = "NOT_FOUND";
        public String error_message = "";
        private static final long serialVersionUID = 1L;

        public GooglePlaceException(String label) {
            super(label);


        }

        public GooglePlaceException(String label, String error_message) {
            super(label);
            this.error_message = error_message;
        }

        public GooglePlaceException(String message, Throwable throwable) {
            super(message, throwable);
        }

    }

	/*	
Google api fetch the 20 Result in one page suppose you want to use the next page 20 result then we use the next_page_token from google first pag xml result .

1) https://maps.googleapis.com/maps/api/place/search/xml?location=Enter latitude,Enter Longitude&radius=10000&types=store&hasNextPage=true&nextPage()=true&sensor=false&key=Enter Google_Map_key

in second step you use the first page's next_Page_token data

2)https://maps.googleapis.com/maps/api/place/search/xml?location=Enter Latitude,Enter Longitude&radius=10000&types=store&hasNextPage=true&nextPage()=true&sensor=false&key=enter google_map_key &pagetoken="Enter the first page token Value"*/
}
