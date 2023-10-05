package dem.esteban.graphql.query.builder.example;

import java.util.List;

public class Country {
    private String name;
    private List<Languages> languages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Languages> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Languages> languages) {
        this.languages = languages;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", languages=" + languages +
                '}';
    }
}