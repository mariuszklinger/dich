package pl.mariuszklinger.core.network

import com.esotericsoftware.kryonet._
import com.esotericsoftware.kryo.Kryo
import pl.mariuszklinger.core.msgs._

class DichServer{

    var listener:DichListener = new DichListener

    def run(port:Int) {
        val server: Server = new Server()

        val kryo: Kryo = server.getKryo()
        kryo.register(classOf[Message])

        server.addListener(listener)
        server.start()
        server.bind(port)
    }
}
