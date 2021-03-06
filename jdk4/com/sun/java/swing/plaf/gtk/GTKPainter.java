/*
 * @(#)GTKPainter.java	1.55 03/05/05
 *
 * Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.sun.java.swing.plaf.gtk;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.*;

/**
 * @version 1.55, 05/05/03
 * @author Joshua Outwater
 * @author Scott Violet
 */
// Need to support:
// default_outside_border: Insets when default.
// interior_focus: Indicates if focus should appear inside border, or
//                       outside border.
// focus-line-width: Integer giving size of focus border
// focus-padding: Integer giving padding between border and focus
//        indicator.
// focus-line-pattern: 
//
class GTKPainter extends SynthPainter {
    static final GTKPainter INSTANCE = new GTKPainter();
    static final Insets EMPTY_INSETS = new Insets(0, 0, 0, 0);
    private static final Insets TEMP_INSETS = new Insets(0, 0, 0, 0);

    /**
     * Space for the default border.
     */
    private static final Insets BUTTON_DEFAULT_BORDER_INSETS =
                                       new Insets(1, 1, 1, 1);


    public void paint(SynthContext context, Object paintKey, Graphics g,
                      int x, int y, int w, int h) {
        Region id = context.getRegion();
        String name = (id.isSubregion()) ? null :
                                           context.getComponent().getName();
        GTKEngine engine = ((GTKStyle)context.getStyle()).getEngine(context);

        if (paintKey == "background") {
            if (!id.isSubregion()) {
                // Offset by the insets, if necessary.
                Border border = context.getComponent().getBorder();

                if (border != null && !(border instanceof UIResource)) {
                    Insets insets = context.getComponent().getInsets(
                                            TEMP_INSETS);

                    if (insets != null) {
                        x += insets.left;
                        y += insets.right;
                        w -= (insets.left + insets.right);
                        h -= (insets.top + insets.bottom);
                    }
                }
            }
            if (id == Region.ARROW_BUTTON) {
                paintArrowButtonBackground(context, engine, g, x, y, w, h);
            }
            else if (id == Region.BUTTON) {
                if (name != null &&
                        name.startsWith("InternalFrameTitlePane.")) {
                    Metacity.INSTANCE.paintButtonBackground(context,
                            g, x, y, w, h);
                } else {
                    paintButtonBackground(context, engine, g, x, y, w, h,
                                          "button");
                }
            }
            else if (id == Region.FORMATTED_TEXT_FIELD) {
                paintTextFieldBackground(context, engine, g, x, y, w, h);
            }
            else if (id == Region.LABEL) {
                if ("TableHeader.renderer" == name ||
                               name == "GTKFileChooser.directoryListLabel" ||
                               name == "GTKFileChooser.fileListLabel") {
                    paintButtonBackground(context, engine, g, x, y, w, h,
                                          "button");
                }
                else if (name == "ComboBox.renderer") {
                    paintTextFieldBackground(context, engine, g, x, y, w, h);
                }
            }
            else if (id == Region.LIST) {
                paintListBackground(context, engine, g, x, y, w, h);
            }
            else if (id == Region.MENU) {
                paintMenuBackground(context, engine, g, x, y, w, h);
            }
            else if (id == Region.MENU_BAR) {
                paintMenuBarBackground(context, engine, g, x, y, w, h);
            }
            else if (id == Region.MENU_ITEM) {
                paintMenuItemBackground(context, engine, g, x, y, w, h);
            }
            else if (id == Region.POPUP_MENU) {
                paintPopupMenuBackground(context, engine, g, x, y, w, h);
            }
            else if (id == Region.PROGRESS_BAR) {
                paintProgressBarBackground(context, engine, g, x, y, w, h);
            }
            else if (id == Region.CHECK_BOX || id == Region.RADIO_BUTTON) {
                paintCheckButtonBackground(context, engine, g, x, y, w, h);
            }
            else if (id == Region.CHECK_BOX_MENU_ITEM
                    || id == Region.RADIO_BUTTON_MENU_ITEM) {
                paintCheckButtonMenuItemBackground(context, engine, g,
                        x, y, w, h);
            }
            else if (id == Region.ROOT_PANE) {
                paintRootPaneBackground(context, engine, g, x, y, w, h);
            }
            else if (id == Region.SCROLL_BAR) {
                paintScrollBarBackground(context, engine, g, x, y, w, h);
            }
            else if (id == Region.SCROLL_BAR_THUMB) {
                paintScrollBarThumbBackground(context, engine, g, x, y, w, h);
            }
            /*
            else if (id == Region.SCROLL_PANE) {
                paintScrollPaneBackground(context, engine, g, x, y, w, h);
            }
            */
            else if (id == Region.SEPARATOR) {
                paintSeparatorBackground(context, engine, g, x, y, w, h);
            }
            else if (id == Region.SLIDER_TRACK) {
                paintSliderTrackBackground(context, engine, g, x, y, w, h);
            }
            else if (id == Region.SLIDER_THUMB) {
                paintSliderThumbBackground(context, engine, g, x, y, w, h);
            }
            else if (id == Region.SPINNER) {
                paintSpinnerBackground(context, engine, g, x, y, w, h);
            }
            else if (id == Region.SPLIT_PANE_DIVIDER) {
                paintSplitPaneDividerBackground(context, engine, g, x, y, w, h);
            }
            else if (id == Region.TABBED_PANE_CONTENT) {
                paintTabbedPaneContentBackground(context,
                    engine, g, x, y, w, h);
            }
            else if (id == Region.TABBED_PANE_TAB) {
                paintTabbedPaneTabBackground(context, engine, g, x, y, w, h);
            }
            else if (id == Region.TEXT_AREA) {
                paintTextAreaBackground(context, engine, g, x, y, w, h);
            }
            else if (id == Region.TEXT_FIELD) {
                if (name == "Tree.cellEditor") {
                    paintTreeCellEditorBackground(context, engine, g, x, y, w,
                                                  h);
                }
                else {
                    paintTextFieldBackground(context, engine, g, x, y, w, h);
                }
            }
            else if (id == Region.TOGGLE_BUTTON) {
                paintToggleButtonBackground(context, engine, g, x, y, w, h);
            }
            else if (id == Region.TOOL_BAR ||
                     id == Region.TOOL_BAR_DRAG_WINDOW) {
                paintToolBarBackground(context, engine, g, x, y, w, h);
            }
            else if (id == Region.TOOL_BAR_CONTENT) {
                paintToolBarContentBackground(context, engine, g, x, y, w, h);
            }
            else if (id == Region.TOOL_TIP) {
                paintToolTipBackground(context, engine, g, x, y, w, h);
            }
            else if (id == Region.TREE) {
                paintTreeBackground(context, engine, g, x, y, w, h);
            }
            else if (id == Region.TREE_CELL) {
                paintTreeCellBackground(context, engine, g, x, y, w, h);
            }
            else if (id == Region.VIEWPORT) {
                paintViewportBackground(context, engine, g, x, y, w, h);
            }
        } else if (paintKey == "border") {
            if (id == Region.INTERNAL_FRAME) {
                Metacity.INSTANCE.paintFrameBorder(context, g, x, y, w, h);
            }
            else if (id == Region.VIEWPORT) {
                paintScrollPaneBackground(context, engine, g, x, y, w, h);
            }
        }
        else if (paintKey == "foreground") {
            if (id == Region.ARROW_BUTTON) {
                paintArrowButtonForeground(context, engine, g, x, y, w, h);
            }
            else if (id == Region.PROGRESS_BAR) {
                paintProgressBarForeground(context, engine, g, x, y, w, h);
            }
            else if (id == Region.SPLIT_PANE_DIVIDER) {
                paintSplitPaneDividerDragBackground(context, engine, g, x, y,
                                                    w,h);
            }
        }
        else if (paintKey == "focus") {
            if (id == Region.TREE_CELL) {
                paintTreeCellFocus(context, engine, g, x, y, w, h);
            }
        }
    }

