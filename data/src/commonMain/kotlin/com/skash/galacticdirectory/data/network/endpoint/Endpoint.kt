package com.skash.galacticdirectory.data.network.endpoint

import com.skash.forge.network.request.Route

sealed class Endpoint(
    // The buildkonfic plugin for KMP sadly does not yet support gradle. 9.0+. We just hardcode it for now
    parent: Route? = Route(basePath = "https://swapi.py4e.com/api"),
    segment: String
) : Route(parent, segment) {

    data object People : Endpoint(segment = "people"){
        data class Details(val id: Int): Endpoint(parent = this, segment = "$id")
    }
    data object Planet : Endpoint(segment = "planets") {
        data class Details(val id: Int): Endpoint(parent = this, segment = "$id")
    }
    data object Species : Endpoint(segment = "species") {
        data class Details(val id: Int): Endpoint(parent = this, segment = "$id")
    }

}