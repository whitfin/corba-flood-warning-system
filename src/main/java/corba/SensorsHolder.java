package corba;


/**
* corba/SensorsHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from flood-warning.idl
* Tuesday, March 24, 2015 1:43:22 PM GMT
*/

public final class SensorsHolder implements org.omg.CORBA.portable.Streamable
{
  public corba.Sensor value[] = null;

  public SensorsHolder ()
  {
  }

  public SensorsHolder (corba.Sensor[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = corba.SensorsHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    corba.SensorsHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return corba.SensorsHelper.type ();
  }

}
