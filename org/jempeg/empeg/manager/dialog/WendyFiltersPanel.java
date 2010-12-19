/* WendyFiltersPanel - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
package org.jempeg.empeg.manager.dialog;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jempeg.manager.ui.ButtonListCellRenderer;
import org.jempeg.manager.ui.ButtonListMouseListener;
import org.jempeg.manager.ui.JTriStateButton;
import org.jempeg.nodestore.WendyFilter;
import org.jempeg.nodestore.WendyFilters;

public class WendyFiltersPanel extends JPanel
{
    private JList myFlagsList;
    private JComboBox myFiltersList;
    private WendyFilters myWendyFilters;
    private WendyFilter myWendyFilter;
    
    protected class AddListener implements ActionListener
    {
	public void actionPerformed(ActionEvent _event) {
	    addFilter();
	}
    }
    
    protected class DeleteListener implements ActionListener
    {
	public void actionPerformed(ActionEvent _event) {
	    deleteFilter();
	}
    }
    
    protected class RenameListener implements ActionListener
    {
	public void actionPerformed(ActionEvent _event) {
	    renameFilter();
	}
    }
    
    protected class WendyFilterListener implements ActionListener
    {
	public void actionPerformed(ActionEvent _event) {
	    String name = (String) myFiltersList.getSelectedItem();
	    if (name != null) {
		WendyFilter filter = myWendyFilters.getFilter(name);
		setCurrentWendyFilter(filter);
	    } else
		setCurrentWendyFilter(null);
	}
    }
    
    protected class WendyFlagListener implements ActionListener
    {
	public void actionPerformed(ActionEvent _event) {
	    String name = getSelectedWendyFlag();
	    if (name != null) {
		int state = getSelectedWendyFlagState();
		WendyFilter filter = getCurrentWendyFilter();
		filter.setState(name, state);
	    }
	}
    }
    
    public WendyFiltersPanel(WendyFilters _wendyFilters) {
	DefaultComboBoxModel wendyFiltersListModel
	    = new DefaultComboBoxModel();
	myFiltersList = new JComboBox(wendyFiltersListModel);
	myFiltersList.addActionListener(new WendyFilterListener());
	DefaultListModel wendyListModel = new DefaultListModel();
	myFlagsList = new JList(wendyListModel);
	setLayout(new BorderLayout());
	JPanel innerPanel = new JPanel();
	innerPanel.setLayout(new BorderLayout());
	ButtonListMouseListener wendyListMouseListener
	    = new ButtonListMouseListener(myFlagsList);
	myFlagsList.setSelectionMode(0);
	myFlagsList.setCellRenderer(new ButtonListCellRenderer());
	myFlagsList.addListSelectionListener(wendyListMouseListener);
	myFlagsList.addKeyListener(wendyListMouseListener);
	myFlagsList.addMouseListener(wendyListMouseListener);
	JScrollPane wendyScrollPane = new JScrollPane(myFlagsList);
	innerPanel.add(myFiltersList, "North");
	innerPanel.add(wendyScrollPane, "Center");
	JPanel buttonLayoutPanel = new JPanel();
	buttonLayoutPanel.setLayout(new BorderLayout());
	JPanel buttonPanel = new JPanel();
	buttonPanel.setLayout(new GridLayout(0, 1));
	JButton addButton = new JButton("Add");
	addButton.addActionListener(new AddListener());
	buttonPanel.add(addButton);
	JButton renameButton = new JButton("Rename");
	renameButton.addActionListener(new RenameListener());
	buttonPanel.add(renameButton);
	JButton deleteButton = new JButton("Delete");
	deleteButton.addActionListener(new DeleteListener());
	buttonPanel.add(deleteButton);
	buttonLayoutPanel.add(buttonPanel, "North");
	add(innerPanel, "Center");
	add(buttonLayoutPanel, "East");
	setWendyFilters(_wendyFilters);
	reloadCurrentFilter();
    }
    
    public void reloadCurrentFilter() {
	String name = (String) myFiltersList.getSelectedItem();
	if (name != null) {
	    WendyFilter filter = myWendyFilters.getFilter(name);
	    setCurrentWendyFilter(filter);
	} else
	    setCurrentWendyFilter(null);
    }
    
    public void setWendyFilters(WendyFilters _wendyFilters) {
	DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
	int size = _wendyFilters.getSize();
	for (int i = 0; i < size; i++) {
	    WendyFilter filter = _wendyFilters.getFilterAt(i);
	    String name = filter.getName();
	    comboBoxModel.addElement(name);
	}
	myWendyFilters = _wendyFilters;
	myFiltersList.setModel(comboBoxModel);
    }
    
    public WendyFilters getWendyFilters() {
	return myWendyFilters;
    }
    
    public void setCurrentWendyFilter(WendyFilter _wendyFilter) {
	myWendyFilter = _wendyFilter;
	DefaultListModel wendyListModel = new DefaultListModel();
	if (_wendyFilter != null) {
	    String[] wendyFlags = myWendyFilters.getWendyFlags().getFlags();
	    for (int i = 0; i < wendyFlags.length; i++) {
		JTriStateButton wendyCheckBox
		    = createWendyCheckBox(wendyFlags[i]);
		wendyListModel.addElement(wendyCheckBox);
	    }
	}
	myFlagsList.setModel(wendyListModel);
    }
    
    public WendyFilter getCurrentWendyFilter() {
	return myWendyFilter;
    }
    
    protected JTriStateButton createWendyCheckBox(String _name) {
	JTriStateButton wendyCheckBox = new JTriStateButton(_name);
	wendyCheckBox.addActionListener(new WendyFlagListener());
	int state = myWendyFilter.getState(_name);
	wendyCheckBox.setTriState(state);
	return wendyCheckBox;
    }
    
    protected int getSelectedWendyFlagState() {
	DefaultListModel wendyListModel
	    = (DefaultListModel) myFlagsList.getModel();
	int selectedIndex = myFlagsList.getSelectedIndex();
	int state;
	if (selectedIndex == -1)
	    state = -1;
	else {
	    JTriStateButton wendyCheckBox
		= (JTriStateButton) wendyListModel.getElementAt(selectedIndex);
	    state = wendyCheckBox.getTriState();
	}
	return state;
    }
    
    protected String getSelectedWendyFlag() {
	DefaultListModel wendyListModel
	    = (DefaultListModel) myFlagsList.getModel();
	int selectedIndex = myFlagsList.getSelectedIndex();
	String wendyFlag;
	if (selectedIndex == -1)
	    wendyFlag = null;
	else {
	    JTriStateButton wendyCheckBox
		= (JTriStateButton) wendyListModel.getElementAt(selectedIndex);
	    wendyFlag = wendyCheckBox.getText();
	}
	return wendyFlag;
    }
    
    public void addFilter() {
	String name = JOptionPane.showInputDialog("Wendy Filter Name: ");
	if (name != null) {
	    WendyFilter filter = new WendyFilter(name);
	    if (myWendyFilters.getFilter(name) == null) {
		myWendyFilters.addFilter(filter);
		setWendyFilters(myWendyFilters);
		myFiltersList.setSelectedItem(name);
	    }
	}
    }
    
    public void renameFilter() {
	int index = myFiltersList.getSelectedIndex();
	if (index != -1) {
	    String origWendyFilter = (String) myFiltersList.getSelectedItem();
	    String newWendyFilter
		= ((String)
		   JOptionPane.showInputDialog(this, "Wendy Filter Name: ",
					       "Wendy Filters", 3, null, null,
					       origWendyFilter));
	    if (newWendyFilter != null) {
		WendyFilter collisionFilter
		    = myWendyFilters.getFilter(newWendyFilter);
		if (collisionFilter == null) {
		    WendyFilter filter
			= myWendyFilters.getFilter(origWendyFilter);
		    filter.setName(newWendyFilter);
		    setWendyFilters(myWendyFilters);
		    myFiltersList.setSelectedItem(newWendyFilter);
		}
	    }
	}
    }
    
    public void deleteFilter() {
	int index = myFiltersList.getSelectedIndex();
	if (index != -1) {
	    String wendyFilter = (String) myFiltersList.getSelectedItem();
	    WendyFilter filter = myWendyFilters.getFilter(wendyFilter);
	    myWendyFilters.removeFilter(filter);
	    setWendyFilters(myWendyFilters);
	}
    }
}
