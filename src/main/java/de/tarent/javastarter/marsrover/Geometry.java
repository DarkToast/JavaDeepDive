package de.tarent.javastarter.marsrover;

public interface Geometry {
    Point next(Point start, Direction direction, int speed);
}
