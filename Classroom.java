import java.util.HashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Classroom {
    public String name;
    public HashMap<String, Semaphore> semCap;
    public boolean inSession;
    
    public Classroom(String name, int studentCapacity) {
        this.semCap = new HashMap<String, Semaphore>();
        this.name = name;
        this.semCap.put("lecturer", new Semaphore(1));
        this.semCap.put("students", new Semaphore(studentCapacity));
        this.semCap.put("visitors", new Semaphore(5));
        this.inSession = false;
    }
    
    public boolean enter(String semName) throws InterruptedException {
        this.semCap.get(semName).acquire();
        return true;
    }
    
    public boolean leave(String semName) throws InterruptedException {
        this.semCap.get(semName).acquire();
        return true;
    }
    
 
}
