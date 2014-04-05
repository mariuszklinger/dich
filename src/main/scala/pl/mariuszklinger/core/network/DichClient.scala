package pl.mariuszklinger.core.network

import com.esotericsoftware.kryonet.{Listener, Client, Connection}
import com.esotericsoftware.kryo.Kryo
import pl.mariuszklinger.core.msgs._

class DichClient {

    var client: Client = new Client()

    val kryo: Kryo = client.getKryo()
    kryo.register(classOf[Message])

    def run(host:String, port:Int) {

        client.addListener(new DichListener)

        client.start()
        client.connect(5000, host, port)
    }

    def send(r:Message){
        client.sendTCP(r)
    }
}
