package pl.mariuszklinger.core.tests

object A{
    var str = ""
}

class A(_str: String) {
    A.str = _str

    def getSecret(): String = {
        A.str
    }
}