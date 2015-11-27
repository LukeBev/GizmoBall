package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.SaveWindow;
import model.Model;

public class SaveListener implements ActionListener{

	private Model model;
	public static String save_name;

	public SaveListener(Model m) {
		model = m;		
	}
	
	public final void actionPerformed(final ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()){
		case "Save":
			String save_name = SaveWindow.fileName.getText().trim();			
			if(!save_name.equals("")){
				save_name = save_name + ".txt";
				System.out.println("File Name: " + save_name);
				// will need to call the class that saves the file
				SaveWindow.window.dispose();
			}
			else{
				// need to change this to a better way of error handling
				System.out.println("Please enter file name");
			}			
			break;
		case "Cancel":
			System.out.println("Cancel has been clicked");
			//SaveWindow.window.dispose();
			break;
		}		
	}
	
}
