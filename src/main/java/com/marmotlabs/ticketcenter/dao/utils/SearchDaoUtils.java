package com.marmotlabs.ticketcenter.dao.utils;

import java.util.Map;
import org.hibernate.Query;
import org.springframework.data.domain.Pageable;

/**
 * Search helper class, used to apply parameters, paging and sorting to the already built query objects.
 * 
 * @author Zuui
 */
public class SearchDaoUtils {

    public static void applyParameters(final Query query, final Map<String, Object> parameters) {
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }

    public static void applyPaging(final Query query, final Pageable pageable) {
        Integer elementsPerPage = pageable.getPageSize();
        Integer firstResult = pageable.getOffset();

        query.setMaxResults(elementsPerPage);
        query.setFirstResult(firstResult);
    }

    /**
     * Applies sorting to the query.
     *
     * @param queryString
     * @param pageable
     */
    public static void applySorting(final StringBuilder queryString, final Pageable pageable) {
        //TODO - implement me
//        String sortColumn = pageable.getSort().getSortColumn();
//        String sortOrder = pageable.getSortOrder();
//
//        if (!StringUtils.isEmpty(sortColumn)) {
//            queryString.append(" order by ").append(sortColumn);
//            if (!StringUtils.isEmpty(sortOrder)) {
//                queryString.append(" ").append(sortOrder).append(" \n");
//            }
//        }
    }
}
