package com.fernandocejas.sample.core.di

import org.koin.core.module.Module

/**
 * The fundamental piece of modularity.
 *
 * An Application always contains features,
 * and that is represented by this
 * contract/abstraction.
 */
interface Feature {

    /**
     * Convention: The feature name should follow
     * the package name where the feature is contained
     * using lower case.
     *
     * Examples:
     *  - core
     *  - login
     *  - movies
     */
    fun name(): String

    /**
     * Koin DI Module that will be included
     * when creating the Dependency Graph
     * at Application Startup.
     *
     * In case of scoping this will facilitate
     * refactor.
     */
    fun diModule(): Module

    /**
     * LEARNING PURPOSE:
     * In order to keep modularity, each feature could
     * point to the tables in the database related
     * to the feature itself.
     */
    // fun databaseTables(): List<Table> = emptyList()
}
