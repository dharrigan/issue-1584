package com.dharrigan.config

import org.springframework.boot.context.properties.ConfigurationProperties
import java.util.ArrayList

@ConfigurationProperties("circular-stream")
data class CircularStreamProperties(

    var types: List<String> = ArrayList()

)
