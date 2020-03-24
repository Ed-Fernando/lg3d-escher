package gnu.util;

import java.io.DataInputStream;
import java.io.BufferedInputStream;


/** Unix environment. */
public class Environment {
  /**
   * All pairs of environment names and values. Prefixed with
   * <code>"\n"</code> to aid searching.
   */
  public static String ALL;


  static {
    try {        
      // TODO windows 95/nt: `command /c set'
      Process process = Runtime.getRuntime ().exec ("env");
      ReadThread readThread = new ReadThread(process);
      readThread.start();
      process.waitFor ();
      // prefix with "\n" to aid searching
      ALL = "\n" + readThread.getString();
      
    } catch (Exception e) {
      ALL = "<failed to access system environment>";
    }
  }


  /** Get the value of an environment variable. */
  public static String value (String name) {
    /* A search key is prefixed with "\n" to avoid partial matching such as
     * matching `HOSTDISPLAY=canning-home:0.0' instead of `DISPLAY=:0.0'. 
     */
    String key = "\n" + name + "=";

    int pair_start = ALL.indexOf (key);
    if (pair_start == -1) return null;
    int value_start = pair_start + key.length ();
    int value_end = ALL.indexOf ('\n', value_start);
    return ALL.substring (value_start, value_end);
  }
  
  static class ReadThread extends Thread {
      private Process process;
      private boolean finished = false;
      private StringBuffer strBuf = new StringBuffer();
      
      public ReadThread( Process process ) {
          this.process = process;
      }
      
      public void run() {
          DataInputStream dis = new DataInputStream( new BufferedInputStream( 
                                                    process.getInputStream() ));
          
          while(!finished) {
              String b;
              try {
                  sleep(10);
                  int count = dis.available ();
                  if (count!=0) {
                      byte [] buffer = new byte [count];
                      dis.readFully(buffer);
                      strBuf.append( new String(buffer) );
                  }
              } catch( Exception e ) {
              }
          }
      }
      
      public String getString() {
          finished = true;
          this.interrupt();
          return strBuf.toString();
      }
  }
}
