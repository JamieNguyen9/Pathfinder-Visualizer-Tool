package main;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;


/*
 * Manages the main control panel shown in the bottom of the screen
 */

// FONT: tahoma

public class MenuControl {
	
	private Window window;
	private MenuControl mc;
	private JLabel speedText, speedNum;
	private JSlider slider;
	private JCheckBox showStep;
	
	
	public MenuControl(Window w) {
		this.window = w;
		
		// set up labels
		speedText = new JLabel("Speed: ");
		speedText.setName("speedText");
		speedText.setFont(new Font("courier", Font.BOLD, 14));
		speedText.setVisible(true);
		
		
		speedNum = new JLabel("0");
		speedNum.setName("speedNum");
		speedNum.setFont(new Font("courier", Font.BOLD, 14));
		speedText.setVisible(true);
		
		// set up slider
		slider = new JSlider();
		slider.setName("slider");
		slider.setOpaque(false);
		slider.setVisible(true);
		
		// set up checkbox
		showStep = new JCheckBox("Show Steps");
		showStep.setName("showStep");
		showStep.setFont(new Font("courier", Font.BOLD, 14));
		showStep.setSelected(false);
		showStep.setFocusable(false);
		showStep.setOpaque(false);
		showStep.setVisible(true);
		
		
		
	}
	
	// Add all JLabels and JSliders to window - to be used in Window.java
	public void addToWindow() {
		window.add(speedText);
		window.add(speedNum);
		window.add(slider);
		window.add(showStep);
	}
	
	public void setPos() {
		// coordinates of the old control panel
		int[] miniW = new int[2];
		miniW[0] = (window.getWidth() / 2) - 151;
		miniW[1] = window.getHeight() - 100;
		
		// set bounds on components - need exact dimensions and placements
		speedText.setBounds(miniW[0] + 10, miniW[1] + 5, 75, 20);
		slider.setBounds(miniW[0] + 5, miniW[1] + 25, 75, 20);
		speedNum.setBounds(miniW[0] + 65, miniW[1] + 5, 20, 20);
		showStep.setBounds(miniW[0] + 90, miniW[1] + 5, 125, 20);
		
		
		
	}
	
}
