package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ij.IJ;

public class hdbscan {
	private String minClusSize;
	private String minPoints;
	boolean cancelled;
	
	public hdbscan() {
		optionsSelect();
		if (cancelled) {
			return;
		}
		
		//There must be a better way to cascade through the options below.
		if (minClusSize != null && !("").equals(minClusSize) && !minClusSize.matches(".*[A-Za-z].*") && Double.valueOf(minClusSize) > 0){
			if (minPoints != null && !("").equals(minPoints) && !minPoints.matches(".*[A-Za-z].*") && Double.valueOf(minPoints) > 0) {
				IJ.log("HDBSCAN proceeding with minimum cluster size set to "+minClusSize+" and minimum points set to "+minPoints+".");
				IJ.run("Cluster My Data", "hdbscan min_clus_size="+minClusSize+" min_points="+minPoints);
			} else {
				IJ.log("HDBSCAN proceeding with minimum cluster size set to "+minClusSize+" and minimum points set to default.");
				IJ.run("Cluster My Data", "hdbscan min_clus_size="+minClusSize);
			}
		} else if (minPoints != null && !("").equals(minPoints) && !minPoints.matches(".*[A-Za-z].*") && Double.valueOf(minPoints) > 0) {
			IJ.log("HDBSCAN proceeding with minimum cluster size set to default and minimum points set to "+minPoints+".");
			IJ.run("Cluster My Data", "hdbscan min_points="+minPoints);
		} else {
			IJ.log("HDBSCAN proceeding with default parameters.");
			IJ.run("Cluster My Data", "hdbscan");
		}
	}
	
	
   public void optionsSelect() {
      JTextField minCS = new JTextField(3);
      JTextField minP = new JTextField(3);
      
      JPanel panel = new JPanel(new GridBagLayout());
      GridBagConstraints constraints = new GridBagConstraints();
      constraints.anchor = GridBagConstraints.WEST;
      constraints.insets = new Insets(0, 4, 0, 0);
      
      constraints.gridx = 0;
      constraints.gridy = 0;
      panel.add(new JLabel("Minimum cluster size:"), constraints);
      
      constraints.gridx = 1;
      panel.add(minCS, constraints);
      
      constraints.gridx = 0;
      constraints.gridy = 1; 
      constraints.gridwidth = 2;
      panel.add(new JLabel("The smallest grouping you wish to have as a cluster."), constraints);
      
      constraints.gridy = 2; 
      panel.add(new JLabel("-----------------------------------------------------------------------------------------"), constraints);
      
      constraints.gridy = 3; 
      constraints.gridwidth = 1;
      panel.add(new JLabel("Minimum points:"), constraints);
      
      constraints.gridx = 1;
      constraints.gridy = 3;     
      panel.add(minP, constraints);
      
      constraints.gridx = 0;
      constraints.gridy = 4;
      constraints.gridwidth = 2;
      panel.add(new JLabel("The larger the value, the more points will be declared as noise,"), constraints);
      constraints.gridy = 5;
      panel.add(new JLabel("and clusters will be restricted to progressively more dense areas."), constraints);
      
      
      //panel.add(new JLabel("Minimum cluster size:"));
      //panel.add(minCS);
      	//panel.add(new JLabel("The smallest grouping you wish to have as a cluster."));
      //intpanel.add(Box.createHorizontalStrut(10)); // a spacer
      //panel.add(new JLabel("Minimum points:"));
      //panel.add(minP);
      	//panel.add(new JLabel("The larger the value, the more points will be declared as noise, and clusters will be restricted to progressively more dense areas."));
      

      int result = JOptionPane.showConfirmDialog(null, panel, "Optionally enter HDBSCAN parameters", JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
    	  minClusSize = minCS.getText();
    	  minPoints = minP.getText();
      } else if (result == JOptionPane.CANCEL_OPTION) {
    	  cancelled = true;
      }
   }
   
}