package pl.mariuszklinger.core.msgs

import pl.mariuszklinger.core.msgs.MESSAGE_TYPE.MESSAGE_TYPE

object MESSAGE_TYPE extends Enumeration {
    type MESSAGE_TYPE = Value

    val CHAT = Value(1)
    val NOTICE = Value(2)
    val ECHO = Value(3)
}

class Message(_mtype:Int, _o:Object) {
    var obj = _o
    val mtype = _mtype

    def this() = this(1, null)
    //print(TYPE.CHAT.id)
}
