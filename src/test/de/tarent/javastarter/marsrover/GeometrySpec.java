package de.tarent.javastarter.marsrover;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeometrySpec {

    Geometry geometry;

    @Test
    public void AGeometryShouldCalcTheNextPointToTheNorth() {
        //given a point
        Point p = new Point(1, 1);

        // when we calculate the next:
        Point newP = geometry.next(p, Direction.N, 2);

        // We get a new point
        assertEquals(newP.getX(), 3);
        assertEquals(newP.getY(), 1);
    }

    @Test
    public void AGeometryShouldCalcTheNextPointToNorthEast() {
        //given a point
        Point p = new Point(1, 1);

        // when we calculate the next:
        Point newP = geometry.next(p, Direction.NE, 2);

        // We get a new point
        assertEquals(newP.getX(), 3);
        assertEquals(newP.getY(), 3);
    }

    @Test
    public void AGeometryShouldCalcTheNextPointToEast() {
        //given a point
        Point p = new Point(1, 1);

        // when we calculate the next:
        Point newP = geometry.next(p, Direction.E, 4);

        // We get a new point
        assertEquals(newP.getX(), 1);
        assertEquals(newP.getY(), 5);
    }

    @Test
    public void AGeometryShouldCalcTheNextPointToSouthEast() {
        //given a point
        Point p = new Point(1, 1);

        // when we calculate the next:
        Point newP = geometry.next(p, Direction.SE, 1);

        // We get a new point
        assertEquals(newP.getX(), 0);
        assertEquals(newP.getY(), 2);
    }

    @Test
    public void AGeometryShouldCalcTheNextPointToTheSouth() {
        //given a point
        Point p = new Point(1, 1);

        // when we calculate the next:
        Point newP = geometry.next(p, Direction.S, 10);

        // We get a new point
        assertEquals(newP.getX(), -9);
        assertEquals(newP.getY(), 1);
    }

    @Test
    public void AGeometryShouldCalcTheNextPointToTheSouthWest() {
        //given a point
        Point p = new Point(1, 1);

        // when we calculate the next:
        Point newP = geometry.next(p, Direction.SW, 3);

        // We get a new point
        assertEquals(newP.getX(), -2);
        assertEquals(newP.getY(), -2);
    }

    @Test
    public void AGeometryShouldCalcTheNextPointToTheWest() {
        //given a point
        Point p = new Point(1, 1);

        // when we calculate the next:
        Point newP = geometry.next(p, Direction.W, 3);

        // We get a new point
        assertEquals(newP.getX(), 1);
        assertEquals(newP.getY(), -2);
    }

    @Test
    public void AGeometryShouldCalcTheNextPointToTheNorthWest() {
        //given a point
        Point p = new Point(1, 1);

        // when we calculate the next:
        Point newP = geometry.next(p, Direction.NW, 3);

        // We get a new point
        assertEquals(newP.getX(), 3);
        assertEquals(newP.getY(), -2);
    }

    @Test
    public void AGeometryHasItsBordersOn90OnX() {
        //given a point
        Point p = new Point(90, 0);

        // when we calculate the next:
        Point newP = geometry.next(p, Direction.N, 1);

        // We get a new point
        assertEquals(newP.getX(), -89);
        assertEquals(newP.getY(), 0);
    }

    @Test
    public void AGeometryHasItsBordersOn90OnX_2() {
        //given a point
        Point p = new Point(-89, 0);

        // when we calculate the next:
        Point newP = geometry.next(p, Direction.S, 1);

        // We get a new point
        assertEquals(newP.getX(), 90);
        assertEquals(newP.getY(), 0);
    }
    
    @Test
    public void AGeometryHasItsBordersOn180OnY() {
        //given a point
        Point p = new Point(0, 180);

        // when we calculate the next:
        Point newP = geometry.next(p, Direction.E, 1);

        // We get a new point
        assertEquals(newP.getX(), 0);
        assertEquals(newP.getY(), -179);
    }

    @Test
    public void AGeometryHasItsBordersOn180OnY_2() {
        //given a point
        Point p = new Point(0, -179);

        // when we calculate the next:
        Point newP = geometry.next(p, Direction.W, 1);

        // We get a new point
        assertEquals(newP.getX(), 0);
        assertEquals(newP.getY(), 180);
    }

}