package pl.mariuszklinger.core.network

import com.esotericsoftware.kryonet.{Listener, Client, Connection}
import com.esotericsoftware.kryo.Kryo
import pl.mariuszklinger.core.msgs._
import pl.mariuszklinger.core.Node

object DichClient{

    def getConnectedCliend(node: Node, host: String, port: Int): Connection = {
        val client = new Client()
        val kryo: Kryo = client.getKryo()
        kryo.register(classOf[Message])

        client.addListener(new DichListener(node))

        client.start
        client.connect(5000, host, port)

        client
    }
}

class DichClient(_node: Node, _conn: Connection) {

    var listener: DichListener = new DichListener(_node)
    var connection: Connection = _conn

    def this(_node: Node, host: String, port: Int) = this(_node, DichClient.getConnectedCliend(_node, host, port))

    def send(r:Message){
        connection.sendTCP(r)
    }
}
