package pl.mariuszklinger.core.network

import com.esotericsoftware.kryonet._
import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryonet.FrameworkMessage.KeepAlive
import java.security.KeyPairGenerator
import pl.mariuszklinger.core.msgs._
import pl.mariuszklinger.core.msgs.MESSAGE_TYPE
import scala.collection.immutable.HashMap

class DichServer{

    def run(port:Int) {
        val server: Server = new Server()

        val kryo: Kryo = server.getKryo()
        kryo.register(classOf[Message])

        server.addListener(new Listener() {

            override def received(connection: Connection, obj: Object) {

                obj match {

                    case ka: KeepAlive =>
                        print("_")

                    case m:Message =>

                        m.mtype match {

                            case MESSAGE_TYPE.CHAT =>
                                println(m.obj)

                            case MESSAGE_TYPE.ECHO_REQ =>
                                println("ECHO_REQ: " + m.obj)
                                connection.sendTCP(new Message(MESSAGE_TYPE.ECHO_RES, m.obj))

                            case MESSAGE_TYPE.ECHO_RES =>
                                println("ECHO_RES: " + m.obj)
                        }
                }

            }
        })
        server.start()
        server.bind(port)
    }
}
