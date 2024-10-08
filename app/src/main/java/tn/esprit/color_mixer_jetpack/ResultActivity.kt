package tn.esprit.color_mixer_jetpack

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tn.esprit.color_mixer_jetpack.ui.theme.ColormixerjetpackTheme

class ResultActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val result = intent.getStringExtra(RESULT)!!
        val name = intent.getStringExtra(NAME)!!
        setContent {
            MyResult(result,name)
        }
    }
}


@Composable
fun Layout(result:String){
    val color:Color
    val image:Int

    if (result== SUCCESS) {
        color = Color.Green
        image = R.drawable.ic_success
    }
    else{
        color = Color.Red
        image= R.drawable.ic_failure
    }

    Box(
        modifier = Modifier
            .size(393.dp)
            .background(color = color),
        contentAlignment = Alignment.Center

    ){
        Column (horizontalAlignment = Alignment.CenterHorizontally){
        Image(
            painter = painterResource(id = image),
            contentDescription = "Description of the image",
            modifier = Modifier.size(150.dp)
        )
        Text(
            text = result,
            fontSize = 24.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold

        ) }
    }
}


@Composable
fun MyResult(result: String, name:String){

    val context = LocalContext.current as? Activity
    Column (horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        val msg:String
        val msg2:String
        if (result== SUCCESS) {
            msg = "Congratulation "
            msg2 = "Your answer is correct!"
        }

        else {
            msg = "I m sorry "
            msg2 = "Your answer is wrong!"
        }

        Layout(result)

        Text(
            text = msg+name,
            fontSize = 24.sp,
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold


        )
        Text(
            text = msg2,
            fontSize = 24.sp,
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold


        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {cbPurple=false
                cbGreen=false
                cbOrange=false
               
                context?.finish() },
            modifier = Modifier.fillMaxWidth().padding(20.dp,20.dp,20.dp,50.dp),

        ) {
            Text(
                text = "Quit",
                color= Color.White,
                fontSize = 20.sp
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewResult(){
    ColormixerjetpackTheme{
        MyResult(result="result",name="name")

    }

}