    Insets getInsets(SynthContext state, Insets insets) {
        Region id = state.getRegion();
        String name = (id.isSubregion()) ? null :
                                           state.getComponent().getName();

        if (insets == null) {
            insets = new Insets(0, 0, 0, 0);
        }
        else {
            insets.top = insets.bottom = insets.left = insets.right = 0;
        }
        if (id == Region.BUTTON || id == Region.TOGGLE_BUTTON
                || id == Region.CHECK_BOX || id == Region.RADIO_BUTTON
                || id == Region.ARROW_BUTTON || id == Region.TOGGLE_BUTTON
                || id == Region.MENU 
                || id == Region.MENU_ITEM
                || id == Region.RADIO_BUTTON_MENU_ITEM
                || id == Region.CHECK_BOX_MENU_ITEM) {
            if ("Spinner.previousButton" == name ||
                    "Spinner.nextButton" == name) {
                return getSpinnerButtonInsets(state, insets);
            } else {
                return getButtonInsets(state, insets);
            }
        }
        else if (id == Region.FORMATTED_TEXT_FIELD) {
            return getTextFieldInsets(state, insets);
        }
        else if (id == Region.INTERNAL_FRAME) {
            insets = Metacity.INSTANCE.getBorderInsets(state, insets);
        }
        else if (id == Region.LABEL) {
            if ("TableHeader.renderer" == name) {
                return getButtonInsets(state, insets);
            }
            else if ("ComboBox.renderer" == name) {
                return getTextFieldInsets(state, insets);
            }
            else if ("Tree.cellRenderer" == name) {
                return getTreeCellRendererInsets(state, insets);
            }
        }
        else if (id == Region.MENU_BAR) {
            return getMenuBarInsets(state, insets);
        }
        else if (id == Region.OPTION_PANE) {
            return getOptionPaneInsets(state, insets);
        }
        else if (id == Region.POPUP_MENU) {
            return getPopupMenuInsets(state, insets);
        }
        else if (id == Region.PROGRESS_BAR) {
            return getProgressBarInsets(state, insets);
        }
        else if (id == Region.SCROLL_BAR) {
            return getScrollBarInsets(state, insets);
        }
        else if (id == Region.SEPARATOR) {
            return getSeparatorInsets(state, insets);
        }
        else if (id == Region.SLIDER) {
            return getSliderInsets(state, insets);
        }
        else if (id == Region.SLIDER_TRACK) {
            return getSliderTrackInsets(state, insets);
        }
        else if (id == Region.SPINNER) {
            return getSpinnerInsets(state, insets);
        }
        else if (id == Region.TABBED_PANE) {
            return getTabbedPaneInsets(state, insets);
        }
        else if (id == Region.TABBED_PANE_CONTENT) {
            return getTabbedPaneContentInsets(state, insets);
        }
        else if (id == Region.TABBED_PANE_TAB) {
            return getTabbedPaneTabInsets(state, insets);
        }
        else if (id == Region.TEXT_FIELD) {
            if (name == "Tree.cellEditor") {
                return getTreeCellEditorInsets(state, insets);
            }
            return getTextFieldInsets(state, insets);
        }
        else if (id == Region.TOOL_BAR) {
            return getToolBarInsets(state, insets);
        }
        else if (id == Region.TOOL_TIP) {
            return getToolTipInsets(state, insets);
        }
        return insets;
    }

    //
    // BUTTON
    //
    private Insets getButtonInsets(SynthContext context, Insets insets) {
        GTKStyle style = (GTKStyle)context.getStyle();
        int focusSize = style.getClassSpecificIntValue(context,
                                                       "focus-line-width",1);
        int focusPad = style.getClassSpecificIntValue(context,
                                                      "focus-padding", 1);
        int xThickness = style.getXThickness();
        int yThickness = style.getYThickness();
        int w = focusSize + focusPad + xThickness;
        int h = focusSize + focusPad + yThickness;
        Component component = context.getComponent();

        insets.left = insets.right = w;
        insets.top = insets.bottom = h;
        if ((component instanceof JButton) &&
                       ((JButton)component).isDefaultCapable()) {
            Insets defaultInsets = style.getClassSpecificInsetsValue(context,
                          "default-border", BUTTON_DEFAULT_BORDER_INSETS);
            insets.left += defaultInsets.left;
            insets.right += defaultInsets.right;
            insets.top += defaultInsets.top;
            insets.bottom += defaultInsets.bottom;
        }
        return insets;
    }

