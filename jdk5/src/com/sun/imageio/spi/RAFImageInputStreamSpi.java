/*
 * @(#)RAFImageInputStreamSpi.java	1.7 03/12/19
 *
 * Copyright 2004 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.sun.imageio.spi;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Locale;
import javax.imageio.spi.ImageInputStreamSpi;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.FileImageInputStream;

public class RAFImageInputStreamSpi extends ImageInputStreamSpi {

    private static final String vendorName = "Sun Microsystems, Inc.";

    private static final String version = "1.0";

    private static final Class inputClass = RandomAccessFile.class;

    public RAFImageInputStreamSpi() {
        super(vendorName, version, inputClass);
    }

    public String getDescription(Locale locale) {
        return "Service provider that instantiates a FileImageInputStream from a RandomAccessFile";
    }

    public ImageInputStream createInputStreamInstance(Object input,
                                                      boolean useCache,
                                                      File cacheDir) {
        if (input instanceof RandomAccessFile) {
            try {
                return new FileImageInputStream((RandomAccessFile)input);
            } catch (Exception e) {
                return null;
            }
        } else {
            throw new IllegalArgumentException
                ("input not a RandomAccessFile!");
        }
    }
}
