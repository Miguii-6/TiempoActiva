package com.example.tiempoactiva


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tiempoactiva.ui.theme.TiempoActivaTheme

class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity"
    private var startTimeMillis: Long = 0L
    private var totalTimeActiveMillis: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TiempoActivaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }

    private fun updateUI() {
        val totalMinutes = (totalTimeActiveMillis / 60000).toInt()
        val totalSeconds = ((totalTimeActiveMillis / 1000) % 60).toInt()
        Toast.makeText(this, "El tiempo activo hasta ahora es de: $totalMinutes min $totalSeconds s", Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart function")
        startTimeMillis = System.currentTimeMillis()
    }

    override fun onPause() {
        super.onPause()
        val endTimeMillis: Long = System.currentTimeMillis()
        totalTimeActiveMillis += endTimeMillis - startTimeMillis
        Log.d(TAG, "onPause function, tiempo activa: $totalTimeActiveMillis ms")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hola $name! El tiempo que ha estado activa la aplicación anteriormente es: ",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TiempoActivaTheme {
        Greeting("Android")
    }
}