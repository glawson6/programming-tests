package com.taptech.alitsource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Gregory Lawson on 12/8/15.
 */
public class DefaultRadioactiveIsotopeImpl implements RadioactiveIsotope {

    private static final int NINE_THOUSAND_99 = 9999;
    private static final int ONE = 1;
    private static final int ZERO = 0;
    private static final int TEN_THOUSAND = 10000;
    private static final double TEN_THOUSAND_D = TEN_THOUSAND;
    private static final double ISOTOPE_K = 8267.0d;
    private static final String CONCENTRATION_MESSAGE = "Concentration should be between 1 and 9999";
    private static final StringBuilder ERROR_DATA_MESSAGE = new StringBuilder("Error should be greater than ");

    private final static  Logger logger = LoggerFactory.getLogger(DefaultRadioactiveIsotopeImpl.class);

    @Override
    public int[] dateRange(final int concentration, final int err){
        if (concentration < ONE || concentration > NINE_THOUSAND_99){
            throw new IllegalArgumentException(CONCENTRATION_MESSAGE);
        }
        int maxError = Math.min(concentration, (TEN_THOUSAND - concentration));
        if (err < 0 || err > maxError){
            StringBuilder message = new StringBuilder("Error should be greater than or equal to ");
            message.append(ZERO).append(" and less than ").append(maxError);
            throw new IllegalArgumentException(message.toString());
        }
        int[] results = new int[2];
        if (err == 0) {
            double realConcentration = concentration / TEN_THOUSAND_D;
            double time = calculateTime(realConcentration);
            int roundedUpTime = (int) roundUpTime(time);
            int roundedDownTime = (int) roundDownTime(time);
            results[0] = roundedDownTime;
            results[1] = roundedUpTime;
        } else {
            double realConcentrationLower = calculateLowerConcentration(concentration,err);
            double realConcentrationHigher = calculateUpperConcentration(concentration, err);
            double timeLower = calculateTime(realConcentrationLower);
            double timeHigher = calculateTime(realConcentrationHigher);
            int roundedUpTime = (int) roundUpTime(timeHigher);
            int roundedDownTime = (int) roundDownTime(timeLower);
            results[0] = roundedDownTime;
            results[1] = roundedUpTime;
        }
        return results;
    }

    public double roundUpTime(double time){
        return Math.ceil(time);
    }

    public double roundDownTime(double time){
        return Math.floor(time);
    }

    public double calculateTime(double actualConcentration) {
        return -ISOTOPE_K * Math.log(actualConcentration);
    }

    public double calculateLowerConcentration(int concentration, int err) {

        return (concentration + err) / TEN_THOUSAND_D ;
    }

    public double calculateUpperConcentration(int concentration, int err) {

        return (concentration - err) / TEN_THOUSAND_D ;
    }
}