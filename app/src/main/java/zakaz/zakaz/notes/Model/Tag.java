package zakaz.zakaz.notes.Model;

public class Tag {
    private String Name;
    private Integer id;

    public Tag(String name, Integer id) {
        Name = name;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Tag(String name) {
        Name = name;
    }
}
