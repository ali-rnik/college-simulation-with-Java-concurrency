import java.util.Vector;

public class Student extends Extras implements Runnable {
    public String name;
    public Vector<Classroom> classroomList;
    
    public Student(String name, Vector<Classroom> classroomList) {
        this.name = name;
        this.classroomList = classroomList;
    }
    
    public synchronized boolean enter(Classroom cr) {
        if (cr.semCap.get("lecturer").availablePermits() == 1) {
            if (cr.semCap.get("students").tryAcquire()) {
                custom_print("Student "+this.name+" Entered "+cr.name+" Classroom!");
            } else {
                custom_print("Student "+this.name+" Could not Entered "+cr.name+" Classroom. Because classroom is full");
                return false;
            }
        } else {
            custom_print("Student "+this.name+" Could not Entered "+cr.name+" Classroom. because Session started.");
            return false;
        }
        
        return true;
    }
    
    public synchronized void leave(Classroom cr) {
        while(cr.semCap.get("lecturer").availablePermits() == 0) {
            simudelay(Simukind.BE_PATIENCE);
            custom_print("Student "+this.name+" Could not leave "+cr.name+" Classroom. because Session started.");
        } 
        custom_print("Student "+this.name+" Left "+cr.name+" Classroom!");                
        cr.semCap.get("students").release();
    }
    
    public void run() {
        while (true) {
            Classroom cr = classroomList.get((int)(Math.random()*(classroomList.size())));
            
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

