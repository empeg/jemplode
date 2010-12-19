/* TagWriterFactory - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
package org.jempeg.tags;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

import org.jempeg.nodestore.IFIDNode;

public class TagWriterFactory
{
    private static Vector TAGWRITERS = new Vector();
    
    public static void removeAllTagExtractors() {
	TAGWRITERS.removeAllElements();
    }
    
    public static void addTagWriter(ITagWriter _tagWriter) {
	TAGWRITERS.addElement(_tagWriter);
    }
    
    public static ITagWriter createTagWriter(IFIDNode _node)
	throws IOException {
	ITagWriter matchingTagWriter = null;
	Enumeration tagExtractorsEnum = TAGWRITERS.elements();
	while (matchingTagWriter == null
	       && tagExtractorsEnum.hasMoreElements()) {
	    ITagWriter tagWriter
		= (ITagWriter) tagExtractorsEnum.nextElement();
	    if (tagWriter.isTagWriterFor(_node))
		matchingTagWriter = tagWriter;
	}
	if (matchingTagWriter == null)
	    matchingTagWriter = new NoOpTagWriter();
	return matchingTagWriter;
    }
}
