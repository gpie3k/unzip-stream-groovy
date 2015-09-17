import spock.lang.Specification

class UnzipTest extends Specification {

   static EXPECTED = ['README.md':'# unzip-stream-groovy']

   def file = new File('test.zip')

   def "should unzip file"() {
      expect:
      Unzip.unzipFiles(new FileInputStream(file)) == EXPECTED
   }

   def "should unzip with zip file"() {
      expect:
      Unzip.unzipFiles(file) == EXPECTED
   }
}

