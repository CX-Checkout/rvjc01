package befaster.solutions;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class Checkout1Test {

    @Test
    public void negativeTest() {
        assertThat(Checkout.checkout("ABEAA"), equalTo(-1));
        assertThat(Checkout.checkout("a"), equalTo(-1));
    }

    @Test
    public void withoutSpecialOffersTest() {
        assertThat(Checkout.checkout("ABCD"), equalTo(50 + 30 + 20 + 15));
    }

    @Test
    public void withSpecialOffersTest() {
        assertThat(Checkout.checkout("AAABBB"), equalTo(130 + 45 + 30));
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