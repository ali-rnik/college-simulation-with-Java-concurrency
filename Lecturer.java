import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;

public class Lecturer extends Extras implements Runnable {
    public String name;
    public Vector<Classroom> classroomList;
    public AtomicBoolean inSession;

    public Lecturer(String name, Vector<Classroom> classroomList) {
        this.name = name;
        this.classroomList = classroomList;
    }
    
    public void startLecture(Classroom cr) {
        cr.inSession.set(true);
    }

    public synchronized boolean enter(Classroom cr) {
        if (cr.semCap.get("lecturer").availablePermits() == 1) {
            if (cr.capStudent - cr.semCap.get("students").availablePermits() < 5 ) {
                custom_print("There are few students to start the class!");
                return false;
            }
            try {
                cr.semCap.get("lecturer").acquire();
                cr.currentLecturer = name;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            custom_print("Lecturer "+this.name+" Entered "+cr.name+" Classroom!");
            return true;
        } else {
            custom_print("Lecturer "+this.name+" Could not Entered "+cr.name+" Classroom. because class is occupied by another lecturer.");
            return false;
        }
    }
    
    public synchronized void leave(Classroom cr) {
        if (cr.semCap.get("lecturer").availablePermits() == 0) {
            cr.currentLecturer = "";
            cr.inSession.set(false);
            cr.semCap.get("lecturer").release();
            custom_print("Lecturer "+this.name+" Left "+cr.name+" Classroom!");
        } else {
            custom_print("Simulation error!");
            System.exit(-1);
        }   
    }

    public void run() {
        while (true) {
            Classroom cr = classroomList.get((int)(Math.random()*(classroomList.size())));
            
            // Delay in Finding class
            simudelay(Simukind.COLLEGEWALK);
            
            // Try To Enter
            if (enter(cr)) {
                // Teaching to students
                this.startLecture(cr);
                simudelay(Simukind.INLECTURE);
                
                // Leave the class.
                leave(cr);
            }            
        }
    }
}

