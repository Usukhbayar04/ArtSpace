package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
private fun ArtSpaceApp() {
    // Initial value set to 0.
    var artWorkIndex by remember { mutableStateOf(0) }
    // If the index is incremented to 4, reset the value to 0.
    if (artWorkIndex == 4) artWorkIndex = 0
    // If the index is decremented to -1, reset the value to 3.
    if (artWorkIndex == -1) artWorkIndex = 3

    val artWork: Int
    val artWorkDesc: Int
    val artWorkName: Int
    val artWorkReleaseYear: Int
    val artWorkPre: Int

    // If the index matches, perform the following operations.
    when (artWorkIndex) {
        0 -> {
            artWork = R.drawable.num_front
            artWorkDesc = R.string.num_desc
            artWorkName = R.string.num_title
            Spacer(Modifier.height(10.dp))
            artWorkPre = R.string.num_pre
            artWorkReleaseYear = R.string.num_rel_year
        }
        1 -> {
            artWork = R.drawable.ufe
            artWorkDesc = R.string.ufe_desc
            artWorkName = R.string.ufe_title
            Spacer(Modifier.height(10.dp))
            artWorkPre = R.string.ufe_pre
            artWorkReleaseYear = R.string.ufe_rel_year
        }
        2 -> {
            artWork = R.drawable.must_about
            artWorkDesc = R.string.must_desc
            artWorkName = R.string.must_title
            Spacer(Modifier.height(10.dp))
            artWorkPre = R.string.must_pre
            artWorkReleaseYear = R.string.must_rel_year
        }
        else -> {
            artWork = R.drawable.msue
            artWorkDesc = R.string.msue_desc
            artWorkName = R.string.msue_title
            Spacer(Modifier.height(10.dp))
            artWorkPre = R.string.msue_pre
            artWorkReleaseYear = R.string.msue_rel_year
        }
    }

    // Place artwork on the UI.
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.white)),
            shape = RectangleShape,
        ) {
            Image(
                painter = painterResource(id = artWork),
                contentDescription = stringResource(id = artWorkDesc),
                modifier = Modifier.padding(30.dp)
            )
        }

        // Limit the size of the spacer.
        BoxWithConstraints {
            if(maxWidth < 413.dp) {
                Spacer(Modifier.height(76.dp))
            } else if (maxWidth < 1281.dp) {
                Spacer(Modifier.height(20.dp))
            }
        }

        // Display information
        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(colorResource(id = R.color.black), fontWeight = FontWeight.Light, fontSize = 24.sp)
                ) {
                    append(stringResource(id = artWorkName))
                }
                append("\n")

                withStyle(
                    style = SpanStyle(colorResource(id = R.color.black), fontWeight = FontWeight.Bold, fontSize = 20.sp)
                ) {
                    append(stringResource(id = artWorkPre))
                }

                withStyle(
                    style = SpanStyle(colorResource(id = R.color.black),  fontWeight = FontWeight.Light, fontSize = 20.sp)
                ) {
                    append(stringResource(id = artWorkReleaseYear))
                }
            },

            modifier = Modifier
                .background(colorResource(id = R.color.white))
                .padding(16.dp),
        )

        Spacer(Modifier.height(28.dp))

        // Place the buttons
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            EditButtons(
                onClick = { artWorkIndex-- },
                buttonColor = R.color.purple_700,
                buttonText = R.string.previous_bottom,
            )

            EditButtons(
                onClick = { artWorkIndex++ },
                buttonColor = R.color.purple_700,
                buttonText = R.string.next_bottom,
            )
        }
    }

}

@Composable
fun EditButtons(onClick: () -> Unit, @StringRes buttonText: Int, @ColorRes buttonColor: Int, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(colorResource(id = buttonColor)),
        modifier = modifier.width(140.dp),
    ) {
        Text(text = stringResource(id = buttonText))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}