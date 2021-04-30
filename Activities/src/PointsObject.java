import java.util.Objects;
public class PointsObject implements  Comparable<PointsObject>{
    private String name;
    private int points;

    public PointsObject(String name, int points){
        this.name = name;
        this.points = points;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public String getName() {
        return name;
    }
    public int getPoints() {
        return points;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if ( o == null || getClass() != o.getClass()) return false;
        PointsObject po = (PointsObject) o;
        return po.points == points && Objects.equals(name, po.name);
    }

    @Override
    public int hashCode() { return Objects.hash(name, points);}

    @Override
    public String toString(){
        return "Name: " + name + "\t\t Points: " + points;
    }

    @Override
    public int compareTo(PointsObject po){
        int x = this.getPoints(); int y = po.getPoints();
        if(x > y) return -1;
        else if(x < y) return 1;
        else return 0;
    }
}
