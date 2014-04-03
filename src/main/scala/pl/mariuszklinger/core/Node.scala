package pl.mariuszklinger.core

import pl.mariuszklinger.core.network.{DichClient, DichServer}
import java.io.IOException
import org.apache.log4j.Logger
import pl.mariuszklinger.core.msgs.{MESSAGE_TYPE, Message}
import javax.xml.ws.Service.Mode
import scala.collection.mutable
import pl.mariuszklinger.core.tools.NeighboursQueue
import pl.mariuszklinger.core.archive.MessageProcessor

class Node(_nick:String, port:Int) {

    private val log = Logger.getLogger(this.getClass())

    private var neighbours = NeighboursQueue
    private val nick = _nick

    val server = new DichServer()
    server.run(port)

    def connect(host:String, port:Int): Boolean = {

        var client = new DichClient()
        client = new DichClient()
        client.run(host, port)

        try {
            neighbours += client
        }
        catch {
            case e: IOException => false
        }

        log.info("connected to " + host + ":" + port)
        true
    }

    def sendText(t:String){
        neighbours.foreach((client:DichClient) => {
            client.send(new Message(MESSAGE_TYPE.CHAT.id, t))
        })
    }

    def sendEcho(t:String){
        neighbours.foreach((client:DichClient) => {
            client.send(new Message(MESSAGE_TYPE.ECHO.id, t))
        })
    }
}