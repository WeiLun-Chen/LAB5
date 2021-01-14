package org.eclipse.om2m.sample.test_ipe.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.om2m.sample.test_ipe.controller.test_ipeController;
import org.osgi.framework.FrameworkUtil;

public class GUI extends JFrame {
	/**
	 * automatically generated
	 */
	private static final long serialVersionUID = 5323077075462722718L;
	/** Logger */
	static Log LOGGER = LogFactory.getLog(GUI.class);
	/** GUI Frame */
	static GUI frame;
	/** GUI Content Panel */
	private JPanel contentPanel;
	/** AIR_ON Icon */
	static ImageIcon iconAirON = new ImageIcon(FrameworkUtil.getBundle(GUI.class).getResource("images/Btn_ON.png"));
	/** AIR_OFF Icon */
	static ImageIcon iconAirOFF = new ImageIcon(FrameworkUtil.getBundle(GUI.class).getResource("images/Btn_OFF.png"));
	/** GUI observer */
	static test_ipeController.Observer obs;
	/** switch button */
	static JButton AirButton = new JButton();
	/** LAMP_1 LABEL */
	static JLabel lbltempGraph = new JLabel("tempGraph");
	static JLabel lblfanGraph = new JLabel("fanGraph");

	/**
	 * Initiate The GUI.
	 */
	public static void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					LOGGER.error("GUI init Error", e);
				}
			}
		});
	}

	/**
	 * Stop the GUI.
	 */
	public static void stop() {
		// SampleModel.deleteObserver(lampObserver);
		frame.setVisible(false);
		frame.dispose();
	}

	/**
	 * Creates the frame.
	 */
	public GUI() {
		setLocationByPlatform(true);
		setVisible(false);
		setResizable(false);
		setTitle("DCNLab test IPE");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screenSize.width - 500) / 2, (screenSize.height - 570) / 2, 497, 570);

		lbltempGraph.setFont(new Font("Vani", Font.BOLD | Font.ITALIC, 30));
		lblfanGraph.setFont(new Font("Vani", Font.BOLD | Font.ITALIC, 30));

		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		// temperature control
		JPanel panel_temp = new JPanel();
		panel_temp.setBounds(10, 5, 319, 260);
		panel_temp.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPanel.add(panel_temp);

		JSplitPane splitPane_graph_temp = new JSplitPane();
		panel_temp.add(splitPane_graph_temp);

		JSplitPane splitPane_temp = new JSplitPane();
		splitPane_temp.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_graph_temp.setLeftComponent(lbltempGraph);
		splitPane_graph_temp.setRightComponent(splitPane_temp);
		panel_temp.add(splitPane_temp);

		JButton btntempPLUS = new JButton("tempPLUS");
		splitPane_temp.setLeftComponent(btntempPLUS);
		// Listener of btntempPLUS
		btntempPLUS.addActionListener(new java.awt.event.ActionListener() {
			// btntempPLUS Button Clicked
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				new Thread() {
					public void run() {
						test_ipeController.setTemp("plus");
					}
				}.start();
			}
		});

		JButton btntempMINUS = new JButton("tempMINUS");
		splitPane_temp.setRightComponent(btntempMINUS);
		// Listener of btntempMINUS
		btntempMINUS.addActionListener(new java.awt.event.ActionListener() {
			// btntempMINUS Button Clicked
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				new Thread() {
					public void run() {
						test_ipeController.setTemp("minus");
					}
				}.start();
			}
		});

		// fan control
		JPanel panel_fan = new JPanel();
		panel_fan.setBounds(10, 271, 319, 260);
		panel_fan.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPanel.add(panel_fan);

		JSplitPane splitPane_graph_fan = new JSplitPane();
		panel_fan.add(splitPane_graph_fan);

		JSplitPane splitPane_fan = new JSplitPane();
		splitPane_fan.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_graph_fan.setLeftComponent(lblfanGraph);
		splitPane_graph_fan.setRightComponent(splitPane_fan);
		panel_fan.add(splitPane_fan);

		JButton btnfanPLUS = new JButton("fanPLUS");
		splitPane_fan.setLeftComponent(btnfanPLUS);
		// Listener of btnfanPLUS
		btnfanPLUS.addActionListener(new java.awt.event.ActionListener() {
			// btnfanPLUS Button Clicked
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				new Thread() {
					public void run() {
						test_ipeController.setFan("plus");
					}
				}.start();
			}
		});

		JButton btnfanMINUS = new JButton("fanMINUS");
		splitPane_fan.setRightComponent(btnfanMINUS);
		// Listener of btnfanMINUS
		btnfanMINUS.addActionListener(new java.awt.event.ActionListener() {
			// btnfanMINUS Button Clicked
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				new Thread() {
					public void run() {
						test_ipeController.setFan("minus");
					}
				}.start();
			}
		});

		// Air switch
		AirButton.setOpaque(false);
		AirButton.setIcon(iconAirOFF);
		AirButton.setBounds(339, 190, 145, 168);
		contentPanel.add(AirButton);
		AirButton.setMinimumSize(new Dimension(30, 23));
		AirButton.setMaximumSize(new Dimension(30, 23));
		AirButton.setPreferredSize(new Dimension(30, 23));

		JLabel labelSwitchAll = new JLabel("ON/OFF");
		labelSwitchAll.setAutoscrolls(true);
		labelSwitchAll.setFont(new Font("Vani", Font.BOLD | Font.ITALIC, 14));
		labelSwitchAll.setFocusCycleRoot(true);
		labelSwitchAll.setBorder(null);
		labelSwitchAll.setBounds(371, 369, 85, 29);
		contentPanel.add(labelSwitchAll);
		// Listener of AirButton
		AirButton.addActionListener(new java.awt.event.ActionListener() {
			// Switch Button Clicked
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// Change all lamps states
				new Thread() {
					public void run() {
						// Send switch all request to create a content with the current State
						test_ipeController.switchAirCon();
					}
				}.start();
			}
		});

		obs = new test_ipeController.Observer() {
			@Override
			public void StateChange(String msg) {
				// TODO Auto-generated method stub
				String sp[] = msg.split(",");
				setLabel(Boolean.parseBoolean(sp[0]), sp[1], sp[2]);
			}
		};
		test_ipeController.setGUIOBSERVER(obs);
	}

	public static void setLabel(boolean newState, String temp, String speed) {
		if (newState == true) {
			AirButton.setIcon(iconAirON);
		} else {
			AirButton.setIcon(iconAirOFF);
		}
		lbltempGraph.setText(temp);
		lblfanGraph.setText(speed);
	}

	/*
	 * main function for test
	 */
	public static void main(String[] args) {
		System.out.println("test");
		GUI.init();
	}
}
