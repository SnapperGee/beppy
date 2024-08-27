package beppy;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

final class DataEntryPanel
{
    final static JPanel create()
    {
        final JPanel buttonAndInputFieldsPane = new JPanel();

        final JButton addButton = new JButton("Add");

        addButton.setName("AddButton");

        final JTextField systolicEntryTextField = new JTextField(10);
        final JTextField diastolicEntryTextField = new JTextField(10);

        systolicEntryTextField.setName("SystolicEntryTextField");
        diastolicEntryTextField.setName("DiastolicEntryTextField");

        buttonAndInputFieldsPane.add(addButton);
        buttonAndInputFieldsPane.add(systolicEntryTextField);
        buttonAndInputFieldsPane.add(diastolicEntryTextField);

        return buttonAndInputFieldsPane;
    }
}
