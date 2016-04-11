package testeditor.gui.question_view;

import testeditor.gui.services.GBC;
import testeditor.gui.services.QTextArea;
import testeditor.question.Answer;
import testeditor.question.Question;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dimitry on 03.04.16.
 */
public class MultiChoiceFrame extends QuestionFrame {
    List<Answer> aList;
    List<JCheckBox> checkBoxList = new ArrayList<>();
    List<JSpinner> spinnerList = new ArrayList<>();
    JPanel answers = new JPanel();
    int aCount;
    int offset = 0;

    public MultiChoiceFrame(Question q) {
        super(q);
        answers.setLayout(new GridBagLayout());

        // ------ Загловки для полей ------
        JLabel trueLabel = new JLabel("<html><p><b>Верно/<br>Неверно</b></p></html>");
        answers.add(trueLabel, new GBC(0,0,1,1,0,0,0,0).setFill(GBC.HORIZONTAL).setInsets(0,5,0,5));
        answers.add(new JSeparator(JSeparator.VERTICAL), new GBC(1,0,1,1,0,0,0,0).setFill(GBC.VERTICAL));
        JLabel answerLabel = new JLabel("<html><p><b>Вариант ответа<b></p></html>");
        answers.add(answerLabel, new GBC(2,0,1,1,0,0,0,0).setFill(GBC.HORIZONTAL).setInsets(0,5,0,5));
        answers.add(new JSeparator(JSeparator.VERTICAL), new GBC(3,0,1,1,0,0,0,0).setFill(GBC.VERTICAL));
        JLabel commentLabel = new JLabel("<html><p><b>Комментарий</b></p></html>");
        answers.add(commentLabel, new GBC(4,0,1,1,0,0,0,0).setFill(GBC.HORIZONTAL).setInsets(0,5,0,5));
        answers.add(new JSeparator(JSeparator.VERTICAL), new GBC(5,0,1,1,0,0,0,0).setFill(GBC.VERTICAL));
        JLabel weightLabel = new JLabel("<html><p><b>Веc</b></p></html>");
        answers.add(weightLabel, new GBC(6,0,1,1,0,0,0,0).setFill(GBC.HORIZONTAL).setInsets(0,5,0,5));
        answers.add(new JSeparator(JSeparator.VERTICAL), new GBC(7,0,1,1,0,0,0,0).setFill(GBC.VERTICAL));

        aList = q.getAnswerList();
        aCount = aList.size();

        for(int i=0; i < aCount; i++) {
            offset++;
            addAnswer( i + 1, aList.get(i).getAText(), aList.get(i).getAComment(),
                    aList.get(i).getDegree());

            updateAnswers();
        }

        JButton addButton = new JButton("<html><font color='green' size=+1>&nbsp;<b>&#10010;&nbsp;</b></font>Добавить&nbsp;</html>");

        answerPanel.add(answers, BorderLayout.NORTH);

        JPanel addButtonPanel = new JPanel(new FlowLayout());
        addButtonPanel.add(addButton);
        addButton.addActionListener(e -> {
                                            addAnswer(aCount+1,"","",0);
                                            answers.updateUI();
                                            aCount++;
                                            aScrollPane.setViewportView(answerPanel);
                                            offset++;
                                            updateAnswers();
                                          });

        answerPanel.add(addButtonPanel, BorderLayout.CENTER);
    }

    public void addAnswer (int pos, String text, String comment, int degree){

        JCheckBox check = new JCheckBox();
        check.setSelected(degree > 0);
        checkBoxList.add(check);

        check.addChangeListener(event -> updateAnswers());

        answers.add(check, new GBC(0, pos, 1, 1, 0, 0, 0, 0).setInsets(10, 5, 10, 10));
        answers.add(new JSeparator(JSeparator.VERTICAL), new GBC(1, pos, 1, 1, 0, 0, 0, 0).setFill(GBC.VERTICAL));

        QTextArea answerText = new QTextArea(text);
        answerText.setLineWrap(true);
        fields.add(answerText);
        answers.add(answerText, new GBC(2, pos, 1, 1, 0, 0, 100, 0).setFill(GBC.BOTH).setInsets(5, 5, 5, 5));
        answers.add(new JSeparator(JSeparator.VERTICAL), new GBC(3, pos, 1, 1, 0, 0, 0, 0).setFill(GBC.VERTICAL));

        QTextArea commentText = new QTextArea(comment);
        commentText.setLineWrap(true);
        answers.add(commentText, new GBC(4, pos, 1, 1, 0, 0, 0, 0).setFill(GBC.BOTH).setInsets(5, 5, 5, 5));
        answers.add(new JSeparator(JSeparator.VERTICAL), new GBC(5, pos, 1, 1, 0, 0, 0, 0).setFill(GBC.VERTICAL));

        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(0,0,100,1);

        JSpinner degreeSpinner = new JSpinner(spinnerNumberModel);
        degreeSpinner.setValue(degree);

        degreeSpinner.addChangeListener(event -> {
            int sumDegree = spinnerList.stream().mapToInt(x -> x.isEnabled() ? (int)x.getValue() : 0).sum();
            System.out.println(sumDegree);
        });

        spinnerList.add(degreeSpinner);
        answers.add(degreeSpinner,new GBC(6, pos, 1, 1, 0, 0, 0, 0).setFill(GBC.HORIZONTAL).setInsets(5, 5, 5, 5));
        answers.add(new JSeparator(JSeparator.VERTICAL), new GBC(7, pos, 1, 1, 0, 0, 0, 0).setFill(GBC.VERTICAL));

        JButton delButton = new JButton("<html><font color='red'><b>&nbsp;&#10006;&nbsp;</b></font></html>");
        delButton.addActionListener((ActionEvent e) -> deleteAnswer(answers.getComponentZOrder(delButton)));
        answers.add(delButton, new GBC(8, pos, 1, 1, 0, 0, 0, 0).setInsets(5, 10, 5, 5));
        answers.add(new JSeparator(), new GBC(0, pos + 1, 9, 1, 0, 0, 0, 0).setFill(GBC.BOTH).setInsets(0, 0, 5, 0));
    }

    public void deleteAnswer (int index){
        answers.remove(index+1);
        for(int i=0; i<=8; i++) answers.remove(index - i);
        answers.updateUI();

        offset--;
    }

    // Обновление панели вариантов, в зависимости от их весов
    public void updateAnswers(){
        int countSelected = 0;

        for (JCheckBox checkBox : checkBoxList)
            countSelected = (checkBox.isSelected()) ? countSelected + 1 : countSelected;

        if (countSelected < 2)
            spinnerList.stream().forEach(s -> s.setEnabled(false));
        else
            for (int i = 0; i < spinnerList.size(); i++)
                spinnerList.get(i).setEnabled(checkBoxList.get(i).isSelected());
    }

    protected List<Answer> collectAnswers() {
        List<Answer> aList = new ArrayList<>();
        Answer answer;
        QTextArea tempTextArea;

        for (int i = 1; i < answers.getComponentCount(); i+=7){
            tempTextArea = (QTextArea)answers.getComponent(i);
            answer = new Answer(tempTextArea.getText());
            aList.add(answer);
        }

        return aList;
    }
}