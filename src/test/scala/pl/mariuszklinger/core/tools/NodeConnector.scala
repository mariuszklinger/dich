package pl.mariuszklinger.core.tools

import pl.mariuszklinger.core.Node

object NodeConnector {

    def connect(n1: Node, n2: Node){
        n1.connect("localhost", n2.port)
    }
}
