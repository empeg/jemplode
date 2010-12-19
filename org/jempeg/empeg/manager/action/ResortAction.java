/* ResortAction - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
package org.jempeg.empeg.manager.action;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.inzyme.container.ContainerSelection;

import org.jempeg.ApplicationContext;
import org.jempeg.nodestore.FIDPlaylist;

public class ResortAction extends AbstractAction
{
    private ApplicationContext myContext;
    
    public ResortAction(ApplicationContext _context) {
	myContext = _context;
    }
    
    public void actionPerformed(ActionEvent _event) {
	ContainerSelection selection = myContext.getSelection();
	if (selection != null) {
	    Object[] values = selection.getSelectedValues();
	    for (int i = 0; i < values.length; i++) {
		if (values[i] instanceof FIDPlaylist) {
		    FIDPlaylist playlist = (FIDPlaylist) values[i];
		    playlist.resort(true);
		}
	    }
	}
    }
}
