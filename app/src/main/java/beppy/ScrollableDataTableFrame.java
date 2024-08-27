package beppy;

import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;

final class ScrollableDataTableFrame
{
    static JScrollPane create(String[][] data)
    {
        final JTable table = new JTable(data, DataFile.HEADER_STRINGS.toArray());
        final JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        return scrollPane;
    }
}
