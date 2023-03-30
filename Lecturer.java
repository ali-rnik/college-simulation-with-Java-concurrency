import java.util.Vector;

public class Lecturer extends Extras implements Runnable {
    public String name;
    public boolean isInClassroom;
    public Vector<Classroom> classroomList;
    public boolean startLecture;

    public Lecturer(String name, Vector<Classroom> classroomList) {
        this.name = name;
        this.classroomList = classroomList;
        this.isInClassroom = false;
    }
    
    public boolean tryEnterClass(Classroom cr) {
        if (cr.lecturerGateCheckin(this.name)) {
            isInClassroom = true;
            return true;
        }
        return false;
    }
    
    public boolean tryLeaveClass(Classroom cr) {
        if(isInClassroom) {
            if (cr.lecturerGateCheckout(this.name)) {
                isInClassroom = false;
                return true;
            }
        }
        return false;
    }

    public void run() {
        while (true) {
            Classroom cr = classroomList.get((int)(Math.random()*(classroomList.size()-1)));
            
            simudelay(Simukind.REST);
            if(!this.tryEnterClass(cr)) {
                System.out.println("Lecturer "+this.name+" Could Not enter "+cr.name+" Classroom!");
                simudelay(Simukind.REST);
                continue;
            }
            System.out.println("Lecturer "+this.name+" Entered "+cr.name+" Classroom!");
            simudelay(Simukind.INLECTURE);

            if(!this.tryLeaveClass(cr)) {
                System.out.println("Simulation Error!");
                System.exit(-1);
            }
            System.out.println("Lecturer "+this.name+" Left "+cr.name+" Classroom!");
        }
    }
}    

