package pl.mariuszklinger.core.network

import com.esotericsoftware.kryonet._
import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryonet.FrameworkMessage.KeepAlive
import pl.mariuszklinger.core.msgs._
import pl.mariuszklinger.core.msgs.MESSAGE_TYPE

class DichServer{

    def run(port:Int) {
        val server: Server = new Server()

        val kryo: Kryo = server.getKryo()
        kryo.register(classOf[Message])

        server.addListener(new DichListener)
        server.start()
        server.bind(port)
    }
}
