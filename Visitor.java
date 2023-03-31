import java.util.Vector;

public class Visitor extends Extras implements Runnable {
    public String name;
    public Vector<Classroom> classroomList;

    public Visitor(String name, Vector<Classroom> classroomList) {
        this.name = name;
        this.classroomList = classroomList;
    }

    public synchronized boolean enter(Classroom cr) {
        if (cr.semCap.get("lecturer").availablePermits() == 1) {
            if (cr.semCap.get("visitors").tryAcquire()) {
                custom_print("Visitor "+this.name+" Entered "+cr.name+" Classroom!");
            } else {
                custom_print("Visitor "+this.name+" Could not Entered "+cr.name+" Classroom. Because classroom is full");
                return false;
            }
        } else {
            custom_print("Visitor "+this.name+" Could not Entered "+cr.name+" Classroom. because Session started.");
            return false;
        }
        
        return true;
    }
    
    public synchronized void leave(Classroom cr) {
        while(cr.semCap.get("lecturer").availablePermits() == 0) {
            simudelay(Simukind.BE_PATIENCE);
            custom_print("Visitor "+this.name+" Could not leave "+cr.name+" Classroom. because Session started.");
        } 
        custom_print("Visitor "+this.name+" Left "+cr.name+" Classroom!");                
        cr.semCap.get("visitors").release();
    }
    
    public void run() {
        while (true) {
            Classroom cr = classroomList.get((int)(Math.random()*(classroomList.size()-1)));
            
            // Delay in Finding class
            simudelay(Simukind.COLLEGEWALK);
            
            // Try To Enter
            if (enter(cr)) {
                // Try to be a good student and listen to lecturer
                simudelay(Simukind.INLECTURE);
                
                // Leave the class with respect.
                leave(cr);
            }
            
            simudelay(Simukind.REST);    
        }
    }
}