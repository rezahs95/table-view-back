package search;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.lang.Math;

@RestController
@CrossOrigin
public class SearchController {

    private Statement sqlStmtObject = new Connect().getSqlStatement();
    private String sqlQuery = null;

    @CrossOrigin
    @PostMapping(path = "/search", consumes = "application/json", produces = "application/json")
    public Response searchItems(@RequestBody Request requestObject) {
        this.sqlQuery = "SELECT * FROM " + requestObject.getTableName() + " WHERE FNAME LIKE '%" + requestObject.getName() + "%' AND EMAIL LIKE '%" + requestObject.getEmail() + "%' AND CDATE BETWEEN TO_DATE('" + requestObject.getStartDate() + "', 'YYYY-DD-MM') AND TO_DATE('" + requestObject.getEndDate()+"', 'YYYY-DD-MM')";
        //this.sqlQuery = "SELECT * FROM " + requestObject.getTableName();
        System.out.println(requestObject.getTableName());
        try {
            ResultSet result = this.sqlStmtObject.executeQuery(this.sqlQuery);
            int pageNum = requestObject.getPageNumber();
            int resNum = requestObject.getResultNumber();
            String[] fnames = new String[100];
            String[] lnames = new String[100];
            String[] emails = new String[100];
            int[] ids = new int[100];
            String[] dates = new String[100];
            Item[] items = new Item[100];
            Response data = new Response();
            int cnt = 0;
            int cntin = 0;
            String temp = "";

            if(requestObject.getPageNumber()==0) {
                while (result.next()) {
                        ids[cnt] = Integer.parseInt(result.getString(1));
                        fnames[cnt] = result.getString(2);
                        lnames[cnt] = result.getString(3);
                        emails[cnt] = result.getString(4);
                        dates[cnt] = result.getString(5);
                        items[cnt] = new Item(ids[cnt], fnames[cnt], lnames[cnt], emails[cnt], dates[cnt]);
                        cnt++;
                        System.out.println(cnt + " In");
                }
                items = Arrays.copyOfRange(items, 0, cnt);
                data.setPageNumber((int) Math.ceil((double) cnt / (double) resNum));
                data.setResultNumber(cnt);
                data.setItems(items);
                data.setEndPage(requestObject.getPageNumber());
                return data;
            } else {
                while (result.next()) {
                    if ((cnt >= (pageNum - 1) * resNum) && (cnt <= (pageNum - 1) * resNum + resNum - 1)) {
                        ids[cnt] = Integer.parseInt(result.getString(1));
                        fnames[cnt] = result.getString(2);
                        lnames[cnt] = result.getString(3);
                        emails[cnt] = result.getString(4);
                        dates[cnt] = result.getString(5);
                        items[cnt] = new Item(ids[cnt], fnames[cnt], lnames[cnt], emails[cnt], dates[cnt]);
                        cnt++;
                        cntin++;
                        System.out.println(cnt + " In");
                    } else {
                        temp = result.getString(2);
                        cnt++;
                        System.out.println(cnt + " Out");
                    }
                }
                items = Arrays.copyOfRange(items, (pageNum - 1) * resNum, (pageNum - 1) * resNum + cntin);
                data.setPageNumber((int) Math.ceil((double) cnt / (double) resNum));
                data.setResultNumber(cnt);
                data.setEndPage(requestObject.getPageNumber());
                data.setItems(items);
                return data;
            }
        } catch (SQLException e) {
            System.out.println("Failed ! Error:"+e);
            return null;
        }
    }
}