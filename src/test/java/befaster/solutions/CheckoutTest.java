package befaster.solutions;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CheckoutTest {

    @Test
    public void negativeTest() {
        assertThat(Checkout.checkout("ABEAA"), equalTo(-1));
    }

    @Test
    public void withoutSpecialOffersTest() {
        assertThat(Checkout.checkout("ABCD"), equalTo(50 + 30 + 20 + 15));
    }

    @Test
    public void withSpecialOffersTest() {
        assertThat(Checkout.checkout("AAABBB"), equalTo(130 + 45 + 30));
    }
}