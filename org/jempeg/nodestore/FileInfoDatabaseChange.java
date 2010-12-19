/* FileInfoDatabaseChange - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
package org.jempeg.nodestore;
import com.inzyme.text.ResourceBundleUtils;

import org.jempeg.protocol.IProtocolClient;
import org.jempeg.protocol.ISynchronizeClient;

public class FileInfoDatabaseChange extends AbstractNodeDatabaseChange
    implements IPlaylistMemberDatabaseChange
{
    private String myTagName;
    private String myOldValue;
    private String myNewValue;
    private PlaylistPair[] myPlaylistPairs;
    
    public FileInfoDatabaseChange(IFIDNode _node, String _tagName,
				  String _oldValue, String _newValue) {
	super(_node);
	myTagName = _tagName;
	myOldValue = _oldValue;
	myNewValue = _newValue;
	if (_node instanceof FIDPlaylist) {
	    FIDPlaylist playlist = (FIDPlaylist) _node;
	    myPlaylistPairs = playlist.getPlaylistPairs();
	}
    }
    
    public String getDescription() {
	return ResourceBundleUtils.getUIString("databaseChange.fileInfo",
					       (new Object[]
						{ getNode().getTitle() }));
    }
    
    public String getTagName() {
	return myTagName;
    }
    
    public String getOldValue() {
	return myOldValue;
    }
    
    public String getNewValue() {
	return myNewValue;
    }
    
    public PlaylistPair[] getPlaylistPairs() {
	return myPlaylistPairs;
    }
    
    public void setPlaylistPairs(PlaylistPair[] _playlistPairs) {
	myPlaylistPairs = _playlistPairs;
    }
    
    public long getLength() {
	return 5000L;
    }
    
    public void synchronize
	(ISynchronizeClient _synchronizeClient,
	 IProtocolClient _protocolClient)
	throws SynchronizeException {
	IFIDNode node = getNode();
	if (node instanceof FIDPlaylist)
	    PlaylistMembersDatabaseChange.synchronizePlaylistTags
		(this, _synchronizeClient, _protocolClient);
	else {
	    _synchronizeClient.synchronizeTags(node, _protocolClient);
	    node.setDirty(false);
	}
    }
}
