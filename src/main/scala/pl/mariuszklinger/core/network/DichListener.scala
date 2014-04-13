package pl.mariuszklinger.core.network

import com.esotericsoftware.kryonet.{Connection, Listener}
import com.esotericsoftware.kryonet.FrameworkMessage.KeepAlive

import pl.mariuszklinger.core.msgs.{MESSAGE_TYPE, Message}
import pl.mariuszklinger.core.Node

import com.esotericsoftware.kryonet.util.TcpIdleSender

class DichListener(_node:Node) extends Listener{

    var node = _node

    def this() = this(null)

    def echoReqCallback(m:Message) {
        println(": ECHO_REQ: " + m.obj)
    }

    def echoResCallback(m:Message) {
        println(": ECHO_RES: " + m.obj)
    }

    def chatMessageCallback(m:Message) {

    }

    override def connected(connection:Connection) {

    }

    override def disconnected(connection:Connection) {

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
                        echoReqCallback(m)
                        connection.sendTCP(new Message(MESSAGE_TYPE.ECHO_RES, m.obj))

                    case MESSAGE_TYPE.ECHO_RES =>
                        echoResCallback(m)
                }
        }
    }
}
