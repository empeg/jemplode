/* BitMover4P - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
package com.sixlegs.image.png;
import java.io.IOException;
import java.io.InputStream;

final class BitMover4P extends BitMover
{
    int fill(int[] pixels, InputStream str, int off, int len)
	throws IOException {
	for (int n = 0; n < len; n++) {
	    int x = str.read();
	    pixels[off++] = x >>> 4 & 0xf;
	    pixels[off++] = x & 0xf;
	}
	return off;
    }
}
