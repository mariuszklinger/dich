package pl.mariuszklinger.core.archive

import scala.collection.mutable
import pl.mariuszklinger.core.msgs.Message

class MessageLog {

    val message_map = new mutable.HashMap[String, Message]()
    var message_buffer = new mutable.LinkedList[Message]()

    def addMessage(m: Message){

        message_map.synchronized{
            message_map.put(MessageProcessor.getHash(m), m)
            message_buffer = message_buffer ++ mutable.LinkedList[Message](m)
        }
    }

    def getLatestMessages(c: Int = 100): Seq[Message] = {
        message_buffer.slice(0, c)
    }
}
