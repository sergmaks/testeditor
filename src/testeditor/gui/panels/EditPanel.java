package testeditor.gui.panels;

import testeditor.gui.services.GBC;

import javax.swing.*;
import java.awt.*;

/**
 * Панель управления элементами списка
 */
public class EditPanel extends JPanel {
    public EditPanel(){
        setLayout(new GridBagLayout());

        JButton editButton = new JButton("Редактировать");
        add(editButton, new GBC(0,0,2,1,10,10,0,0).setFill(GridBagConstraints.HORIZONTAL).setInsets(10,20,0,20));

        JButton createButton = new JButton("Создать");
       add(createButton,new GBC(0,1,2,1,10,10,0,0).setFill(GridBagConstraints.HORIZONTAL).setInsets(10,20,0,20));

        JButton deleteButton = new JButton("Удалить");
        add(deleteButton,new GBC(0,2,2,1,10,10,0,0).setFill(GridBagConstraints.HORIZONTAL).setInsets(10,20,0,20));

        JButton beginButton = new JButton("В начало");
        add(beginButton,new GBC(0,3,2,1,10,10,0,0).setFill(GridBagConstraints.HORIZONTAL).setInsets(10,20,0,20));

        JButton upButton = new JButton("Вверх");
        add(upButton,new GBC(0,4,2,1,10,10,0,0).setFill(GridBagConstraints.HORIZONTAL).setInsets(10,20,0,20));

        JButton downButton = new JButton("Вниз");
        add(downButton,new GBC(0,5,2,1,10,10,0,0).setFill(GridBagConstraints.HORIZONTAL).setInsets(10,20,0,20));

        JButton endButton = new JButton("В конец");
        add(endButton, new GBC(0,6,2,1,10,10,0,0).setFill(GridBagConstraints.HORIZONTAL).setInsets(10,20,0,20));

        JLabel spinLabel = new JLabel("Перейти к:");
        add(spinLabel,new GBC(0,7,1,1,0,0,0,100).setAnchor(GridBagConstraints.PAGE_END).setInsets(10,10,20,20));


        JSpinner listSpinner = new JSpinner(new SpinnerNumberModel(1,1,1,1));
        add(listSpinner, new GBC(1,7,1,1,0,0,0,100).setAnchor(GridBagConstraints.PAGE_END).setInsets(10,20,20,0).setFill(GridBagConstraints.HORIZONTAL));
    }
}
