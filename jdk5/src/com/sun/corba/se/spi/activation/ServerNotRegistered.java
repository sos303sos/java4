package com.sun.corba.se.spi.activation;


/**
* com/sun/corba/se/spi/activation/ServerNotRegistered.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../../../../src/share/classes/com/sun/corba/se/spi/activation/activation.idl
* Friday, October 9, 2009 2:05:46 AM PDT
*/

public final class ServerNotRegistered extends org.omg.CORBA.UserException
{
  public int serverId = (int)0;

  public ServerNotRegistered ()
  {
    super(ServerNotRegisteredHelper.id());
  } // ctor

  public ServerNotRegistered (int _serverId)
  {
    super(ServerNotRegisteredHelper.id());
    serverId = _serverId;
  } // ctor


  public ServerNotRegistered (String $reason, int _serverId)
  {
    super(ServerNotRegisteredHelper.id() + "  " + $reason);
    serverId = _serverId;
  } // ctor

} // class ServerNotRegistered
