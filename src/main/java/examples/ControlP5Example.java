package examples;

import controlP5.ControlP5;
import processing.core.PApplet;

public class ControlP5Example extends PApplet {

	private ControlP5 cp5;

	private int myColorBackground = color(0, 0, 0);

	private int sliderValue = 100;

	@Override
	public void settings() {
		// Add settings here
		size(640, 480);
	}

	@Override
	public void setup() {
		noStroke();
		cp5 = new ControlP5(this);

		cp5.addSlider("sliderValue").setRange(100, 200).setValue(120).setPosition(100, 200).setSize(100, 10);

		cp5.addSlider("slider").setRange(100, 200).setValue(128).setPosition(100, 160).setSize(100, 10);
	}

	@Override
	public void draw() {
		background(myColorBackground);
		fill(sliderValue);
		rect(0, 0, width, 100);
	}

	public void slider(int theColor) {
		myColorBackground = color(theColor);
		println("a slider event. setting background to " + theColor);
		cp5.getController("sliderValue").setValue(theColor);
	}

	public void keyPressed() {
		cp5.getController("sliderValue").setValue(150);
	}

	public static void main(String[] args) {
		// Program execution starts here
		PApplet.main(ControlP5Example.class.getName());
	}

}
