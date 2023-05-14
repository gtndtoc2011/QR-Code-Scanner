package com.qrcode.scanner

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun UrlListView(modifier: Modifier, list: List<String>){
    LazyColumn(
        modifier = modifier
    ){
        itemsIndexed(list) { index, item ->
            UrlItem(index = index, str = item)
        }
    }
}