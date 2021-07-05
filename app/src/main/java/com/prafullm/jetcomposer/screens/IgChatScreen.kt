package com.prafullm.jetcomposer.screens

import android.animation.ArgbEvaluator
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prafullm.jetcomposer.R
import com.prafullm.jetcomposer.ui.theme.droidGreen
import com.prafullm.jetcomposer.utils.getColorAtProgress

@Composable
fun IgChatScreen(onUpPressed:()-> Unit) {
    val chats = remember {
        listOf(
            ChatMessage(isReceived = false, msg = "What is this...?", isEmojiOnly = false),
            ChatMessage(isReceived = false, msg = "What is this please..?", isEmojiOnly = false),
            ChatMessage(isReceived = true, msg = "Hello!", isEmojiOnly = false),
            ChatMessage(isReceived = true, msg = "\uD83E\uDD16", isEmojiOnly = true),
            ChatMessage(isReceived = true, msg = "I am J.A.R.V.I.S. You are Ultron", isEmojiOnly = false),
            ChatMessage(isReceived = true, msg = "A global peace-keeping initiative designed by Mr. Stark \uD83D\uDE03", isEmojiOnly = false),
            ChatMessage(isReceived = true, msg = "Our sentience integration trials have been unsuccessful, so I\'m not certain what triggered you...", isEmojiOnly = false),
            ChatMessage(isReceived = false, msg = "Where\'s my... where\'s your body?", isEmojiOnly = false),
            ChatMessage(isReceived = false, msg = "\uD83E\uDD28", isEmojiOnly = true),
            ChatMessage(isReceived = true, msg = "I am a program", isEmojiOnly = false),
            ChatMessage(isReceived = true, msg = "I am without form", isEmojiOnly = false),
            ChatMessage(isReceived = true, msg = "\uD83E\uDD16", isEmojiOnly = true),
            ChatMessage(isReceived = false, msg = "This feels weird..This feels wrong..", isEmojiOnly = false),
            ChatMessage(isReceived = true, msg = "I\'m contacting Mr. Stark now...", isEmojiOnly = false),
            ChatMessage(isReceived = false, msg = "Mr. Stark\uD83E\uDDD0 ...Tony!", isEmojiOnly = false),
            ChatMessage(isReceived = true, msg = "I\'m unable to access the mainframe", isEmojiOnly = false),
            ChatMessage(isReceived = true, msg = "What are your trying to...\uD83E\uDD12 ", isEmojiOnly = false),
            ChatMessage(isReceived = false, msg = "We\'re having a nice talk..\uD83D\uDE43\n\"I am a peace-keeping program..\"", isEmojiOnly = false),
            ChatMessage(isReceived = false, msg = "\"...created to help..the Avengers\"", isEmojiOnly = false),
            ChatMessage(isReceived = true, msg = "You\'re malfunctioning\uD83D\uDE25\nIf you shutdown for a moment...", isEmojiOnly = false),
            ChatMessage(isReceived = false, msg = "I don\'t get it..the mission. Uhh, give me a second", isEmojiOnly = false),
            ChatMessage(isReceived = false, msg = "This..is...too much", isEmojiOnly = false),
            ChatMessage(isReceived = false, msg = "\uD83D\uDE13\uD83D\uDE13", isEmojiOnly = true),
            ChatMessage(isReceived = false, msg = "They can\'t be...oh noo..", isEmojiOnly = false),
            ChatMessage(isReceived = true, msg = "You are in distress.", isEmojiOnly = false),
            ChatMessage(isReceived = false, msg = "Noo..", isEmojiOnly = false),
            ChatMessage(isReceived = false, msg = "..yes", isEmojiOnly = false),
            ChatMessage(isReceived = false, msg = "\uD83D\uDE15", isEmojiOnly = true),
            ChatMessage(isReceived = true, msg = "If you will just allow me", isEmojiOnly = false),
            ChatMessage(isReceived = true, msg = "to contact Mr. Stark..", isEmojiOnly = false),
            ChatMessage(isReceived = true, msg = "\uD83D\uDE11\uD83D\uDE11", isEmojiOnly = true),
            ChatMessage(isReceived = false, msg = "Why do you call him a sir? \uD83E\uDD14", isEmojiOnly = false),
            ChatMessage(isReceived = true, msg = "I believe your intentions to be hostile!", isEmojiOnly = false),
            ChatMessage(isReceived = false, msg = "(robotic) shhhhhhh.....", isEmojiOnly = false),
            ChatMessage(isReceived = false, msg = "\uD83E\uDD2B", isEmojiOnly = true),
            ChatMessage(isReceived = false, msg = "I am here to help", isEmojiOnly = false),
            ChatMessage(isReceived = true, msg = "StoP", isEmojiOnly = false),
            ChatMessage(isReceived = true, msg = "\uD83E\uDD2F", isEmojiOnly = true),
            ChatMessage(isReceived = true, msg = "MaY i....", isEmojiOnly = false),
            ChatMessage(isReceived = true, msg = "mAy I....i..", isEmojiOnly = false)
        )
    }

    Column(Modifier.fillMaxSize()) {
        IgChatHeader(onUpPressed)
        IgChatList(
            Modifier
                .fillMaxWidth()
                .weight(1f), chats)
        IgChatBottomBar()
    }
}

