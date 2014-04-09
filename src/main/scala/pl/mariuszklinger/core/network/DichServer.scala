package pl.mariuszklinger.core.network

import com.esotericsoftware.kryonet._
import com.esotericsoftware.kryo.Kryo

import pl.mariuszklinger.core.msgs._
import pl.mariuszklinger.core.Node

class DichServer(_node:Node){

    def this() = this(null)

    var listener:DichListener = new DichListener(_node)
    var server:Server = new Server

    def run(port:Int) {
        val kryo: Kryo = server.getKryo()
        kryo.register(classOf[Message])

        server.addListener(listener)
        server.start()
        server.bind(port)
    }
}
