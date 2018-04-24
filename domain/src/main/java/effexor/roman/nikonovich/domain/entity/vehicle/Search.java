package effexor.roman.nikonovich.domain.entity.vehicle;

public class Search implements Comparable<Search> {
    private String idSearch;
    private String nameSearch;
    private long dateCreate;
    private int countNew = 0;

    public Search(String idSearch, String nameSearch, long dateCreate, int countNew) {
        this.idSearch = idSearch;
        this.nameSearch = nameSearch;
        this.dateCreate = dateCreate;
        this.countNew = countNew;

    }

    public int getCountNew() {
        return countNew;
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
        return (int) (search.dateCreate - this.dateCreate);
    }
}
