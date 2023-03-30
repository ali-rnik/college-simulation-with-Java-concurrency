import java.util.Vector;

public class Visitor implements Runnable {
    public String name;
    public boolean isInClassroom;
    public Vector<Classroom> classroomList;

    public Visitor(String name, Vector<Classroom> classroomList) {
        this.name = name;
        this.classroomList = classroomList;
        this.isInClassroom = false;
    }

    public boolean tryEnterClass(Classroom cr) {
        if (cr.visitorGateCheckin()) {
            isInClassroom = true;
            return true;
        }
        return false;
    }

    public boolean tryLeaveClass(Classroom cr) {
        if(isInClassroom) {
            if (cr.visitorGateCheckout()) {
                isInClassroom = false;
                return true;
            }
        }
        return false;
    }

       public void run() {
        while (true) {
            Classroom cr = classroomList.get((int)(Math.random()*(classroomList.size()-1)));
            try {
                restFor((int)(Math.random()*4));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!this.tryEnterClass(cr)) {
                System.out.println("Student "+this.name+" Could Not enter "+cr.name+" Classroom!");
                try {
                    restFor((int)(Math.random()*4));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            System.out.println("Student "+this.name+" Entered "+cr.name+" Classroom!");

            try {
                restFor((int)(Math.random()*4));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!this.tryLeaveClass(cr)) {
                System.out.println("Logical Error!");
                System.exit(-1);
            }
            System.out.println("Student "+this.name+" Left "+cr.name+" Classroom!");
        }
    }
}