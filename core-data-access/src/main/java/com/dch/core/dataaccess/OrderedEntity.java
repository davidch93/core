package com.dch.core.dataaccess;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Base entity with ordered field.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@MappedSuperclass
public abstract class OrderedEntity extends BaseEntity {

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
