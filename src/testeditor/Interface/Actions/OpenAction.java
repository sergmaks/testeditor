package testeditor.Interface.Actions;

import testeditor.Test;
import testeditor.parser.*;
import testeditor.question.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

/**
 * Класс-слушатель для события открытия файла
 */
public class OpenAction extends AbstractAction {
    private DefaultListModel questionList;

    /**
     * @param listModel - модель списка для JList, куда добавляем вопросы
     */
    public OpenAction(DefaultListModel listModel){
        questionList = listModel;

        this.putValue(Action.NAME,"Открыть");
        this.putValue(Action.SHORT_DESCRIPTION,"Открыть файл теста");
        this.putValue(Action.SMALL_ICON, new ImageIcon("src/testeditor/Interface/img/open.png"));
    }

    public void actionPerformed(ActionEvent event){
        Test test; // Объект теста
        JFileChooser openDialog = new JFileChooser(); // объект диалогового окна

        //------- Настраиваем диалоговое окно -------//
        openDialog.setCurrentDirectory(new File(".")); //корневая дирректория по умолчанию
        openDialog.setAcceptAllFileFilterUsed(false); //убираем в фильтрах "All files"
        openDialog.addChoosableFileFilter(new FileNameExtensionFilter("Все поддерживаемые форматы (*.gift, *.xml)","gift","xml"));
        openDialog.addChoosableFileFilter(new FileNameExtensionFilter("GIFT Moodle test (*.gift)","gift"));
        openDialog.addChoosableFileFilter(new FileNameExtensionFilter("XML Moodle test (*.xml)","xml"));

        //------- Обрабатываем файл теста -------//
        int result = openDialog.showDialog(null,"Открыть файл");
        if (result == JFileChooser.APPROVE_OPTION){
            questionList.clear();
            try {
                test = Parser.parse(openDialog.getSelectedFile().getAbsolutePath());

                for (Question question: test){
                    questionList.addElement(question.getQHead());
                }
            }
            catch (Exception ex) {ex.printStackTrace();}
        }


    }
}
