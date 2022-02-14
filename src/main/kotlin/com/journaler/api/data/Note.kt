package com.journaler.api.data

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "note")
data class Note (
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(36)")
    var id: String = "",
    var title: String,
    var message: String,
    var location: String = ""
) {

    /**
     * Hibernate tries createsa bean via reflection
     * It does the object create by calling the no-arg constructor
     * Then it uses the setter methods to set the properties.
     *
     * If there is no default constructor, the following exception happens:
     * org.hibernate.InstantiationException: No default constructor for entity....
     */
    constructor() : this(
        "", "", "", ""
    )
}

@Entity
@Table(name = "todo")
data class Todo (
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(36)")
    var id: String = "",
    var title: String,
    var message: String,
    var schedule: Long,

    var location: String = ""
) {

    /**
     * Hibernate tries createsa bean via reflection
     * It does the object create by calling the no-arg constructor
     * Then it uses the setter methods to set the properties.
     *
     * If there is no default constructor, the following exception happens:
     * org.hibernate.InstantiationException: No default constructor for entity....
     */
    constructor() : this(
        "", "", "", -1, ""
    )
}