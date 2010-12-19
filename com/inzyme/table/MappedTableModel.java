/* MappedTableModel - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
package com.inzyme.table;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class MappedTableModel extends AbstractTableModel
    implements TableModelListener
{
    protected TableModel model;
    
    public TableModel getModel() {
	return model;
    }
    
    public void setModel(TableModel _model) {
	model = _model;
	_model.addTableModelListener(this);
    }
    
    public void removeListeners() {
	if (model != null)
	    model.removeTableModelListener(this);
    }
    
    public Object getValueAt(int aRow, int aColumn) {
	return model.getValueAt(aRow, aColumn);
    }
    
    public void setValueAt(Object aValue, int aRow, int aColumn) {
	model.setValueAt(aValue, aRow, aColumn);
    }
    
    public int getRowCount() {
	return model == null ? 0 : model.getRowCount();
    }
    
    public int getColumnCount() {
	return model == null ? 0 : model.getColumnCount();
    }
    
    public String getColumnName(int aColumn) {
	return model.getColumnName(aColumn);
    }
    
    public Class getColumnClass(int aColumn) {
	return model.getColumnClass(aColumn);
    }
    
    public boolean isCellEditable(int row, int column) {
	return model.isCellEditable(row, column);
    }
    
    public void tableChanged(TableModelEvent e) {
	fireTableChanged(e);
    }
}
