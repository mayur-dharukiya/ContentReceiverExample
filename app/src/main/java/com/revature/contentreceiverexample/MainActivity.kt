package com.revature.contentreceiverexample

import android.annotation.SuppressLint
import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.revature.contentreceiverexample.ui.theme.ContentReceiverExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContentReceiverExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    UI()
                }
            }
        }
    }
}
@SuppressLint("Range")
@Composable
fun UI()
{


    var context= LocalContext.current
    var name by remember { mutableStateOf("") }
   // val strBuild = StringBuilder()
    Column() {


        Button(onClick = {


            // inserting complete table details in this text field


            // creating a cursor object of the
            // content URI
            val cursor = context.contentResolver.query(Uri.parse("content://com.revature.user.provider/users"), null, null, null, null)

            // iteration of the cursor
            // to print whole table
            if (cursor!!.moveToFirst()) {
             val strBuild = StringBuilder()
                while (!cursor.isAfterLast) {

                    strBuild.append("""
      
    ${cursor.getString(cursor.getColumnIndex("id"))}-${cursor.getString(cursor.getColumnIndex("name"))}
    """.trimIndent())
                    cursor.moveToNext()
                }
                Log.d("Data1","$strBuild")
            } else {
                Log.d("Data2","No Records Found")
            }

        }) {


            Text(text = "Show Data")
        }




    }
}


