package befaster.solutions;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class Checkout3Test {

    @Test
    public void negativeTest() {
        assertThat(Checkout.checkout("ABRAA"), equalTo(-1));
        assertThat(Checkout.checkout("a"), equalTo(-1));
    }

    @Test
    public void withoutSpecialOffersTest() {
        assertThat(Checkout.checkout("ABCD"), equalTo(50 + 30 + 20 + 15));
    }

    @Test
    public void withSpecialOffersTest() {
        assertThat(Checkout.checkout("AAABBB"), equalTo(130 + 45 + 30));
        assertThat(Checkout.checkout("AAAAAAAAABBB"), equalTo(200 + 130 + 50 + 45 + 30));
        assertThat(Checkout.checkout("BBBBEEE"), equalTo(45 + 30 + 40*3));
        assertThat(Checkout.checkout("FFFFF"), equalTo(40));
        assertThat(Checkout.checkout("FFFFFF"), equalTo(40));
    }

    @Test
    public void emptyReceiptTest() {
        assertThat(Checkout.checkout(""), equalTo(0));
    }

    @Test
    public void simpleTest() {
        assertThat(Checkout.checkout("C"), equalTo(20));
    }
}