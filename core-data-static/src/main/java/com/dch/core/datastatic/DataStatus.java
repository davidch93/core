package com.dch.core.datastatic;

/**
 * List of data status that represent database column.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Apr 23, 2017
 * @since 1.0.0-SNAPSHOT
 */
public enum DataStatus {

    /**
     * Status for activated data.
     */
    ACTIVATED,

    /**
     * Status for deleted data.
     */
    DELETED,

    /**
     * Status for in-activated data.
     */
    INACTIVATED,

    /**
     * Status for terminated data.
     */
    TERMINATED;
}