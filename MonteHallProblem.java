import java.util.Scanner;
 
public class MonteHallProblem {
    private Scanner scan;
    int otherdoor = 0;
    int doorchosen = 0;
    private int correctdoor = 0;
    int dummydoor = 0;
    
    int wins = 0;
    int totalplays;
    int sames = 0;
    
    
    public boolean playRound(boolean showresult, boolean ai, boolean switchdoor) {
        //Selecting Original Door
        if(!ai) {
            scan = new Scanner(System.in);
        }
        
        correctdoor = (int)(Math.random() * 3);
        
        if(!ai) {
            System.out.println("Choose Door (0-2)");
            doorchosen = Integer.parseInt(scan.nextLine().trim());
        }else {
            doorchosen = (int)(Math.random() * 3);
        }
        
        //Dummy Door Reveal
        int random = (int)(Math.random() * 2);  
        
        if(correctdoor == doorchosen) {
            sames++;
            
            if(correctdoor == 0) {
                if(random == 0) {
                    dummydoor = 1;
                    otherdoor = 2;
                }else {
                    dummydoor = 2;
                    otherdoor = 1;
                }
            }else if (correctdoor == 1) {
                if(random == 0) {
                    dummydoor = 0;
                    otherdoor = 2;
                }else {
                    dummydoor = 2;
                    otherdoor = 0;
                }
            }else if (correctdoor == 2) {
                if(random == 0) {
                    dummydoor = 0;
                    otherdoor = 1;
                }else {
                    dummydoor = 1;
                    otherdoor = 0;
                }
            }
            
        }else{
            otherdoor = correctdoor;
            
            if(correctdoor == 0) {
                if(doorchosen == 1) {
                    dummydoor = 2;
                }else {
                    dummydoor = 1;
                }
            }else if (correctdoor == 1) {
                if(doorchosen == 0) {
                    dummydoor = 2;
                }else {
                    dummydoor = 0;
                }
            }else if (correctdoor == 2) {
                if(doorchosen == 0) {
                    dummydoor = 1;
                }else {
                    dummydoor = 0;
                }
            }
        }
        
        //Switching Original Door?
        if(!ai) {
            System.out.println("You Chose Door #" + doorchosen +"! A Goat Was Behind Door #" + dummydoor + "! Choose Between The Remaining Doors " + doorchosen + " and " + otherdoor);
            doorchosen = Integer.parseInt(scan.nextLine().trim());
        }else {
            if(switchdoor) {
                doorchosen = otherdoor;
            }
        }
        
        //Win Detection and Counting
        totalplays++;       
        if(doorchosen == correctdoor) {
            wins++;
            if(showresult) {
                System.out.println("You Win The Prize! The Correct Door Was Door #" + correctdoor);
                System.out.println("Wins: " + wins + "/" + totalplays);
            }
            return true;
        }
        
        if(showresult) {
            System.out.println("You Get A Goat! The Correct Door Was Door #" + correctdoor + ". You Chose Door #" + doorchosen);
            System.out.println("Wins: " + wins + "/" + totalplays);
        }
        return false;
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------
//Testing
    
    public static void main(String[] args) {
        MonteHallProblem m = new MonteHallProblem();
        boolean automation = true;
        int plays = 100000;
        
        //Automation
        if(automation) {
            for(int i = 0; i < plays; i++) {
                m.playRound(false, true, true);
            }
            System.out.println("Total Wins For Only Switching: " + m.wins + "/" + m.totalplays);
            System.out.println("Times The Original Door Chosen Was The Same The Prize Door: " + m.sames + "/" + m.totalplays);
            
            System.out.println("");
            
            m.wins = 0;
            m.totalplays = 0;
            m.sames = 0;
            
            for(int i = 0; i < plays; i ++) {
                m.playRound(false, true, false);
            }
            System.out.println("Total Wins For Only No Switching: " + m.wins + "/" + m.totalplays);
            System.out.println("Times The Original Door Chosen Was The Same The Prize Door: " + m.sames + "/" + m.totalplays);
        
        //Manual Play
        }else {
            for(int i = 0; i < plays; i++) {
                m.playRound(true, false, false);
            }       
        }
    }
}
