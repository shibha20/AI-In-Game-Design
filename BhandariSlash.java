package reboot;

import java.awt.Color;
import java.util.Random;

public class BhandariSlash extends Opponent {
    
    public static double SPEED = 3; // Hack moves up to 3 units/turn
    private Exit exit; 
    Random rand = new Random();

    public BhandariSlash(double x, double y) {
        super(x, y, SPEED, Color.ORANGE);
    }
    
   
    public void move(Dot dot, Bob bob, BhandariSlash slash,BhandariHack hack, Moving[] wires, Wall[] walls, Exit exit) {
        double direction = 0; // Set this to the direction to move.
        double speed = 0; // Speed to move in this direction. It should not exceed SPEED     
        
         
       double bobDistanceToExit = Math.pow( (Math.pow((bob.getX()-exit.getX()),2)+Math.pow((bob.getY()-exit.getY()),2)),0.5);
       double dotDistanceToExit = Math.pow( (Math.pow((dot.getX()-exit.getX()),2)+Math.pow((dot.getY()-exit.getY()),2)),0.5);
       double slashDistanceToBob = Math.pow( (Math.pow((bob.getX()-getX()),2)+Math.pow((bob.getY()-getY()),2)),0.5);

       
       //If Bob is dead, forget everything and pursue Dot. 
       if (bob.isAlive() != true){
           speed= 3;
           direction = chaseDot(dot, bob,slash,wires, walls,exit);
       }
       
       /*Else check if Slash is alive. If Slash is alive, it supports Hack either by chasing Dot with Hack when Dot is too close to the
       exit or by distracting Bob when Dot is not that close to the exit.*/
       
       else if (hack.isAlive()){
           //If Dot is too close to the exit (within 250-300 units), Slash pursues Dot regardless of where Bob is so that it can support Hack. 
            if (dotDistanceToExit <(rand.nextInt((300 - 250) + 1) + 250)) {                     
               speed= 3;
               direction = chaseDot(dot, bob,slash,wires, walls,exit) - rand.nextInt(120);
           }
            /* If Dot is far, Slash distracts Bob by approaching Bob when Bob is less than 400-450 from the exit.
              While Slash is distracting Bob, Hack will be pursuing Dot (Coordination)*/
            else if (bobDistanceToExit < (rand.nextInt((450 - 400) + 1) + 400)){       
                speed= 3;                 
                direction = distractBob(dot, bob, slash,wires, walls,exit) ;
            }
        }
       
        //If Hack is dead, decide whether to puruse Dot or run away from Bob based on the situation. 
       else if (hack.isAlive() !=true ){
           
            //If Bob reached exit, chase Dot. 
            if (exit.intersects(bob)){
               speed= 3;
               direction = chaseDot(dot, bob, slash, wires, walls,exit);
            }
            
            //If Dot is far from the wall but Bob is somewhere between 100-200 from Slash, run away from Bob.  
            else if ( dotDistanceToExit > 200 && slashDistanceToBob <  (rand.nextInt((200 - 100) + 1) + 100) ) {                      
                   speed= 3;   
                   direction = runFromBob(dot, bob, slash, wires, walls,exit);
                  
               }
            
            //If Dot is too close to the wall, forget about Bob and chase Dot. 
            else if (dotDistanceToExit <150) {                     
               speed= 3;
               direction = chaseDot(dot, bob, slash, wires, walls,exit);
           }
       }
        
       if (speed > SPEED) speed = SPEED;
        move(direction, speed, walls);   
    }
    
    //function called to set direction when Slash needs to chase dot
    public double chaseDot(Dot dot, Bob bob, BhandariSlash slash, Moving[] wires, Wall[] walls,Exit exit){
        double direction = 0;
                   double xd= dot.getX();
                   double yd = dot.getY(); 
                   double dx = getX() - xd;
                   double dy = getY() - yd;                              
                   //if dot is above hack and left to hack 
                   if (dot.getY() <= getY()){
                       if (dot.getX() <= getX()){
                           direction =  180 +  (Math.atan(dy/dx)/Math.PI*180);
                       }

                       else{
                           direction =  (Math.atan(dy/dx)/Math.PI*180)+ rand.nextInt(50);
                           }
                   }else if (dot.getY() > getY()){
                       if (dot.getX() <= getX()){
                           direction =  180 +  (Math.atan(dy/dx)/Math.PI*180);
                       }
                       else{
                           direction =  (Math.atan(dy/dx)/Math.PI*180)+ rand.nextInt(50);
                       }
                   }  
        return direction;
    }
   
    
    //function called to set direction when Slash needs to run from Bob
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
    
    //function called to set direction when Hack needs to distract Bob by moving towards it 
    public double distractBob(Dot dot, Bob bob, BhandariSlash slash, Moving[] wires, Wall[] walls,Exit exit){
        double xd= bob.getX() +rand.nextInt(50) ;
        double yd = bob.getY() + rand.nextInt(60);     
        double dx = xd  - getX();
        double dy = yd - getY();                               
        double direction = Math.atan2(dy,dx)/Math.PI*180;
        return direction;
    }
    
}