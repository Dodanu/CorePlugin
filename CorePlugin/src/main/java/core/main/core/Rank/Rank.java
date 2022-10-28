package core.main.core.Rank;

public class Rank {

    public Integer id;

    public String name;

    public String displayName;

    public String color;

    public Boolean staffRank;

    public Integer getId() { return id; }

    public String getName() { return name; }

    public String getDisplayName() { return displayName; }

    public String getColor() { return color; }

    public Boolean getStaffRank() { return staffRank; }

    public void getId(Integer id) { this.id = id; }

    public void getName(String name) { this.name = name; }

    public void getDisplayName(String name) { this.displayName = name; }

    public void getColor(String color) { this.color = color; }

    public void getStaffRank(Boolean staffRank) { this.staffRank = staffRank; }

}
