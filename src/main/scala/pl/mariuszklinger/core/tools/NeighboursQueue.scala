package pl.mariuszklinger.core.tools

import pl.mariuszklinger.core.network.DichClient
import scala.collection.mutable


object NodeOrdering extends Ordering[DichClient](){
    def compare(x:DichClient, y:DichClient) = -1
}

class NeighboursQueue extends mutable.PriorityQueue[DichClient]()(NodeOrdering){

}