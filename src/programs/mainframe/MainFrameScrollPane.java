package programs.mainframe;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.border.EmptyBorder;

import main.ProgramList;

public class MainFrameScrollPane {
    private static JList<ProgramList> list;
    static ProgramList[] programs = ProgramList.values();

    public static void create(JPanel panel) {        
              list = new JList<>(programs);
              list.setEnabled(true);
              list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

              JScrollPane scrollPane = new JScrollPane(list);
              scrollPane.setVisible(true);
              scrollPane.setSize(panel.getWidth() / 3, panel.getHeight());
              
              panel.add(scrollPane, BorderLayout.WEST);	  
    }

    
    
    public static ProgramList getSelectedProgramValue() {
        return list.getSelectedValue();
    }


}