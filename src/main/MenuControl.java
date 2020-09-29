package main;


import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.KeyStroke;
import javax.swing.JComboBox;


/*
 * Manages the main control panel shown in the bottom of the screen
 */

// FONT: tahoma

public class MenuControl {
	
	private Window window;
	private JLabel speedText, speedNum;
	private JSlider slider;
	private JCheckBox showStep;
	private JButton runStop, clear;
	private JComboBox<String> algoList;
	
	
	public MenuControl(Window w) {
		this.window = w;
		
		// set up labels
		speedText = new JLabel("Speed: ");
		speedText.setName("speedText");
		speedText.setFont(new Font("SansSerif", Font.BOLD, 13));
		speedText.setFocusable(false);
		speedText.setVisible(true);
		
		
		speedNum = new JLabel("0");
		speedNum.setName("speedNum");
		speedNum.setFont(new Font("SansSerif", Font.BOLD, 13));
		speedNum.setFocusable(false);
		speedNum.setVisible(true);
		
		// set up slider
		slider = new JSlider();
		slider.setName("slider");
		slider.setOpaque(false);
		slider.setVisible(true);
		slider.setFocusable(false);
		
		// set up checkbox
		showStep = new JCheckBox("Show Steps");
		showStep.setName("showStep");
		showStep.setFont(new Font("SansSerif", Font.BOLD, 13));
		showStep.setSelected(false);
		showStep.setFocusable(false);
		showStep.setOpaque(false);
		showStep.setVisible(true);
		showStep.setFocusable(false);
		
		// set up button
		runStop = new JButton("Run");
		runStop.setText("Run");
		runStop.setOpaque(true);
		runStop.setVisible(true);
		runStop.setFocusPainted(false);
		runStop.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
		runStop.setFont(new Font("SansSerif", Font.BOLD, 13));
		runStop.setFocusable(false);
		runStop.addActionListener(window);
		
		clear = new JButton("Clear");
		clear.setText("Clear");
		clear.setOpaque(true);
		clear.setVisible(true);
		clear.setFocusPainted(false);
		clear.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
		clear.setFont(new Font("SansSerif", Font.BOLD, 14));
		clear.setFocusable(false);
		clear.addActionListener(window);
		
		// set up combo box
		String[] algorithms = {"A* Search", "Dijkstra's Algorithm"};
		algoList = new JComboBox<String>(algorithms);
		algoList.setFont(new Font("SansSerif", Font.BOLD, 14));
		algoList.setVisible(true);
		algoList.setSelectedIndex(0);
		algoList.setFocusable(false);
		algoList.addActionListener(window);
		algoList.setOpaque(false);
		
		
		
	}
	
	// Add all JLabels and JSliders to window - to be used in Window.java
	public void addToWindow() {
		window.add(speedText);
		window.add(speedNum);
		window.add(slider);
		window.add(showStep);
		window.add(runStop);
		window.add(clear);
		window.add(algoList);
	}
	
	public void setPos() {
		// coordinates of the old control panel
		int[] miniW = new int[2];
		miniW[0] = (window.getWidth() / 2) - 151;
		miniW[1] = window.getHeight() - 100;
		
		// set bounds on components - need exact dimensions and placements
		speedText.setBounds(miniW[0] + 10, miniW[1] + 50, 75, 20);
		slider.setBounds(miniW[0] + 5, miniW[1] + 70, 75, 20);
		speedNum.setBounds(miniW[0] + 65, miniW[1] + 50, 30, 20);
		showStep.setBounds(miniW[0] + 95, miniW[1] + 50, 125, 20);
		runStop.setBounds(miniW[0] + 217, miniW[1] + 7, 75, 20);
		clear.setBounds(miniW[0] + 217, miniW[1] + 35, 75, 20);
		algoList.setBounds(miniW[0] + 10, miniW[1] + 5, 200, 25);
	}
	
	public JCheckBox getCheckbox() {
		return showStep;
	}
	
	public JLabel getSpeedNum () {
		return speedNum;
	}
	
	public JSlider getSlider() {
		return slider;
	}
	
	public JButton getRun() {
		return runStop;
	}
	
}
