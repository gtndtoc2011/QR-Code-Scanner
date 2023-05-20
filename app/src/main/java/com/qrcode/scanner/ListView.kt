package com.qrcode.scanner

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun ListView(modifier: Modifier, list: List<String>){

    val listState = rememberLazyListState()
    val context = LocalContext.current

    val onListItemClick = {text: String ->
           Toast.makeText(
                context,
                text,
                Toast.LENGTH_SHORT
            ).show()
    }

    //automatically scroll to the latest item in the list
    LaunchedEffect(key1 = list.size){
        if(list.size == 0) return@LaunchedEffect
        listState.animateScrollToItem(list.size - 1)
    }

    LazyColumn(
        state = listState,
        modifier = modifier,
        reverseLayout = true    // place the latest item on the top of the LazyColumn
    ){
        itemsIndexed(list) { index, item ->
            ListItem(index = index, str = item, onItemClick = onListItemClick)
        }
    }
}