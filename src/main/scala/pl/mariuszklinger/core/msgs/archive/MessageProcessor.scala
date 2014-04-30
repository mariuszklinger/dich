package pl.mariuszklinger.core.archive

import pl.mariuszklinger.core.msgs._
import java.security.MessageDigest

object MessageProcessor{

    def getHash(m:Message): String ={
        MessageDigest.getInstance("MD5").digest((m.nick + m.obj.toString).getBytes("UTF-8")).toString
    }
}

class MessageProcessor {

    val message_log = new MessageLog

    def archiveMessage(m:Message){
        message_log.addMessage(m)
    }

}
