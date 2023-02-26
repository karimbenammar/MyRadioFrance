package com.example.myradiofrance.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myradiofrance.R
import com.example.myradiofrance.domain.model.Shows
import com.example.myradiofrance.ui.theme.Brown
import com.example.myradiofrance.ui.theme.BrownDark
import com.example.myradiofrance.ui.theme.BrownDarkest

@Composable
fun ShowItem(show: Shows.ShowEdge.Show) {
    Box(
        modifier = Modifier
            .padding(end = 8.dp, start = 8.dp, bottom = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = 2.dp,
            shape = RoundedCornerShape(corner = CornerSize(6.dp))
        ) {
            Row(
                modifier = Modifier
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                BrownDarkest.copy(alpha = 0.8f),
                                BrownDark.copy(alpha = 0.8f),
                                Brown.copy(alpha = 0.8f)
                            ),
                            start = Offset(0f, Float.POSITIVE_INFINITY),
                            end = Offset(Float.POSITIVE_INFINITY, 0f)
                        )
                    )
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterVertically),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp),
                        painter = painterResource(id = R.drawable.browse_podcasts_24),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Column {
                        Text(
                            text = show.title,
                            style = MaterialTheme.typography.button,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        if (show.standFirst.isNotBlank()) {
                            Text(
                                text = show.standFirst,
                                style = MaterialTheme.typography.caption,
                                color = Color.White,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.horizontalScroll(rememberScrollState())
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun ShowItemPreview() {
    ShowItem(show = Shows.ShowEdge.Show(
        id = "001179a9-fe05-4a03-a0a0-7c360539db54_4",
        title = "MAXXI Classique",
        url = "https://www.francemusique.fr/francemusique/podcasts/maxxi-classique",
        standFirst = ""
    ))
}
