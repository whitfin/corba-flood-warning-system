package corba;


/**
* corba/LMSPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from flood-warning.idl
* Tuesday, March 24, 2015 1:43:22 PM GMT
*/

public abstract class LMSPOA extends org.omg.PortableServer.Servant
 implements corba.LMSOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("_get_name", new java.lang.Integer (0));
    _methods.put ("_get_alertLog", new java.lang.Integer (1));
    _methods.put ("receiveAlert", new java.lang.Integer (2));
    _methods.put ("removeSensor", new java.lang.Integer (3));
    _methods.put ("registerSensor", new java.lang.Integer (4));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // corba/LMS/_get_name
       {
         String $result = null;
         $result = this.name ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 1:  // corba/LMS/_get_alertLog
       {
         corba.Alert $result[] = null;
         $result = this.alertLog ();
         out = $rh.createReply();
         corba.AlertLogHelper.write (out, $result);
         break;
       }

       case 2:  // corba/LMS/receiveAlert
       {
         corba.Alert alert = corba.AlertHelper.read (in);
         this.receiveAlert (alert);
         out = $rh.createReply();
         break;
       }

       case 3:  // corba/LMS/removeSensor
       {
         corba.SensorPair pair = corba.SensorPairHelper.read (in);
         this.removeSensor (pair);
         out = $rh.createReply();
         break;
       }

       case 4:  // corba/LMS/registerSensor
       {
         String zone = in.read_string ();
         corba.SensorMeta $result = null;
         $result = this.registerSensor (zone);
         out = $rh.createReply();
         corba.SensorMetaHelper.write (out, $result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:corba/LMS:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public LMS _this() 
  {
    return LMSHelper.narrow(
    super._this_object());
  }

  public LMS _this(org.omg.CORBA.ORB orb) 
  {
    return LMSHelper.narrow(
    super._this_object(orb));
  }


} // class LMSPOA
