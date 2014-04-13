
import pl.mariuszklinger.core.Node

object Main {

    def main(args: Array[String]){
        val n1 = new Node("John", 8081)
        val n2 = new Node("Luke", 8083)

        n1.run()
        n2.run()

        n2.connect("localhost", 8081)
        n2.sendEchoRequest("hello!")
    }
}
