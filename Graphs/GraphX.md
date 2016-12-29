##Chapter 1 Two important technologies: Spark and graphs
###Types of graph data
	1.Network Graph
	2.Tree Graph
    3.RDBMS-like Data (table)
	4.adjacency matrix
	5.sparse matrix
###Properties Graph
	Spark中的一个概念，顾名思义，就是vertex和edge各带有自己的一些properties
	GraphX中顶点的表示方法 (VertexID, Properties)
	GraphX中边的表示方法 (SrcVertexID, DestVertexID, Proerties)
##Chapter 4 GraphX Basics
	1.Basic Graph Class: Graph[VD,ED]
	  VD表示顶点的属性集，可以是个类(序列化?), 可以是Tuple, Int...
	  ED同VD

	//关于顶点的一些类
	2.class VertexRDD[VD] extends RDD[(VertexId, VD)]

	//关于边的一些类
	3.class Edge[ED]: 类属性，方法
		- var attr: ED //边的属性
		- var dstId: VertexId
		- var srcId: VertexId
		//给定一个顶点，返回边的另外一个顶点
		- def otherVertexId(vid: VertexId): VertexId
		//给定一个顶点，返回边的朝向(由此顶点发出or进入)
		- def relativeDirection(vid: VertexId): EdgeDirection

	4.class EdgeTriplet[VD, ED] extends Edge[ED]
	  相比Edge[ED], EdgeTripletp[VD,ED]多了顶点的信息
	  	- var dstAttr: VD
	  	- var srcAttr: VD
	  	- def otherVertexAttr(vid: VertexId): VD
	  	- def vertexAttr(vid: VertexId): VD
	  	- def toTuple: ((VertexId, VD), (VertexId, VD), ED)
	
	5.class EdgeContext[VD, ED, A] extends AnyRef //A指的是message(消息)的类型
	  EdgeContext是指在send message时，边包含的信息以及消息的信息
	  	- def attr: ED
	  	- def dstAttr: VD
	  	- def dstId: VertexId
	  	- def srcAttr: VD
	  	- def srcId: VertexId
	  	- def sendToDst(msg: A): Unit
	  	- def sendToSrc(msg: A): Unit
	  	- def toEdgeTriplet: EdgeTriplet[VD, ED]

###图操作
	
	graph = Graph(VD, ED)
###属性操作

	1. Graph: val edges: EdgeRDD[ED]
	   Graph: val vertices: VertexRDD[VD]
       Graph: val triplets: RDD[EdgeTriplet[VD, ED]]

	   graph.triplets.collect

	2. def mapVertices[VD2](map: (VertexId, VD) => VD2): Graph[VD2, ED]
	   //将vertex的 VD类型 map 到 VD2类型
	   graph.mapVertices[Int]((vid, attr) => if (attr.flag == 1) 0 else 1)

	3. def mapEdges[ED2](map: (Edge[ED]) => ED2): Graph[VD, ED2]
	   //将edge的 ED类型 map 到 ED2类型

	4. def mapTriplets[ED2](map: (EdgeTriplet[VD, ED]) => ED2): Graph[VD, ED2]
	   //将triplets的edge属性做map操作
	   graph.mapTriplets[Int](triplet => triplet.srcAttr - triplet.destAttr)

###聚合操作

	1. def aggregateMessages[A](
			sendMsg: (EdgeContext[VD, ED, A]) => Unit,
			mergeMsg: (A, A) => A,
			tripletFields: TripletFields = TripletFields.All
	   ): VertexRDD[A]
	   //操作用户自定义的sendMeg函数到每一个 EdgeTriplet上，
	   //然后对每一个vertex使用mergeMsg聚合得到的msg
	   val inDeg: RDD[(VertexId, Int)] =
		  rawGraph.aggregateMessages[Int](ctx => ctx.sendToDst(1), _ + _)

	2. @Deprecated //虽然废弃，但是api里还在使用
	   def mapReduceTriplets[A](
			mapFunc: (EdgeTriplet[VD, ED]) ⇒ Iterator[(VertexId, A)],
			reduceFunc: (A, A) ⇒ A,
			activeSetOpt: Option[(VertexRDD[_], EdgeDirection)] = None
	   ): VertexRDD[A]
	   //基本方法使用和aggregateMessages使用是一致的，自定义func不太一样
	   val rawGraph: Graph[(),()] = Graph.textFile("twittergraph")
	   val inDeg: RDD[(VertexId, Int)] =
			mapReduceTriplets[Int](et => Iterator((et.dstId, 1)), _ + _)
	

