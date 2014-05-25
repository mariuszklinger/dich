package pl.mariuszklinger.core.archive

import scala.collection.mutable.{HashMap, Stack, PriorityQueue, LinkedList}

import pl.mariuszklinger.core.msgs.Message

class MessageLog {

    val message_map = new HashMap[String, Message]()

    // stack to keep missing message hashes
    val missing_msg = new Stack[String]()

    private def msg_ordering = new Ordering[Message]{
        override def compare(x: Message, y: Message): Int = {
            if(x.parent_hc == y.msg_hash) -1 else 1
        }
    }
    var message_buffer = new PriorityQueue[Message]()(msg_ordering)

    def addMessage(m: Message){

        message_map.synchronized{

            if(message_map contains m.msg_hash){
                return
            }

            if(message_map contains m.parent_hc){
                missing_msg.push(m.parent_hc)
            }

            message_map.put(MessageProcessor.getHash(m), m)
            message_buffer = message_buffer ++ LinkedList[Message](m)
        }
    }

    def getLatestMessages(c: Int = 100): Seq[Message] = {
        message_buffer.toSeq.slice(0, c)
    }
}
