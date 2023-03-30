import java.util.concurrent.TimeUnit;

public class Extras {
    public enum Simukind {
        COLLEGEWALK,
        INLECTURE,
        MISSEDLECTURE,
        REST
    }
    
    void simudelay(Simukind simukind) {
        switch(simukind) {
            // The class length could be between 9 seconds (in real world 90 mins)
            //  to 12 seconds (in real world 2 Hours or 120 mins)
            case INLECTURE: {
                try {
                    TimeUnit.MILLISECONDS.sleep(9000+(int)(Math.random()*3000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            // students or lecturers could rest between classes between 1.5 seconds to 2 seconds (15 mins to 20 mins)
            case COLLEGEWALK: {
                try {
                    TimeUnit.MILLISECONDS.sleep(1500+(int)(Math.random()*2000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break; 
            }
            
            // if a student miss a lecture she should wait at least one INLECTURE + COLLEGEWALK
            case MISSEDLECTURE: {
                try {
                    TimeUnit.MILLISECONDS.sleep(9000+1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break; 
            }

            case REST: {
                try {
                    TimeUnit.MILLISECONDS.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        
    }
}
