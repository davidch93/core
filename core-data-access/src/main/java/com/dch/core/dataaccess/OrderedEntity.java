package com.dch.core.dataaccess;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Base entity with ordered field.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Apr 23, 2017
 * @since 1.0.0-SNAPSHOT
 */
@MappedSuperclass
public abstract class OrderedEntity extends BaseEntity {

    private static final long serialVersionUID = -1713977518150279772L;

    @Column(name = "item_order")
    protected Long order;

    /**
     * @return the order
     */
    public Long getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(Long order) {
        this.order = order;
    }
}
