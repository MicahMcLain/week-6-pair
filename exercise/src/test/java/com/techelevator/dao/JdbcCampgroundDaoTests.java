package com.techelevator.dao;

import com.techelevator.model.Campground;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class JdbcCampgroundDaoTests extends BaseDaoTests {

    private final Campground CAMPGROUND_1 = new Campground(1, 1, "Test Campground 1", 1, 12, 35.0);
    private final Campground CAMPGROUND_2 = new Campground(2, 1, "Test Campground 2", 1, 12, 35.0);
//    private final Campground CAMPGROUND_3 = new Campground(3, 2, "test2", 1, 12, 25.0);
    private CampgroundDao dao;



    @Before
    public void setup() {
        dao = new JdbcCampgroundDao(dataSource);
    }

    @Test
    public void getCampgroundById_Should_Return_Specific_Campground() {
        Campground campground = dao.getCampgroundById(1);

        assertEquals("Incorrect campground returned for ID 1", 1, campground.getCampgroundId());
    }

    @Test
    public void getCampgroundsByParkId_Should_Return_All_Campgrounds_For_Park() {
        List<Campground> c = dao.getCampgroundsByParkId(1);
        Assert.assertEquals(2, c.size());
//        assertCampgroundsMatch(CAMPGROUND_1, c.get(0));
//        assertCampgroundsMatch(CAMPGROUND_2, c.get(1));

    }

//    private void assertCampgroundsMatch(Campground expected, Campground actual) {
//        Assert.assertEquals(expected.getCampgroundId(), actual.getCampgroundId());
//        Assert.assertEquals(expected.getDailyFee(), actual.getDailyFee(), 0.1);
//        Assert.assertEquals(expected.getName(), actual.getName());
//        Assert.assertEquals(expected.getOpenFromMonth(), actual.getOpenFromMonth());
//        Assert.assertEquals(expected.getOpenToMonth(), actual.getOpenToMonth());
//        Assert.assertEquals(expected.getParkId(), actual.getParkId());
//    }

}
