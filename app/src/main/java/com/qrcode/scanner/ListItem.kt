package com.qrcode.scanner

import android.media.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job

@Composable
fun ListItem(index: Int, str: String, onItemClick: (String) -> Unit) {
    Card(elevation = 4.dp,
        modifier = Modifier
            .padding(8.dp)
            .clickable{onItemClick(str)}
    ){
      Row(verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier.padding(8.dp)){
          Text("$index: $str")
      }
    }
}