package effexor.roman.nikonovich.domain.entity.vehicle;

public class Search {
    private String idSearch;
    private String nameSearch;

    public Search(String idSearch, String nameSearch) {
        this.idSearch = idSearch;
        this.nameSearch = nameSearch;
    }

    public String getIdSearch() {
        return idSearch;
    }

    public void setIdSearch(String idSearch) {
        this.idSearch = idSearch;
    }

    public String getNameSearch() {
        return nameSearch;
    }

    public void setNameSearch(String nameSearch) {
        this.nameSearch = nameSearch;
    }
}
