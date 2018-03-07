package com.balaji.countrydetaildemo;

import com.balaji.countrydetaildemo.model.Country;
import com.balaji.countrydetaildemo.model.Row;
import com.balaji.countrydetaildemo.utils.AppUtils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void removeEmptyDataWithAllNull() throws Exception {

        Country country = new Country();
        List<Row> rows = new ArrayList<>();

        Row row1 = new Row();
        row1.setTitle("title");
        row1.setDescription("desc");
        row1.setImageHref("image");
        rows.add(row1);

        Row row2 = new Row();
        row2.setTitle(null);
        row2.setDescription(null);
        row2.setImageHref(null);
        rows.add(row2);

        country.setRows(rows);
        assertEquals(1, AppUtils.removeEmptyData(country).getRows().size());

    }

    @Test
    public void removeEmptyDataWithSingleNull() throws Exception {

        Country country = new Country();
        List<Row> rows = new ArrayList<>();

        Row row1 = new Row();
        row1.setTitle("title");
        row1.setDescription("desc");
        row1.setImageHref("image");
        rows.add(row1);

        Row row2 = new Row();
        row2.setTitle("title");
        row2.setDescription(null);
        row2.setImageHref(null);
        rows.add(row2);

        country.setRows(rows);
        assertEquals(2, AppUtils.removeEmptyData(country).getRows().size());

    }
}