package com.taptech.altisource;

import com.taptech.alitsource.DefaultRadioactiveIsotopeImpl;
import com.taptech.alitsource.RadioactiveIsotope;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

/**
 * Created by tap on 12/8/15.
 */
public class DefaultRadioactiveIsotopeImplTest {

    private final Logger logger = LoggerFactory.getLogger(DefaultRadioactiveIsotopeImplTest.class);

    private static final int LOWER_BOUND1 = 5566;
    private static final int UPPER_BOUND1 = 5898;
    private static final int LOWER_BOUND2 = 5730;
    private static final int UPPER_BOUND2 = 5731;
    private static final int LOWER_BOUND3 = 76141;
    private static final int UPPER_BOUND3 = 76142;
    private static final int LOWER_BOUND4 = 8740;
    private static final int UPPER_BOUND4 = 8827;

    @Test
    public void testDateRange5000Err100() {
        logger.info("Running test testDateRange5000Err100");
        RadioactiveIsotope radioactiveIsotope = new DefaultRadioactiveIsotopeImpl();
        int con = 5000;
        int err = 100;
        logger.info("Calling dateRange with concentration {} and error {}",con,err);
        int[] results = radioactiveIsotope.dateRange(con,err);
        logger.info("Results [{},{}]",results[0], results[1]);
        assertTrue("The lower bound should be 5566", LOWER_BOUND1 == results[0]);
        assertTrue("The upper bound should be 5898",UPPER_BOUND1 == results[1]);
    }

    @Test
    public void testDateRange5000Err0() {
        logger.info("Running test testDateRange5000Err0");
        RadioactiveIsotope radioactiveIsotope = new DefaultRadioactiveIsotopeImpl();
        int con = 5000;
        int err = 0;
        logger.info("Calling dateRange with concentration {} and error {}",con,err);
        int[] results = radioactiveIsotope.dateRange(con,err);
        logger.info("Results [{},{}]",results[0], results[1]);
        assertTrue("The lower bound should be 5730", LOWER_BOUND2 == results[0]);
        assertTrue("The upper bound should be 5731",UPPER_BOUND2 == results[1]);
    }

    @Test
    public void testDateRange1Err0() {
        logger.info("Running test testDateRange1Err0");
        RadioactiveIsotope radioactiveIsotope = new DefaultRadioactiveIsotopeImpl();
        int con = 1;
        int err = 0;
        logger.info("Calling dateRange with concentration {} and error {}",con,err);
        int[] results = radioactiveIsotope.dateRange(con,err);
        logger.info("Results [{},{}]",results[0], results[1]);
        assertTrue("The lower bound should be 5730", LOWER_BOUND3 == results[0]);
        assertTrue("The upper bound should be 5731",UPPER_BOUND3 == results[1]);
    }

    @Test
    public void testDateRange3456Err18() {
        logger.info("Running test testDateRange1Err0");
        RadioactiveIsotope radioactiveIsotope = new DefaultRadioactiveIsotopeImpl();
        int con = 3456;
        int err = 18;
        logger.info("Calling dateRange with concentration {} and error {}",con,err);
        int[] results = radioactiveIsotope.dateRange(con,err);
        logger.info("Results [{},{}]",results[0], results[1]);
        assertTrue("The lower bound should be 5730", LOWER_BOUND4 == results[0]);
        assertTrue("The upper bound should be 5731",UPPER_BOUND4 == results[1]);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testDateRange0ErrNegative1() {
        logger.info("Running test testDateRange0ErrNegative1");
        RadioactiveIsotope radioactiveIsotope = new DefaultRadioactiveIsotopeImpl();
        int con = 0;
        int err = -1;
        logger.info("Calling dateRange with concentration {} and error {}",con,err);
        int[] results = radioactiveIsotope.dateRange(con,err);

    }

    @Test(expected=IllegalArgumentException.class)
    public void testDateRange0Err100() {
        logger.info("Running test testDateRange0Err100");
        RadioactiveIsotope radioactiveIsotope = new DefaultRadioactiveIsotopeImpl();
        int con = 0;
        int err = 100;
        logger.info("Calling dateRange with concentration {} and error {}",con,err);
        int[] results = radioactiveIsotope.dateRange(con,err);

    }

    @Test(expected=IllegalArgumentException.class)
    public void testDateRange5000ErrNegative1() {
        logger.info("Running test testDateRange5000ErrNegative1");
        RadioactiveIsotope radioactiveIsotope = new DefaultRadioactiveIsotopeImpl();
        int con = 5000;
        int err = -1;
        logger.info("Calling dateRange with concentration {} and error {}",con,err);
        int[] results = radioactiveIsotope.dateRange(con,err);

    }
}
