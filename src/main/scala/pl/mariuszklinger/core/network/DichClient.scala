package pl.mariuszklinger.core.network

import com.esotericsoftware.kryonet.{Listener, Client, Connection}
import com.esotericsoftware.kryo.Kryo
import pl.mariuszklinger.core.msgs._

class DichClient {

    var listener:DichListener = new DichListener
    val client: Client = new Client()

    def run(host:String, port:Int) {

        val kryo: Kryo = client.getKryo()
        kryo.register(classOf[Message])

        client.addListener(listener)

        client.start
        client.connect(5000, host, port)
    }

    def send(r:Message){
        client.sendTCP(r)
    }
}
