package com.example.rfbank.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.rfbank.R
import com.example.rfbank.model.User

@Composable
fun UserEntry(user: User) {
    Column {
        Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)) {
            Image(
                painter = rememberAsyncImagePainter(user.picture.thumbnail),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )
            Column(Modifier.padding(horizontal = 16.dp)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = user.name.last + " " + user.name.first,
                        color = Color.Gray
                    )
                    Text("15:38", color = Color.Gray)
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = user.registered.age.toString() + " years from " + user.nat,
                        color = Color.Gray
                    )
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(R.drawable.star),
                            contentDescription = "",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Gray // Ensures the star outline is black
                        )
                    }
                }
            }
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxSize(), color = Color.LightGray
        )
    }
}