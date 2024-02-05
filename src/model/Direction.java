package model;

public enum Direction {
    DOWN(0, 1), LEFT(-1, 0), UP(0, -1), RIGHT(1, 0);
    
    Direction(int x, int y){
        this.x = x;
        this.y = y;
    }
    public final int x, y;
    
    public static Direction rotateCW(Direction d) {
        if (d == Direction.UP) return Direction.RIGHT;
        if (d == Direction.RIGHT) return Direction.DOWN;
        if (d == Direction.DOWN) return Direction.LEFT;
        if (d == Direction.LEFT) return Direction.UP;
        return null;
    }
    
    public static Direction[] getAllDirections() {
        Direction[] a = {UP, RIGHT, DOWN, LEFT};
        return a;
    }
}