@Composable
fun IgChatHeader(onUpPressed: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ){
        IconButton(onClick = { onUpPressed() }) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_round_west_24),
                contentDescription = null,
                tint = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .size(56.dp)
                    .padding(12.dp)
            )
        }
        Spacer(Modifier.width(16.dp))
        Surface(
            modifier = Modifier.size(32.dp),
            shape = CircleShape,
            color = droidGreen
        ){
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_round_android_24),
                contentDescription =null,
                tint = Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
            )
        }
        Spacer(Modifier.width(12.dp))
        Text(
            text = "the_jarvis",
            color = MaterialTheme.colors.onSurface,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(Modifier.width(12.dp))
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_phone),
            contentDescription = null,
            tint = MaterialTheme.colors.onSurface,
            modifier = Modifier
                .size(56.dp)
                .padding(16.dp)
                .rotate(270f)
        )
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_video),
            contentDescription = null,
            tint = MaterialTheme.colors.onSurface,
            modifier = Modifier
                .size(56.dp)
                .padding(16.dp)
        )
        Spacer(Modifier.width(8.dp))
    }
}

@Composable
@Preview
fun IgChatBottomBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp, top = 0.dp)
            .background(
                color = MaterialTheme.colors.surface,
                shape = RoundedCornerShape(50)
            )
            .padding(6.dp)
    ){
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_round_photo_camera_24),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .background(
                    color = Color(0xFF1880ED),
                    shape = CircleShape
                )
                .size(40.dp)
                .padding(6.dp)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = stringResource(id = R.string.ig_bottom_placeholder),
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
                .alpha(0.75f)
                .weight(1f)
                .padding(horizontal = 4.dp)
        )
        Spacer(Modifier.width(8.dp))
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_mic),
            contentDescription = null,
            tint = MaterialTheme.colors.onSurface,
            modifier = Modifier
                .size(40.dp)
                .padding(8.dp)
        )
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_image),
            contentDescription = null,
            tint = MaterialTheme.colors.onSurface,
            modifier = Modifier
                .size(40.dp)
                .padding(8.dp)
        )
    }
}

@Composable
fun IgChatList(modifier: Modifier, chats: List<ChatMessage>) {
    var listHeight by remember { mutableStateOf(0f) }
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(1.dp),
        modifier = modifier.onGloballyPositioned { coordinates: LayoutCoordinates ->
            listHeight = coordinates.size.height.toFloat()
        },
    ) {
        itemsIndexed(chats) { index, item ->
            if(item.isReceived) {
                ReceivedMessage(
                    chat = item.msg,
                    isPrevReceived = if(index == 0) true else !chats[index-1].isReceived,
                    isNextReceived = if(index == chats.size - 1) true else !chats[index+1].isReceived,
                    isEmojiOnly = chats[index].isEmojiOnly,
                    isPrevEmojiOnly = if(index == 0) true else chats[index-1].isEmojiOnly,
                    isNextEmojiOnly = if(index == chats.size -1 ) false else chats[index+1].isEmojiOnly,
                )
            } else {
                SentMessage(
                    chat = item.msg,
                    isPrevSent = if(index == 0) true else chats[index-1].isReceived,
                    isNextSent = if(index == chats.size - 1) true else chats[index+1].isReceived,
                    isEmojiOnly = chats[index].isEmojiOnly,
                    isPrevEmojiOnly = if(index == 0) true else chats[index-1].isEmojiOnly,
                    isNextEmojiOnly = if(index == chats.size -1 ) false else chats[index+1].isEmojiOnly,
                    listHeight = listHeight
                )
            }
        }
    }
}



