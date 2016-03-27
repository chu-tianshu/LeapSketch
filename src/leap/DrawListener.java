package leap;

import java.awt.BasicStroke;
import java.awt.Color;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.FingerList;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.GestureList;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Vector;

public class DrawListener extends Listener {
    
	private Integer lastX, lastZ;
    boolean draw = false;
    
    Canvas canvas;

    public DrawListener(Canvas canvas) {
        
    	this.canvas = canvas;
    }

    public void onInit(Controller controller) {
        
    	System.out.println("Initialized");
    }

    public void onConnect(Controller controller) {
        
    	System.out.println("Connected");
        controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
    }

    public void onDisconnect(Controller controller) {
        
    	// Note: not dispatched when running in a debugger.
        System.out.println("Disconnected");
    }

    public void onExit(Controller controller) {
        
    	System.out.println("Exited");
    }

    public void onFrame(Controller controller) {
        
    	Frame frame = controller.frame();

        // Get the most recent frame and report some basic information

        if (!frame.hands().isEmpty()) {
        	
        	System.out.println("Hands");
            
        	// Get the first hand
            Hand hand = frame.hands().frontmost();
            
            System.out.println(hand.palmPosition());
            
            int X = (int) ((25.0 / 13.0) * (hand.palmPosition().getX() + 130));
            int Y = (int) hand.palmPosition().getY();
            int Z = (int) ((25.0 / 13.0) * (hand.palmPosition().getZ() + 130));
            
            if (Y > 300) {
            	
            	if (draw == true) {
            		
            		draw = false;
            	}
            } else {
            	
            	canvas.changeColorAndSize(Color.BLACK, new BasicStroke(hand.pinchStrength() * 10));
            	
            	if (draw == false) {
            		
            		draw = true;
            		
            		canvas.drawLineSegment(X, Z, X, Z);
            	} else {
            		
            		canvas.drawLineSegment(lastX, lastZ, X, Z);
            	}
            }
                
            lastX = X;
            lastZ = Z;
        }

        GestureList gestures = frame.gestures();
        
        for (int i = 0; i < gestures.count(); i++) {
        	
            Gesture gesture = gestures.get(i);

            switch (gesture.type()) {
            case TYPE_SWIPE:
                System.out.println("Swiping.");
            default:
                System.out.println("Unknown gesture type.");
                break;
            }
        }
    }

    private float convert(float num, int dir) {
        
    	switch (dir) {
        
    	case 0:
            return (num + 175) * 500 / 350;
        case 1:
            return 500 - (num - 20) * 500 / 735;
        default:
            return 0;

        }
    }
}
