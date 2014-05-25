package pl.mariuszklinger.core

import java.io.IOException
import org.apache.log4j.Logger
import scala.collection.mutable.HashMap

import pl.mariuszklinger.core.network.{DichListener, DichClient, DichServer}
import pl.mariuszklinger.core.msgs.{MESSAGE_TYPE, Message}
import pl.mariuszklinger.core.tools.NeighboursQueue
import pl.mariuszklinger.core.archive.MessageProcessor

class Node(_nick: String, _port: Int) {

    private val log = Logger.getLogger(this.getClass())

    val nick = _nick
    val port = _port

    val dich_server = new DichServer(this)

    private val neighbours_map = new HashMap[Int, DichClient]()
    private val neighbours = new NeighboursQueue

    val listener = new DichListener(this)
    val message_processor = new MessageProcessor

    def this() = this("John", 8081)

    def run(){
        dich_server.run(port)
    }

    def connect(host:String, port:Int): Boolean = {

        try {
            val dc = new DichClient(this, host, port)
            addClient(dc)
        }
        catch {
            case e: IOException => false
        }

        true
    }

    def addClient(dc: DichClient){
        neighbours += dc
        neighbours_map += (dc.connection.getID -> dc)
    }

    def getClientByID(ID: Int): DichClient = {
        neighbours_map.get(ID).get
    }

    def sendText(t:String){
        _send(new Message(MESSAGE_TYPE.CHAT, nick, t))
    }

    def sendEchoRequest(t:String){
        _send(new Message(MESSAGE_TYPE.ECHO_REQ, nick, t))
    }

    def sendEchoResponse(t:String){
        _send(new Message(MESSAGE_TYPE.ECHO_RES, nick, t))
    }

    private def _send(m:Message){
        neighbours foreach((client:DichClient) => {
            client.send(m)
        })
    }
}