import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class College {
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
    
    public void startSimulation() throws InterruptedException {
        ExecutorService studentsPool = Executors.newFixedThreadPool(studentList.size());
        ExecutorService lecturersPool = Executors.newFixedThreadPool(lecturerList.size());

        for (int i = 0; i < this.studentList.size(); i++) {
            studentsPool.execute(this.studentList.get(i));
        }

/*         for (int i = 0; i < this.lecturerList.size(); i++) {
            lecturersPool.execute(this.lecturerList.get(i));
        }  */

    }
}