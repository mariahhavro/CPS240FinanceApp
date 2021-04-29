import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;
import javax.swing.plaf.basic.BasicToggleButtonUI;

class AccToggle extends JToggleButton {
    public AccToggle() {
    	
        setUI(new BasicToggleButtonUI()); //Removes selectColor

        ////Your Custom L&F Settings////
        setBackground(new Color(252, 205, 53));
        setForeground(new Color(128, 0, 0));
        //setBorder(null);
        //setFocusPainted(false);

        ////Add your own select color by setting background////
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(((JToggleButton) e.getSource()).isSelected()) {
                    setBackground(new Color(228, 180, 24));
                } else {
                	setBackground(new Color(252, 205, 53));
                    
                }
            }
        });
    }
}