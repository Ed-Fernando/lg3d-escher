package gnu.x11.event;

import gnu.x11.Display;


/** X mapping notify event. */
public class MappingNotify extends Event {
  public static final int CODE = 34;

  public static final int MAPPING_MODIFIER = 0;
  public static final int MAPPING_KEYBOARD = 1;
  public static final int MAPPING_POINTER  = 2;

  public MappingNotify (Display display, byte [] data) {
    super (display, data, 0); 
  }

  public void set_window (int i) {} // no window
  public int window_id () { return 0; } // no window

  public int get_request () {
    return read4(20);
  }
}
