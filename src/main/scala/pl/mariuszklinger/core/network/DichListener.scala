package pl.mariuszklinger.core.network

import com.esotericsoftware.kryonet.{Connection, Listener}
import com.esotericsoftware.kryonet.FrameworkMessage.{Ping, KeepAlive}

import pl.mariuszklinger.core.msgs.{MESSAGE_TYPE, Message}
import pl.mariuszklinger.core.Node

class DichListener(_node: Node) extends Listener{

    var node = _node

    def this() = this(null)

    def echoReqCallback(m: Message) {
        println(node.nick + ":[ECHO_REQ]:\t" + m.obj)
    }

    def echoResCallback(m: Message) {
        println(node.nick + ":[ECHO_RES]:\t" + m.obj)
    }

    def chatMessageCallback(m: Message) {
        // TODO
    }

    override def connected(connection: Connection) {
        _node.addClient(new DichClient(_node, connection))
    }

    override def disconnected(connection: Connection) {

    }

    override def received(connection: Connection, obj: Object) {

        obj match {

            case p: Ping =>

                if(p.isReply){
                    _node.getClientByID(connection.getID).ping = connection.getReturnTripTime
                }

            case ka: KeepAlive =>

                print("_")

            case m: Message =>

                m.mtype match {

                    case MESSAGE_TYPE.CHAT =>
                        println(m.obj)
                        _node.message_processor.archiveMessage(m)

                    case MESSAGE_TYPE.ECHO_REQ =>
                        echoReqCallback(m)
                        DichClient.send(connection, new Message(MESSAGE_TYPE.ECHO_RES, node.nick, m.obj))

                    case MESSAGE_TYPE.ECHO_RES =>
                        echoResCallback(m)
                }
        }
    }
}
