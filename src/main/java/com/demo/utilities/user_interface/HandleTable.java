package com.demo.utilities.user_interface;

import com.demo.config.BasicTestConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.demo.config.ReporterConfig.test;


public class HandleTable extends BasicTestConfig {


    private static final Logger LOG  = LogManager.getLogger(HandleTable.class);


    public static void handleWebTable(WebElement table) throws Exception {
        //To locate rows of table.
        List < WebElement > rows_table = table.findElements(By.tagName("tr"));

        //To calculate no of rows In table.
        int rows_count = rows_table.size();

        //Loop will execute for all the rows of the table
        for (int row = 0; row < rows_count; row++) {

            //To locate columns(cells) of that specific row.
            List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));

            //To calculate no of columns(cells) In that specific row.
            int columns_count = Columns_row.size();
            System.out.println("Number of cells In Row " + row + " are " + columns_count);

            //Loop will execute till the last cell of that specific row.
            for (int column = 0; column < columns_count; column++) {
                //To retrieve text from the cells.
                String celltext = Columns_row.get(column).getText();
                System.out.println("Cell Value Of row number " + row + " and column number " + column + " Is " + celltext);
            }
        }
    }
}