/*
 * @(#)FileImageOutputStream.java	1.12 03/01/23
 *
 * Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javax.imageio.stream;

import java.io.DataInput;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * An implementation of <code>ImageOutputStream</code> that writes its
 * output directly to a <code>File</code> or
 * <code>RandomAccessFile</code>.
 *
 * @version 0.5
 */
public class FileImageOutputStream extends ImageOutputStreamImpl {

    private RandomAccessFile raf;

    /**
     * Constructs a <code>FileImageOutputStream</code> that will write
     * to a given <code>File</code>.
     *
     * @param f a <code>File</code> to write to.
     *
     * @exception IllegalArgumentException if <code>f</code> is
     * <code>null</code>.
     * @exception SecurityException if a security manager exists
     * and does not allow write access to the file.
     * @exception FileNotFoundException if <code>f</code> is a
     * directory or cannot be opened for reading and writing for any
     * other reason.
     * @exception IOException if an I/O error occurs.
     */
    public FileImageOutputStream(File f)
        throws FileNotFoundException, IOException {
        this(f == null ? null : new RandomAccessFile(f, "rw"));
    }

    /**
     * Constructs a <code>FileImageOutputStream</code> that will write
     * to a given <code>RandomAccessFile</code>.
     *
     * @param raf a <code>RandomAccessFile</code> to write to.
     *
     * @exception IllegalArgumentException if <code>raf</code> is
     * <code>null</code>.
     */
    public FileImageOutputStream(RandomAccessFile raf) {
        if (raf == null) {
            throw new IllegalArgumentException("raf == null!");
        }
        this.raf = raf;
    }

    public int read() throws IOException {
        checkClosed();
        bitOffset = 0;
        int val = raf.read();
        if (val != -1) {
            ++streamPos;
        }
        return val;
    }

    public int read(byte[] b, int off, int len) throws IOException {
        checkClosed();
        bitOffset = 0;
        int nbytes = raf.read(b, off, len);
        if (nbytes != -1) {
            streamPos += nbytes;
        }
        return nbytes;
    }

    public void write(int b) throws IOException {
        checkClosed();
        flushBits();
        raf.write(b);
        ++streamPos;
    }

    public void write(byte[] b, int off, int len) throws IOException {
        checkClosed();
        flushBits();
        raf.write(b, off, len);
        streamPos += len;
    }

    public long length() {
        try {
            checkClosed();
            return raf.length();
        } catch (IOException e) {
            return -1L;
        }
    }

    /**
     * Sets the current stream position and resets the bit offset to
     * 0.  It is legal to seeking past the end of the file; an
     * <code>EOFException</code> will be thrown only if a read is
     * performed.  The file length will not be increased until a write
     * is performed.
     *
     * @exception IndexOutOfBoundsException if <code>pos</code> is smaller
     * than the flushed position.
     * @exception IOException if any other I/O error occurs.
     */
    public void seek(long pos) throws IOException {
        checkClosed();
        if (pos < flushedPos) {
            throw new IndexOutOfBoundsException("pos < flushedPos!");
        }
        bitOffset = 0;
        raf.seek(pos);
        streamPos = raf.getFilePointer();
    }

    public void close() throws IOException {
        super.close();
        raf.close();
    }
}
