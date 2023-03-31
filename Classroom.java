import java.util.HashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Classroom {
    public String name, currentLecturer;
    public HashMap<String, Semaphore> semCap;
    public AtomicBoolean inSession;
    public int capStudent;

    public Classroom(String name, int studentCapacity) {
        this.semCap = new HashMap<String, Semaphore>();
        this.name = name;
        this.currentLecturer = "";
        this.semCap.put("lecturer", new Semaphore(1));
        this.semCap.put("students", new Semaphore(studentCapacity));
        this.semCap.put("visitors", new Semaphore(5));
        this.capStudent = studentCapacity;
        this.inSession = new AtomicBoolean();
        this.inSession.set(false);
    } 
}
