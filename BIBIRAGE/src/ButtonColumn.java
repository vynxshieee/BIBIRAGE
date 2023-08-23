import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {
    private final JButton renderButton;
    private final JButton editButton;
    private String buttonText;
    private JTable table;
    private Action action;

    public ButtonColumn(JTable table, Action action, int column) {
        this.table = table;
        this.action = action;
        renderButton = new JButton();
        editButton = new JButton();
        editButton.setFocusPainted(false);
        editButton.addActionListener(this);
        Frame.hoverEffect(editButton);
        Frame.clickChangeColor(editButton);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(column).setCellRenderer(this);
        columnModel.getColumn(column).setCellEditor(this);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (hasFocus) {
            renderButton.setForeground(table.getForeground());
            renderButton.setBackground(new Color(210, 210, 210));
            renderButton.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
        } else if (isSelected) {
            renderButton.setForeground(table.getSelectionForeground());
            renderButton.setBackground(table.getSelectionBackground());
        } else {
            renderButton.setForeground(table.getForeground());
            renderButton.setBackground(new Color(210, 210, 210));
            renderButton.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
        }

        renderButton.setText((value == null) ? "" : value.toString());
        return renderButton;
    }


    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        editButton.setText((value == null) ? "" : value.toString());
        this.buttonText = (value == null) ? "" : value.toString();
        editButton.setBackground(new Color(234, 234, 234));
        editButton.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
        return editButton;
    }

    @Override
    public Object getCellEditorValue() {
        return buttonText;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int row = table.convertRowIndexToModel(table.getEditingRow());
        fireEditingStopped();
        ActionEvent event = new ActionEvent(table, ActionEvent.ACTION_PERFORMED, String.valueOf(row));
        action.actionPerformed(event);
    }
}
