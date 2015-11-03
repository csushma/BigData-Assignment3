//val review = sc.textFile("/sxc149730/input/review3.csv")
val review = sc.textFile("E:/MS_UTD/SUMMER2015/BigData Class/Homework3/datasethw3/review3.csv")
val linesReview = review.map(line=> line.split("::")).map(l => (l(2),l(20)))
val filteredDate = linesReview.filter{case (key, value) => !(value.contains("NaN"))}
val step1 = filteredDate.aggregateByKey((0.0, 0))((a, b) => (a._1 + b.toInt, a._2 + 1), (a, b) =>(a._1 + b._1, a._2 + b._2))

val avgByKey = step1.mapValues(i => i._1/i._2)

val descRatings = avgByKey.map(item => item.swap).sortByKey().map(item => item.swap)
descRatings.top(10)(Ordering.by(_._2)).foreach(println)