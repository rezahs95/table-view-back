package search;

public class Response {
    private int pages;
    private int results;
    private Item[] items;

    public int getPages() {
        return pages;
    }

    public int getResults() {
        return results;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setResults(int results) {
        this.results = results;
    }
}
