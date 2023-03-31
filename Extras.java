import java.util.concurrent.TimeUnit;

public class Extras {
    public enum Simukind {
        COLLEGEWALK,
        INLECTURE,
        REST,
        BE_PATIENCE,
        MONITOR_TIME,
    }

    public boolean tableBasedSimulation = true;

    void custom_print(String string) {
        if (this.tableBasedSimulation == false) {
            System.out.println(string);
            System.out.flush();
        }
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
            // students or lecturers could rest between classes between 3 seconds to 4.5 seconds (30 mins to 45 mins)
            case COLLEGEWALK: {
                try {
                    TimeUnit.MILLISECONDS.sleep(3000+(int)(Math.random()*1500));
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
            case BE_PATIENCE: {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000+(int)(Math.random()*500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            case MONITOR_TIME: {
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        
    }
}
