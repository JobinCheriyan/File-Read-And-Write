package experion.com.fileoperation

import android.content.Context
import android.os.Environment
import org.jetbrains.anko.toast
import java.io.File
/*This class which help us to create an internal directory*/
class InternalDirectory(context: Context, writeExternalDirectoryCheck: Boolean) {

    private var context: Context
    private var writeExternalDirectoryCheck: Boolean
    private var storageDirectory: File? = null

    init {
        this.writeExternalDirectoryCheck = writeExternalDirectoryCheck
        this.context = context
    }

    /*Function help us to get an internal directory*/
    fun getDirectory(directoryname: String): File? {
        if (writeExternalDirectoryCheck) {
            storageDirectory = context.getExternalFilesDir(directoryname)

        }else
        {
            context.toast(Constant.WRITE_EXTERNAL_DIRECTORY_PERMISSION_NOT_GRANDED)
            storageDirectory == null
        }
        return storageDirectory
    }
}





