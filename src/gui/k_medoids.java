package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ij.IJ;

public class k_medoids {
	private String K;
	boolean cancelled;
	
	public k_medoids() {
		optionsSelect();
		if (cancelled) {
			return;
		}
		
		if (K != null && !("").equals(K) && !K.matches(".*[A-Za-z].*") && Integer.valueOf(K) > 0){
			IJ.run("Cluster My Data", "k_medoids="+K);
		} else {
			IJ.showMessage("k should be a whole number over 0.");
			return;
		}
		
	}
	
	   public void optionsSelect() {
		      JTextField k = new JTextField(3);
		      
		      JPanel panel = new JPanel(new GridBagLayout());
		      GridBagConstraints constraints = new GridBagConstraints();
		      constraints.anchor = GridBagConstraints.WEST;
		      constraints.insets = new Insets(0, 4, 0, 0);
		      
		      constraints.gridx = 0;
		      constraints.gridy = 0;
		      panel.add(new JLabel("K-medoids 'k':"), constraints);
		      
		      constraints.gridx = 1;
		      panel.add(k, constraints);
		      
		      constraints.gridx = 0;
		      constraints.gridy = 1; 
		      constraints.gridwidth = 2;
		      panel.add(new JLabel("The number of clusters to form."), constraints);
		       
		      //panel.add(new JLabel("Minimum cluster size:"));
		      //panel.add(minCS);
		      	//panel.add(new JLabel("The smallest grouping you wish to have as a cluster."));
		      //intpanel.add(Box.createHorizontalStrut(10)); // a spacer
		      //panel.add(new JLabel("Minimum points:"));
		      //panel.add(minP);
		      	//panel.add(new JLabel("The larger the value, the more points will be declared as noise, and clusters will be restricted to progressively more dense areas."));
		      

		      int result = JOptionPane.showConfirmDialog(null, panel, "Enter K-medoid parameters", JOptionPane.OK_CANCEL_OPTION);
		      if (result == JOptionPane.OK_OPTION) {
		    	  K = k.getText();
		      } else if (result == JOptionPane.CANCEL_OPTION) {
		    	  cancelled = true;
		      }
	   
	   }
	    
}