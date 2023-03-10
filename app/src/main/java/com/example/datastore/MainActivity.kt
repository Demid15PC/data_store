package com.example.datastore

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.datastore.datastore.StoreUserEmail
import com.example.datastore.datastore.User
import com.example.datastore.ui.theme.DataStoreTheme

class MainActivity : ComponentActivity() {
       override fun onCreate(savedInstanceState: Bundle?) {
              super.onCreate(savedInstanceState)
              setContent {
                     DataStoreTheme {
                            // A surface container using the 'background' color from the theme
                            Surface(
                                   modifier = Modifier.fillMaxSize(),
                                   color = MaterialTheme.colors.background
                            ) {
                                   MainScreen()
                            }
                     }
              }
       }
}

@Composable
fun MainScreen() {
       // context
       val context = LocalContext.current
       // scope
       val scope = rememberCoroutineScope()
       // datastore Email
       val dataStore = StoreUserEmail(context)
       // get saved email

       var login by remember { mutableStateOf("") }
       var password by remember { mutableStateOf("") }

       Column(modifier = Modifier.fillMaxSize()) {
              Text(
                     modifier = Modifier
                            .padding(16.dp, top = 30.dp),
                     text = "Email",
                     color = Color.Gray,
                     fontSize = 12.sp
              )
              //email field
              OutlinedTextField(
                     modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp)
                            .fillMaxWidth(),
                     value = login,
                     onValueChange = { login = it },
              )
              //email field
              OutlinedTextField(
                     modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp)
                            .fillMaxWidth(),
                     value = password,
                     onValueChange = { password = it },
              )
              Spacer(modifier = Modifier.height(120.dp))
              // save button
              Button(
                     modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(start = 16.dp, end = 16.dp),
                     onClick = {
                                   val user = User(login, password)
                                   dataStore.saveUser(user)
                     },
              ) {
                     // button text
                     Text(
                            text = "Save",
                            color = Color.White,
                            fontSize = 18.sp
                     )
              }
              Spacer(modifier = Modifier.height(100.dp))
              Button(
                     modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(start = 16.dp, end = 16.dp),
                     onClick = {
                            val user = dataStore.getUser()
                            Toast.makeText(context, "${user.login} - ${user.password} ", Toast.LENGTH_SHORT).show()
                     },
              ) {
                     // button text
                     Text(
                            text = "Get",
                            color = Color.White,
                            fontSize = 18.sp
                     )
              }
       }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
       DataStoreTheme {
              MainScreen()
       }
}