/*
 * @@COPYRIGHT@@
 */
package xal.tools.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import xal.tools.swing.wheelswitch.util.SetEvent;
import xal.tools.swing.wheelswitch.util.SetListener;
import xal.tools.swing.Wheelswitch;
import com.cosylab.gui.components.demonstrator.AbstractDemoPanel;
import com.cosylab.gui.components.util.IconHelper;
/**
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 * 
 * @author <a href="mailto:gasper.pajor@cosylab.com">Gasper Pajor</a>
 * @version $id$
 */
public class WheelswitchTest extends AbstractDemoPanel {
	String defaultText = "Wheelswitch displays a formatted numerical value with a set of numerical digits which can optionally be individually modified for precision setting of values.\n"+
	"Click on any of the numerical digits inside the wheelswitch to modify them through the two-button switch on the right side of the component.";

	/**
	 * Constructor for WheelSwitchDemoContent.
	 */
	public WheelswitchTest() {
		super("Wheelswitch");
		setIcon(IconHelper.createIcon("Resources/icons/components/Wheelswitch16.gif"));
		getHelpTextArea().setText(defaultText);

	}
/**
 * 
 * @version @@VERSION@@
 * @author Jernej Kamenik(jernej.kamenik@cosylab.com)
 */	
public class WheelSwitchDemoPanel extends JPanel {
    Wheelswitch wSwitch;
    JTextField txt1;
    JTextField txt2;
    JTextField txt3;
    JTextField txt4;
    JTextField txt5;
    JButton jb1;
    JButton jb2;
    JCheckBox tgb1, tgb2, tgb3;
    JPanel pan0; 

	private class HelpAdapter extends MouseAdapter {
            String string;
            
            /**
             * @param newString String
             */
            public HelpAdapter(String newString) {
            	string = newString;
            }
            
            /**
             * @param e MouseEvent
             * @see java.awt.event.MouseListener#mouseEntered(MouseEvent)
             */
            public void mouseEntered(MouseEvent e) {
                getHelpTextArea().setText(string);
            }
            /**
             * @param e MouseEvent
             * @see java.awt.event.MouseListener#mouseExited(MouseEvent)
             */
            public void mouseExited(MouseEvent e) {
		        getHelpTextArea().setText(defaultText);
            }
	}

