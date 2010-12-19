/* FIDInputStream - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
package org.jempeg.protocol;
import java.io.IOException;

import com.inzyme.io.ChainedIOException;
import com.inzyme.io.RefByteArrayOutputStream;
import com.inzyme.io.SeekableInputStream;

public class FIDInputStream extends SeekableInputStream
{
    private IProtocolClient myClient;
    private long myFID;
    private int myPos;
    private int myLength;
    
    public FIDInputStream(IProtocolClient _client, long _fid)
	throws ProtocolException {
	myClient = _client;
	myClient.open();
	myClient.readLock();
	myFID = _fid;
	myLength = (int) _client.getLength(_fid);
    }
    
    public long length() throws IOException {
	return (long) myLength;
    }
    
    public void seek(long _position) throws IOException {
	myPos = (int) _position;
    }
    
    public long tell() throws IOException {
	return (long) myPos;
    }
    
    public int available() throws IOException {
	return myLength - myPos;
    }
    
    public int read() throws IOException {
	if (myPos == myLength)
	    return -1;
	byte[] b = new byte[1];
	read(b);
	int value = b[0];
	return value & 0xff;
    }
    
    public synchronized int read(byte[] _buffer, int _offset, int _length)
	throws IOException {
	try {
	    if (myPos == myLength)
		return -1;
	    RefByteArrayOutputStream bufferOS = new RefByteArrayOutputStream();
	    myClient.read(myFID, (long) myPos,
			  (long) Math.min(_length, myLength - myPos), bufferOS,
			  (long) myLength);
	    int bytesRead = bufferOS.size();
	    System.arraycopy(bufferOS.getByteArray(), 0, _buffer, _offset,
			     bytesRead);
	    myPos += bytesRead;
	    return bytesRead;
	} catch (Exception e) {
	    throw new ChainedIOException
		      ("Unable to read from the requested FID.", e);
	}
    }
    
    public synchronized long skip(long n) throws IOException {
	int skipped = (int) Math.min(n, (long) (myLength - myPos));
	myPos += skipped;
	return (long) skipped;
    }
    
    public int read(byte[] _buffer) throws IOException {
	return read(_buffer, 0, _buffer.length);
    }
    
    public void close() throws IOException {
	try {
	    super.close();
	    myClient.unlock();
	    myClient.close();
	} catch (ProtocolException e) {
	    throw new ChainedIOException
		      ("Unable to unlock or close ProtocolClient.", e);
	}
    }
}
