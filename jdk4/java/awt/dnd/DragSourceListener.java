/*
 * @(#)DragSourceListener.java	1.18 03/01/23
 *
 * Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java.awt.dnd;

import java.util.EventListener;

/**
 * The <code>DragSourceListener</code> defines the 
 * event interface for originators of
 * Drag and Drop operations to track the state of the user's gesture, and to
 * provide appropriate &quot;drag over&quot; 
 * feedback to the user throughout the
 * Drag and Drop operation.
 *
 * @version 	1.18, 01/23/03
 * @since 1.2
 */

public interface DragSourceListener extends EventListener {

    /**
     * Called as the cursor's hotspot enters a platform-dependent drop site.
     * This method is invoked when all the following conditions are true:
     * <UL>
     * <LI>The cursor's hotspot enters the operable part of a platform- 
     * dependent drop site.
     * <LI>The drop site is active.
     * <LI>The drop site accepts the drag.
     * </UL>
     * 
     * @param dsde the <code>DragSourceDragEvent</code>
     */
    void dragEnter(DragSourceDragEvent dsde);

    /**
     * Called as the cursor's hotspot moves over a platform-dependent drop site.
     * This method is invoked when all the following conditions are true:
     * <UL>
     * <LI>The cursor's hotspot has moved, but still intersects the
     * operable part of the drop site associated with the previous
     * dragEnter() invocation. 
     * <LI>The drop site is still active.
     * <LI>The drop site accepts the drag.
     * </UL>
     * 
     * @param dsde the <code>DragSourceDragEvent</code>
     */
    void dragOver(DragSourceDragEvent dsde);

    /**
     * Called when the user has modified the drop gesture.
     * This method is invoked when the state of the input
     * device(s) that the user is interacting with changes.
     * Such devices are typically the mouse buttons or keyboard
     * modifiers that the user is interacting with.
     * 
     * @param dsde the <code>DragSourceDragEvent</code>
     */
    void dropActionChanged(DragSourceDragEvent dsde);

    /**
     * Called as the cursor's hotspot exits a platform-dependent drop site.
     * This method is invoked when any of the following conditions are true:
     * <UL>
     * <LI>The cursor's hotspot no longer intersects the operable part
     * of the drop site associated with the previous dragEnter() invocation.
     * </UL>
     * OR
     * <UL>
     * <LI>The drop site associated with the previous dragEnter() invocation
     * is no longer active.
     * </UL>
     * OR
     * <UL>
     * <LI> The current drop site has rejected the drag.
     * </UL>
     * 
     * @param dse the <code>DragSourceEvent</code>
     */
    void dragExit(DragSourceEvent dse);

    /**
     * This method is invoked to signify that the Drag and Drop
     * operation is complete. The getDropSuccess() method of
     * the <code>DragSourceDropEvent</code> can be used to 
     * determine the termination state. The getDropAction() method
     * returns the operation that the drop site selected 
     * to apply to the Drop operation. Once this method is complete, the
     * current <code>DragSourceContext</code> and
     * associated resources become invalid.
     * 
     * @param dsde the <code>DragSourceDropEvent</code>
     */
    void dragDropEnd(DragSourceDropEvent dsde);
}






