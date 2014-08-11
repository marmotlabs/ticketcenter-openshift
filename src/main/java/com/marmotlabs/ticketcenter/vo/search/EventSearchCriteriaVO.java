package com.marmotlabs.ticketcenter.vo.search;

import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * VO that holds the search parameters of the index page.
 *
 * @author Zuui
 */
public class EventSearchCriteriaVO {

    private String nameKeyword;

    private Long cityId;

    private Date afterDate;

    private Date beforeDate;

    public String getNameKeyword() {
        return nameKeyword;
    }

    public void setNameKeyword(String nameKeyword) {
        this.nameKeyword = nameKeyword;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Date getAfterDate() {
        return afterDate;
    }

    public void setAfterDate(Date afterDate) {
        this.afterDate = afterDate;
    }

    public Date getBeforeDate() {
        return beforeDate;
    }

    public void setBeforeDate(Date beforeDate) {
        this.beforeDate = beforeDate;
    }

    /**
     * Returns true if the nameKeyword criteria is not-null and not-void
     *
     * @return
     */
    public Boolean hasNameKeyword() {
        return !StringUtils.isEmpty(nameKeyword);
    }

    /**
     * Returns true if the cityId is not-null and not -1
     *
     * @return
     */
    public Boolean hasCityId() {
        return (cityId != null && cityId != -1);
    }

    /**
     * Returns true if dateAfter is not null
     *
     * @return
     */
    public Boolean hasDateAfter() {
        return (afterDate != null);
    }

    /**
     * Returns true if dateBefore is not null
     *
     * @return
     */
    public Boolean hasDateBefore() {
        return (beforeDate != null);
    }

    /**
     * Returns true if at least one of the filtering elements is not null, false
     * otherwise.
     *
     * @return
     */
    public Boolean hasFilters() {
        return hasNameKeyword() || hasCityId() || hasDateAfter() || hasDateBefore();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("nameKeyword", nameKeyword).
                append("cityId", cityId).
                append("afterDate", afterDate).
                append("beforeDate", beforeDate).
                toString();
    }

}
