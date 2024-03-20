import com.myGatlingTest.JavaClass
import io.gatling.core.feeder.Feeder

object MyJavaFeeder extends Feeder[String] {

  // Implement the `Feeder` interface methods
  override def hasNext: Boolean = true

  override def next(): Map[String, String] = {
//    val data = MyJavaClass.generateData() // Invoke your Java method to generate data
    val javaObject = new JavaClass()
    val data = javaObject.encode("test begins....");
    Map("data" -> data) // Map the data to a key-value pair in the feeder
  }
}