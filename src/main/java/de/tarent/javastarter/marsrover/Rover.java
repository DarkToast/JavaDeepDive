package de.tarent.javastarter.marsrover;

public interface Rover {
    
    public Point getPosition();

    public Direction getDirection();

    public Rover moveForward();

    public Rover moveBackward();

    public Rover turnLeft();

    public Rover turnRight();

    public Rover setSpeed(int speed);
}
