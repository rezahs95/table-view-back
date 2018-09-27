package search;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

@RestController
public class SearchController {

    private Statement sqlStmtObject = new Connect().getSqlStatement();
    private String sqlQuery = null;

    @PostMapping(path = "/search", consumes = "application/json", produces = "application/json")
    public Item[] searchItems(@RequestBody Request requestObject) {
        //this.sqlQuery = "SELECT * FROM " + requestObject.tableName + " WHERE `FNAME` LIKE `%" + requestObject.firstName + "%` AND `LNAME` LIKE `%" + requestObject.lastName + "%` AND `EMAIL` = " + requestObject.email + " AND `CDATE` BETWEEN " + requestObject.from + " AND " + requestObject.to;
        this.sqlQuery = "SELECT * FROM " + requestObject.getTableName();
        System.out.println(requestObject.getTableName());
        try {
            ResultSet result = this.sqlStmtObject.executeQuery(this.sqlQuery);

            String[] fnames = new String[100];
            String[] lnames = new String[100];
            String[] emails = new String[100];
            int[] ids = new int[100];
            String[] dates = new String[100];
            Item[] items = new Item[100];


            int cnt = 0;
            while (result.next()) {
                ids[cnt] = Integer.parseInt(result.getString(1));
                fnames[cnt] = result.getString(2);
                lnames[cnt] = result.getString(3);
                emails[cnt] = result.getString(4);
                dates[cnt] = result.getString(5);
                items[cnt] = new Item(ids[cnt], fnames[cnt], lnames[cnt], emails[cnt], dates[cnt]);
                cnt++;
            }
            items = Arrays.copyOfRange(items, 0, cnt);
            return items;
        } catch (SQLException e) {
            System.out.println("Failed !");
            return null;
        }
    }
}
