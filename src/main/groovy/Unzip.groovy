import org.codehaus.groovy.runtime.IOGroovyMethods

import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipInputStream

import static com.google.common.io.CharStreams.toString

class Unzip {

   static Map unzipFiles(File file) {
      def zipFile = new ZipFile(file)
      zipFile.entries().findAll({ !it.directory }).collectEntries { ZipEntry entry ->
         [entry.name, zipFile.getInputStream(entry).text]
      }
   }

   static Map unzipFiles(InputStream fin) {
      def files = [:]

      //noinspection GroovyAssignabilityCheck
      IOGroovyMethods.withStream(new ZipInputStream(fin)) { ZipInputStream zis ->
         for (ZipEntry ze = zis.nextEntry; ze != null; ze = zis.nextEntry) {
            files[ze.name] = toString(new InputStreamReader(zis))
         }
      }
      files
   }
}
