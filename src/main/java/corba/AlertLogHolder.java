package corba;


/**
* corba/AlertLogHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from flood-warning.idl
* Tuesday, March 24, 2015 1:43:22 PM GMT
*/

public final class AlertLogHolder implements org.omg.CORBA.portable.Streamable
{
  public corba.Alert value[] = null;

  public AlertLogHolder ()
  {
  }

  public AlertLogHolder (corba.Alert[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = corba.AlertLogHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    corba.AlertLogHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return corba.AlertLogHelper.type ();
  }

}