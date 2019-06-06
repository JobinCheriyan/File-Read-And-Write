package experion.com.readandwritekotlin

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import experion.com.fileoperation.ExternalDirectory
import experion.com.fileoperation.InternalDirectory
import experion.com.fileoperation.ReadWrite
import java.io.File
import java.io.FileOutputStream

/*Class which demonstrate read and write operation of a file*/
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* To create an external directory do the following steps
        * First create an object of external directory by passing context and write external storage permission check*/
        var externalDirectory: ExternalDirectory = ExternalDirectory(
            this,
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        )
        //using the externalDirectory object we can create our new directory by passing its name
        externalDirectory.makeExternalDirectory("new")
        //using the externalDirectory object we can access the directory which already exist
        var fileDirectory: File? = externalDirectory.getExtrenalDirectory("new")
        /*To create an internal directory we can do the following steps
        * first create an object of InternalDirectory by pass context and write external storage permission check*/
        var internalDirectory: InternalDirectory = InternalDirectory(
            this,
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        )
        /*Use the getDirectory function to create a new directory in internal storage*/
        internalDirectory.getDirectory("New_Directory")
        /*instead of this custom directory android providing some defaut pre defined directories
        * To access those Internal Directories use the following line of code
        *  getExternalFilesDir(Environment.DIRECTORY_ALARMS)*/


        /*To start with read and write operations of file we should create an object of ReadWrite class
        * To create and object we need to pass the conext, read and write external storage permission check*/

        var readWrite: ReadWrite = ReadWrite(
            this,
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED,
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        )


        //To read a file in the form of byte we can use the function readFile as follows
        var fileInBytes: ByteArray? = readWrite.readFile(fileDirectory!!, "cy.pdf")
        //To write content into a new file we can use writeIntoNewFile function as follows
        readWrite.writeIntoNewFile(fileInBytes!!, fileDirectory, "boom.txt")
        /*To append the content of a particular file we can use the following line
        * But this property is applicable only for text file which having the extensions like txt,xml,json,csv*/
        var filePath: String = Environment.getExternalStoragePublicDirectory("new").toString()
        val file: File = File(filePath + File.separator + "write.txt")
        var bytes: ByteArray = file.readBytes()
        for (byte in bytes) {
            print(byte.toChar())
        }
        readWrite.appendContentToFile(bytes, fileDirectory!!, "boom.txt")
        /*To repalce  the content of a particular file we can use the following line*/
        readWrite.replaceContentOfFile(fileInBytes!!, fileDirectory, "jc.pdf")




    }
}
