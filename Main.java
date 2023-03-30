import java.lang.Thread;
import java.io.*;
import java.util.UUID;
import java.util.Vector;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Vector<Classroom> classroomList = new Vector<Classroom>();
        classroomList.add(new Classroom("AAAA", 60));
        classroomList.add(new Classroom("TTTT", 60));
        classroomList.add(new Classroom("QQQQ", 20));
        classroomList.add(new Classroom("WWWW", 30));        

        Vector<Lecturer> lecturerList = new Vector<Lecturer>();
        String[] lecturers = {"Osama", "Barry", "Faheem", "Alex", "Aqeel", "Waseem"};
        for (int i = 0; i < lecturers.length; i++)
            lecturerList.add(new Lecturer(lecturers[i], classroomList));
          
        Vector<Visitor> visitorList = new Vector<Visitor>();
        for (int i = 0; i < 15; i++) {
            String name = UUID.randomUUID().toString();
            visitorList.add(new Visitor(name, classroomList));
        }
        
        Vector<Student> studentList = new Vector<Student>();
        for (int i = 0; i < 200; i++) {
            String name = UUID.randomUUID().toString().substring(0, 8);
            studentList.add(new Student(name, classroomList));
        }

        College college = new College(classroomList, lecturerList, visitorList, studentList);
        college.startSimulation();
    }    
}