package com.journaler.api.controller

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class TodoLaterThanRequest(@JsonProperty("date") val date: Date)