import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class College extends Extras {
    public Vector<Classroom> classroomList;
    public Vector<Lecturer> lecturerList;
    public Vector<Visitor> visitorList;
    public Vector<Student> studentList;
    
    public College(Vector<Classroom> classroomList, Vector<Lecturer> lecturerList, Vector<Visitor> visitorList,
    Vector<Student> studentList) {
        this.classroomList = classroomList;
        this.lecturerList = lecturerList;
        this.visitorList = visitorList;
        this.studentList = studentList;
    }
    
    public void monitor() {
        if (!tableBasedSimulation) {
            return;
        }
        
        final Object[][] table = new String[classroomList.size()+4][];
        table[0] = new String[] { "=============", "=============", "=============", "=============", "=============" };
        table[1] = new String[] { "Classroom", "Lecturer", "InSession", "Students", "Visitors" };
        table[2] = new String[] { "=============", "=============", "=============", "=============", "=============" };

        while (true) {
            simudelay(Simukind.MONITOR_TIME);
            for (int i = 3; i < classroomList.size()+3; i++) {
                table[i] = new String[5];
                Classroom cr = classroomList.get(i-3);
                table[i][0] = cr.name;
                table[i][1] = cr.currentLecturer;
                table[i][2] = String.valueOf( cr.inSession.get());
                table[i][3] = String.valueOf(cr.capStudent - cr.semCap.get("students").availablePermits());
                table[i][4] = String.valueOf(5 - cr.semCap.get("visitors").availablePermits());
            }
            table[classroomList.size()+4-1] = new String[] {"-----------", "-----------", "-----------", "-----------", "-----------" };

            for (final Object[] row : table) {
                System.out.format("%15s%15s%15s%15s%15s%n", row);
            }
            System.out.println();
            System.out.println();
        }
    }
    
    public void startSimulationAndMonitor() throws InterruptedException {
        ExecutorService studentsPool = Executors.newFixedThreadPool(studentList.size());
        ExecutorService visitorsPool = Executors.newFixedThreadPool(visitorList.size());
        ExecutorService lecturersPool = Executors.newFixedThreadPool(lecturerList.size());
        
        
        for (int i = 0; i < this.studentList.size(); i++) {
            studentsPool.execute(this.studentList.get(i));
        }
        
        for (int i = 0; i < this.visitorList.size(); i++) {
            visitorsPool.execute(this.visitorList.get(i));
        }
        
        for (int i = 0; i < this.lecturerList.size(); i++) {
            lecturersPool.execute(this.lecturerList.get(i));
        } 
        
        this.monitor();
    }
}