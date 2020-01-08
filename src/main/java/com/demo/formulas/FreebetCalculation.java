package com.demo.formulas;

import static com.demo.test_properties.TestData.*;


public class FreebetCalculation {


    public static double calculatePotentialPayout() throws Exception {
        Double stake = new Double(freebetBonusStake);
        Double odd   = new Double(selectionDecimal);
        double payout = stake * odd - stake;
        return payout;
    }
}
