package testeditor.gui.test_view;

import testeditor.gui.question_view.actions.CreateQuestionAction;
import testeditor.gui.question_view.actions.EditQuestionAction;
import testeditor.gui.question_view.actions.RemoveQuestionAction;
import testeditor.gui.services.EditPanelButton;
import testeditor.gui.services.GBC;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

/**
 * Панель управления элементами списка
 */
public class EditPanel extends JPanel {
    private JButton createButton;
    private JSpinner listSpinner;

    private ArrayList<JButton> buttons;

    public EditPanel(JList list) {
        int max = ((DefaultListModel)list.getModel()).isEmpty() ? 1 : list.getModel().getSize();

        setLayout(new GridBagLayout());

        buttons = new ArrayList<>();

        JButton editButton = new EditPanelButton(new EditQuestionAction(list));
        buttons.add(editButton);
        add(editButton, new GBC(0, 0, 2, 1, 10, 10, 0, 0).setFill(GridBagConstraints.HORIZONTAL)
                                                         .setInsets(10, 20, 0, 20));

        createButton = new EditPanelButton(new CreateQuestionAction(list));
        buttons.add(createButton);
        add(createButton,new GBC(0, 1, 2, 1, 10, 10, 0, 0).setFill(GridBagConstraints.HORIZONTAL)
                                                          .setInsets(10, 20, 0, 20));

        JButton deleteButton = new EditPanelButton(new RemoveQuestionAction(list));
        buttons.add(deleteButton);
        add(deleteButton, new GBC(0, 2, 2, 1, 10, 10, 0, 0).setFill(GridBagConstraints.HORIZONTAL)
                                                           .setInsets(10, 20, 0, 20));

        JButton beginButton = new EditPanelButton("<html><font color='#4682B4' size=+1><b>&#9650;&nbsp;&nbsp;&nbsp;</b></font>В начало</html>");
        buttons.add(beginButton);
        add(beginButton, new GBC(0, 3, 2, 1, 10, 10, 0, 0).setFill(GridBagConstraints.HORIZONTAL)
                                                          .setInsets(10, 20, 0, 20));

        JButton upButton = new EditPanelButton("<html><font color='#4682B4' size=+1><b>&#8657;&nbsp;&nbsp;&nbsp;</b></font>Вверх</html>");
        buttons.add(upButton);
        add(upButton, new GBC(0, 4, 2, 1, 10, 10, 0, 0).setFill(GridBagConstraints.HORIZONTAL)
                                                       .setInsets(10, 20, 0, 20));

        JButton downButton = new EditPanelButton("<html><font color='#4682B4' size=+1><b>&#8659;&nbsp;&nbsp;&nbsp;</b></font>Вниз</html>");
        buttons.add(downButton);
        add(downButton,new GBC(0, 5, 2, 1, 10, 10, 0, 0).setFill(GridBagConstraints.HORIZONTAL)
                                                        .setInsets(10, 20, 0, 20));

        JButton endButton = new EditPanelButton("<html><font color='#4682B4' size=+1><b>&#9660;&nbsp;&nbsp;&nbsp;</b></font>В конец</html>");
        buttons.add(endButton);
        add(endButton, new GBC(0, 6, 2, 1, 10, 10, 0, 0).setFill(GridBagConstraints.HORIZONTAL)
                                                        .setInsets(10, 20, 0, 20));

        JLabel spinLabel = new JLabel("<html>Переместить<br>на позицию №:</html>");
        add(spinLabel, new GBC(0, 7, 1, 1, 0, 0, 0, 100).setAnchor(GridBagConstraints.PAGE_END)
                                                        .setInsets(10, 10, 20, 20));

        listSpinner = new JSpinner(new SpinnerNumberModel(1, 1, max, 1));
        listSpinner.addChangeListener((ChangeEvent event) -> {
            list.setSelectedIndex(((int)listSpinner.getValue())-1);//выделяем выбранный элемент
            list.ensureIndexIsVisible(list.getSelectedIndex());//делаем выделенный элемент видимым
        });
        add(listSpinner, new GBC(1, 7, 1, 1, 0, 0, 0, 100).setAnchor(GridBagConstraints.PAGE_END)
                                                          .setInsets(10, 20, 20, 0)
                                                          .setFill(GridBagConstraints.HORIZONTAL));

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2) editButton.doClick();
            }
        });

    }

    public JButton getCreateButton() {
        return createButton;
    }
    public JSpinner getListSpinner() {
        return listSpinner;
    }
    public List<JButton> getButtons() {
        return buttons;
    }
}