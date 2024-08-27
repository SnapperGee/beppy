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

        addButton.setName("Add Button");

        final JTextField systolicTextField = new JTextField(10);
        final JTextField diastolicTextField = new JTextField(10);

        buttonAndInputFieldsPane.add(addButton);
        buttonAndInputFieldsPane.add(systolicTextField);
        buttonAndInputFieldsPane.add(diastolicTextField);

        return buttonAndInputFieldsPane;
    }
}
