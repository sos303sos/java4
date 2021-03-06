/*
 * @(#)StackTraceElement.java   1.7 03/01/23
 *
 * Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java.lang;

/**
 * 由VM初始化,主要是堆信息
 * 这个类的主要作用就是事定位到堆报错的信息.
 * An element in a stack trace, as returned by {@link
 * Throwable#getStackTrace()}.  Each element represents a single stack frame.
 * All stack frames except for the one at the top of the stack represent
 * a method invocation.  The frame at the top of the stack represents the 
 * the execution point at which the stack trace was generated.  Typically,
 * this is the point at which the throwable corresponding to the stack trace
 * was created.
 *
 * @since  1.4
 * @author Josh Bloch
 */
public final class StackTraceElement implements java.io.Serializable {
    // Initialized by VM
    private String declaringClass;//类名111
    private String methodName;//方法名
    private String fileName;//文件名
    private int    lineNumber;//行数

    /**
     * 私有化构造方法,只有虚拟机能调用
     * Prevent inappropriate instantiation.  Only the VM creates these.
     * It creates them "magically" without invoking this constructor.
     */
    private StackTraceElement() { /* assert false; */ }

    /**
     * Returns the name of the source file containing the execution point
     * represented by this stack trace element.  Generally, this corresponds
     * to the <tt>SourceFile</tt> attribute of the relevant <tt>class</tt>
     * file (as per <i>The Java Virtual Machine Specification</i>, Section
     * 4.7.7).  In some systems, the name may refer to some source code unit
     * other than a file, such as an entry in source repository.
     *
     * @return the name of the file containing the execution point
     *         represented by this stack trace element, or <tt>null</tt> if
     *         this information is unavailable.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Returns the line number of the source line containing the execution
     * point represented by this stack trace element.  Generally, this is
     * derived from the <tt>LineNumberTable</tt> attribute of the relevant
     * <tt>class</tt> file (as per <i>The Java Virtual Machine
     * Specification</i>, Section 4.7.8).
     *
     * @return the line number of the source line containing the execution
     *         point represented by this stack trace element, or a negative
     *         number if this information is unavailable.
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * Returns the fully qualified name of the class containing the
     * execution point represented by this stack trace element.
     *
     * @return the fully qualified name of the <tt>Class</tt> containing
     *         the execution point represented by this stack trace element.
     */
    public String getClassName() {
        return declaringClass;
    }

    /**
     * Returns the name of the method containing the execution point
     * represented by this stack trace element.  If the execution point is
     * contained in an instance or class initializer, this method will return
     * the appropriate <i>special method name</i>, <tt>&lt;init&gt;</tt> or
     * <tt>&lt;clinit&gt;</tt>, as per Section 3.9 of <i>The Java Virtual
     * Machine Specification</i>.
     *
     * @return the name of the method containing the execution point
     *         represented by this stack trace element.
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * Returns true if the method containing the execution point
     * represented by this stack trace element is a native method.
     *
     * @return <tt>true</tt> if the method containing the execution point
     *         represented by this stack trace element is a native method.
     */
    public boolean isNativeMethod() {
        return lineNumber == -2;
    }

    /**
     * Returns a string representation of this stack trace element.  The
     * format of this string depends on the implementation, but the following
     * examples may be regarded as typical:
     * <ul>
     * <li>
     *   <tt>"MyClass.mash(MyClass.java:9)"</tt> - Here, <tt>"MyClass"</tt>
     *   is the <i>fully-qualified name</i> of the class containing the
     *   execution point represented by this stack trace element,
     *   <tt>"mash"</tt> is the name of the method containing the execution
     *   point, <tt>"MyClass.java"</tt> is the source file containing the
     *   execution point, and <tt>"9"</tt> is the line number of the source
     *   line containing the execution point.
     * <li>
     *   <tt>"MyClass.mash(MyClass.java)"</tt> - As above, but the line
     *   number is unavailable.
     * <li>
     *   <tt>"MyClass.mash(Unknown Source)"</tt> - As above, but neither
     *   the file name nor the line  number are available.
     * <li>
     *   <tt>"MyClass.mash(Native Method)"</tt> - As above, but neither
     *   the file name nor the line  number are available, and the method
     *   containing the execution point is known to be a native method.
     * </ul>
     * @see    Throwable#printStackTrace()
     */
    public String toString() {
        return getClassName() + "." + methodName +
            (isNativeMethod() ? "(Native Method)" :
             (fileName != null && lineNumber >= 0 ?
              "(" + fileName + ":" + lineNumber + ")" :
              (fileName != null ?  "("+fileName+")" : "(Unknown Source)")));
    }

    /**
     * Returns true if the specified object is another
     * <tt>StackTraceElement</tt> instance representing the same execution
     * point as this instance.  Two stack trace elements <tt>a</tt> and
     * <tt>b</tt> are equal if and only if:
     * <pre>
     *     equals(a.getFileName(), b.getFileName()) &&
     *     a.getLineNumber() == b.getLineNumber()) &&
     *     equals(a.getClassName(), b.getClassName()) &&
     *     equals(a.getMethodName(), b.getMethodName())
     * </pre>
     * where <tt>equals</tt> is defined as:
     * <pre>
     *     static boolean equals(Object a, Object b) {
     *         return a==b || (a != null && a.equals(b));
     *     }
     * </pre>
     * 
     * @param  obj the object to be compared with this stack trace element.
     * @return true if the specified object is another
     *         <tt>StackTraceElement</tt> instance representing the same
     *         execution point as this instance.
     */
    public boolean equals(Object obj) {
        if (obj==this)
            return true;
        if (!(obj instanceof StackTraceElement))
            return false;
        StackTraceElement e = (StackTraceElement)obj;
        return e.declaringClass.equals(declaringClass) && e.lineNumber == lineNumber
            && eq(methodName, e.methodName) && eq(fileName, e.fileName);
    }

    private static boolean eq(Object a, Object b) {
        return a==b || (a != null && a.equals(b));
    }

    /**
     * Returns a hash code value for this stack trace element.
     */
    public int hashCode() {
        int result = 31*declaringClass.hashCode() + methodName.hashCode();
        result = 31*result + (fileName == null ?   0 : fileName.hashCode());
        result = 31*result + lineNumber;
        return result;
    }

    private static final long serialVersionUID = 6992337162326171013L;
}
