/*
 * @(#)CertPathValidatorResult.java	1.4 03/01/23
 *
 * Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java.security.cert;

/**
 * A specification of the result of a certification path validator algorithm.
 * <p>
 * The purpose of this interface is to group (and provide type safety 
 * for) all certification path validator results. All results returned 
 * by the {@link CertPathValidator#validate CertPathValidator.validate}
 * method must implement this interface.  
 *
 * @see CertPathValidator
 *
 * @version 	1.4 01/23/03
 * @since	1.4
 * @author	Yassir Elley
 */
public interface CertPathValidatorResult extends Cloneable {

    /**
     * Makes a copy of this <code>CertPathValidatorResult</code>. Changes to the
     * copy will not affect the original and vice versa.
     *
     * @return a copy of this <code>CertPathValidatorResult</code>
     */
    Object clone();
}
