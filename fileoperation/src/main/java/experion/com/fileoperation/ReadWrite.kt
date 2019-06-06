package experion.com.fileoperation

import android.app.usage.ExternalStorageStats
import android.content.Context
import android.webkit.MimeTypeMap
import org.jetbrains.anko.toast
import java.io.File
import java.io.FileOutputStream

/*class to hand read and write function of a file*/
class ReadWrite(context: Context, writeExternalStorageCheck: Boolean, readExternalStorageCheck: Boolean) {
    private var context: Context = context
    private var writeExternalStorageCheck: Boolean
    private var readExternalStorageCheck: Boolean

    init {
        this.writeExternalStorageCheck = writeExternalStorageCheck
        this.readExternalStorageCheck = readExternalStorageCheck
    }

    /*Function help to read the file in the form of bytes and will return a byte array
    * we need to pass both file directory and file name to covert it into bytes*/
    fun readFile(directoryPath: File, fileName: String): ByteArray? {
        var fileInBytes: ByteArray? = null
        if (writeExternalStorageCheck && readExternalStorageCheck) {
            val file: File = File(directoryPath.toString() + File.separator + fileName)
            if (file.exists()) {
                var fileExtention = MimeTypeMap.getFileExtensionFromUrl(file.toString())
                fileInBytes = file.readBytes()
            } else {
                context.toast(Constant.FILE_NOT_AVILABLE)
            }

        } else {
            context.toast(Constant.PROVIDE_NEEDED_PERMISSION)
        }

        return fileInBytes
    }
    /*If we have some file content which we need to write into a new file we can use this function
     * we need to pass  file content in bytes ,directory in which we need to write the content and the file name */

    fun writeIntoNewFile(fileContentInBytes: ByteArray, directoryPath: File, fileName: String) {
        if (writeExternalStorageCheck && readExternalStorageCheck) {
            val newFile: File = File(directoryPath.toString() + File.separator + fileName)
            if (newFile.exists()) {
                context.toast(Constant.FILE_ALREADY_EXIST)
            } else {
                File(directoryPath.toString() + File.separator + fileName).writeBytes(fileContentInBytes)
                context.toast(Constant.WRITE_SUCCESSFULLY)

            }
        } else {
            context.toast(Constant.PROVIDE_NEEDED_PERMISSION)

        }

    }
/*if we have some content in byte to append with an existing file we can use this
* We need to pass the file content in byte ,directory path,and file name
* This appending property is applicable only for text files which having the extensions like txt,json,xml,csv */

    fun appendContentToFile(filecontentInBytes: ByteArray, directoryPath: File, fileName: String) {
        if (writeExternalStorageCheck && readExternalStorageCheck) {
            val file: File = File(directoryPath.toString() + File.separator + fileName)
            var fileExtention = MimeTypeMap.getFileExtensionFromUrl(file.toString())
            if ((fileExtention == Constant.TXT) || (fileExtention == Constant.JSON) || (fileExtention == Constant.XML) || (fileExtention == Constant.CSV)) {
                if (file.exists()) {
                    var fileOutputStream: FileOutputStream =
                        FileOutputStream(directoryPath.toString() + File.separator + fileName, true)
                    fileOutputStream.write(filecontentInBytes)
                    fileOutputStream.close()

                } else {
                    context.toast(Constant.FILE_NOT_AVILABLE)
                }

            } else {
                context.toast(Constant.DOESNT_SUPPORTS_APPEND)
            }

        } else {
            context.toast(Constant.PROVIDE_NEEDED_PERMISSION)
        }


    }

    /*This function can use when we need to change the whole content of a file
    * So the resultant file will be the same as before but the whole content will be new
    * we need to pass the content of file in bytes ,directory path and the file name*/


    fun replaceContentOfFile(filecontentInBytes: ByteArray, directoryPath: File, fileName: String) {
        if (readExternalStorageCheck && writeExternalStorageCheck) {
            val file: File = File(directoryPath.toString() + File.separator + fileName)
            var fileExtention = MimeTypeMap.getFileExtensionFromUrl(file.toString())

            if (file.exists()) {
                var fileOutputStream: FileOutputStream =
                    FileOutputStream(directoryPath.toString() + File.separator + fileName, false)
                fileOutputStream.write(filecontentInBytes)
                fileOutputStream.close()

            } else {
                context.toast(Constant.FILE_NOT_AVILABLE)
            }
        } else {
            context.toast(Constant.PROVIDE_NEEDED_PERMISSION)
        }


    }

}
