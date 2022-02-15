package com.journaler.api.controller

import com.fasterxml.jackson.annotation.JsonProperty

data class NoteFindByTitleRequest (@JsonProperty("title") val title: String)