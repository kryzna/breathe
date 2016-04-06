import com.rgp.breathe.helper.Helper;
import org.junit.Assert;
import org.junit.Test;

public class HelperTest {

    @Test
    public void testHelperClassTest() {
        Helper helperInstance = new Helper();
        Assert.assertTrue(helperInstance instanceof com.rgp.breathe.helper.Helper);
    }

    @Test
    public void DateLengthTest() {
        final String format = "dd/MM/yyyy hh:mm:ss a";

        String sdate = Helper.getFormattedDate(format);
        Assert.assertTrue(sdate.length() == format.length() + 1);
    }

    @Test
    public void containsValidDateTest() {
        final String format = "dd/MM/yyyy hh:mm:ss a";

        String sdate = Helper.getFormattedDate(format);
        Assert.assertTrue(sdate.matches("^[0-9]{2}\\/[0-9]{2}\\/[0-9]{4} [0-9]{2}\\:[0-9]{2}\\:[0-9]{2} [A-Z]{2}"));
    }

    @Test
    public void containsValidYearTest() {
        final String format = "yyyy";
        String sdate = Helper.getFormattedDate(format);
        Assert.assertTrue(Integer.parseInt(sdate) > 2015);
    }

}