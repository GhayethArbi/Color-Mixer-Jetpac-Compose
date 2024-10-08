package tn.esprit.color_mixer_jetpack

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
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




class AnswerActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve the values from the Intent
        val color1 = intent.getStringExtra(COLOR1) ?: "Unknown"
        val color2 = intent.getStringExtra(COLOR2) ?: "Unknown"
        val name = intent.getStringExtra(NAME) ?: "Unknown"
        val correctColor = intent.getStringExtra(MIXED_COLOR) ?: "Unknown"
        Log.d("AnswerActivity", "Received Name: $name, Color1: $color1, Color2: $color2, MixedColor: $correctColor")
        enableEdgeToEdge()

        // Pass the retrieved values to the Screen composable
        setContent {
            Screen(name, color1, color2, correctColor)

        }
    }
}

@Composable
fun ColorRadioGroupA(color1: Color){

    Box(
        modifier = Modifier
            .size(50.dp)
            .background(color1)
            .padding(end = 10.dp)
    )
}

@Composable
fun RabioButtonitemA(checkedStateP:MutableState<Boolean>,checkedStateG:MutableState<Boolean>,checkedStateO:MutableState<Boolean>){


    Row(
        modifier = Modifier.fillMaxWidth().padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Checkbox(
            checked = checkedStateP.value,
            onCheckedChange = { checkedStateP.value = it
                cbPurple= checkedStateP.value

            }
        )
        ColorRadioGroupA(Color(0xFF800080))
        Checkbox(
            checked = checkedStateG.value,
            onCheckedChange = { checkedStateG.value = it
                cbGreen=checkedStateG.value
            }
        )
        ColorRadioGroupA(Color.Green)

        Checkbox(
            checked = checkedStateO.value,
            onCheckedChange = { checkedStateO.value = it
                cbOrange=checkedStateO.value
            }
        )
        ColorRadioGroupA(Color(0xFFFFA500))
    }
}






@Composable
fun Screen(name: String, color1: String, color2: String, mixedColor: String) {
    val checkedStateP = remember { mutableStateOf(false) }
    val checkedStateG = remember { mutableStateOf(false) }
    val checkedStateO = remember { mutableStateOf(false) }
    var answer:String
    val context = LocalContext.current
    val context1 = LocalContext.current as? Activity
    ColormixerjetpackTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "You chose $color1 and $color2",
                fontSize=24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                textAlign = TextAlign.Center

            )
            Spacer(modifier = Modifier.weight(1f))

            RabioButtonitemA(checkedStateP, checkedStateG,checkedStateO)
            Spacer(modifier = Modifier.weight(2f))
            Button( onClick = {
                if(cbGreen && cbPurple|| cbGreen && cbOrange|| cbOrange && cbPurple){
                    Toast.makeText( context, "You most choose only one color!", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                if(!cbGreen && !cbPurple && !cbOrange) {
                    Toast.makeText( context, "You most choose at least one color!", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                answer = if(cbGreen) GREEN
                else if(cbPurple) PURPLE
                else  ORANGE

                val intent = Intent(context, ResultActivity::class.java)
                if(answer!=mixedColor)
                intent.apply{
                    putExtra(NAME, name)
                    putExtra(RESULT, FAILED)
                }
                else
                    intent.apply {
                        putExtra(NAME, name)
                        putExtra(RESULT,SUCCESS)
                    }

                context.startActivity(intent)

                context1?.finish()



            },
                modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp,20.dp,20.dp, bottom = 50.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text("Mix",
                    fontSize = 20.sp)
            }
        }
    }
}





@Preview(showBackground = true)
@Composable
fun PreviewScreen() {
    Screen(name = "John", color1 = "Red", color2 = "Blue", mixedColor = "Purple")
   
}