    private void paintButtonBackground(SynthContext context, GTKEngine engine,
                                       Graphics g, int x, int y, int w, int h,
                                       String detail) {
        // paing the default shadow
        int state = context.getComponentState();
        int gtkState = GTKLookAndFeel.synthStateToGTKState(
                                      context.getRegion(), state);
        GTKStyle style = (GTKStyle)context.getStyle();
        boolean interiorFocus = style.getClassSpecificBoolValue(context,
                                                 "interior-focus", true);
        int focusSize = style.getClassSpecificIntValue(context,
                                                       "focus-line-width",1);
        int focusPad = style.getClassSpecificIntValue(context, "focus-padding",
                                                      1);
        int totalFocusSize = focusSize + focusPad;
        int xThickness = style.getXThickness();
        int yThickness = style.getYThickness();
        Component component = context.getComponent();

        // Paint the default indicator
        if ((component instanceof JButton) &&
                       ((JButton)component).isDefaultCapable()) {
            Insets defaultInsets = (Insets)style.getClassSpecificInsetsValue(
                      context, "default-border", BUTTON_DEFAULT_BORDER_INSETS);

            if ((state & SynthConstants.DEFAULT) == SynthConstants.DEFAULT) {
                engine.paintBox(context, g, SynthConstants.ENABLED,
                                GTKConstants.SHADOW_IN, "buttondefault",
                                x, y, w, h);
            }
            x += defaultInsets.left;
            y += defaultInsets.top;
            w -= (defaultInsets.left + defaultInsets.right);
            h -= (defaultInsets.top + defaultInsets.bottom);
        }

        // Render the box.
        if (!interiorFocus && (state & SynthConstants.FOCUSED) ==
                              SynthConstants.FOCUSED) {
            x += totalFocusSize;
            y += totalFocusSize;
            w -= 2 * totalFocusSize;
            h -= 2 * totalFocusSize;
        }
        if (!(component.getParent() instanceof JToolBar) ||
                        gtkState != SynthConstants.ENABLED) {
            int shadowType;
            if ((state & SynthConstants.PRESSED) == SynthConstants.PRESSED) {
                shadowType = GTKConstants.SHADOW_IN;
            }
            else {
                shadowType = GTKConstants.SHADOW_OUT;
            }
            engine.paintBox(context, g, gtkState,  shadowType, detail,
                            x, y, w, h);
        }

        // focus
        if ((state & SynthConstants.FOCUSED) == SynthConstants.FOCUSED) {
            if (interiorFocus) {
                x += xThickness + focusPad;
                y += yThickness + focusPad;
                w -= 2 * (xThickness + focusPad);
                h -= 2 * (yThickness + focusPad);
            }
            else {
                x -= totalFocusSize;
                y -= totalFocusSize;
                w += 2 * totalFocusSize;
                h += 2 * totalFocusSize;
            }
            engine.paintFocus(context, g, gtkState, detail, x, y, w, h);
        }
    }


    //
    // ARROW_BUTTON
    //
    private void paintArrowButtonForeground(SynthContext context,
                                            GTKEngine engine, Graphics g,
                                            int x, int y, int w, int h) {
        int state = GTKLookAndFeel.synthStateToGTKState(
                           Region.ARROW_BUTTON, context.getComponentState());
        int shadowType;

        if (state == SynthConstants.PRESSED) {
            shadowType = GTKConstants.SHADOW_OUT;
        }
        else {
            shadowType = GTKConstants.SHADOW_IN;
        }
        int direction = ((SynthArrowButton)context.getComponent()).
                        getDirection();
        switch (direction) {
        case SwingConstants.NORTH:
            direction = GTKConstants.ARROW_UP;
            break;
        case SwingConstants.SOUTH:
            direction = GTKConstants.ARROW_DOWN;
            break;
        case SwingConstants.EAST:
            direction = GTKConstants.ARROW_RIGHT;
            break;
        case SwingConstants.WEST:
            direction = GTKConstants.ARROW_LEFT;
            break;
        }
        Component c = context.getComponent();
        String name = c.getName();
        String detail = "arrow";

        if (name == "ScrollBar.button") {
            if (c.getParent() instanceof JScrollBar) {
                if (((JScrollBar)c.getParent()).getOrientation() ==
                                   SwingConstants.HORIZONTAL) {
                    detail = "hscrollbar";
                }
                else {
                    detail = "vscrollbar";
                }
            }
        }

        // Note quite sure what their algorithm is, but this works pretty
        // good.
        int size = Math.min(w / 2, h / 2);
        x += (w - size) / 2;
        y += (h - size) / 2;

        engine.paintArrow(context, g, state, shadowType, direction,
                          detail, x, y, size, size);
    }

    private void paintArrowButtonBackground(SynthContext context,
                                            GTKEngine engine, Graphics g,
                                            int x, int y, int w, int h) {
        Component c = context.getComponent();
        String name = c.getName();
        String detail = "button";

        if (name == "ScrollBar.button") {
            if (c.getParent() instanceof JScrollBar) {
                if (((JScrollBar)c.getParent()).getOrientation() ==
                                   SwingConstants.HORIZONTAL) {
                    detail = "hscrollbar";
                }
                else {
                    detail = "vscrollbar";
                }
            }
        } else if ("Spinner.previousButton" == name ||
                   "Spinner.nextButton" == name) {
            detail = "spinbutton";
        }
        paintButtonBackground(context, engine, g, x, y, w, h, detail);
    }

    //
    // CHECK_BUTTON 
    //
    private void paintCheckButtonBackground(SynthContext context,
                                            GTKEngine engine, Graphics g,
                                            int x, int y, int w, int h) {
        int gtkState = GTKLookAndFeel.synthStateToGTKState(
                context.getRegion(), context.getComponentState());

        if ((gtkState & SynthConstants.MOUSE_OVER) != 0) {
            engine.paintFlatBoxNormal(context, g, SynthConstants.MOUSE_OVER,
                                "checkbutton", x, y, w, h);
        }
    }


