package testeditor;

import testeditor.question.*;
import testeditor.saver.*;
import testeditor.Interface.*;

import javax.swing.*;
import java.awt.*;
import java.io.PrintStream;


public class TestEditor {
    public static void main(String[] args) {
<<<<<<< HEAD

        /*
        Test test = Test.getTestFromFile("test.gift");
=======
        /*
        Test test =	Test.getTestFromFile("test.gift");
>>>>>>> 56308f4... GUI Beginning (Questions' Heads Output)
        Saver s = new GiftSaver(test, "Test1.gift");
        for (Question q : test) {

            s.save(q);

            break; // только для тестирования. Т.к. для сохранения даже одного вопроса файл переписывается полностью,
            // то нет смысла гонять весь цикл.
        }
<<<<<<< HEAD
     */
=======
        */
>>>>>>> 56308f4... GUI Beginning (Questions' Heads Output)
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame testFrame = new MainFrame();

                testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                testFrame.setTitle("TestEditor");
                testFrame.setVisible(true);
            }
        });
<<<<<<< HEAD
=======

>>>>>>> 56308f4... GUI Beginning (Questions' Heads Output)
    }
}