package istat.android.tools.geography.gplaces;

import java.util.ArrayList;
import java.util.List;

public class PlacesList {
List<Place> places=new ArrayList<Place>();
String next_place_token;
public boolean hasNextPage(){
	if(next_place_token!=null|| next_place_token.equals("")) return true; else return false;
}
public List<Place> getPlaces() {
	return places;
}
public String getPagetoken() {
	return next_place_token;
}
}
