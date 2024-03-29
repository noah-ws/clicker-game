package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.Box;

import model.Clicker;
import control.ButtonClickListener;

public class ClickerSimulator {

	private JFrame window;

	// buttons & spacers
	JButton clickButton = new JButton("Click!"); // main clicky button for game
	JButton marketButton = new JButton("Click Market"); // button to go to market
	JButton statsButton = new JButton("Click Stats"); // button to view stats
	JButton exitButton = new JButton("Exit");
	Component spacer = Box.createRigidArea(new Dimension(15, 0));
	Clicker clicker; // creates clicker class for functionality
	JLabel walletText = new JLabel();
	JLabel clickPerSec = new JLabel("", SwingConstants.CENTER);


	public ClickerSimulator(JFrame window) {
		this.window = window;
		clicker = new Clicker(this);
		clicker.initPassiveClickInfo(); // intializes clickerPassive info array
	}

	// overloaded constructor to pass clicker information
	public ClickerSimulator(JFrame window, Clicker clicker) {
		this.window = window;
		this.clicker = clicker;
	}

	public void init() {
		Container cp = window.getContentPane();

		clicker.setClickSim(this);

		JPanel northPanel = new JPanel(); // north panel for click $
		walletText.setText("Wallet: " + (double) (Math.round(clicker.getWallet() * 10) / 10.0));
		clickPerSec.setText("Clicks per Second: " + (double) (Math.round(clicker.getCPS() * 10) / 10.0));
		//northPanel.setLayout(new GridLayout(2, 1));
		northPanel.add(walletText);
		//northPanel.add(clickPerSec);
		cp.add(BorderLayout.NORTH, northPanel);

		JPanel centerPanel = new JPanel(); // center panel for click button
		//clickButton.setSize(new Dimension(70, 30));
		centerPanel.setLayout(new GridLayout(2, 1));
		centerPanel.add(clickButton);
		centerPanel.add(clickPerSec);
		cp.add(BorderLayout.CENTER, centerPanel);

		JPanel southPanel = new JPanel(); // for click market, stats, settings, and button spacers
		//southPanel.setLayout(new GridLayout(1, 7)); // 1x7 grid for buttons & spacers
		//southPanel.add(spacer);      // **********************
		southPanel.add(marketButton);
		//southPanel.add(spacer);		 // adds buttons & spacing to south panels
		southPanel.add(statsButton);
		//southPanel.add(spacer);
		southPanel.add(exitButton);
		//southPanel.add(spacer);      // **********************
		cp.add(BorderLayout.SOUTH, southPanel);

		/* ****** Action Listeners Below ****** */
		ButtonClickListener buttonClickListener = new ButtonClickListener(this);
		exitButton.addActionListener(buttonClickListener);
		clickButton.addActionListener(buttonClickListener);
		marketButton.addActionListener(buttonClickListener);
		statsButton.addActionListener(buttonClickListener);
	}
	

	/***** Getters Below *****/

	public JButton getClickButton() {
		return clickButton;
	}

	public JButton getMarketButton() {
		return marketButton;
	}

	public JButton getStatsButton() {
		return statsButton;
	}

	public JButton getExitButton() {
		return exitButton;
	}

	public JFrame getWindow() {
		return window;
	}
	
	public Clicker getClicker() {
		return clicker;
	}

	public JLabel getWalletText() {
		return walletText;
	}

}