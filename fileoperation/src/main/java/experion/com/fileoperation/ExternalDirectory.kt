package experion.com.fileoperation

import android.content.Context
import android.os.Environment
import org.jetbrains.anko.toast
import java.io.File

/*Class to create an external Directory*/
class ExternalDirectory(context: Context, writeExternalDirectoryCheck: Boolean) {
    var writeExternalDirectoryCheck: Boolean


    init {
        this.writeExternalDirectoryCheck = writeExternalDirectoryCheck
    }

    var storageDirectory: File? = null
    var context: Context = context

    /*Function to make an external directory
    * Passing directory name as the parameter
    * directoryName is a File which contains the whole path of the directory about create*/
    fun makeExternalDirectory(directoryName: String): File? {
        if (writeExternalDirectoryCheck) {
            storageDirectory = Environment.getExternalStoragePublicDirectory("/" + directoryName + "/")
            if (storageDirectory!!.exists()) {

                context.toast(Constant.DIRECTORY_EXIST)
                storageDirectory == null

            } else {
                storageDirectory?.mkdir()

            }
        } else {
            context.toast(Constant.WRITE_EXTERNAL_DIRECTORY_PERMISSION_NOT_GRANDED)
            storageDirectory == null
        }

        return storageDirectory

    }

    /*To access a particular directory we can use this function
    * directoryName is the name of directory which we wish to open*/
    fun getExtrenalDirectory(directoryName: String): File? {
        if (writeExternalDirectoryCheck) {
            storageDirectory = Environment.getExternalStoragePublicDirectory("/" + directoryName + "/")
            if (!storageDirectory!!.exists()) {
                context.toast(Constant.DIRECTORY_NOT_AVAILABLE)
                storageDirectory == null
            }

        } else {
            context.toast(Constant.WRITE_EXTERNAL_DIRECTORY_PERMISSION_NOT_GRANDED)
            storageDirectory == null
        }
        return storageDirectory
    }
}