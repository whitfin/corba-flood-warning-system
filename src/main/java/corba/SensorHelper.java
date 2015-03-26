package corba;


/**
* corba/SensorHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from flood-warning.idl
* Tuesday, March 24, 2015 1:43:22 PM GMT
*/

abstract public class SensorHelper
{
  private static String  _id = "IDL:corba/Sensor:1.0";

  public static void insert (org.omg.CORBA.Any a, corba.Sensor that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static corba.Sensor extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (corba.SensorHelper.id (), "Sensor");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static corba.Sensor read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_SensorStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, corba.Sensor value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static corba.Sensor narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof corba.Sensor)
      return (corba.Sensor)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      corba._SensorStub stub = new corba._SensorStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static corba.Sensor unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof corba.Sensor)
      return (corba.Sensor)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      corba._SensorStub stub = new corba._SensorStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}