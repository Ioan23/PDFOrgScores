package com.example.pdforgscores

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pdforgscores.ui.theme.PDFORGScoresTheme
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PDFORGScoresTheme {

                val db = FirebaseFirestore.getInstance()
                val user: MutableMap<String, Any> = HashMap()

                user["firstName"] = "John"
                user["lastName"] = "Smith"


                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    db.collection("users")
                        .add(user)
                        .addOnSuccessListener {
                            Log.d( "FB", "onCreate: ${it.id}")

                        }.addOnFailureListener {
                            Log.d( "FB", "onCreate: $it")

                        }

                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PDFORGScoresTheme {
        Greeting("Android")
    }
}