package testeditor.gui.QuestionFrames;

import testeditor.gui.ParentFrame;
import testeditor.gui.services.QLabel;
import testeditor.gui.services.QTextArea;
import testeditor.question.Answer;
import testeditor.question.Question;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.GroupLayout.Alignment.*;

/**
 * Created by dimitry on 03.04.16.
 */
abstract public class QuestionFrame extends ParentFrame {
    protected JPanel answerPanel  = new JPanel();
    protected ArrayList<JTextComponent> fields = new ArrayList<>();
    protected JScrollPane aScrollPane;
    protected Question q;
    private QLabel labelName;
    private QLabel labelQuestion;

    public QuestionFrame(Question q) {
        this.q = q;
        setSize((int)(INITIAL_WIDTH / 1.5), INITIAL_HEIGHT);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout(30, 30));

        JPanel north = new JPanel();
        GroupLayout northLayout = new GroupLayout(north);
        north.setLayout(northLayout);
        northLayout.setAutoCreateContainerGaps(true);
        northLayout.setAutoCreateGaps(true);

        labelName = new QLabel("<html><b>Название:</b></html>");
        labelQuestion = new QLabel("<html><b>Вопрос:</b></html>");
        QLabel labelType = new QLabel("<html><b>Тип вопроса: </b>" + q.TYPE + "</html>");
        QTextArea nameTextArea = new QTextArea(q.getQName());
        QTextArea qTextArea = new QTextArea(q.getQText());
        fields.add(nameTextArea);
        fields.add(qTextArea);

        northLayout.setHorizontalGroup(northLayout.createSequentialGroup()
                .addGroup(northLayout.createParallelGroup(LEADING)
                        .addComponent(labelName)
                        .addComponent(labelQuestion)
                        .addComponent(labelType))
                .addGroup(northLayout.createParallelGroup(LEADING)
                        .addComponent(nameTextArea)
                        .addComponent(qTextArea))
        );

        northLayout.linkSize(SwingConstants.HORIZONTAL, labelName, labelQuestion);

        northLayout.setVerticalGroup(northLayout.createSequentialGroup()
                .addGroup(northLayout.createParallelGroup(BASELINE)
                        .addComponent(labelName)
                        .addComponent(nameTextArea))
                .addGroup(northLayout.createParallelGroup(LEADING)
                        .addComponent(labelQuestion)
                        .addComponent(qTextArea))
                .addComponent(labelType)
        );

        add(north, BorderLayout.NORTH);

        answerPanel.setLayout(new BorderLayout(10, 10));

        aScrollPane = new JScrollPane(answerPanel);
        aScrollPane.setViewportBorder(null);
        aScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        // aScrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
        //    public void adjustmentValueChanged(AdjustmentEvent e) {
        //       e.getAdjustable().setValue(e.getAdjustable().getMaximum());}});
        Border aScrollPaneBorder = BorderFactory.createTitledBorder("Варианты ответа");
        aScrollPane.setBorder(aScrollPaneBorder);
        add(aScrollPane);

        JPanel savePanel = new JPanel();
        savePanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 10));

        JButton saveButton = new JButton("Сохранить", new ImageIcon("src/testeditor/gui/img/save.png"));
        JButton cancelButton = new JButton("Отмена");
        cancelButton.addActionListener(e -> QuestionFrame.this.setVisible(false));

        savePanel.add(saveButton);
        savePanel.add(cancelButton);

        JPanel hintPanel = new JPanel();
        hintPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 10));

        JLabel hintLabel = new JLabel("Подсказка");
        hintPanel.add(hintLabel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(savePanel, BorderLayout.NORTH);
        bottomPanel.add(hintPanel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void saveQuestion() {
        q.setQName(labelName.getText());
        q.setQText(labelQuestion.getText());
        q.setAnswers(collectAnswers());
        q.save();
    }

    abstract protected List<Answer> collectAnswers();
}
