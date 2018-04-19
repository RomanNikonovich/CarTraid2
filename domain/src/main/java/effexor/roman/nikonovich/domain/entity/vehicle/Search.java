package effexor.roman.nikonovich.domain.entity.vehicle;

public class Search implements Comparable<Search>{
    private String idSearch;
    private String nameSearch;
    private long dateCreate;

    public Search(String idSearch, String nameSearch, long dateCreate) {
        this.idSearch = idSearch;
        this.nameSearch = nameSearch;
        this.dateCreate = dateCreate;
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

    @Override
    public int compareTo(Search search) {
        return (int)(search.dateCreate - this.dateCreate);
    }
}