    //
    // CHECK_BUTTON_MENU_ITEM
    //
    public void paintCheckButtonMenuItemBackground(SynthContext context,
                                            GTKEngine engine, Graphics g,
                                            int x, int y, int w, int h) {
        int gtkState = GTKLookAndFeel.synthStateToGTKState(
                context.getRegion(), context.getComponentState());
        if ((context.getComponentState() & SynthConstants.MOUSE_OVER) != 0) {
            engine.paintBox(context, g, gtkState, GTKConstants.SHADOW_OUT,
                "menuitem", x, y, w, h);
        }
    }


    //
    // LIST
    //
    private void paintListBackground(SynthContext context,
                                    GTKEngine engine, Graphics g,
                                    int x, int y, int w, int h) {
        int state = GTKLookAndFeel.synthStateToGTKState(
                Region.LIST, context.getComponentState());

        if (context.getComponent().getName() == "ComboBox.list") {
            // Paint it's shadow.
        }
        engine.paintFlatBoxText(context, g, state, "base", x, y, w, h);

    }

    //
    // MENU_BAR
    //
    private Insets getMenuBarInsets(SynthContext context, Insets insets) {
        GTKStyle style = (GTKStyle)context.getStyle();
        int internalPadding = style.getClassSpecificIntValue(context,
                                                       "internal-padding", 1);
        int xThickness = style.getXThickness();
        int yThickness = style.getYThickness();
        insets.top = insets.bottom = internalPadding + yThickness;
        insets.left = insets.right = internalPadding + xThickness;
        return insets;
    }

    private void paintMenuBarBackground(SynthContext context,
                                        GTKEngine engine, Graphics g,
                                        int x, int y, int w, int h) {
        GTKStyle style = (GTKStyle)context.getStyle();
        int shadowType = style.getClassSpecificIntValue(context,
                "shadow-type", GTKConstants.SHADOW_OUT);
        int gtkState = GTKLookAndFeel.synthStateToGTKState(
                context.getRegion(), context.getComponentState());

        engine.paintBox(context, g, gtkState, shadowType, "menubar",
                x, y, w, h);
    }

    //
    // MENU
    //
    private void paintMenuBackground(SynthContext context,
                                     GTKEngine engine, Graphics g,
                                     int x, int y, int w, int h) {
        int gtkState = GTKLookAndFeel.synthStateToGTKState(
                context.getRegion(), context.getComponentState());
        if (gtkState == SynthConstants.MOUSE_OVER) {
            engine.paintBox(context, g, gtkState, GTKConstants.SHADOW_OUT,
                "menuitem", x, y, w, h);
        }
    }

    //
    // MENU_ITEM
    //
    private Insets getMenuItemInsets(SynthContext context, Insets insets) {
        GTKStyle style = (GTKStyle)context.getStyle();
        int internalPadding = style.getClassSpecificIntValue(context,
                                                       "internal-padding", 1);
        int xThickness = style.getXThickness();
        int yThickness = style.getYThickness();
        insets.top = insets.bottom = internalPadding + yThickness;
        insets.left = insets.right = internalPadding + xThickness;
        return insets;
    }

    private void paintMenuItemBackground(SynthContext context,
                                     GTKEngine engine, Graphics g,
                                     int x, int y, int w, int h) {
        int gtkState = GTKLookAndFeel.synthStateToGTKState(
                context.getRegion(), context.getComponentState());
        if ((context.getComponentState() & SynthConstants.MOUSE_OVER) != 0) {
            engine.paintBox(context, g, gtkState, GTKConstants.SHADOW_OUT,
                "menuitem", x, y, w, h);
        }
    }

    private Insets getPopupMenuInsets(SynthContext context, Insets insets) {
        insets.left = insets.right = insets.top = insets.bottom = 2;
        return insets;
    }
    
    private void paintPopupMenuBackground(SynthContext context,
                                        GTKEngine engine, Graphics g,
                                        int x, int y, int w, int h) {
        int gtkState = GTKLookAndFeel.synthStateToGTKState(
                context.getRegion(), context.getComponentState());
        engine.paintBox(context, g, gtkState, GTKConstants.SHADOW_OUT,
                "menu", x, y, w, h);

        GTKStyle style = (GTKStyle)context.getStyle();
        int xThickness = style.getXThickness();
        int yThickness = style.getYThickness();

        engine.paintBackground(context, g, gtkState, style.getGTKColor(
               context.getComponent(), context.getRegion(), gtkState,
               GTKColorType.BACKGROUND), x + xThickness, y + yThickness,
               w - xThickness - xThickness, h - yThickness - yThickness);
    }

    //
    // PROGRESS_BAR
    //
    private Insets getProgressBarInsets(SynthContext context, Insets insets) {
        GTKStyle style = (GTKStyle)context.getStyle();
        insets.left = insets.right = style.getXThickness();
        insets.top = insets.bottom = style.getYThickness();
        return insets;
    }

    private void paintProgressBarBackground(SynthContext context,
                                            GTKEngine engine, Graphics g,
                                            int x, int y, int w, int h) {
        GTKStyle style = (GTKStyle)context.getStyle();
        w -= style.getXThickness();
        h -= style.getYThickness();
        // Draw the trough.
        engine.paintBox(context, g, SynthConstants.ENABLED,
                            GTKConstants.SHADOW_IN, "trough", x, y, w, h);

    }

    private void paintProgressBarForeground(SynthContext context,
                                            GTKEngine engine, Graphics g,
                                            int x, int y, int w, int h) {
        // Draw the actual progress of the progress bar.
        if (w != 0 || h != 0) {
            engine.paintBox(context, g, SynthConstants.ENABLED,
                GTKConstants.SHADOW_OUT, "bar", x, y, w, h);
        }
    }

