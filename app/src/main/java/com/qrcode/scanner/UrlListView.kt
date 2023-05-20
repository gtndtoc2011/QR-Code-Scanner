package com.qrcode.scanner

import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun UrlListView(modifier: Modifier, list: List<String>){

    val context = LocalContext.current
    val onListItemClick = {text: String ->
        Toast.makeText(
            context,
            text,
            Toast.LENGTH_SHORT
        ).show()
    }

    LazyColumn(
        modifier = modifier,
        //reverseLayout = true
    ){
        itemsIndexed(list) { index, item ->
            UrlItem(index = index, str = item, onItemClick = onListItemClick)
        }
    }
}