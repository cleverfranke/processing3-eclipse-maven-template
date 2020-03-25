package com.cleverfranke;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import processing.core.PApplet;

public class ColorTest {
	
	private final static float COLOR_FLOAT_INCREMENT = 0.01f;
	
	@Test
	public void testGrayscale() {
		PApplet applet = new PApplet();
		
		// Test all valid greyscale values
		for (int i = 0; i < 255; i++) {
			assertEquals(Color.color(i), applet.color(i));
		}
		
		// Test some edge cases
		assertEquals(Color.color(Float.MIN_VALUE), applet.color(0));
		assertEquals(Color.color(-1f), applet.color(0));
		assertEquals(Color.color(256f), applet.color(255));
		assertEquals(Color.color(Float.MAX_VALUE), applet.color(255));
		
	}
	
	@Test
	public void testGrayscaleFloat() {
		PApplet applet = new PApplet();
		
		// Test all valid greyscale values
		for (float f = 0f; f <= 1f; f += COLOR_FLOAT_INCREMENT) {
			assertEquals(Color.color(f), applet.color(f * 255f));
		}
		
		// Test some edge cases
		assertEquals(Color.color(Float.MIN_VALUE), applet.color(0));
		assertEquals(Color.color(-1f), applet.color(0));
		assertEquals(Color.color(256f), applet.color(255));
		assertEquals(Color.color(Float.MAX_VALUE), applet.color(255));
		
	}
	
	@Test
	public void testGrayscaleAlpha() {
		PApplet applet = new PApplet();
		
		// Test all valid greyscale values
		for (float g = 0f; g <= 1f; g += COLOR_FLOAT_INCREMENT) {
			for (float a = 0f; a <= 1f; a += COLOR_FLOAT_INCREMENT) {
				assertEquals(Color.color(g, a), applet.color(g * 255f, a * 255f));
			}
		}
		
	}
	
	@Test
	public void testRgb() {
		PApplet applet = new PApplet();
		
		for (int r = 0; r < 255; r++) {
			for (int g = 0; g < 255; g++) {
				for (int b = 0; b < 255; b++) {
					assertEquals(Color.color(r, g, b), applet.color(r, g, b));
				}
			}
		}
		
	}
	
	@Test
	public void testRgbFloat() {
		PApplet applet = new PApplet();
		
		for (float r = 0f; r <= 1f; r++) {
			for (float g = 0f; g <= 1f; g++) {
				for (float b = 0f; b <= 1f; b++) {
					assertEquals(Color.color(r, g, b), applet.color(r * 255f, g * 255f, b * 255f));
				}
			}
		}
		
	}
	
	@Test
	public void testRgbAlpha() {
		PApplet applet = new PApplet();
		
		for (float r = 0f; r <= 1f; r++) {
			for (float g = 0f; g <= 1f; g++) {
				for (float b = 0f; b <= 1f; b++) {
					for (float a = 0f; a <= 1f; a++) {
						assertEquals(Color.color(r, g, b, a), applet.color(r * 255f, g * 255f, b * 255f, a * 255f));
					}
				}
			}
		}	
	}
	
	@Test
	public void testHex() {
		PApplet applet = new PApplet();
		
		final int increment = 10;
		
		for (int r = 0; r < 255; r += increment) {
			for (int g = 0; g < 255; g+= increment) {
				for (int b = 0; b < 255; b += increment) {
					assertEquals(Color.color(String.format("#%02X%02X%02X", r, g, b)), applet.color(r, g, b)); // #RRGGBB
					assertEquals(Color.color(String.format("#%02x%02x%02x", r, g, b)), applet.color(r, g, b)); // #rrggbb
					assertEquals(Color.color(String.format("%02X%02X%02X", r, g, b)), applet.color(r, g, b)); // RRGGBB
					assertEquals(Color.color(String.format("%02x%02x%02x", r, g, b)), applet.color(r, g, b)); // rrggbb
					
					
					for (int a = 0; a < 255; a += increment) {
						assertEquals(Color.color(String.format("#%02X%02X%02X%02X", r, g, b, a)), applet.color(r, g, b, a)); // #RRGGBBAA
						assertEquals(Color.color(String.format("#%02x%02x%02x%02x", r, g, b, a)), applet.color(r, g, b, a)); // #rrggbbaa
						assertEquals(Color.color(String.format("%02X%02X%02X%02X", r, g, b, a)), applet.color(r, g, b, a)); // RRGGBBAA
						assertEquals(Color.color(String.format("%02x%02x%02x%02x", r, g, b, a)), applet.color(r, g, b, a)); // rrggbbaa
					}
					
				}
			}
		}
	
		// Test some edge cases
		assertEquals(Color.color(""), applet.color(0));
		assertEquals(Color.color("hello world"), applet.color(0));
		assertEquals(Color.color("000000"), applet.color(0));
		assertEquals(Color.color("ffffff"), applet.color(255, 255, 255, 255));
		
	}
	
	@Test
	public void testComponents() {
		
		PApplet applet = new PApplet();
		
		final int increment = 10;
		
		for (int r = 0; r < 255; r += increment) {
			for (int g = 0; g < 255; g+= increment) {
				for (int b = 0; b < 255; b += increment) {
					for (int a = 0; a < 255; a += increment) {
						int color = applet.color(r, g, b, a);
						assertEquals(Color.red(color), r);
						assertEquals(Color.green(color), g);
						assertEquals(Color.blue(color), b);
						assertEquals(Color.alpha(color), a);
					}
				}
			}
		}
		
	}
	
	@Test
	public void testAlpha() {
		
		final int increment = 10;
		
		for (int r = 0; r < 255; r += increment) {
			for (int g = 0; g < 255; g+= increment) {
				for (int b = 0; b < 255; b += increment) {
					
					int color = Color.color(r, g, b);
					
					for (int a = 0; a < 255; a += increment) {
						
						int colorWithAlpha = Color.colorWithAlpha(color, a);
						
						// Test for equal colors
						assertEquals(Color.red(color), Color.red(colorWithAlpha));
						assertEquals(Color.green(color), Color.green(colorWithAlpha));
						assertEquals(Color.blue(color), Color.blue(colorWithAlpha));
						
						// Test for alpha
						assertEquals(Color.alpha(colorWithAlpha), a);
						
					}
				}
			}
		}
		
	}
	
	@Test
	public void testAlphaFloat() {
		
		for (float r = 0; r < 1f; r += COLOR_FLOAT_INCREMENT) {
			for (float g = 0; g < 1f; g+= COLOR_FLOAT_INCREMENT) {
				for (float b = 0; b < 1f; b += COLOR_FLOAT_INCREMENT) {
					
					int color = Color.color(r, g, b);
					
					for (float a = 0; a < 1f; a += COLOR_FLOAT_INCREMENT) {
						
						int colorWithAlpha = Color.colorWithAlpha(color, a);
						
						// Test for equal colors
						assertEquals(Color.red(color), Color.red(colorWithAlpha));
						assertEquals(Color.green(color), Color.green(colorWithAlpha));
						assertEquals(Color.blue(color), Color.blue(colorWithAlpha));
						
						// Test for alpha
						assertEquals(Color.alpha(colorWithAlpha), (int) (a * 255f));
						
					}
				}
			}
		}
		
	}

}
