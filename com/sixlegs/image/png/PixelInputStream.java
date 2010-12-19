/* PixelInputStream - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
package com.sixlegs.image.png;
import java.io.IOException;
import java.io.InputStream;

final class PixelInputStream extends InputStream
{
    private final BitMover mover;
    private final InputStream str;
    private final int[] leftover = new int[8];
    private int leftamt = 0;
    final int fillSize;
    private int[] _i = new int[1];
    
    PixelInputStream(PngImage img, InputStream str) throws PngException {
	this.str = str;
	fillSize = Math.max(1, 8 / img.data.header.depth);
	mover = BitMover.getBitMover(img);
    }
    
    public int read() throws IOException {
	read(_i, 0, 1);
	return _i[0];
    }
    
    public int read(int[] b, int off, int len) throws IOException {
	int needed = len;
	int total = len;
	if (leftamt > 0) {
	    int fromleft = needed > leftamt ? leftamt : needed;
	    System.arraycopy(leftover, 8 - leftamt, b, off, fromleft);
	    needed -= fromleft;
	    leftamt -= fromleft;
	}
	if (needed > 0) {
	    off = mover.fill(b, str, off, needed / fillSize);
	    needed %= fillSize;
	    if (needed > 0) {
		leftamt = fillSize - needed;
		mover.fill(leftover, str, 8 - fillSize, 1);
		System.arraycopy(leftover, 8 - fillSize, b, off, needed);
	    }
	}
	return total;
    }
    
    public void close() throws IOException {
	super.close();
	str.close();
    }
}
