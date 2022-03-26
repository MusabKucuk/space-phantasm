package UI;

import Domain.GamePlay.Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class BuildModeGUI extends JFrame implements ActionListener {

	static JPanel panel = new JPanel();
	static JButton start = new JButton("Start");
	static JButton load = new JButton("load");
	static JButton add = new JButton("Add");
	static JButton exit = new JButton("Exit");
	static JButton save = new JButton("Save");
	static JLabel numberofSimpleObs = new JLabel("Number of Simple Obsticles");
	static JLabel numberofFirmObs = new JLabel("Number of Firm Obsticles");
	static JLabel numberofExplosiveObs = new JLabel("Number of Explosive Obsticles");
	static JLabel numberofMagicalObs = new JLabel("Number of Magical Obsticles");
	static JTextField simpleObsNumber = new JTextField(2);
	static JTextField firmObsNumber = new JTextField(2);
	static JTextField explosiveObsNumber = new JTextField(2);
	static JTextField magicalObsNumber = new JTextField(2);
	static JLabel warningMes = new JLabel("");
    RunningModeGUI runningModeGUI;
	JLabel pic = new JLabel(new ImageIcon("Images/ex1.png"));

	public BuildModeGUI() {
		setBounds(300, 200, 1200, 800);
		setResizable(false);
		setTitle("Building Mode");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel.add(save);
		panel.add(load);
		panel.add(numberofSimpleObs);
		panel.add(simpleObsNumber);

		panel.add(numberofFirmObs);
		panel.add(firmObsNumber);

		panel.add(numberofExplosiveObs);
		panel.add(explosiveObsNumber);
		
		panel.add(numberofMagicalObs);
		panel.add(magicalObsNumber);

		panel.add(warningMes);
		panel.add(add);	
		panel.add(start);
		panel.add(exit);

		
		//panel.add(pic);
		panel.setBackground(Color.GRAY);

		add(panel);
		
		save.addActionListener(e -> {

			File file = new File("src/save.txt");
			try (FileWriter fileWriter = new FileWriter(file)) {

				fileWriter.write(Game.getInstance().ObstacleListStateSaver());
				fileWriter.close();
			} catch (IOException x) {
				// Cxception handling
			}

			add(panel);
		});

		add.addActionListener(e -> {
			
			if(simpleObsNumber.getText().equals("")) {
				simpleObsNumber.setText("0");
			}
			if(firmObsNumber.getText().equals("")) {
				firmObsNumber.setText("0");
			}
			if(magicalObsNumber.getText().equals("")) {
				magicalObsNumber.setText("0");
			}
			if(explosiveObsNumber.getText().equals("")){
				explosiveObsNumber.setText("0");
			}			
			
			Game.getInstance().getSimpleObstacle().setSimpleObsNum(Integer.parseInt(simpleObsNumber.getText()));
			Game.getInstance()
					.setSimpleObsArray(Game.getInstance().randomSuperObstacleArrayGenerator(
							Game.getInstance().getSimpleObstacle().getSimpleObsNum(), Integer.parseInt(firmObsNumber.getText()),
							Integer.parseInt(explosiveObsNumber.getText()) , Integer.parseInt(magicalObsNumber.getText())));
			if(Game.getInstance().getSimpleObsArray() != null) warningMes.setText("Go now!!!");
		});

		start.addActionListener(e -> {
			if(Game.getInstance().getSimpleObsArray() != null) {
				this.dispose();
				runningModeGUI = new RunningModeGUI();
				runningModeGUI.setVisible(true);
			} else {
				warningMes.setText("!!!Obstacle size is empty!!!");
			}
			
		});

		load.addActionListener(e -> {
			try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/save.txt"))) {
				String line = bufferedReader.readLine();
				Game.getInstance().generateArrayListfromLoad(line);
				if(Game.getInstance().getSimpleObsArray() != null) warningMes.setText("Go now!!!");
			} catch (FileNotFoundException y) {
				// Exception handling
			} catch (IOException y) {
				// Exception handling
			}
		});
		
		exit.addActionListener(e -> {
			System.exit(-1);
		});
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}
}
