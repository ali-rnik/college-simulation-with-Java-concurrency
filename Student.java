import java.util.Random;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class Student extends Extras implements Runnable {
    public String name;
    public boolean isInClassroom;
    public Vector<Classroom> classroomList;
    
    public Student(String name, Vector<Classroom> classroomList) {
        this.name = name;
        this.classroomList = classroomList;
    }
    
    public synchronized boolean enter(Classroom cr) {
        if (cr.semCap.get("lecturer").availablePermits() == 1) {
            if (cr.semCap.get("students").tryAcquire()) {
                System.out.println("Student "+this.name+" Entered "+cr.name+" Classroom!");
            } else {
                System.out.println("Student "+this.name+" Could not Entered "+cr.name+" Classroom. Because classroom is full");
                return false;
            }
        } else {
            System.out.println(cr.semCap.get("lecturer").availablePermits());
            System.out.println("Student "+this.name+" Could not Entered "+cr.name+" Classroom. because Session started.");
            return false;
        }
        
        return true;
    }
    
    public synchronized void leave(Classroom cr) {
        while(cr.semCap.get("lecturer").availablePermits() == 0) {
            System.out.println("Student "+this.name+" Could not leave "+cr.name+" Classroom. because Session started.");
        } 
        System.out.println("Student "+this.name+" Left "+cr.name+" Classroom!");                
        cr.semCap.get("students").release();
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
        }
    }
}

