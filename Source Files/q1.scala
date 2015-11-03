print("Please enter your input : " )
val address = Console.readLine

val business = sc.textFile("E:/MS_UTD/SUMMER2015/BigData Class/Homework3/datasethw3/business3.csv")
val linesBusiness = business.filter(line => line.contains(address)).map(line=> line.split("::")).map(l =>l(2))
linesBusiness.collect()

//val business = sc.textFile("/sxc149730/input/business3.csv")