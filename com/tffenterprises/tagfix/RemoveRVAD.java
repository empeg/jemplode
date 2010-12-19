/* RemoveRVAD - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
package com.tffenterprises.tagfix;
import java.io.File;
import java.io.IOException;

import com.tffenterprises.music.io.TaggedFile;
import com.tffenterprises.music.tag.ID3v2;
import com.tffenterprises.music.tag.id3v2.Frame;

public class RemoveRVAD implements FileAction
{
    public void finish() {
	/* empty */
    }
    
    public boolean performAction(File f) throws IOException {
	boolean result = false;
	if (f.isFile() && f.getName().indexOf(".mp3") != -1) {
	    TaggedFile file = null;
	    try {
		file = new TaggedFile(f, TaggedFile.ReadWrite);
	    } catch (IOException e) {
		System.out
		    .println("(" + f.getName() + " failed to be opened)");
		return false;
	    }
	    if (file.hasID3v2()) {
		ID3v2 tag = file.getID3v2();
		System.out.println("Original tag:\n" + tag);
		for (Frame frame = tag.getFrameOfType("RVAD"); frame != null;
		     frame = tag.getFrameOfType("RVAD"))
		    tag.removeFrame(frame);
		System.out.println("New tag:\n" + tag);
		file.writeID3v2(tag);
		result = true;
	    }
	    file.close();
	}
	return result;
    }
}
