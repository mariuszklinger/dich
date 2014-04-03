package pl.mariuszklinger.core.network

import com.esotericsoftware.kryonet.{Listener, Client, Connection}
import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryonet.FrameworkMessage.KeepAlive
import pl.mariuszklinger.core.msgs._

class DichClient {

    var client: Client = new Client()

    val kryo: Kryo = client.getKryo()
    kryo.register(classOf[SomeRequest])
    kryo.register(classOf[SomeResponse])
    kryo.register(classOf[MESSAGE_TYPE.MESSAGE_TYPE])
    kryo.register(classOf[Message])

    def run(host:String, port:Int) {

        client.addListener(new Listener() {
            override def received(connection: Connection, obj: Object) {

                obj match {
                    case sr: SomeResponse => println(sr.text)
                    case ka: KeepAlive => print("C")
                }
            }
        })

        client.start()
        client.connect(5000, host, port)
        //println("connected?" + client.isConnected)

        val request: Message = new Message(MESSAGE_TYPE.CHAT.id, "[msg] client => server")
        client.sendTCP(request)
    }

    def send(r:Message){
        client.sendTCP(r)
    }
}
