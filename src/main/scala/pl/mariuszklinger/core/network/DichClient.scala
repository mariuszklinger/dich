package pl.mariuszklinger.core.network

import com.esotericsoftware.kryonet.{Listener, Client, Connection}
import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryonet.FrameworkMessage.KeepAlive
import pl.mariuszklinger.core.msgs._

class DichClient {

    var client: Client = new Client()

    val kryo: Kryo = client.getKryo()
    kryo.register(classOf[Message])

    def run(host:String, port:Int) {

        client.addListener(new Listener() {
            override def received(connection: Connection, obj: Object) {

                obj match {
                    case ka: KeepAlive =>
                        print("-")

                    case m:Message =>

                        m.mtype match {

                            case MESSAGE_TYPE.ECHO_REQ =>
                                println("ECHO_REQ: " + m.obj)
                                connection.sendTCP(new Message(MESSAGE_TYPE.ECHO_RES, m.obj))

                            case MESSAGE_TYPE.ECHO_RES =>
                                println("ECHO_RES: " + m.obj)
                        }
                }
            }
        })

        client.start()
        client.connect(5000, host, port)

        //val request: Message = new Message(MESSAGE_TYPE.CHAT, "[msg] client => server")
        //client.sendTCP(request)
    }

    def send(r:Message){
        client.sendTCP(r)
    }
}
