package reboot;

import java.awt.Color;
import java.util.Random;

public class BhandariHack extends Opponent {
    
    public static double SPEED = 3; // Hack moves up to 3 units/turn
    private Exit exit;      // Goal for player
    Random rand = new Random(); 

    public BhandariHack(double x, double y) {
        super(x, y, SPEED, Color.YELLOW);
        
    }
 
    public void move(Dot dot, Bob bob, BhandariSlash slash, Moving[] wires, Wall[] walls,Exit exit) {  
      // Set this to the direction to move.
       double speed = 0; // Speed to move in this direction. It should not exceed SPEED     
       double direction = 0;
        
       double dotDistanceToExit = Math.pow( (Math.pow((dot.getX()-exit.getX()),2)+Math.pow((dot.getY()-exit.getY()),2)),0.5);
       double hackDistanceToBob = Math.pow( (Math.pow((bob.getX()-getX()),2)+Math.pow((bob.getY()-getY()),2)),0.5);
       
       //If Bob is dead, forget everything and pursue Dot. 
       if (bob.isAlive() != true){
           speed= 3;
           direction = chaseDot(dot, bob, slash, wires, walls,exit);
       }

       //Else check if Slash is alive. If Slash is alive, chase Dot when Dot is within 300-400 units from the exit. 
       else if (slash.isAlive()){
           if (dotDistanceToExit < (rand.nextInt((400 - 300) + 1) + 300)){                      
            speed= 3;                    
            direction = chaseDot(dot, bob, slash, wires, walls,exit) ;
         }  
          
       }  
       
       //If Slash is dead, decide whether to puruse Dot or run away Bob based on the situation. 
       else if (slash.isAlive() !=true ){
           
            //If Bob reached exit,since Bob can not come back, chase Dot. 
            if (exit.intersects(bob)){
               speed= 3;
               direction = chaseDot(dot, bob, slash, wires, walls,exit);
            }
            
            //If Dot is far from the wall but Bob is somewhere between 100-200 from Hack, run away from Bob.  
            else if ( dotDistanceToExit > 200 && hackDistanceToBob <  (rand.nextInt((200 - 100) + 1) + 100) ) {                      
                   speed= 3;   
                   direction = runFromBob(dot, bob, slash, wires, walls,exit);
                  
               }
            
            //If Dot is too close to the wall, forget about Bob and chase Dot. 
            else if (dotDistanceToExit <200) {                     
               speed= 3;
               direction = chaseDot(dot, bob, slash, wires, walls,exit);
           }
       }
   
        if (speed > SPEED) speed = SPEED;
        move(direction, speed, walls);   
    }
    
    //function called to set direction when Hack needs to chase dot
    public double chaseDot(Dot dot, Bob bob, BhandariSlash slash, Moving[] wires, Wall[] walls,Exit exit){
        double direction = 0;
                   double xd= dot.getX();
                   double yd = dot.getY(); 
                   double dx = getX() - xd;
                   double dy = getY() - yd;                              
                   //if dot is above hack and left to hack 
                   if (dot.getY() < getY()){
                       if (dot.getX() < getX()){
                           direction =  180 +  (Math.atan(dy/dx)/Math.PI*180);
                       }

                       else{
                           direction =  (Math.atan(dy/dx)/Math.PI*180)+ rand.nextInt(50);
                           }
                   }else if (dot.getY() > getY()){
                       if (dot.getX() < getX()){
                           direction =  180 +  (Math.atan(dy/dx)/Math.PI*180);
                       }
                       else{
                           direction =  (Math.atan(dy/dx)/Math.PI*180)+ rand.nextInt(50);
                       }
                   }  
        return direction;
    }
   
    //function called to set direction when Hack needs to run from Bob
    public double runFromBob(Dot dot, Bob bob, BhandariSlash slash, Moving[] wires, Wall[] walls,Exit exit){
        double direction = 0;
                   double xd= bob.getX() +rand.nextInt(50) ;
                   double yd = bob.getY() + rand.nextInt(60);     
                   double dx = xd  - getX();
                   double dy = yd - getY();   
                   if (bob.getY() <= getY()){
                       if (bob.getX() >= getX()){
                           direction =  180 - Math.atan2(dy,dx)/Math.PI*180+ rand.nextInt(50);
                       }
                       else{
                           direction =  270 + Math.atan2(dy,dx)/Math.PI*180+rand.nextInt(50);
                           }
                   }else if (bob.getY() > getY()){
                       if (bob.getX() >= getX()){
                           direction =  180 -  Math.atan2(dy,dx)/Math.PI*180+rand.nextInt(50);
                       }
                       else{
                           direction =  90+ Math.atan2(dy,dx)/Math.PI*180+rand.nextInt(50);
                       }
                   }    
        return direction;
    }
}