package hda.outcome.rules.tests.cotrollers;

import hda.outcome.rules.tests.PageObjects.*;
import hda.outcome.rules.tests.testContext.TestExecutionContext;

public class PageObjectFacade {

    private CartPage cartpage;

    public CartPage getCartPage(TestExecutionContext testExecutionContext) {
        return (cartpage == null) ? cartpage = new CartPage(testExecutionContext) : cartpage;
    }

}
