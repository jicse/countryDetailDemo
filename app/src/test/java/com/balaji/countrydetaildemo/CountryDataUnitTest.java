package com.balaji.countrydetaildemo;

import com.balaji.countrydetaildemo.model.Country;
import com.balaji.countrydetaildemo.model.Row;
import com.balaji.countrydetaildemo.utils.AppUtils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CountryDataUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test_removeEmptyDataWithAllNull() throws Exception {

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
    public void test_removeEmptyDataWithSingleNull() throws Exception {

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

    @Test
    public void test_checkRowEmptyWithData() {
        Row row = new Row();
        row.setTitle("Title");
        row.setDescription("Description");
        row.setImageHref("http//www.sample.com/");
        assertSame(false, AppUtils.checkIsRowEmpty(row));
    }

    @Test
    public void test_checkRowEmptyWithoutData() {
        Row row = new Row();
        row.setTitle("");
        row.setDescription("");
        row.setImageHref(null);
        assertSame(true, AppUtils.checkIsRowEmpty(row));
    }

    @Test
    public void test_checkRowEmptyWithNull() {
        assertSame(true, AppUtils.checkIsRowEmpty(null));
    }
}