    private void paintScrollPaneBackground(SynthContext context,
                                           GTKEngine engine, Graphics g,
                                           int x, int y, int w, int h) {
        engine.paintShadow(context, g, SynthConstants.ENABLED,
                           GTKConstants.SHADOW_IN, "scrolled_window",
                           x, y, w, h);
    }

    Insets getScrollPaneInsets(SynthContext context, Insets insets) {
        GTKStyle style = (GTKStyle)context.getStyle();
        insets.right = insets.left = style.getXThickness();
        insets.top = insets.bottom = style.getYThickness();
        return insets;
    }

    //
    // SEPARATOR
    //
    private Insets getSeparatorInsets(SynthContext context, Insets insets) {
        return insets;
    }

    private void paintSeparatorBackground(SynthContext context,
                                          GTKEngine engine, Graphics g,
                                          int x, int y, int w, int h) {
        if (((JSeparator)context.getComponent()).getOrientation() ==
                JSeparator.HORIZONTAL) {
            engine.paintHline(context, g, SynthConstants.ENABLED,
                "hseparator", x, y, w, h);
        } else {
            engine.paintVline(context, g, SynthConstants.ENABLED,
                "vseparator", x, y, w, h);
        }
    }

    //
    // SLIDER
    //
    private Insets getSliderInsets(SynthContext context, Insets insets) {
        GTKStyle style = (GTKStyle)context.getStyle();
        int xThickness = style.getXThickness();
        int yThickness = style.getYThickness();
        insets.top = insets.bottom = yThickness;
        insets.right = insets.left = xThickness;
        return insets;
    }

    private Insets getSliderTrackInsets(SynthContext context, Insets insets) {
        GTKStyle style = (GTKStyle)context.getStyle();
        int focusSize = style.getClassSpecificIntValue(context,
                                                        "focus-line-width", 1);
        int focusPad = style.getClassSpecificIntValue(context,
                                                      "focus-padding", 1);
        insets.top = insets.bottom = insets.left = insets.right =
            focusSize + focusPad;
        return insets;
    }

    private void paintSliderTrackBackground(SynthContext context,
                                       GTKEngine engine, Graphics g,
                                       int x, int y, int w,int h) {
        GTKStyle style = (GTKStyle)context.getStyle();

        int state = context.getComponentState();
        int gtkState = GTKLookAndFeel.synthStateToGTKState(context.getRegion(),
                                                           state);
        engine.paintBox(context, g, gtkState,
                            GTKConstants.SHADOW_IN, "trough", x, y, w, h);

        if ((state & SynthConstants.FOCUSED) == SynthConstants.FOCUSED) {
            int focusSize = style.getClassSpecificIntValue(context,
                "focus-line-width", 1);
            focusSize += style.getClassSpecificIntValue(context,
                "focus-padding", 1);
            x -= focusSize;
            y -= focusSize;
            w += 2 * focusSize;
            h += 2 * focusSize;
            engine.paintFocus(context, g, SynthConstants.ENABLED,
                              "trough", x, y, w, h);
        }
    }

    private void paintSliderThumbBackground(SynthContext context,
                                       GTKEngine engine, Graphics g,
                                       int x, int y, int w, int h) {
        int orientation;

        if (((JSlider)context.getComponent()).getOrientation() ==
                              JSlider.HORIZONTAL) {
            orientation = GTKConstants.HORIZONTAL;
        }
        else {
            orientation = GTKConstants.VERTICAL;
        }
        engine.paintSlider(context, g, GTKLookAndFeel.synthStateToGTKState(
               context.getRegion(), context.getComponentState()),
               GTKConstants.SHADOW_OUT, "slider", x, y, w, h, orientation);
    }

    //
    // SPINNER
    //
    private void paintSpinnerBackground(SynthContext context,
                                        GTKEngine engine, Graphics g,
                                        int x, int y, int w, int h) {
        int gtkState = GTKLookAndFeel.synthStateToGTKState(context.getRegion(),
                context.getComponentState());
        engine.paintBox(context, g, gtkState, GTKConstants.SHADOW_IN,
            "spinbutton", x, y, w, h);
    }

    private Insets getSpinnerInsets(SynthContext context, Insets insets) {
        GTKStyle style = (GTKStyle)context.getStyle();
        int xThickness = style.getXThickness();
        int yThickness = style.getYThickness();
        insets.top = insets.bottom = yThickness;
        insets.right = 1;
        insets.left = xThickness;
        return insets;
    }

    private Insets getSpinnerButtonInsets(SynthContext context, Insets insets) {
        insets.top = insets.bottom = 1;
        insets.right = insets.left = 1;
        return insets;
    }

    //
    // SPLIT_PANE_DIVIDER
    //
    private void paintSplitPaneDividerBackground(SynthContext context,
                                       GTKEngine engine, Graphics g,
                                       int x, int y, int w, int h) {
        int orientation;
        if (((JSplitPane)context.getComponent()).getOrientation() ==
                                 JSplitPane.VERTICAL_SPLIT) {
            orientation = GTKConstants.HORIZONTAL;
        }
        else {
            orientation = GTKConstants.VERTICAL;
        }

        engine.paintHandle(context, g, GTKLookAndFeel.synthStateToGTKState(
                context.getRegion(), context.getComponentState()),
                GTKConstants.UNDEFINED, "paned", x, y, w, h, orientation);
    }

    private void paintSplitPaneDividerDragBackground(SynthContext context,
                                       GTKEngine engine, Graphics g,
                                       int x, int y, int w, int h) {
        g.setColor(context.getStyle().getColor(context,
                                               GTKColorType.BACKGROUND));
        g.fillRect(x, y, w, h);
        paintSplitPaneDividerBackground(context, engine, g, x, y, w, h);
    }

    //
    // TABBED_PANE
    //
    private Insets getTabbedPaneInsets(SynthContext context, Insets insets) {
        GTKStyle style = (GTKStyle)context.getStyle();
        int xThickness = style.getXThickness();
        int yThickness = style.getYThickness();
        insets.top = insets.bottom = yThickness;
        insets.right = insets.left = xThickness;
        return insets;
    }

