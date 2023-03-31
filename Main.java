import java.lang.Thread;
import java.io.*;
import java.util.UUID;
import java.util.Vector;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Vector<Classroom> classroomList = new Vector<Classroom>();
        classroomList.add(new Classroom("W201", 60));
        classroomList.add(new Classroom("W202", 60));
        classroomList.add(new Classroom("W101", 20));
        classroomList.add(new Classroom("JS101", 30));        

        Vector<Lecturer> lecturerList = new Vector<Lecturer>();
        String[] lecturers = {"Osama", "Barry", "Faheem", "Alex", "Aqeel", "Waseem"};
        for (int i = 0; i < lecturers.length; i++)
            lecturerList.add(new Lecturer(lecturers[i], classroomList));
          
        Vector<Visitor> visitorList = new Vector<Visitor>();
        for (int i = 0; i < 15; i++) {
            String name = UUID.randomUUID().toString().substring(0, 8);
            visitorList.add(new Visitor(name, classroomList));
        }
        
        Vector<Student> studentList = new Vector<Student>();
        for (int i = 0; i < 200; i++) {
            String name = UUID.randomUUID().toString().substring(0, 8);
            studentList.add(new Student(name, classroomList));
        }

        College college = new College(classroomList, lecturerList, visitorList, studentList);
        college.startSimulationAndMonitor();
    }    
}