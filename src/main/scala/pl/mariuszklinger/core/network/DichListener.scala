package pl.mariuszklinger.core.network

import com.esotericsoftware.kryonet.{Connection, Listener}
import com.esotericsoftware.kryonet.FrameworkMessage.KeepAlive
import pl.mariuszklinger.core.msgs.{MESSAGE_TYPE, Message}

class DichListener extends Listener{

    def echoReqCallback(connection:Connection, m:Message) {
        println("ECHO_REQ: " + m.obj)
        connection.sendTCP(new Message(MESSAGE_TYPE.ECHO_RES, m.obj))
    }

    override def received(connection:Connection, obj:Object) {

        obj match {

            case ka: KeepAlive =>
                print("_")

            case m:Message =>

                m.mtype match {

                    case MESSAGE_TYPE.CHAT =>
                        println(m.obj)

                    case MESSAGE_TYPE.ECHO_REQ =>
                        echoReqCallback(connection, m)

                    case MESSAGE_TYPE.ECHO_RES =>
                        println("ECHO_RES: " + m.obj)
                }
        }
    }
}
