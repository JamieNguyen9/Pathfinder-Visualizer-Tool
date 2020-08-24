package main;

import algorithms.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Window extends JPanel
					implements ActionListener, MouseListener, MouseMotionListener, KeyListener {

	private JFrame window;
	private int size = 25; // length of one side of block
	private char key;
	
	// Constructor
	public Window(String title) {
		
		key = 0;
		
		// Add Listeners
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		
		// Config JPanel
		setLayout(null);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		
		// Create Window
		window = new JFrame();
		window.setTitle(title);
		window.setContentPane(this);
		window.getContentPane().setPreferredSize(new Dimension(1025, 775));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setLocationRelativeTo(null); // null makes it open in the center of the screen
		window.setVisible(true);
		
		this.revalidate();
		this.repaint();
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// create grid
		int height = this.getHeight();
		int width = this.getWidth();
		
		g.setColor(Color.white.darker());
		for(int i = 0; i < height; i+= size) {
			for(int j = 0; j < width; j += size) {
				g.drawRect(j, i, size, size);
			}
		}
		
		g.setColor(Color.black);
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		key = e.getKeyChar();
		
		// terminate program if 'Esc' is pressed
		if(key == KeyEvent.VK_ESCAPE) {
			window.dispose();
			System.exit(0);
		}
		
		// change algorithm to a*
		if(key == KeyEvent.VK_1) {
			window.dispose();
			new Window("Pathfinding Visualizer (Algorithm: A*)");
		}
		
		// change algorithm to djikstra
		if(key == KeyEvent.VK_2) {
			window.dispose();
			new Window("Pathfinding Visualizer (Algorithm: Djikstra's Algorithm)");
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) { key = 0; }

	// Create and Remove node using mouse and keyboard buttons
	public void nodeControl(MouseEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// Invoked when a mouse button is pressed and then dragged
		nodeControl(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		nodeControl(e);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		// Tracks Mouse Movement
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e)  {}

	@Override
	public void mouseExited(MouseEvent e)   {}

	@Override
	public void mousePressed(MouseEvent e)  {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	public static void main(String[] args) { 
		new Window("Pathfinding Visualizer (Algorithm: A*)");
		
	}

}
