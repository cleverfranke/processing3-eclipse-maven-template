package com.cleverfranke;

/**
 * Class to statically create Processing compatible colors without
 * relying on PGraphics or PApplet
 */
public class Color {
	
	private Color() {}

	/**
	 * Create grey scale color
	 * 
	 * @param grey [0f, 1f]
	 * @return
	 */
	public static int color(float grey) {
		return color((int) (grey * 255f));
	}
	
	/**
	 * Create grey scale color
	 * 
	 * @param grey [0, 255]
	 * @return
	 */
	public static int color(int grey) {
		return color(grey, grey, grey, 255);
	}
	
	/**
	 * Create grey scale color with alpha
	 * 
	 * @param grey [0f, 1f]
	 * @param a [0f, 1f]
	 * @return
	 */
	public static int color(float grey, float a) {
		return color((int) (grey * 255f), (int) (a * 255f));
	}
	
	/**
	 * Create grey scale color with alpha
	 * 
	 * @param grey [0, 255]
	 * @param a [0, 255]
	 * @return
	 */
	public static int color(int grey, int a) {
		return color(grey, grey, grey, a);
	}
	
	/**
	 * Create color
	 * 
	 * @param r [0f, 1f]
	 * @param g [0f, 1f]
	 * @param b [0f, 1f]
	 * @return
	 */
	public static int color(float r, float g, float b) {
		return color((int) (r * 255f), (int) (g * 255f), (int) (b * 255f));
	}
	
	/**
	 * Create color
	 * 
	 * @param r [0, 255]
	 * @param g [0, 255]
	 * @param b [0, 255]
	 * @return
	 */
	public static int color(int r, int g, int b) {
		return color(r, g, b, 255);
	}
	
	/**
	 * Create color with alpha
	 * 
	 * @param r [0f, 1f]
	 * @param g [0f, 1f]
	 * @param b [0f, 1f]
	 * @param a [0f, 1f]
	 * @return
	 */
	public static int color(float r, float g, float b, float a) {
		return color((int) (r * 255f), (int) (g * 255f), (int) (b * 255f), (int) (a * 255f));
	}
	
	/**
	 * Create color with alpha
	 * 
	 * @param r [0, 255]
	 * @param g [0, 255]
	 * @param b [0, 255]
	 * @param a [0, 255]
	 * @return
	 */
	public static int color(int r, int g, int b, int a) {
		r = clampValue(r);
		g = clampValue(g);
		b = clampValue(b);
		a = clampValue(a);
		return (a << 24) | (r << 16) | (g << 8) | b;
	}
	
	/**
	 * Create color from HEX string
	 * 
	 * @param hex #RRGGBB or #RRGGBBAA
	 * @return
	 */
	/**
	 * Create color from HEX string
	 * 
	 * @param hex #RRGGBB, RRGGBB, #RRGGBBAA or RRGGBBAA
	 * @return
	 */
	public static int color(String hex) {
		
		if (hex.length() == 8) { 
			
			// RRGGBBAA
			return color(
				Integer.valueOf(hex.substring(0, 2), 16),
				Integer.valueOf(hex.substring(2, 4), 16),
				Integer.valueOf(hex.substring(4, 6), 16),
				Integer.valueOf(hex.substring(6, 8), 16));
			
		} else if (hex.length() == 9) {
			
			// #RRGGBBAA, convert to RRGGBBAA
			return color(hex.substring(1));
		
		}else if (hex.length() == 6) {
			
			// RRGGBB, convert to RRGGBBAA
			return color(hex + "FF");
			
		} else if (hex.length() == 7) { 
			
			// #RRGGBB, convert to RRGGBBAA
			return color(hex.substring(1) + "FF");
			
		} else {
			return color(0);
		}
			
	}
	
	/**
	 * Fetch red component from color
	 * 
	 * @param color
	 * @return
	 */
	public static int red(int color) {
		return (color >> 16) & 0xFF;
	}
	
	/**
	 * Fetch green component from color
	 * 
	 * @param color
	 * @return
	 */
	public static int green(int color) {
		return (color >> 8) & 0xFF;
	}
	
	/**
	 * Fetch blue component from color
	 * 
	 * @param color
	 * @return
	 */
	public static int blue(int color) {
		return color& 0xFF;
	}
	
	/**
	 * Fetch alpha component from color
	 * 
	 * @param color
	 * @return
	 */
	public static int alpha(int color) {
		return (color >> 24) & 0xFF;
	}
	
	/**
	 * Apply alpha to existing color
	 * 
	 * @param color
	 * @param a  [0, 255]
	 * @return
	 */
	public static int colorWithAlpha(int color, int a) {
		return color(red(color), green(color), blue(color), a);
	}
	
	/**
	 * Apply alpha to existing color
	 * 
	 * @param color
	 * @param a  [0f, 1f]
	 * @return
	 */
	public static int colorWithAlpha(int color, float a) {
		return color(red(color), green(color), blue(color), (int) (a * 255f));
	}
	
	/**
	 * Clamp v to [0, 255]
	 * @param v
	 * @return
	 */
	private static int clampValue(int v) {
		return Math.min(255, Math.max(0, v));
	}
	
}
