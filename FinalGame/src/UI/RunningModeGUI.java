package UI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Domain.GamePlay.Game;

@SuppressWarnings("serial")
public class RunningModeGUI extends JFrame {

	static GamePanel GamePanel;

	static JButton restart= new JButton("Restart");
	static JButton pause = new JButton("Pause");
	static JButton resume = new JButton("Resume");
	static JButton exit = new JButton("Exit");
	static JButton save = new JButton("Save");
	static JButton load = new JButton("Load");
	static JButton paddleExpansionAbility = new JButton("Expand Paddle");
	static JButton magicalhex = new JButton("Use Magical Hex");

	public RunningModeGUI() {

		setBounds(300, 200, 1200, 800);
		setResizable(false);
		setTitle("The Unpredictable GAME");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = initializeGamePanel();

		pause.setSize(50, 20);
		resume.setSize(50, 20);
		exit.setSize(50, 20);
		save.setSize(50, 20);
		load.setSize(50, 20);

		add(panel);
		panel.add(save);
		panel.add(load);
		panel.add(pause);
		panel.add(resume);
		panel.add(restart);
		panel.add(exit);
		panel.add(paddleExpansionAbility);
		panel.add(magicalhex);

		resume.setVisible(false);
		save.setVisible(false);
		load.setVisible(false);
		add(panel);

		pause.addActionListener(e -> {
			Game.getInstance().setRunning(false);
			GamePanel.PauseGame();
			pause.setVisible(false);
			resume.setVisible(true);
			save.setVisible(true);
			load.setVisible(true);
			add(panel);
		});

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

		load.addActionListener(e -> {
			try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/save.txt"))) {
				String line = bufferedReader.readLine();
				Game.getInstance().generateArrayListfromLoad(line);

			} catch (FileNotFoundException y) {
				// Exception handling
			} catch (IOException y) {
				// Exception handling
			}
			add(panel);
		});

		resume.addActionListener(e -> {
			Game.getInstance().setRunning(true);
			GamePanel.ResumeGame();
			pause.setVisible(true);
			resume.setVisible(false);
			save.setVisible(false);
			load.setVisible(false);
			add(panel);
		});

		exit.addActionListener(e -> {
			System.exit(-1);
		});
		restart.addActionListener(e -> {     
			this.dispose();	
			BuildModeGUI buildModeGUI = new BuildModeGUI();
			buildModeGUI.setVisible(true);
		});
		
		paddleExpansionAbility.addActionListener(e -> {
			Game.getInstance().getPaddleExpansionAbility().setPressed_T(true);
			if(Game.getInstance().getInventory().contains(0)) {
				Game.getInstance().getPaddleExpansionAbility().paddleExpansion();
				Game.getInstance().getInventory().removeAll(Arrays.asList(0));
			    }
			add(panel);
		});  
		
		magicalhex.addActionListener(e -> {
			Game.getInstance().getMagicalHex().setPressed_H(true);
			if(Game.getInstance().getInventory().contains(1)) {
				Game.getInstance().getMagicalHex().magicalHex();
				Game.getInstance().getInventory().removeAll(Arrays.asList(1));
				Game.getInstance().getMagicalHex().setCannon1PosY(Game.getInstance().getPaddle().getPositionY() - 10);
				Game.getInstance().getMagicalHex().setCannon2PosY(Game.getInstance().getPaddle().getPositionY() - 10);
			    }
			add(panel);
		});  
	}

	private JPanel initializeGamePanel() {
		return GamePanel = new GamePanel();
	}
}
