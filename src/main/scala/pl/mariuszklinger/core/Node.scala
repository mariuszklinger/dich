package pl.mariuszklinger.core

import java.io.IOException
import org.apache.log4j.Logger

import pl.mariuszklinger.core.network.{DichClient, DichServer}
import pl.mariuszklinger.core.msgs.{MESSAGE_TYPE, Message}
import pl.mariuszklinger.core.tools.NeighboursQueue

class Node(_nick:String, _port:Int) {

    private val log = Logger.getLogger(this.getClass())

    private var neighbours = NeighboursQueue
    var nick = _nick
    var port = _port

    def this() = this("John", 8081)

    val dich_server = new DichServer(this)

    def run(){
        dich_server.run(port)
    }

    def connect(host:String, port:Int): Boolean = {

        var client = new DichClient(this)
        client.run(host, port)

        try {
            neighbours += client
        }
        catch {
            case e: IOException => false
        }

        true
    }

    private def _send(m:Message){
        neighbours.foreach((client:DichClient) => {
            client.send(m)
        })
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
}