    //
    // TABBED_PANE_CONTENT
    //
    private Insets getTabbedPaneContentInsets(SynthContext context,
            Insets insets) {
        GTKStyle style = (GTKStyle)context.getStyle();
        int xThickness = style.getXThickness();
        int yThickness = style.getYThickness();
        insets.top = insets.bottom = yThickness;
        insets.right = insets.left = xThickness;
        return insets;
    }

    private void paintTabbedPaneContentBackground(SynthContext context,
                                            GTKEngine engine, Graphics g,
                                            int x, int y, int w, int h) {
        JTabbedPane tabPane = (JTabbedPane)context.getComponent();
        GTKStyle style = (GTKStyle)context.getStyle();
        Region region = context.getRegion();
        int placement = GTKLookAndFeel.SwingOrientationConstantToGTK(
                                     tabPane.getTabPlacement());
        int start = 0;
        int size = 0;

        int selectedIndex = tabPane.getSelectedIndex();
        if (selectedIndex != -1) {
            Rectangle tabBounds = tabPane.getBoundsAt(selectedIndex);

            if (placement == GTKConstants.TOP ||
                             placement == GTKConstants.BOTTOM) {
                start = tabBounds.x - 1;
                size = tabBounds.width;
            }
            else {
                start = tabBounds.y - 1;
                size = tabBounds.height;
            }
        }
        engine.paintBoxGap(context, g, GTKLookAndFeel.synthStateToGTKState(
                    context.getRegion(), context.getComponentState()),
                    GTKConstants.SHADOW_OUT, "notebook", x, y, w, h,
                    placement, start, size);
    }

    //
    // TABBED_PANE_TAB
    //
    private Insets getTabbedPaneTabInsets(SynthContext context,
            Insets insets) {
        GTKStyle style = (GTKStyle)context.getStyle();
        int xThickness = style.getXThickness();
        int yThickness = style.getYThickness();
        int focusSize = 0;
        int pad = 2;

        focusSize = style.getClassSpecificIntValue(context,
                "focus-line-width",1);
        insets.left = insets.right = focusSize + pad + xThickness;
        insets.top = insets.bottom = focusSize + pad + yThickness;
        return insets;
    }

    private void paintTabbedPaneTabBackground(SynthContext context,
                                           GTKEngine engine, Graphics g,
                                           int x, int y, int w, int h) {
        GTKStyle style = (GTKStyle)context.getStyle();
        int state = context.getComponentState();
        int tabIndex = ((TabContext)context).getTabIndex();
        int selectedIndex = ((JTabbedPane)context.getComponent()).
                            getSelectedIndex();
        int offset = (selectedIndex == tabIndex) ? 0 : 2;
        int side;
        state = GTKLookAndFeel.synthStateToGTKState(context.getRegion(), state);
        switch (((JTabbedPane)context.getComponent()).getTabPlacement()) {
        case SwingConstants.TOP:
            side = GTKConstants.BOTTOM;
            y += offset;
            h -= offset;
            break;
        case SwingConstants.BOTTOM:
            h -= offset;
            side = GTKConstants.TOP;
            break;
        case SwingConstants.LEFT:
            x += offset;
            w -= offset;
            side = GTKConstants.RIGHT;
            break;
        default:
            w -= offset;
            side = GTKConstants.LEFT;
            break;
        }
        engine.paintExtension(context, g, state, GTKConstants.SHADOW_OUT,
                "tab", x, y, w, h, side);
    }

    //
    // TEXT_AREA
    //
    private void paintTextAreaBackground(SynthContext context,
                                         GTKEngine engine, Graphics g,
                                         int x, int y, int w, int h) {
        GTKStyle style = (GTKStyle)context.getStyle();
        int state = context.getComponentState();
        engine.paintFlatBoxText(context, g, state, "base", x, y, w, h);
    }

    //
    // TEXT_FIELD
    //
    // NOTE: comobobox and FormattedTextField calls this too.
    private void paintTextFieldBackground(SynthContext context,
                                          GTKEngine engine, Graphics g,
                                          int x, int y, int w, int h) {
        GTKStyle style = (GTKStyle)context.getStyle();
        boolean interiorFocus = style.getClassSpecificBoolValue(context,
                                                 "interior-focus", true);
        int state = context.getComponentState();
        int gtkState = GTKLookAndFeel.synthStateToGTKState(
                                        context.getRegion(), state);
        int focusSize;
        int xThickness = style.getXThickness();
        int yThickness = style.getYThickness();

        paintTextBackground(context, engine, g, x, y, w, h);
        state = GTKLookAndFeel.synthStateToGTKState(context.getRegion(),state);

        if (!interiorFocus && (state & SynthConstants.FOCUSED) ==
                              SynthConstants.FOCUSED) {
            focusSize = style.getClassSpecificIntValue(context,
                                                       "focus-line-width",1);
            x += focusSize;
            y += focusSize;
            w -= 2 * focusSize;
            h -= 2 * focusSize;
        }
        else {
            focusSize = 0;
        }
        engine.paintFlatBoxText(context, g, SynthConstants.ENABLED, "entry_bg",
               x + xThickness, y + yThickness, w - (2 * xThickness),
               h - (2 * yThickness));

        if (context.getComponent().getName() != "Spinner.formattedTextField") {
            engine.paintShadow(context, g, SynthConstants.ENABLED,
                           GTKConstants.SHADOW_IN, "entry", x, y, w, h);
        }

        if (focusSize > 0) {
            x -= focusSize;
            y -= focusSize;
            w += 2 * focusSize;
            h += 2 * focusSize;
            engine.paintFocus(context, g, gtkState, "entry", x, y, w, h);
        }
    }

    // NOTE: this is called for ComboBox, and FormattedTextField. too.
    private Insets getTextFieldInsets(SynthContext context, Insets insets) {
        GTKStyle style = (GTKStyle)context.getStyle();
        int xThickness = style.getXThickness();
        int yThickness = style.getYThickness();
        boolean interiorFocus = style.getClassSpecificBoolValue(context,
                                                 "interior-focus", true);
        int focusSize = 0;
        int pad = 2;

        if (!interiorFocus) {
            focusSize = style.getClassSpecificIntValue(context,
                                                       "focus-line-width",1);
        }
        insets.left = insets.right = focusSize + pad + xThickness;
        insets.top = insets.bottom = focusSize + pad + yThickness;
        return insets;
    }


