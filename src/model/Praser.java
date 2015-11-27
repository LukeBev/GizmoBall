package model;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JFileChooser;

import view.BuildModeGUI;


public class Praser {

	private final int L = 25;
	public static String colour;
	public static String connect_id;

	BufferedReader reader;
	public void fileReader(Model gm){
		try {
			//String file = "TestInput";	
			
			String opcode;
			String id;
			int coorX;
			int coorY;
			int absorberX;
			int absorberY;
			
			final JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(fc);
			
			//FileInputStream input = new FileInputStream(file);
			//Use bufferReader next time
			if(fc.getSelectedFile() != null){
			Scanner scanLine = new Scanner(fc.getSelectedFile());
			while (scanLine.hasNextLine()) {
				try{
					String next_line = scanLine.nextLine();
					String[] split_next_line = next_line.split("\\s+");
				    String last_index = split_next_line[split_next_line.length-1];
				    System.out.println(last_index);
					//System.out.println(next_line);
					//opcode = scanLine.next();
					coorX = 0;
					coorY = 0;
				    opcode = split_next_line[0];
				    absorberX = 0;
					absorberY = 0;
					colour = "no colour";
					
					if(opcode.equals("Square")){
						id = split_next_line[1];
						coorX = Integer.parseInt(split_next_line[2])*L;
						coorY = Integer.parseInt(split_next_line[3])*L;
						
						for(char c : last_index.toCharArray()){
							if (!Character.isDigit(c)){
								colour = last_index;
							}
						}
										
						System.out.println("The colour of this gizmo is: " + colour);
						gm.addGizmo(opcode, id, coorX, coorY,25,absorberX,absorberY, colour);
						BuildShapes.square_id++;
					}
					else if(opcode.equals("Triangle")){
						id = split_next_line[1];
						coorX = Integer.parseInt(split_next_line[2])*L;
						coorY = Integer.parseInt(split_next_line[3])*L;
						for(char c : last_index.toCharArray()){
							if (!Character.isDigit(c)){
								colour = last_index;
							}
						}
						gm.addGizmo(opcode, id, coorX, coorY,25,absorberX,absorberY, colour);
						BuildShapes.tri_id++;
					}
					else if(opcode.equals("Circle")){
						id = split_next_line[1];
						coorX = Integer.parseInt(split_next_line[2])*L;
						coorY = Integer.parseInt(split_next_line[3])*L;
						for(char c : last_index.toCharArray()){
							if (!Character.isDigit(c)){
								colour = last_index;
							}
						}
						gm.addGizmo(opcode, id, coorX, coorY,25,absorberX,absorberY, colour);
						BuildShapes.cir_id++;
					}
					else if(opcode.equals("LeftFlipper")){
						id = split_next_line[1];
						coorX = Integer.parseInt(split_next_line[2])*L;
						coorY = Integer.parseInt(split_next_line[3])*L;
						for(char c : last_index.toCharArray()){
							if (!Character.isDigit(c)){
								colour = last_index;
							}
						}
						gm.addGizmo(opcode, id, coorX, coorY,0,absorberX,absorberY, colour);
						BuildShapes.lf_id++;
					}
					else if(opcode.equals("RightFlipper")){
						id = split_next_line[1];
						coorX = Integer.parseInt(split_next_line[2])*L;
						coorY = Integer.parseInt(split_next_line[3])*L;
						for(char c : last_index.toCharArray()){
							if (!Character.isDigit(c)){
								colour = last_index;
							}
						}
						gm.addGizmo(opcode, id, coorX, coorY,0,absorberX,absorberY, colour);
						BuildShapes.rf_id++;
					}
					else if(opcode.equals("Absorber")){
						id = split_next_line[1];
						coorX = Integer.parseInt(split_next_line[2])*L;
						coorY = Integer.parseInt(split_next_line[3])*L;
						absorberX = Integer.parseInt(split_next_line[4])*L;
						absorberY = Integer.parseInt(split_next_line[5])*L;
						for(char c : last_index.toCharArray()){
							if (!Character.isDigit(c)){
								colour = last_index;
							}
						}
						gm.addGizmo(opcode, id, coorX, coorY,absorberX,absorberY,0, colour);
						BuildShapes.ab_id++;
					}
					else if(opcode.equals("Connect")){
						id = split_next_line[1];
						connect_id = split_next_line[2];
						gm.addGizmo(opcode, id, coorX, coorY,25,absorberX,absorberY, colour);
					}
					else if(opcode.equals("KeyConnect")){
						id = split_next_line[1];
						gm.addGizmo(opcode, id, coorX, coorY,25,absorberX,absorberY, colour);
					}
					else if(opcode.equals("Rotate")){
						id = split_next_line[1];
						gm.addGizmo(opcode, id, coorX, coorY,0,absorberX,absorberY, colour);
					}
					else if(opcode.equals("Ball")){
						id = split_next_line[1];
						double cx = Double.parseDouble(split_next_line[2]);
						coorX = (int) cx;
						double cy = Double.parseDouble(split_next_line[3]);
						coorY = (int) cy;						
						gm.addGizmo(opcode, id, coorX, coorY,25,absorberX,absorberY, colour);
					}
				}
				catch(NoSuchElementException e){
					System.out.println("Element not found");
				}
			}
			scanLine.close();
		}
		}

		catch(FileNotFoundException e){
			System.out.println("File not found.");			
		}
		
		//BuildModeGUI bmg = new BuildModeGUI(gm);
		//bmg.buildGUI();
		System.out.println(gm.getBall().size());
		BuildModeGUI.window.repaint();
	}
	
	

	public void fileWriter() {
		//TODO similar to above
	}
}	
