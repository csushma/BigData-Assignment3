// Spark Shell Commands
val userID = "50wPDE_Gk936RYV7i4FJhw"
val reviewFile = sc.textFile("/sxc149730/input/review3.csv")
val reviewArray = reviewFile.toArray
val businessRatedList = new scala.collection.mutable.MutableList[String]

	for(i <- 0 until reviewArray.length){
		val reviewLine = reviewArray(i).split("::");
		if(reviewLine(8) == userID && reviewLine(20) == "4") {
			businessRatedList += reviewLine(2)	
		}
	}

//Reading Item similarity Matrix
val simMatrix = sc.textFile("/sxc149730/output/indicator-matrix/part-00000")
val iArray = simMatrix.toArray
val itemMap = new scala.collection.mutable.HashMap[String,List[String]].withDefaultValue(Nil)

	for(i <- 0 until iArray.length){
		val itemLine = iArray(i).split("\\s+");
		val itemKey = itemLine(0);
		if(itemLine.length > 1){
			val commaSplit = itemLine(1).split(",");
			for( j <- 0 until commaSplit.length){
				val businessSplit = commaSplit(j).split(":");
				val businessID = businessSplit(0);
				itemMap(itemKey) ::= businessID;
			}
		}
	}

for(i <- 0 until businessRatedList.length){
	val businessRow = businessRatedList(i);	
	println("");
	println(businessRow+" ====> ");	
		for(i <- 0 until itemMap(businessRow).length){
			print(itemMap(businessRow)(i) + " ");
		}	
}
