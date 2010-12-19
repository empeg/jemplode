/**
* Copyright (c) 2001, Mike Schrag & Daniel Zimmerman
* All rights reserved.
*
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are met:
*
* Redistributions of source code must retain the above copyright notice,
* this list of conditions and the following disclaimer.
*
* Redistributions in binary form must reproduce the above copyright notice,
* this list of conditions and the following disclaimer in the documentation
* and/or other materials provided with the distribution.
*
* Neither the name of Mike Schrag, Daniel Zimmerman, nor the names of any
* other contributors may be used to endorse or promote products derived from
* this software without specific prior written permission.
*
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
* "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
* TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
* PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE REGENTS OR CONTRIBUTORS BE
* LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
* CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
* SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
* INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
* CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
* ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
* POSSIBILITY OF SUCH DAMAGE.
*/
package org.jempeg.empeg.logoedit.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.jempeg.ApplicationContext;
import org.jempeg.empeg.logoedit.GrabScreenDialog;
import org.jempeg.empeg.manager.plugins.JEmplodePlugin;
import org.jempeg.protocol.ProtocolException;

import com.inzyme.util.Debug;

/**
* Grabs the contents of the Empeg screen at displays them
* in a new frame.
*
* @author Mike Schrag
* @version $Revision: 1.4 $
*/
public class GrabScreenAction extends AbstractAction implements JEmplodePlugin {
	private ApplicationContext myContext;

	public GrabScreenAction(ApplicationContext _context) {
		myContext = _context;
	}

	public void actionPerformed(ActionEvent _event) {
		try {
			GrabScreenDialog grabScreenDialog = new GrabScreenDialog(myContext);
			grabScreenDialog.setVisible(true);
		}
		catch (ProtocolException e) {
			Debug.handleError(myContext.getFrame(), e, true);
		}
	}
}