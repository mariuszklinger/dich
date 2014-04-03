package pl.mariuszklinger.core.network

import com.esotericsoftware.kryonet._
import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryonet.FrameworkMessage.KeepAlive
import java.security.KeyPairGenerator
import pl.mariuszklinger.core.msgs._
import pl.mariuszklinger.core.msgs.MESSAGE_TYPE.MESSAGE_TYPE
import scala.collection.immutable.HashMap

class DichServer{

    def run(port:Int) {
        val server: Server = new Server()

        val kryo: Kryo = server.getKryo()
        kryo.register(classOf[SomeRequest])
        kryo.register(classOf[SomeResponse])
        kryo.register(classOf[MESSAGE_TYPE.MESSAGE_TYPE])
        kryo.register(classOf[Message])

        server.addListener(new Listener() {
            override def received(connection: Connection, obj: Object) {

                val message = obj.asInstanceOf[Message]

                message.mtype match {

                    //case ka: KeepAlive =>
                    //    print("S")

                    case MESSAGE_TYPE.CHAT.id =>
                        print(message.obj.asInstanceOf[String])
                }

                /*
                val response: SomeResponse = new SomeResponse()
                response.text = "Thanks"
                connection.sendTCP(response)
                */
            }
        })
        server.start()
        server.bind(port)
    }
}
