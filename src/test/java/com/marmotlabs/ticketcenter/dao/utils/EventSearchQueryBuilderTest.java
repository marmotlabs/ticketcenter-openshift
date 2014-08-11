package com.marmotlabs.ticketcenter.dao.utils;

import com.marmotlabs.ticketcenter.vo.search.EventSearchCriteriaVO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Zuui
 */
public class EventSearchQueryBuilderTest {

    @Test
    public void testGetSearchQuery_nameKeyword() throws ParseException {
        EventSearchCriteriaVO searchCriteria = new EventSearchCriteriaVO();
        searchCriteria.setNameKeyword("kylie");

        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-YYYY");
        String stringSdf = "21-08-1982";
        Date beforeDate = sdf.parse(stringSdf);
        searchCriteria.setBeforeDate(beforeDate);
        EventSearchQuery searchQuery = EventSearchQueryBuilder.getSearchQuery(searchCriteria);
        Assert.assertNotNull(searchQuery);
        Assert.assertNotNull(searchQuery.getParameters());
        Assert.assertEquals("select e \nfrom Event e \n where 1=1 \n and UPPER(e.name) like :nameKeyword \n and e.date <= :dateBefore \n group by e.id \n", searchQuery.getQueryString().toString());
        Assert.assertEquals(2, searchQuery.getParameters().size());
    }

    @Test
    public void testGetSearchQuery_nameKeywordCityId() {
        EventSearchCriteriaVO searchCriteria = new EventSearchCriteriaVO();
        searchCriteria.setNameKeyword("radiohead");
        searchCriteria.setCityId(89L);
        EventSearchQuery searchQuery = EventSearchQueryBuilder.getSearchQuery(searchCriteria);
        Assert.assertNotNull(searchQuery);
        Assert.assertNotNull(searchQuery.getParameters());
        System.out.println("searchQuery: " + searchQuery.getQueryString().toString());
    }

}
