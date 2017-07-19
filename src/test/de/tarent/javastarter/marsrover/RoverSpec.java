package de.tarent.javastarter.marsrover;

import org.junit.Test;

import static org.junit.Assert.*;

public class RoverSpec {

    private Rover getNewRover() {
        return null;
    }

    @Test
    void ARoverHasAStartPosition() {
        //expect point 0,0
        Point p = getNewRover().getPosition();

        assertEquals(p.getX(), 0);
        assertEquals(p.getY(), 0);
    }

    @Test
    void ARoverHasAStartDirectionToTheNorth() {
        //expect the direction to north.
        assertEquals(getNewRover().getDirection(), Direction.N);
    }
    
    @Test
    void ARoverHasTheStartSpeedOf_1() {
        // given: A new rover
        Rover r = getNewRover();

        // when: The rover moves forward
        Point p = r.moveForward().getPosition();
        
        // then: The direction is +1
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 0);
    }

    @Test
    void ARoverCanTurnLeftToNorthWest() {
        // given: A new rover
        Rover r = getNewRover();

        // when: The rover moves forward
        Direction d = r.turnRight().getDirection();

        // then: The direction is +1
        assertEquals(d , Direction.NW);
    }

    @Test
    void ARoverCanTurnRightToNorthEast() {
        // given: A new rover
        Rover r = getNewRover();

        // when: The rover moves forward
        Direction d = r.turnLeft().getDirection();

        // then: The direction is +1
        assertEquals(d , Direction.NE);
    }

    @Test
    void ARoverCanMakeSeveralMovesOnPlanetMars() {
        // given: A new rover
        Rover r = getNewRover();

        // when: The rover is programmed with several moves
        Rover movedRover = r
            .moveForward()
            .turnLeft()
            .turnLeft()
            .setSpeed(4)
            .moveForward()  // 1, -4
            .turnLeft()     // SW
            .setSpeed(1)
            .moveBackward(); // 2, -3

        // then: The          
        assertEquals(movedRover.getDirection() , Direction.SW);
        assertEquals(movedRover.getPosition().getX() , 2);
        assertEquals(movedRover.getPosition().getY() , -3);

    }
}