    private void paintTextBackground(SynthContext context,
                                     GTKEngine engine, Graphics g,
                                     int x, int y, int w,int h) {
        // Text is odd, the background is filled in with
        // TEXT_BACKGROUND
        JComponent c = context.getComponent();
        // Text is odd in that it uses the TEXT_BACKGROUND vs BACKGROUND.
        if (c.isOpaque() && c.getBackground() instanceof ColorUIResource) {
            g.setColor(((GTKStyle)context.getStyle()).getGTKColor(
                       context.getComponent(), context.getRegion(),
                       SynthConstants.ENABLED, GTKColorType.TEXT_BACKGROUND));
            g.fillRect(x, y, w, h);
        }
    }


    //
    // OPTION_Pane
    //

    private Insets getOptionPaneInsets(SynthContext context, Insets insets) {
        insets.left = insets.right = insets.top = insets.bottom = 6;
        return insets;
    }


    //
    // TOGGLE_BUTTON
    //
    public void paintToggleButtonBackground(SynthContext context,
                                            GTKEngine engine, Graphics g,
                                            int x, int y, int w, int h) {

        int state = context.getComponentState();
        int gtkState = GTKLookAndFeel.synthStateToGTKState(context.getRegion(),
                                                           state);
        GTKStyle style = (GTKStyle)context.getStyle();
        boolean interiorFocus = style.getClassSpecificBoolValue(context,
                                                 "interior-focus", true);
        int focusSize = style.getClassSpecificIntValue(context,
                                                       "focus-line-width",1);
        int focusPad = style.getClassSpecificIntValue(context, "focus-padding",
                                                      1);
        int totalFocusSize = focusSize + focusPad;
        int xThickness = style.getXThickness();
        int yThickness = style.getYThickness();
        JToggleButton toggleButton = (JToggleButton)context.getComponent();

        if (!interiorFocus && (state & SynthConstants.FOCUSED) ==
                              SynthConstants.FOCUSED) {
            x += totalFocusSize;
            y += totalFocusSize;
            w -= 2 * totalFocusSize;
            h -= 2 * totalFocusSize;
        }

        int shadow = GTKConstants.SHADOW_OUT;
        if (toggleButton.isSelected() ||
                ((state & SynthConstants.PRESSED) != 0)) {
            shadow = GTKConstants.SHADOW_IN;
        }
        engine.paintBox(context, g, gtkState, shadow, "button", x, y, w, h);

        // focus
        if ((state & SynthConstants.FOCUSED) == SynthConstants.FOCUSED) {
            if (interiorFocus) {
                x += xThickness + focusPad;
                y += yThickness + focusPad;
                w -= 2 * (xThickness + focusPad);
                h -= 2 * (yThickness + focusPad);
            }
            else {
                x -= totalFocusSize;
                y -= totalFocusSize;
                w += 2 * totalFocusSize;
                h += 2 * totalFocusSize;
            }
            engine.paintFocus(context, g, gtkState, "button", x, y, w, h);
        }
    }

    //
    // ROOT_PANE
    //
    public void paintRootPaneBackground(SynthContext context,
                                        GTKEngine engine, Graphics g,
                                        int x, int y, int w, int h) {
        int gtkState = GTKLookAndFeel.synthStateToGTKState(
                          context.getRegion(), context.getComponentState());

        engine.paintFlatBoxNormal(context, g, gtkState, "base", x, y, w, h);
    }

    //
    // SCROLL_BAR
    //
    private void paintScrollBarBackground(SynthContext context,
                                          GTKEngine engine, Graphics g,
                                          int x, int y, int w,int h) {
        GTKStyle style = (GTKStyle)context.getStyle();
        int state = context.getComponentState();
        int gtkState = GTKLookAndFeel.synthStateToGTKState(context.getRegion(),
                                                           state);
        int focusSize = style.getClassSpecificIntValue(context,
                                                        "focus-line-width",1);
        int focusPad = style.getClassSpecificIntValue(context,
                                                      "focus-padding", 1);
        int totalFocus = focusSize + focusPad;

        engine.paintBox(context, g, SynthConstants.PRESSED,
                            GTKConstants.SHADOW_IN, "trough", x + totalFocus,
                            y + totalFocus, w - 2 * totalFocus,
                            h - 2 * totalFocus);
        if ((state & SynthConstants.FOCUSED) == SynthConstants.FOCUSED) {
            engine.paintFocus(context, g, SynthConstants.ENABLED,
                              "trough", x, y, w, h);
        }
    }

    private Insets getScrollBarInsets(SynthContext context, Insets insets) {
        GTKStyle style = (GTKStyle)context.getStyle();
        if (context.getComponent().isFocusable()) {
            int focusSize = style.getClassSpecificIntValue(context,
                                                        "focus-line-width",1);
            int focusPad = style.getClassSpecificIntValue(context,
                                                      "focus-padding", 1);
            int w = focusSize + focusPad;
            int h = focusSize + focusPad;

            insets.left = insets.right = w;
            insets.top = insets.bottom = h;
        }
        int troughBorder = style.getClassSpecificIntValue(context,
                                                          "trough-border", 1);
        insets.left += troughBorder;
        insets.right += troughBorder;
        insets.top += troughBorder;
        insets.bottom += troughBorder;
        return insets;
    }


    //
    // SCROLL_BAR_THUMB
    //
    private void paintScrollBarThumbBackground(SynthContext context,
                                          GTKEngine engine, Graphics g,
                                          int x, int y, int w, int h) {
        int orientation;
        if (((JScrollBar)context.getComponent()).getOrientation() ==
                         JScrollBar.VERTICAL) {
            orientation = GTKConstants.VERTICAL;
        }
        else {
            orientation = GTKConstants.HORIZONTAL;
        }
        engine.paintSlider(context, g, GTKLookAndFeel.synthStateToGTKState(
               context.getRegion(), context.getComponentState()),
               GTKConstants.SHADOW_OUT, "slider", x, y, w, h, orientation);
    }

