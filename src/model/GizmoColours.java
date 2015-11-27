package model;
import java.awt.Color;
import java.util.HashMap;
import java.util.Random;


public class GizmoColours {
	
	public static HashMap<String, Color> String_to_Colour = new HashMap<String, Color>();
	public static HashMap<Color, String> Colour_to_String = new HashMap<Color, String>();
	private String string_colour;
	private Color colour_value;
	
	public GizmoColours(){
		String_to_Colour.put("red", Color.RED);
		String_to_Colour.put("blue", Color.BLUE);
		String_to_Colour.put("green", Color.GREEN);
		String_to_Colour.put("pink", Color.MAGENTA);
		String_to_Colour.put("yellow", Color.YELLOW);
		String_to_Colour.put("orange", Color.ORANGE);
		
		Colour_to_String.put(Color.RED, "red");
		Colour_to_String.put(Color.BLUE, "blue");
		Colour_to_String.put(Color.GREEN, "green");
		Colour_to_String.put(Color.MAGENTA, "pink");
		Colour_to_String.put(Color.YELLOW, "yellow");
		Colour_to_String.put(Color.ORANGE, "orange");
	}
	
	public Color getColourFromString(String colour){
		colour_value = String_to_Colour.get(colour);		
		return colour_value;
	}
	
	public String getStringFromColor(Color colour){
		string_colour = Colour_to_String.get(colour);
		return string_colour;		
	}
	
	public String collideColour(){
		String[] new_colours = {"red", "green", "blue", "pink", "yellow", "orange"};
		Random r = new Random();
		int random = r.nextInt(5-0) + 1;
		String c = new_colours[random];
		return c;
	}
	
}
