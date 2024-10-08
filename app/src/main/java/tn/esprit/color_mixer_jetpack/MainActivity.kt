package tn.esprit.color_mixer_jetpack


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tn.esprit.color_mixer_jetpack.ui.theme.ColormixerjetpackTheme

const val RED = "RED"
const val BLUE = "BLUE"
const val YELLOW = "YELLOW"
const val PURPLE = "PURPLE"
const val GREEN = "GREEN"
const val ORANGE = "ORANGE"

var cbRed : Boolean = false
var cbBlue : Boolean = false
var cbYellow : Boolean = false
var cbPurple : Boolean = false
var cbOrange : Boolean = false
var cbGreen : Boolean = false



const val NAME = "NAME"
const val MIXED_COLOR = "COLOR"
const val COLOR1 = "COLOR 1"
const val COLOR2 = "COLOR 2"
const val RESULT = "RESULT"
const val SUCCESS = "SUCCESS"
const val FAILED = "FAILED"

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingPreview()
        }
    }
}

@Composable
fun RabioButtonitem(){
    val checkedStateB = remember { mutableStateOf(false) }
    val checkedStateY = remember { mutableStateOf(false) }
    val checkedStateR = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth().padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Checkbox(
            checked = checkedStateB.value,
            onCheckedChange = { checkedStateB.value = it
                cbBlue= checkedStateB.value

            }
        )
        ColorRadioGroup(Color.Blue)
        Checkbox(
            checked = checkedStateY.value,
            onCheckedChange = { checkedStateY.value = it
                cbYellow=checkedStateY.value
            }
        )
        ColorRadioGroup(Color.Yellow)

        Checkbox(
            checked = checkedStateR.value,
            onCheckedChange = { checkedStateR.value = it
                cbRed=checkedStateR.value
            }
        )
        ColorRadioGroup(Color.Red)
    }
}

@Composable
fun ColorRadioGroup(color1: Color){

    Box(
        modifier = Modifier
            .size(50.dp)
            .background(color1)
            .padding(end = 10.dp)
        )
}

@Composable
fun TxtChoose(){//i want it in the center of the page
    Text(text = "Choose 2 colors",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth().padding(20.dp),
        textAlign = TextAlign.Center)
}

@Composable
fun FullNameTextField(nameState:MutableState<String>) {


    OutlinedTextField(
        value = nameState.value,
        onValueChange = {
            nameState.value = it

        },
        modifier = Modifier.fillMaxWidth().padding(20.dp),
        textStyle = androidx.compose.ui.text.TextStyle(
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        ),
        label = { Text("Fullname") },
        singleLine = true
    )
}

@Composable
fun FullNameText() {
    Text(
        text = "Enter Your Name",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth().padding(20.dp, top = 50.dp),
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val nameState = remember { mutableStateOf("") }
    val context = LocalContext.current

    ColormixerjetpackTheme {
        Column(

            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            FullNameText()
            FullNameTextField(nameState)
            Spacer(modifier = Modifier.weight(1f))
            TxtChoose()
            RabioButtonitem()

            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    if(nameState.value.isEmpty()){
                        Toast.makeText(context , "You must enter your name !", Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    var a=0
                    var b=0
                    var c=0

                    if (cbYellow) a=1
                    if (cbBlue) b=1
                    if (cbRed) c=1
                    if (a+b+c !=2 ) {
                        Toast.makeText(context , "You must choose 2 colors!", Toast.LENGTH_SHORT).show()
                        return@Button
                    } else {
                        if(cbBlue&& cbRed){
                            val intent = Intent(context, AnswerActivity::class.java ).apply{
                                putExtra(NAME,nameState.value)
                                putExtra(COLOR1, BLUE)
                                putExtra(COLOR2, RED)
                                putExtra(MIXED_COLOR, PURPLE)
                            }
                            context.startActivity(intent)

                        }else if(cbBlue){
                            val intent = Intent(context, AnswerActivity::class.java ).apply{
                                putExtra(NAME,nameState.value)
                                putExtra(COLOR1, BLUE)
                                putExtra(COLOR2, YELLOW)
                                putExtra(MIXED_COLOR, GREEN)

                            }
                            context.startActivity(intent)
                        }else{
                            val intent = Intent(context, AnswerActivity::class.java ).apply{
                                putExtra(NAME,nameState.value)
                                putExtra(COLOR1, YELLOW)
                                putExtra(COLOR2, RED)
                                putExtra(MIXED_COLOR, ORANGE)

                            }
                            context.startActivity(intent)


                        }


                    }







                          },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, bottom = 50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text("Mix",
                    fontSize = 20.sp)
            }

        }
    }
}