    //
    // TOOL_BAR
    //
    private void paintToolBarBackground(SynthContext context, GTKEngine engine,
                                        Graphics g, int x, int y,
                                        int w, int h) {
        int state = context.getComponentState();
        int gtkState = GTKLookAndFeel.synthStateToGTKState(context.getRegion(),
                state);
        GTKStyle style = (GTKStyle)context.getStyle();

        g.setColor(style.getGTKColor(context.getComponent(),
                context.getRegion(), state, GTKColorType.BACKGROUND));
        engine.paintFlatBox(context, g, gtkState, "handlebox_bin", x, y, w, h);
    }

    private Insets getToolBarInsets(SynthContext context, Insets insets) {
        GTKStyle style = (GTKStyle)context.getStyle();
        int xThickness = style.getXThickness();
        int yThickness = style.getYThickness();
        insets.top = insets.bottom = yThickness;
        insets.right = insets.left = xThickness;
        return insets;
    }

    private void paintToolBarContentBackground(SynthContext context,
            GTKEngine engine, Graphics g, int x, int y, int w, int h) {
        int state = context.getComponentState();
        int gtkState = GTKLookAndFeel.synthStateToGTKState(context.getRegion(),
                                                           state);
        engine.paintShadow(context, g, SynthConstants.ENABLED,
            GTKConstants.SHADOW_OUT, "handlebox_bin", x, y, w, h);
    }

    //
    // TOOL_TIP
    //
    private void paintToolTipBackground(SynthContext context,
                                        GTKEngine engine, Graphics g,
                                        int x, int y, int w,int h) {
        engine.paintFlatBoxNormal(context, g, SynthConstants.ENABLED,"tooltip",
                            x, y, w, h);
    }

    private Insets getToolTipInsets(SynthContext context, Insets insets) {
        GTKStyle style = (GTKStyle)context.getStyle();

        insets.left = insets.right = style.getXThickness();
        insets.top = insets.bottom = style.getYThickness();
        return insets;
    }


    //
    // TREE_CELL
    //
    private void paintTreeCellBackground(SynthContext context,
                                        GTKEngine engine, Graphics g,
                                        int x, int y, int w,int h) {
        int gtkState = GTKLookAndFeel.synthStateToGTKState(
                          context.getRegion(), context.getComponentState());
        // the string arg should alternate based on row being painted, but
        // we currently don't pass that in.
        engine.paintFlatBoxText(context, g, gtkState, "cell_odd", x, y, w, h);
    }

    private void paintTreeCellFocus(SynthContext context,
                                    GTKEngine engine, Graphics g,
                                    int x, int y, int w,int h) {
        int gtkState = GTKLookAndFeel.synthStateToGTKState(
                          context.getRegion(), context.getComponentState());
        engine.paintFocus(context, g, gtkState, "treeview", x, y, w, h);
    }


    //
    // TREE_CELL_RENDERER
    //
    private Insets getTreeCellRendererInsets(SynthContext context,
                                             Insets insets) {
        insets.left = insets.right = insets.top = insets.bottom = 1;
        return insets;
    }


    //
    // TREE_CELL_EDITOR
    //
    private Insets getTreeCellEditorInsets(SynthContext context,
                                             Insets insets) {
        insets.left = insets.right = insets.top = insets.bottom = 1;
        return insets;
    }

    private void paintTreeCellEditorBackground(SynthContext context,
                                     GTKEngine engine, Graphics g,
                                     int x, int y, int w,int h) {
        int gtkState = GTKLookAndFeel.synthStateToGTKState(
                          context.getRegion(), context.getComponentState());
        engine.paintFlatBoxText(context, g, gtkState, "entry_bg", x, y, w, h);
    }


    //
    // TREE
    //
    private void paintTreeBackground(SynthContext context,
                                        GTKEngine engine, Graphics g,
                                        int x, int y, int w,int h) {
        // As far as I can tell, these don't call into the engine.
        int gtkState = GTKLookAndFeel.synthStateToGTKState(
                          context.getRegion(), context.getComponentState());
        g.setColor(((GTKStyle)context.getStyle()).getGTKColor(
                context.getComponent(), context.getRegion(), gtkState,
                GTKColorType.TEXT_BACKGROUND));
        g.fillRect(x, y, w, h);
    }


    //
    // VIEWPORT
    //
    private void paintViewportBackground(SynthContext context,
                                        GTKEngine engine, Graphics g,
                                        int x, int y, int w,int h) {
        // As far as I can tell, these don't call into the engine.
        // Also note that you don't want this to call into the engine
        // as if it where to paint a background JViewport wouldn't scroll
        // correctly.
        int gtkState = GTKLookAndFeel.synthStateToGTKState(
                          context.getRegion(), context.getComponentState());
        g.setColor(((GTKStyle)context.getStyle()).getGTKColor(
                context.getComponent(), context.getRegion(), gtkState,
                GTKColorType.TEXT_BACKGROUND));
        g.fillRect(x, y, w, h);
    }



    // Refer to GTKLookAndFeel for details on this.
    static class ListTableFocusBorder extends AbstractBorder implements
                          UIResource {
        public void paintBorder(Component c, Graphics g, int x, int y,
                                int w, int h) {
            g.setColor(Color.BLACK);
            GTKEngine.INSTANCE._paintFocus(g, x, y, w, h,
                                           GTKEngine.DEFAULT_FOCUS_PATTERN, 1);
        }
        public Insets getBorderInsets(Component c) {
            return new Insets(1, 1, 1, 1);
        }
        public Insets getBorderInsets(Component c, Insets i) {
            if (i == null) {
                return getBorderInsets(c);
            }
            i.left = i.right = i.top = i.bottom = 1;
            return i;
        }
        public boolean isBorderOpaque() {
            return true;
        }
    }
}
