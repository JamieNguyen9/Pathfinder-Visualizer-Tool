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

@SuppressWarnings("serial")
public class Window extends JPanel
					implements ActionListener, MouseListener, MouseMotionListener, KeyListener {

	private JFrame window;
	private int size = 25; // length of one side of block
	private char key;
	private boolean hover;
	private Node startNode;
	private Node endNode;
	private Node wall;
	private MenuControl menu;
	private Algorithm a;
	
	// Constructor
	public Window(String title, Algorithm a) {
		
		key = 0;
		startNode = null;
		endNode = null;
		wall = null;
		menu = new MenuControl(this);
		hover = false;
		this.a = a;
		a.addWindow(this);
		
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
		
		menu.addToWindow();
		
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
		
		// draw startNode 
		if(startNode != null) {
			g.setColor(Color.red.brighter());
			g.fillRect(startNode.getCoord()[0], startNode.getCoord()[1], size, size);
		}
		
		
		// draw endNode 
		if(endNode != null) {
			g.setColor(Color.blue.brighter());
			g.fillRect(endNode.getCoord()[0], endNode.getCoord()[1], size, size);
		}
		
		// draw wall
		
		// manage color of panel when mouse is over panel
		if(hover) {
			g.setColor(new Color(154, 173, 173, 115));
		}
		else {
			g.setColor(new Color(196, 207, 207, 115));
		}
		
		// create control panel
		g.fillRect((this.getWidth() / 2) - 151, this.getHeight()-100, 300, 90);
		
		menu.setPos();
		
		
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
			new Window("Pathfinding Visualizer (Algorithm: A*)", new AStar(this, size));
		}
		
		// change algorithm to djikstra
		if(key == KeyEvent.VK_2) {
			window.dispose();
			//new Window("Pathfinding Visualizer (Algorithm: Djikstra's Algorithm)");
		}
		
		// start the algorithm
		if(key == KeyEvent.VK_SPACE) {
			// TODO
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) { key = 0; }

	// Create and Remove node using mouse and keyboard buttons
	public void nodeControl(MouseEvent e) {
		// left click ( add to board )
		if(SwingUtilities.isLeftMouseButton(e)) {
			int xLeftover = e.getX() % size;
			int yLeftover = e.getY() % size;
			
			// startNode
			if(key == 'q') {
				if(startNode == null) { // if it does not exist
					startNode = new Node(e.getX() - xLeftover, e.getY() - yLeftover);
				}
				else { // if it already exists set it to a different block
					startNode.setCoord(e.getX() - xLeftover, e.getY() - yLeftover);
				}
				
				// put it on to the screen
				this.repaint();
			}
			
			// endNode
			if(key ==  'w') {
				if(endNode == null) {
					endNode = new Node(e.getX() - xLeftover, e.getY() - yLeftover);
				}
				else {
					endNode.setCoord(e.getX() - xLeftover, e.getY() - yLeftover);
				}
				
				this.repaint();
			}
			
			// wall
			
			
		}
		// right click ( remove from board )
		if(SwingUtilities.isRightMouseButton(e)) {
			int findX = e.getX() - (e.getX() % size);
			int findY = e.getY() - (e.getY() % size);
			
			// startNode
			if(startNode != null && (startNode.getCoord()[0] == findX && startNode.getCoord()[1] == findY)) {
				startNode = null;
				this.repaint();
			}
			
			// endNode
			if(endNode != null && (endNode.getCoord()[0] == findX && endNode.getCoord()[1] == findY)) {
				endNode = null;
				this.repaint();
			}
			
		}
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
		// Tracks Mouse Movement: checks to see if mouse is at hovered button panel
		
		// check if within x bounds of menu panel
		if(e.getX() > (this.getWidth() / 2) - 151 && e.getX() < this.getWidth() + 300) {
			
			// now within y bounds of menu panel
			if(e.getY() > this.getHeight() - 100 && e.getY() < this.getHeight() - 10) {
				hover = true;
			}
			else {
				hover = false;
			}	
		}
		else {
			hover = false;
		}
		repaint();
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
		new Window("Pathfinding Visualizer (Algorithm: A*)", new AStar(25));
		
	}

}
