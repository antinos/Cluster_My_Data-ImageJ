package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ij.IJ;

public class dbscan {
	private String epsilon;
	private String minPoints;
	boolean cancelled;
	
	public dbscan() {
		optionsSelect();
		if (cancelled) {
			return;
		}
		
		if (epsilon != null && !("").equals(epsilon) && !epsilon.matches(".*[A-Za-z].*") && Double.valueOf(epsilon) > 0){
			if (minPoints != null && !("").equals(minPoints) && !minPoints.matches(".*[A-Za-z].*") && Double.valueOf(minPoints) > 0) {
				IJ.log("DBSCAN proceeding with epsilon set to "+epsilon+" and minimum points set to "+minPoints+".");
				IJ.run("Cluster My Data", "dbscan epsilon="+epsilon+" min_points="+minPoints);
			} else {
				IJ.log("DBSCAN proceeding with epsilon set to "+epsilon+" and minimum points set to default.");
				IJ.run("Cluster My Data", "dbscan epsilon="+epsilon);
			}
		} else if (minPoints != null && !("").equals(minPoints) && !minPoints.matches(".*[A-Za-z].*") && Double.valueOf(minPoints) > 0) {
			IJ.log("DBSCAN proceeding with epsilon set to default and minimum points set to "+minPoints+".");
			IJ.run("Cluster My Data", "dbscan min_points="+minPoints);
		} else {
			IJ.log("DBSCAN proceeding with default parameters.");
			IJ.run("Cluster My Data", "dbscan");
		}
	}
	
   public void optionsSelect() {
      JTextField eps = new JTextField(3);
      JTextField minP = new JTextField(3);
      
      JPanel panel = new JPanel(new GridBagLayout());
      GridBagConstraints constraints = new GridBagConstraints();
      constraints.anchor = GridBagConstraints.WEST;
      constraints.insets = new Insets(0, 4, 0, 0);
      
      constraints.gridx = 0;
      constraints.gridy = 0;
      panel.add(new JLabel("Epsilon:"), constraints);
      
      constraints.gridx = 1;
      panel.add(eps, constraints);
      
      constraints.gridx = 0;
      constraints.gridy = 1; 
      constraints.gridwidth = 2;
      panel.add(new JLabel("How close points should be to each other to be considered part of a cluster."), constraints);
      
      constraints.gridy = 2; 
      panel.add(new JLabel("If the distance between two points is <=eps, these points are considered neighbours."), constraints);
      
      constraints.gridy = 3; 
      panel.add(new JLabel("-----------------------------------------------------------------------------------------------------"), constraints);
      
      constraints.gridy = 4; 
      constraints.gridwidth = 1;
      panel.add(new JLabel("Minimum points:"), constraints);
      
      constraints.gridx = 1;
      constraints.gridy = 4;     
      panel.add(minP, constraints);
      
      constraints.gridx = 0;
      constraints.gridy = 5;
      constraints.gridwidth = 2;
      panel.add(new JLabel("The larger the value, the more points will be declared as noise,"), constraints);
      constraints.gridy = 6;
      panel.add(new JLabel("and clusters will be restricted to progressively more dense areas."), constraints);
      
      
      //panel.add(new JLabel("Minimum cluster size:"));
      //panel.add(minCS);
      	//panel.add(new JLabel("The smallest grouping you wish to have as a cluster."));
      //intpanel.add(Box.createHorizontalStrut(10)); // a spacer
      //panel.add(new JLabel("Minimum points:"));
      //panel.add(minP);
      	//panel.add(new JLabel("The larger the value, the more points will be declared as noise, and clusters will be restricted to progressively more dense areas."));
      

      int result = JOptionPane.showConfirmDialog(null, panel, "Optionally enter DBSCAN parameters", JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
    	  epsilon = eps.getText();
    	  minPoints = minP.getText();
      } else if (result == JOptionPane.CANCEL_OPTION) {
    	  cancelled = true;
      }
   }
	
}