package search;

public class Response {
    private int pageNumber;
    private int resultNumber;
    private int endPage;
    private Item[] items;

    public int getPageNumber() {
        return pageNumber;
    }

    public int getResultNumber() {
        return resultNumber;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setResultNumber(int resultNumber) {
        this.resultNumber = resultNumber;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }
}