
import pl.mariuszklinger.core.Node

object Main {

    def main(args: Array[String]){
        val n1 = new Node("John", 8081)
        val n2 = new Node("Luke", 8083)
        val n3 = new Node("Dave", 8087)

        n1.run()
        n2.run()
        n3.run()

        n2.connect("localhost", 8081)
        n3.connect("localhost", 8081)

        //n2.sendEchoRequest("hello!")
        //n3.sendEchoRequest("hello!")
    }
}
