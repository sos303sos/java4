package org.omg.CosNaming;

/**
* org/omg/CosNaming/BindingHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.1"
* from ../../../../src/share/classes/org/omg/CosNaming/nameservice.idl
* Friday, June 20, 2003 8:34:40 AM GMT
*/


/**
   * A name-to-object association is called a Binding.
   */
public final class BindingHolder implements org.omg.CORBA.portable.Streamable
{
  public org.omg.CosNaming.Binding value = null;

  public BindingHolder ()
  {
  }

  public BindingHolder (org.omg.CosNaming.Binding initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = org.omg.CosNaming.BindingHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    org.omg.CosNaming.BindingHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return org.omg.CosNaming.BindingHelper.type ();
  }

}
