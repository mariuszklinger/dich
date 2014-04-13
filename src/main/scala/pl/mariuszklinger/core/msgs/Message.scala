package pl.mariuszklinger.core.msgs

object MESSAGE_TYPE {

    type MT = Byte

    val ECHO_REQ:MT = -2.toByte
    val ECHO_RES:MT = -1.toByte

    val CHAT:MT = 1.toByte
    val NOTICE:MT = 2.toByte
}

class Message(_mtype:MESSAGE_TYPE.MT, _o:Object) {
    var obj = _o
    val mtype = _mtype

    def this() = this(MESSAGE_TYPE.CHAT, "= DEFAULT =")
}
