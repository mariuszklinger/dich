package pl.mariuszklinger.core.archive

import scala.collection.mutable
import pl.mariuszklinger.core.msgs.Message

class MessageLog {

    val message_map = new mutable.HashMap[String, Message]()
    val message_buffer = new mutable.LinkedList[Message]()

    def addMessage(hash:String, m:Message){
        message_map.put(hash, m)
        message_buffer.append(mutable.LinkedList[Message](m))
    }

    def getLatestMessages(c:Int = 100):List[Message] = {
        return null
    }
}