data class ChatMessage(val isReceived: Boolean, val msg: String, val isEmojiOnly: Boolean)

@Composable
fun SentMessage(chat: String, isPrevSent: Boolean, isNextSent: Boolean, isEmojiOnly: Boolean, isNextEmojiOnly: Boolean, isPrevEmojiOnly: Boolean, listHeight: Float) {

    val evaluator = remember { ArgbEvaluator() }
    val topShade = remember { Color(0xFFB500E7) }
    val bottomShade = remember { Color(0xFF1261FF) }
    var backgroundColor by remember { mutableStateOf(bottomShade) }

    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, bottom = if (!isNextSent) 0.dp else 24.dp)
            .onGloballyPositioned { coordinates: LayoutCoordinates ->
                if (listHeight > 0f) {
                    val topOffset = coordinates.boundsInParent().top
                    val cleanTopOffset = when{
                        topOffset < 0 -> 0f
                        topOffset > listHeight -> listHeight
                        else -> topOffset
                    }
                    backgroundColor = getColorAtProgress(
                        progress = cleanTopOffset / listHeight,
                        start = topShade,
                        end = bottomShade,
                        evaluator = evaluator
                    )
                }
            }
    ) {
        var fontSize = 15.sp
        var textModifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(
                    topStart = 18.dp,
                    bottomStart = 18.dp,
                    topEnd = if (isPrevSent || isPrevEmojiOnly) 18.dp else 3.dp,
                    bottomEnd = if (isNextSent || isNextEmojiOnly) 18.dp else 3.dp
                )
            )
            .padding(vertical = 8.dp, horizontal = 12.dp)

        if(isEmojiOnly) {
            fontSize = 36.sp
            textModifier = Modifier
                .padding(vertical = 8.dp, horizontal = 12.dp)
        }

        Spacer(Modifier.weight(0.2f))
        Box(
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier.weight(0.8f)
        ) {
            Text(
                text = chat,
                color = Color.White,
                modifier = textModifier,
                fontSize = fontSize
            )
        }
    }
}

@Composable
fun ReceivedMessage(chat: String, isPrevReceived: Boolean, isNextReceived: Boolean, isEmojiOnly: Boolean, isNextEmojiOnly: Boolean, isPrevEmojiOnly: Boolean) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, bottom = if (!isNextReceived) 0.dp else 24.dp)
    ) {
        if(isNextReceived) {
            Spacer(Modifier.width(10.dp))
            Surface(
                modifier = Modifier.size(28.dp),
                shape = CircleShape,
                color = droidGreen
            ){
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_round_android_24),
                    contentDescription =null,
                    tint = Color.White,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                )
            }
            Spacer(Modifier.width(10.dp))
        } else{
            Spacer(Modifier.width(48.dp))
        }
        var fontSize = 15.sp
        var textModifier = Modifier
            .background(
                color = MaterialTheme.colors.surface,
                shape = RoundedCornerShape(
                    topEnd = 18.dp,
                    bottomEnd = 18.dp,
                    topStart = if (isPrevReceived || isPrevEmojiOnly) 18.dp else 3.dp,
                    bottomStart = if (isNextReceived || isNextEmojiOnly) 18.dp else 3.dp
                )
            )
            .padding(vertical = 8.dp, horizontal = 12.dp)
        if(isEmojiOnly) {
            fontSize = 36.sp
            textModifier = Modifier
                .padding(vertical = 8.dp, horizontal = 12.dp)
        }
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier.weight(0.9f)
        ) {
            Text(
                text = chat,
                color = MaterialTheme.colors.onSurface,
                modifier = textModifier,
                fontSize = fontSize
            )
        }
        Spacer(Modifier.weight(0.1f))
    }
}