    /**
     */
    public WheelSwitchDemoPanel() {
        wSwitch = new Wheelswitch("+####.##E+###",12.3456789,"unit");
        wSwitch.setGraphMax(200);
        wSwitch.setGraphMin(-200);
		
        txt1 = new JTextField("+####.##E+###",10);
        txt2 = new JTextField("12.3456789",10);
        txt3 = new JTextField("unit",10);
        txt4 = new JTextField("200",10);
        txt5 = new JTextField("-200",10);       
        jb1 = new JButton("Apply Settings");
        jb2 = new JButton("Get Value");
        tgb1 = new JCheckBox("Stretch");
        tgb2 = new JCheckBox("Enhanced");
        tgb3 = new JCheckBox("Editable");

        txt1.addMouseListener(new HelpAdapter("The values in the wheelswitch can be display in numerous formats, controlled by the format string.\n To change the format alter this string and press the 'Apply' button."));
        txt2.addMouseListener(new HelpAdapter("Enter the value to be set to the wheelswitch here nad press the 'Apply' button."));
		txt3.addMouseListener(new HelpAdapter("Units can be also displayed next to the value in the wheelswitch. To change the unit display enter the new text here and press the 'Apply' button."));
        txt4.addMouseListener(new HelpAdapter("Values in the wheelswitch can be bounded by setting maximum and minimum values. To change these, enter new values here press the 'Apply' button"));
        txt5.addMouseListener(new HelpAdapter("Values in the wheelswitch can be bounded by setting maximum and minimum values. To change these, enter new values here press the 'Apply' button"));
       
        jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wSwitch.setValue(Double.parseDouble(txt2.getText()));
                wSwitch.setFormat(txt1.getText());
                wSwitch.setUnit(txt3.getText());
                wSwitch.setGraphMax(Double.parseDouble(txt4.getText()));
                wSwitch.setGraphMin(Double.parseDouble(txt5.getText()));
            }
        });
        jb1.addMouseListener(new HelpAdapter("Press the 'Apply' button to apply settings in the textfields to the wheelswitch."));

        jb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txt2.setText(String.valueOf(wSwitch.getValue()));
            }
        });
        jb2.addMouseListener(new HelpAdapter("Press the 'Get Value' button to read the value in the wheelswitch and display it in the 'Value' textfield."));
                
        wSwitch.addSetListener(new SetListener() {
            public void setPerformed(SetEvent e) {
                txt2.setText(String.valueOf(e.getDoubleValue()));               
            }
        });
        
       // pan0 = new JPanel();
        
        tgb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tgb1.isSelected()) wSwitch.getParent().setLayout(new GridLayout(1,1));
                else wSwitch.getParent().setLayout(new FlowLayout());
                wSwitch.getParent().doLayout();
                wSwitch.getParent().validate();
                repaint();
            }
        });
        tgb1.addMouseListener(new HelpAdapter("Check here to demonstrate wheelswitch resizing."));
        
        tgb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tgb2.isSelected()) wSwitch.setEnhanced(true);
                else wSwitch.setEnhanced(false);
            }
        });
        tgb2.setSelected(true);
        tgb2.addMouseListener(new HelpAdapter("Check here to set the enhnced mode of the wheelswitch."));

        tgb3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tgb3.isSelected()) wSwitch.setEditable(true);
                else wSwitch.setEditable(false);
            }
        });
        tgb3.setSelected(true);
        tgb3.addMouseListener(new HelpAdapter("Check here to set the editable mode of the wheelswitch."));

        // Composition
        
        setLayout(new GridBagLayout());
        GridBagConstraints cons  = new GridBagConstraints(0,0,1,1,0,0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(6,6,0,0),0,0);            

		// Wheelswitch Panel

        JPanel panWs = new JPanel();
        panWs.setBorder(new LineBorder(Color.GRAY));
        panWs.add(wSwitch);
      	
		// 1st part of conf panel       	
       
        JPanel pan0 = new JPanel();
        pan0.setLayout(new GridBagLayout());
        
        pan0.add(new JLabel("Format: "), cons);
        cons.gridy = cons.gridy +1;
        pan0.add(new JLabel("Value: "), cons);
        cons.gridy = cons.gridy +1;
        pan0.add(new JLabel("Unit: "), cons);
        cons.gridy=0;
        cons.gridx=1;
        pan0.add(txt1, cons);
        cons.gridy = cons.gridy +1;
        pan0.add(txt2, cons);
        cons.gridy = cons.gridy +1;
        pan0.add(txt3, cons);
       
       	// 2nd part of conf panel
                       
        JPanel pan1 = new JPanel();
        pan1.setLayout(new GridBagLayout());
        
        cons.gridx=0;
        cons.gridy=0;
        pan1.add(new JLabel("Maximum: "), cons);
        cons.gridy = cons.gridy +1;
        pan1.add(new JLabel("Minimum: "), cons);
        cons.gridy=0;
        cons.gridx=1;
        pan1.add(txt4, cons);
        cons.gridy = cons.gridy +1;
        pan1.add(txt5, cons);

		// 3rd part of conf panel

        JPanel pan2 = new JPanel();
        pan2.setLayout(new GridBagLayout());         
    
        cons.gridx=0;
        cons.gridy=0;
        cons.anchor= GridBagConstraints.CENTER;
        pan2.add(jb1, cons);
        cons.gridx = cons.gridx +1;
        pan2.add(jb2, cons);
        cons.anchor= GridBagConstraints.EAST;       

		// 4th part of conf panel

        JPanel pan3 = new JPanel();
        pan3.setLayout(new GridBagLayout());         
    
        cons.gridx=0;
        cons.gridy=0;
        cons.anchor= GridBagConstraints.CENTER;
        pan3.add(tgb1, cons);
        cons.gridx = cons.gridx +1;
        pan3.add(tgb2, cons);
        cons.gridx = cons.gridx +1;
        pan3.add(tgb3, cons);


		// Conf / Control panel
        
        JPanel panControl = new JPanel();
        panControl.setLayout(new GridBagLayout());         
    
        cons.gridx=0;
        cons.gridy=0;
        panControl.add(pan0, cons);
        cons.gridx=cons.gridx+1;
        panControl.add(pan1, cons);
        cons.gridx=0;
        cons.gridy=cons.gridy+1;
        cons.gridwidth =2;
        panControl.add(pan2, cons);
        cons.gridy=cons.gridy+1;
        panControl.add(pan3, cons);
        
		// adding to this
        
        cons.insets.left=0;        
        cons.anchor=GridBagConstraints.CENTER;
        cons.gridwidth=1;
        cons.gridx=0;
        cons.gridy=0;
        cons.fill=GridBagConstraints.BOTH;
        cons.weightx=1;
        cons.weighty=1;
        add(panWs,cons );
        cons.gridy=cons.gridy+1;
        JPanel divider = new JPanel();
        divider.setPreferredSize(new Dimension(1,20));
        add(divider, cons);
        cons.weighty=0;
        cons.gridy=cons.gridy+1;
        add(panControl, cons);
        cons.weighty=1;
        cons.gridy=cons.gridy+1;
        JPanel divider2 = new JPanel();
        divider2.setPreferredSize(new Dimension(1,20));
        add(divider2, cons);


    }

}
	/**
	 * @return JComponent
	 * @see AbstractDemoPanel#getContentPanel()
	 */
	public JComponent initializeContentPanel() {
		return new WheelSwitchDemoPanel();
	}

  /**
   * @return boolean
   * @see com.cosylab.util.Suspendable#isSuspended()
   */
  public boolean isSuspended() {
    return false;
  }

  /**
   * @param suspended boolean
   * @see com.cosylab.util.Suspendable#setSuspended(boolean)
   */
  public void setSuspended(boolean suspended) {
  }

}
